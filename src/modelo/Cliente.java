package modelo;


import java.util.ArrayList;

public class Cliente {
	private String nome,telefone,email,endereco;
	private ArrayList<Pedido> pedidos = new ArrayList<>();
	
	public Cliente(String n, String tel, String em, String end) {
		this.nome = n;
		this.telefone = tel;
		this.email = em;
		this.endereco = end;
	}
	
	public void adicionar(Pedido p) {
		pedidos.add(p);
		p.setCliente(this);
	}
	public void remover(Pedido p) {
		pedidos.remove(p);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}
	public Pedido getPedidoAberto() {
		for (Pedido p: pedidos) {
			if(!p.isFechado())
				return p;
		}
		return null;
	}
	public Pedido getUltimoPedido() {
		if (this.getPedidos().size() <= 0)
			return null;
		return this.getPedidos().get(this.getPedidos().size() - 1);
	}
	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", pedidos=" + pedidos + "]";
	}

	
}
