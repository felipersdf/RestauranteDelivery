package aplicacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Pedido;
import modelo.Produto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class TelaRetirarProduto extends JFrame {

	private JPanel contentPane;
	private JLabel label;
	private JLabel label_1;
	private JTextField textField;
	private JTextField textField_1;
	private JButton button;

	private String telefone, nomeProduto;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaRetirarProduto frame = new TelaRetirarProduto();
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
	public TelaRetirarProduto() {
		setTitle("Retirar Produto de Pedido");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 272, 199);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new JLabel("Telefone: ");
		label.setBounds(22, 34, 112, 14);
		contentPane.add(label);
		
		label_1 = new JLabel("Produto: ");
		label_1.setBounds(22, 69, 82, 14);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(89, 31, 100, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(89, 66, 100, 20);
		contentPane.add(textField_1);
		
		button = new JButton("Retirar Produto");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telefone = textField.getText();
				nomeProduto = textField_1.getText();
				ArrayList<Produto> produtos;
				ArrayList<Pedido> pedidos;
				
				if(telefone.equals("") || nomeProduto.equals(""))
					JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
				
				produtos = Fachada.listarProdutos(nomeProduto);
				pedidos = Fachada.listarPedidos();
				
				
				for(Produto p: produtos) {
					if(p.getNome().equals(nomeProduto)) {
						try {
							Fachada.removerProdutoPedido(telefone, p.getId());
							JOptionPane.showMessageDialog(null, "Produto Retirado!");
							System.out.println();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				for(Pedido pe: pedidos) {
					System.out.println(pe);
				}
				textField.setText("");
				textField_1.setText("");
				
			}
		});
		button.setBounds(66, 103, 123, 34);
		contentPane.add(button);
	}

}
