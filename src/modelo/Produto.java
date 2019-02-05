package modelo;

import java.util.ArrayList;

public class Produto implements Comparable<Produto>{
	private int id;
	private String nome;
	private double preco;
	private ArrayList<Pedido> pedidos = new ArrayList<>();
	
	
	public Produto(String n, double p, int id){
		this.id = id;
		this.nome = n;
		this.preco = p;
	}
	
	public void adicionar(Pedido p) {
		pedidos.add(p);
	}
	public void remover(Pedido p) {
		pedidos.remove(p);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public String getDescricao() {
		return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + ", pedidos=" + pedidos + "]";
	}
	public String toString() {
		return nome;
	}
	public int compareTo(Produto outro) {
		return this.getNome().compareToIgnoreCase(outro.getNome());
	}
	
}
	

