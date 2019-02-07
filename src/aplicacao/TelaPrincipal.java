package aplicacao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.ArrayList;

import modelo.Pedido; 
import modelo.Produto;
import modelo.Combo;
import modelo.Cliente;
import fachada.Fachada;

public class TelaPrincipal {

	
	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	private JMenu menu_1;
	private JMenu menu_2;
	private JMenuItem menuItem_1;
	private JMenuItem menuItem_2;
	private JMenuItem menuItem_3;
	private JMenuItem menuItem_4;
	private JMenuItem menuItem_5;
	private JMenuItem menuItem_6;
	private JMenuItem menuItem_7;
	private JMenuItem menuItem_8;
	private JMenuItem menuItem_9;
	private JMenuItem menuItem_10;
	private JMenuItem menuItem_11;
	private JMenuItem menuItem_12;
	private JMenuItem menuItem_13;
	private JMenuItem menuItem_14;
	private JMenuItem menuItem_15;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Arrecada\u00E7\u00E3o ");
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try{
					//  Pré-cadastro
					Produto p;
					Cliente c;
					Pedido pe;
					Combo com;
					ArrayList<Integer> listacombo = new ArrayList();
					ArrayList<Integer> listacombo2 = new ArrayList();
					listacombo.add(1);
					listacombo.add(2);
					
					listacombo2.add(5);
					listacombo2.add(1);					
					listacombo2.add(3);
					
					p = Fachada.cadastrarProduto("temaki", 12.0);
					p = Fachada.cadastrarProduto("sushi", 26.0);
					p = Fachada.cadastrarProduto("sashimi", 22.0);
					p = Fachada.cadastrarProduto("coca-cola", 12.0);
					p = Fachada.cadastrarProduto("suco", 10.0);
					p = Fachada.cadastrarProduto("pizza", 21.0);
					p = Fachada.cadastrarProduto("guarana", 8.0);
					p = Fachada.cadastrarProduto("maria joana", 10.0);
					com = Fachada.criarCombo("Combo1",listacombo);
					com = Fachada.criarCombo("Combo2",listacombo2);
						
					c = Fachada.cadastrarCliente("Felipe", "01", "felipe@gmail.com" , "Rua X");
					c = Fachada.cadastrarCliente("maria", "98820-0222", "maria@gmail.com","Rua Y"); 
					
					
					pe = Fachada.abrirPedido("01");
					Fachada.adicionarProdutoPedido("01", 1);
					Fachada.adicionarProdutoPedido("01", 2);
					Fachada.adicionarProdutoPedido("01", 3);
					Fachada.fecharPedido("01");
					
					pe = Fachada.abrirPedido("98820-0222");
					Fachada.adicionarProdutoPedido("98820-0222", 3);
					Fachada.adicionarProdutoPedido("98820-0222", 6);
					Fachada.fecharPedido("98820-0222");
					
					
					
					
					
				}catch(Exception e1){
					System.out.println(e1.getMessage());
				}
			}
		});
		frame.setBounds(100, 100, 450, 280);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		menu = new JMenu("Cliente");
		menuBar.add(menu);
		
		menuItem = new JMenuItem("Cadastrar Cliente");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroCliente t = new TelaCadastroCliente();
				t.setVisible(true);
			}
		});
		menu.add(menuItem);
		
		menuItem_5 = new JMenuItem("Listar Clientes");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListarClientes t = new TelaListarClientes();
				t.setVisible(true);
			}
		});
		menu.add(menuItem_5);
		
		menu_1 = new JMenu("Pedido");
		menuBar.add(menu_1);
		
		menuItem_1 = new JMenuItem("Abrir Pedido");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAbrirPedido p = new TelaAbrirPedido();
				p.setVisible(true);
			}
		});
		menu_1.add(menuItem_1);
		
		menuItem_2 = new JMenuItem("Consultar Pedido");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaConsultarPedido p = new TelaConsultarPedido();
				p.setVisible(true);
			}
		});
		menu_1.add(menuItem_2);
		
		menuItem_4 = new JMenuItem("Cancelar Pedido");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCancelarPedido p = new TelaCancelarPedido();
				p.setVisible(true);
			}
		});
		menu_1.add(menuItem_4);
		
		menuItem_3 = new JMenuItem("Fechar Pedido");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaFecharPedido f = new TelaFecharPedido();
				f.setVisible(true);
			}
		});
		menu_1.add(menuItem_3);
		
		menuItem_6 = new JMenuItem("Listar Pedidos");
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListarPedidos t = new TelaListarPedidos();
				t.setVisible(true);
			}
		});
		menu_1.add(menuItem_6);
		
		menuItem_7 = new JMenuItem("Listar Pedidos por Cliente");
		menuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListarPedidoCliente j = new TelaListarPedidoCliente();
				j.setVisible(true);
			}
		});
		menu_1.add(menuItem_7);
		
		menuItem_14 = new JMenuItem("Excluir Pedido");
		menuItem_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = JOptionPane.showInputDialog(null, "Digite o id do pedido: ");
				Fachada.excluirPedido(Integer.parseInt(id));
				JOptionPane.showMessageDialog(null,"Pedido "+Integer.parseInt(id)+" excluído!");
			}
		});
		menu_1.add(menuItem_14);
		
		menuItem_11 = new JMenuItem("Enviar Pedido");
		menu_1.add(menuItem_11);
		
		menuItem_12 = new JMenuItem("Arrecada\u00E7\u00E3o");
		menuItem_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dia = JOptionPane.showInputDialog(null, "Informe o dia: ");
				double arrec = Fachada.calcularArrecadacao(LocalDate.parse(dia).getDayOfMonth());
				
				JOptionPane.showMessageDialog(null, "A arrecadação do dia foi de: " + arrec + "!");
			}
		});
		menu_1.add(menuItem_12);
		
		menu_2 = new JMenu("Produto");
		menuBar.add(menu_2);
		
		menuItem_8 = new JMenuItem("Cadastrar Produto");
		menuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroProduto p = new TelaCadastroProduto();
				p.setVisible(true);
			}
		});
		
		menuItem_15 = new JMenuItem("Listar Produtos");
		menuItem_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListarProdutos o = new TelaListarProdutos();
				o.setVisible(true);
			}
		});
		menu_2.add(menuItem_15);
		menu_2.add(menuItem_8);
		
		menuItem_9 = new JMenuItem("Adicionar Produto em Pedido");
		menuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAddProdutoPedido t = new TelaAddProdutoPedido();
				t.setVisible(true);
			}
		});
		menu_2.add(menuItem_9);
		
		menuItem_10 = new JMenuItem("Retirar Produto de um Pedido");
		menuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaRetirarProduto t = new TelaRetirarProduto();
				t.setVisible(true);
			}
		});
		menu_2.add(menuItem_10);
		
		menuItem_13 = new JMenuItem("Criar Combo");
		menu_2.add(menuItem_13);
	}

}
