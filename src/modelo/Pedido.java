package modelo;


import java.util.ArrayList;

public class Pedido {
	private int id;
	private String data;
	private double total;
	private String entregador;
	private boolean fechado;
	private Cliente cliente;
	private ArrayList<Produto> produtos = new ArrayList<>();
	
	public Pedido (int cod, Cliente cli) {
		this.id = cod;
		this.cliente = cli;
	}
	
	public void adicionar(Produto p) {
		produtos.add(p);
	}
	public void remover(Produto p) {
		produtos.remove(p);
	}
	
	 public Produto localizarProduto(String nome){
	        for(Produto p : produtos){
	            if(p.getNome().equals(nome))
	                return p;
	        }
	        return null;
	    }
	 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public double getTotal() {
		total = 0;
		for (Produto p: produtos) {
			total += p.getPreco();
		}
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getEntregador() {
		return entregador;
	}

	public void setEntregador(String entregador) {
		this.entregador = entregador;
	}

	public boolean isFechado() {
		return fechado;
	}

	public void setFechado(boolean fechado) {
		this.fechado = fechado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ArrayList<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}
	
	
	@Override
	public String toString() {
		return "Pedido [id=" + id + ", data=" + data + ", total=" + this.getTotal() + ", entregador=" + entregador + ", fechado="
				+ fechado + ","+" produtos= "+ this.getProdutos();
	}
	
}
