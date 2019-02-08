package fachada;

import java.util.ArrayList;
import java.time.*;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Properties;
import java.util.TreeMap;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeMessage.RecipientType;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import repositorio.Restaurante;
import modelo.Produto;
import modelo.Cliente;
import modelo.Combo;
import modelo.Pedido;

public class Fachada {
	public static Restaurante restaurante = new Restaurante();
	public static int idPedido = 0, num_produto = 1;
	public static String nome= "";
	
	
	public static int geraID(String nomeClasse) {
		if (nomeClasse.equals("Pedido"))
			return ++idPedido;
		return 0;
	}
	
	public static ArrayList<Produto>listarProdutos(){
			return restaurante.getProdutos();
	}
	
	public static ArrayList<Produto>listarProdutos(String nome){
		ArrayList<Produto> prods = new ArrayList<>();
		
		for (Produto p: restaurante.getProdutos()) {
			if(p.getNome().contains(nome))
				prods.add(p);
		}
		
		prods.sort(new Comparator<Produto>(){
			public int compare(Produto produto, Produto outroProduto) {
					return produto.getNome().compareTo(outroProduto.getNome());
			}
		});
		
	   return prods;
	}	
			
	public static TreeMap<String, Cliente> listarClientes(){
		return restaurante.getClientes();
	}
	
	public static ArrayList<Pedido>listarPedidos(){
		return restaurante.getPedidos();
	}
	
	public static ArrayList<Pedido>listarPedidos(String telefone){
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
		
		for(Pedido p: restaurante.getPedidos()) {
			if (p.getCliente().getTelefone().equals(telefone))
				pedidos.add(p);
		}
		return pedidos;
	}
	
	public static Produto cadastrarProduto(String nome, double preco) throws Exception {
		Produto aux = restaurante.localizarProduto(nome);
		if(aux != null) {
			throw new Exception("Produto já cadastrado.");
		}
		if(nome.isEmpty()) {
			throw new Exception("Nome de produto inválido.");
		}
		if(preco <= 0) {
			throw new Exception("Preço inválido.");
		}
		Produto p = new Produto(nome, preco, num_produto);
		num_produto++;
		restaurante.adicionar(p);
		return p;
	}
	
	public static Cliente cadastrarCliente(String nome, String tel, String em, String end) throws Exception{
		Cliente c = restaurante.localizarCliente(nome);
		
		if(c != null) {
			throw new Exception("Cliente já cadastrado.");
		}
		Cliente cli = new Cliente (nome, tel, em, end);
		restaurante.adicionar(cli);
		return cli;
	}
	public static Pedido abrirPedido(String telefone) throws Exception {
		Cliente c = restaurante.localizarCliente(telefone);
		Pedido p, pverifica;
		
		if(c == null) {
			throw new Exception("Cliente inexistente.");
		}
		
		pverifica = c.getPedidoAberto();
		
		if(pverifica != null) {
			throw new Exception("O cliente já possui um pedido aberto!");
		}
			p = new Pedido(geraID("Pedido"), c);
			c.adicionar(p);
			restaurante.adicionar(p);
			return p;	
		}
	
	public static void adicionarProdutoPedido(String telefone, int id_prod) throws Exception{
		Cliente cli = restaurante.localizarCliente(telefone);
	    Produto prod = restaurante.localizarProdutoById(id_prod); 
	    
		if(cli == null) 
			throw new Exception("Cliente inexistente.");
		
		if(prod == null) 
			throw new Exception("Produto inexistente.");
	 
	    Pedido ultimoPedido = cli.getPedidoAberto();
	   
    	if(ultimoPedido.isFechado())
  		  throw new Exception("O pedido esta fechado");
		
		prod.adicionar(ultimoPedido);
	    ultimoPedido.adicionar(prod);
	}
	public static void removerProdutoPedido(String telefone, int id_prod) throws Exception{
		Cliente cli = restaurante.localizarCliente(telefone);
	    Produto prod = restaurante.localizarProdutoById(id_prod);
	    
		if(cli == null) 
			throw new Exception("Cliente inexistente.");
		
		if(prod == null) 
			throw new Exception("Produto inexistente.");
		
		ArrayList<Pedido> pedids = cli.getPedidos();
		for(Pedido ped: pedids) {
			if(!ped.isFechado()) {
				ped.remover(prod);
				prod.remover(ped);
			}
		}
	}

	public static ArrayList<Pedido> consultarPedido(String telefone) throws Exception {
		Cliente cli = restaurante.localizarCliente(telefone);
		
		if (cli == null)
			throw new Exception("Cliente inexistente");
		
		ArrayList<Pedido> pedido = cli.getPedidos();
		
		if (pedido == null)
			return null;
		
		return pedido; //Último pedido feito
	}
	
	public static void cancelarPedido(String telefone) throws Exception {
		Cliente cli = restaurante.localizarCliente(telefone);
		Pedido pedido = cli.getPedidoAberto();
		
		if(pedido != null) {
			restaurante.remover(pedido);
			cli.remover(pedido);
			
		}else {
			throw new Exception("Pedido ja esta fechado");
		}

		}
	
	public static void fecharPedido(String telefone) throws Exception {
		Cliente cli = restaurante.localizarCliente(telefone);

		if (cli == null)
			throw new Exception("Cliente inexistente");
		
		Pedido pedido = cli.getUltimoPedido();
		if (pedido.isFechado()) {
			throw new Exception("A conta ja foi fechada");
		}else {
			
		java.util.Date data = Calendar.getInstance().getTime();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formato.format(data);
		
		pedido.setData(dataFormatada);

		pedido.setFechado(true);
	}
}

