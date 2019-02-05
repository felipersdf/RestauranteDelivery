package modelo;
import java.util.ArrayList;


public class Combo extends Produto {
	
	private ArrayList<Produto> componentes = new ArrayList<>();
	
	public Combo(String n, double p, int id, ArrayList<Produto> prods) {
		super(n,p,id);
		componentes = prods;
	}
	
	public ArrayList<Produto> getComponentes() {
		return componentes;
	}

	@Override
	public String toString() {
		return "[componentes=" + componentes + ", preco=" + getPreco() + "]";
	}

	public void setComponentes(ArrayList<Produto> componentes) {
		this.componentes = componentes;
	}
	
	
}
