package aplicacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Produto;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.TextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class TelaListarProdutos extends JFrame {

	private JPanel contentPane;
	private JButton button;
	private TextArea textArea;
	private JTextField textField;
	private JLabel label;
	
	private String text="";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListarProdutos frame = new TelaListarProdutos();
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
	public TelaListarProdutos() {
		setTitle("Listar Produtos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 411, 264);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		button = new JButton("Listar Produtos");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeProduto = textField.getText();
				ArrayList<Produto> prods = new ArrayList<>();
				
				if(nomeProduto != "") {
					prods = Fachada.listarProdutos(nomeProduto);
					for(Produto p: prods) {
						text += "Nome: " + p.getNome() + ", preço: " + p.getPreco() + "\n";
					}
				}else {
					prods = Fachada.listarProdutos();
					for(Produto p: prods) {
						text += "Nome: " + p.getNome() + ", preço: " + p.getPreco() + "\n";
					}
				}
				textArea.setText(text);
			}
		});
		button.setBounds(219, 11, 128, 23);
		contentPane.add(button);
		
		textArea = new TextArea();
		textArea.setBounds(21, 55, 347, 147);
		contentPane.add(textArea);
		
		textField = new JTextField();
		textField.setBounds(70, 12, 107, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		label = new JLabel("Produto: ");
		label.setBounds(10, 15, 64, 14);
		contentPane.add(label);
	}
}