	public static void fecharPedido(String telefone, String entregador) throws Exception {
		Cliente cli = restaurante.localizarCliente(telefone);

		if (cli == null)
			throw new Exception("Cliente inexistente");
		
		Pedido pedido = cli.getUltimoPedido();
		if (pedido.isFechado()) {
			throw new Exception("A conta ja foi fechada");
		}else {
			
		Date data = Calendar.getInstance().getTime();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formato.format(data);
		
		pedido.setData(dataFormatada);
		pedido.setEntregador(entregador);
		
		pedido.setFechado(true);
	}
}
	public static double calcularArrecadacao(int data) {
		double total = 0;
		ArrayList<Pedido> pedidos = restaurante.getPedidos();
        DateTimeFormatter fr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
		for(Pedido ped: pedidos) {
			if(LocalDate.parse(ped.getData(),fr).getDayOfMonth() == data) {
			ArrayList<Produto> produtos_dia = ped.getProdutos();
			for(Produto prod: produtos_dia) {
				total += prod.getPreco();
				}
			}
		}
		return total;
	}
	public static Combo criarCombo(String nome, ArrayList<Integer> lista) {
		Combo c = null;
		double precos = 0;
		ArrayList<Produto> produtos = restaurante.getProdutos();
		ArrayList<Produto> prodselec = new ArrayList<>();
		
		for(Integer i: lista) {
			prodselec.add(produtos.get(i-1));
		}
		for(Produto p: prodselec) {
			precos += p.getPreco();

		}
		precos -= (precos * 10/100);;
		c = new Combo(nome, precos,  num_produto, prodselec);
		num_produto++;
		return c;
	}

	public static void excluirPedido(int id) throws Exception {

		ArrayList<Pedido> peds = restaurante.getPedidos();
		Pedido pe = restaurante.localizarPedido(id);

		if(pe == null)
			throw new Exception("Pedido não encontrado!");
		else {
		for(Pedido p: peds) {
			if(p.getId() == id) {
				for(Produto prod: p.getProdutos()) {
					prod.remover(p);
				}
			Cliente cli = p.getCliente();
			restaurante.remover(p);
			cli.remover(p);
		}
}
		}
	}	
	public static void enviarPedido(String telefone, String email) throws MessagingException {
		Document document = new Document();
		Cliente cli = restaurante.localizarCliente(telefone);
		Pedido ultimoPedido = cli.getUltimoPedido(); 
		
		final String emailEnvio = "canalhabot@gmail.com";
		final String senha = "canalhabot01@";
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailEnvio, senha);
			}
		});

		
		try {
			//Instanciando o documento PDF
			PdfWriter.getInstance(document, new FileOutputStream("pdf//teste.pdf"));
			document.setPageSize(PageSize.A6);
			//Adicionando um titulo ao arquivo
			document.addSubject("Testando criação de PDF em Java com api Itext");
			//setando o criador do arquivo
			document.addCreator("iText");
			//setando o autor do arquivo criado
			document.addAuthor("Restaurane Ifoof");
			//Abrindo o documento PDF criado
			document.open();
			
			// adicionando um parágrafo ao documento
			document.add(new Paragraph(ultimoPedido.toString()));
			document.close();
			//Se arquivo for criado com sucesso, é exibida uma mensagem de confirmação
			System.out.println("PDF criado com sucesso!");
			//criando a mensagem
			MimeMessage msg = new MimeMessage(session);

			//inserir os emails 
			Address from = new InternetAddress(emailEnvio);
			Address to = new InternetAddress(email);

			//configurando o remetente e o destinatario
			msg.setFrom(from);
			msg.addRecipient(RecipientType.TO, to);

			//configurando a data de envio,  o assunto e o texto da mensagem
			msg.setSentDate(new Date());
			msg.setSubject("Enviando Email com mensagem e anexo");		
			msg.setSubject("Email com último pedido realizado: " );
			msg.setText("exemplo de email");
			msg.setHeader("XPriority", "1");
			
			// conteudo html que sera atribuido a mensagem
			String htmlMessage = "<html> Email com anexo </html>";
			//criando a Multipart
			Multipart multipart = new MimeMultipart();
			//criando a primeira parte da mensagem
			MimeBodyPart attachment0 = new MimeBodyPart();
			//configurando o htmlMessage com o mime type
			attachment0.setContent(htmlMessage,"text/html; charset=UTF-8");
			//adicionando na multipart
			multipart.addBodyPart(attachment0);
			//arquivo que será anexado
			String pathname = "pdf/teste.pdf";
			
			File file = new File(pathname);
			//criando a segunda parte da mensagem
			MimeBodyPart attachment1 = new MimeBodyPart();  
			attachment1.setDataHandler(new DataHandler(new FileDataSource(file)));
			
			attachment1.setFileName(file.getName());
			
			multipart.addBodyPart(attachment1);

			msg.setContent(multipart);
			
			Transport.send(msg);
			
			System.out.println("Email enviado com sucesso!");
		} catch (MessagingException mex) {
			System.out.println("problema no envio" + mex);
		}
		catch(DocumentException de) {         
			System.out.println(de.getMessage());
		}
		catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		document.close();
		


}
}
	
	
