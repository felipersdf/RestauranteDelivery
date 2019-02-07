package fachada;

import java.util.ArrayList;
import java.time.*;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.TreeMap;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

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

	public static void excluirPedido(int id) {

		ArrayList<Pedido> peds = restaurante.getPedidos();
		
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
	
//	public static void enviarPedido(String telefone, String email) {
//		
//	}


}
}
	
	
