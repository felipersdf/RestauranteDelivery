package aplicacao;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programação Orientada a Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/
 
 
import java.util.ArrayList;
 
import fachada.Fachada;
import modelo.Pedido;
import modelo.Produto;
import modelo.Cliente;
import modelo.Combo;
 
public class Aplicacao_console {
    public Aplicacao_console() {
        cadastrar();
        listar();
    }
 
    public void cadastrar(){
        try {   
            Produto p;
            ArrayList<Pedido> pt;
            Pedido pe;
            Combo com;
            Cliente cli;
            double tot;
            ArrayList<Integer> aa = new ArrayList();
            aa.add(1);
            aa.add(2);
            aa.add(3);
            
            p = Fachada.cadastrarProduto("arroz", 3.0);
            p = Fachada.cadastrarProduto("feijao", 5.0);
            p = Fachada.cadastrarProduto("leite", 2.0);
            p = Fachada.cadastrarProduto("carne", 30.0);
            p = Fachada.cadastrarProduto("oleo", 10.0);
            
            cli = Fachada.cadastrarCliente("Felipe", "0102", "felipe@gmail.com" , "Rua X");

            cli = Fachada.cadastrarCliente("Tatiane", "0103", "tati@gmail.com" , "Rua Y");
//            System.out.println("cliente cadastrado:"+cli.getNome());

            //Pedido 01
            pe = Fachada.abrirPedido("0102");
            System.out.println("pedido " + pe.getId() +" aberto");   
            Fachada.adicionarProdutoPedido("0102", 1);
            Fachada.adicionarProdutoPedido("0102", 2);
            Fachada.adicionarProdutoPedido("0102", 3);
            Fachada.fecharPedido("0102", "Fulano");
            pe = Fachada.abrirPedido("0102");
            pt = Fachada.consultarPedido("0102");
            System.out.println(pt);
            
            //Pedido 02
            pe = Fachada.abrirPedido("0103");
            System.out.println("pedido " + pe.getId() +" aberto");   
            Fachada.adicionarProdutoPedido("0103", 1);
            Fachada.adicionarProdutoPedido("0103", 4);
            Fachada.adicionarProdutoPedido("0103", 5);
            Fachada.fecharPedido("0103", "Zezo");
            
//            tot = Fachada.calcularArrecadacao("31");
//            System.out.println("O total arrecadado do dia foi: " + tot);

            com = Fachada.criarCombo(aa);
            System.out.println("Combo criado " + com);
            System.out.println(com.getId());
            
     
            
         }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
 
 
 
     
 
    public void listar(){
        String texto;
        ArrayList<Produto> lista1 = Fachada.listarProdutos();
        texto = "Listagem de produtos: \n";
        if (lista1.isEmpty())
            texto += "não tem produto cadastrado\n";
        else   
            for(Produto p: lista1) 
                texto +=  p + "\n"; 
        System.out.println(texto);
 
 
        ArrayList<Pedido> lista2 = Fachada.listarPedidos();
        texto = "Listagem de pedidos: \n";
        if (lista2.isEmpty())
            texto += "não tem pedido cadastrado\n";
        else
            for(Pedido p: lista2) 
                texto +=  p + "\n"; 
        System.out.println(texto);
    }
 
 
 
 
    //  ***********************************************
    public static void main (String[] args)   
    //  ***********************************************
    {
        new Aplicacao_console();
    }
 
}