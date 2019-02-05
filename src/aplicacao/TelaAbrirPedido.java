package aplicacao;

import modelo.Pedido;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaAbrirPedido extends JFrame {

	private JPanel contentPane;
	private JButton button;
	private JTextField textField;
	private JLabel label;
	private JLabel label_1;
	
	private String telefone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAbrirPedido frame = new TelaAbrirPedido();
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
	public TelaAbrirPedido() {
		setTitle("Abrir Pedido");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 187);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		button = new JButton("Abrir Pedido");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telefone = textField.getText();
				
				if(telefone.equals("")) 
					JOptionPane.showMessageDialog(null, "Campo vazio!");
				else {
					try {
						Pedido pedido = Fachada.abrirPedido(telefone);
						label_1.setText("Pedido " + pedido.getId() + " do cliente " + pedido.getCliente().getNome() + " aberto! ");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		button.setBounds(62, 66, 151, 23);
		contentPane.add(button);
		
		textField = new JTextField();
		textField.setBounds(79, 32, 122, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		label = new JLabel("Informe o n\u00FAmero de telefone : ");
		label.setBounds(62, 11, 238, 23);
		contentPane.add(label);
		
		label_1 = new JLabel("");
		label_1.setBounds(10, 110, 274, 27);
		contentPane.add(label_1);
	}

}
