package repositorio;

import java.util.ArrayList;
import java.util.TreeMap;

import modelo.Cliente;
import modelo.Pedido;
import modelo.Produto;

public class Restaurante {
	private ArrayList<Produto> produtos = new ArrayList<>();
	private ArrayList<Pedido> pedidos = new ArrayList<>();
	private TreeMap<String, Cliente> clientes = new TreeMap<>();
	
	public void adicionar(Pedido p) {
		pedidos.add(p);
	}
	public void remover(Pedido p) {
		pedidos.remove(p);
	}
	public void remover(Produto p) {
		produtos.remove(p);
	}
	public void adicionar(Produto p) {
		produtos.add(p);
	}
	public void adicionar(Cliente c) {
		clientes.put(c.getTelefone(), c);
	}
	public void remover(Cliente c) {
		clientes.remove(c.getTelefone());
	}
	//localizar clientes, produtos, pedidos
	public Cliente localizarCliente(String nome){
		 return clientes.get(nome);
    }
	
	public Pedido localizarPedido(int id){
	        for(Pedido pe : pedidos){
	            if(pe.getId() == id)
	                return pe;
	        }
	        return null;
	    }
	
	public Produto localizarProduto(String nome){
	        for(Produto p: produtos){
	            if(p.getNome().equals(nome))
	                return p;
	        }
	        return null;
	    }
	public Produto localizarProdutoById(int id) {
		for(Produto p: produtos) {
			if(p.getId() == id)
				return p;
		}
		return null;
	}
	
	//gets and setters
	public ArrayList<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}
	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public TreeMap<String, Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(TreeMap<String, Cliente> clientes) {
		this.clientes = clientes;
	}
	@Override
	public String toString() {
		return "Restaurante [produtos=" + produtos + ", pedidos=" + pedidos + ", clientes=" + clientes + "]";
	}
	
	
	
} 
