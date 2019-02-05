package aplicacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Cliente;
import modelo.Pedido;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.*;
import java.lang.*;
import java.awt.event.ActionEvent;

public class TelaListarPedidoCliente extends JFrame {

	private JPanel contentPane;
	private JLabel label;
	private JTextField textField;
	private JButton button;
	private String telefone, texto="";
	private TextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListarPedidoCliente frame = new TelaListarPedidoCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaListarPedidoCliente() {
		setTitle("Listar Pedidos por Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 377, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new JLabel("Telefone: ");
		label.setBounds(65, 11, 143, 19);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(128, 10, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		button = new JButton("Listar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telefone = textField.getText();
				ArrayList<Pedido> pedidos;
				Collection<Cliente> clientes;
				
				pedidos = Fachada.listarPedidos();
				clientes = Fachada.listarClientes().values();

				for(Cliente cli: clientes) {
					if(cli.getTelefone().equals(telefone)) {
						pedidos = cli.getPedidos();

						for(Pedido p: pedidos) {
							texto += "Nº Pedido: " + p.getId() + ", Produtos: " + p.getProdutos()+ "\n";
						}
					}
				}
				textArea.setText(texto);
			}
		});
		button.setBounds(248, 9, 89, 23);
		contentPane.add(button);
		
		textArea = new TextArea();
		textArea.setBounds(40, 51, 297, 114);
		contentPane.add(textArea);
	}

}
