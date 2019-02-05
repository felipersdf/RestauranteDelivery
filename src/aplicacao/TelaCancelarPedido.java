package aplicacao;

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

public class TelaCancelarPedido extends JFrame {

	private JPanel contentPane;
	private JButton button;
	private JTextField textField;
	private JLabel label;
	private String telefone;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCancelarPedido frame = new TelaCancelarPedido();
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
	public TelaCancelarPedido() {
		setTitle("Cancelar Pedido");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 259, 161);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		button = new JButton("Cancelar Pedido");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telefone = textField.getText();
				if(telefone.equals("")) {
					JOptionPane.showMessageDialog(null, "Campo Vazio!");
				}
				else {
				try {
					Fachada.cancelarPedido(telefone);
					JOptionPane.showMessageDialog(null,"Pedido Cancelado!");
					textField.setText("");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
				}
		});
		button.setBounds(54, 53, 128, 42);
		contentPane.add(button);
		
		textField = new JTextField();
		textField.setBounds(96, 22, 94, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		label = new JLabel("Telefone: ");
		label.setBounds(23, 25, 94, 17);
		contentPane.add(label);
	}

}
