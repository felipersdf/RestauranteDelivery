package aplicacao;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Cliente;

import java.util.Collection;
import java.util.TreeMap;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import java.awt.TextArea;

public class TelaListarClientes extends JFrame {

	private JPanel contentPane;
	private JButton button;
	private TextArea textArea;

//	Falta listar cliente com TreeMap
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListarClientes frame = new TelaListarClientes();
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
	public TelaListarClientes() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		button = new JButton("Listar Clientes");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = ""; 
				Collection<Cliente> clientes;
				
				clientes = Fachada.listarClientes().values();
				for(Cliente cli: clientes) {
					texto += "Nome: " + cli.getNome() + ", Pedidos: " + cli.getPedidos().size() + "\n";
				}
			textArea.setText(texto);
			}
		});
		button.setBounds(155, 11, 124, 23);
		contentPane.add(button);
		
		textArea = new TextArea();
		textArea.setBounds(21, 52, 380, 160);
		contentPane.add(textArea);
	}
}
