package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.bean.Cliente;
import model.bean.Filme;
import model.dao.ClienteDAO;
import model.dao.FilmeDAO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;

public class JFListarCliente extends JFrame {

	private JPanel contentPane;
	private JTable jtCliente;
	private JButton btnLimpar;
	private JButton btnCancelar;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFListarCliente frame = new JFListarCliente();
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
	public JFListarCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Listar Clientes");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 10, 115, 23);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 31, 541, 289);
		contentPane.add(scrollPane);
		
		jtCliente = new JTable();
		jtCliente.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nome", "CPF", "Email", "Telefone","Endereço"
			}
		));
		scrollPane.setViewportView(jtCliente);
		
		btnLimpar = new JButton("Atualizar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(jtCliente.getSelectedRow() != -1) {
					JFAtualizarCliente ac = new JFAtualizarCliente((int)jtCliente.getValueAt(jtCliente.getSelectedRow(), 0 ));
					ac.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null,"Selecione uma linha");
				}
				readJtable();
			}
		});
		btnLimpar.setBounds(113, 330, 93, 26);
		contentPane.add(btnLimpar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(216, 330, 93, 26);
		contentPane.add(btnCancelar);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.setBounds(10, 330, 93, 26);
		contentPane.add(btnNewButton);
		
		readJtable();
	}
	
	
	public void readJtable() {
		DefaultTableModel modelo = (DefaultTableModel) jtCliente.getModel();
		modelo.setNumRows(0);
		ClienteDAO cDAO = new ClienteDAO();
		for(Cliente c: cDAO.read()) {
			modelo.addRow(new Object[] {
					c.getClienteId(),
					c.getClienteNome(),
					c.getClienteCPF(),
					c.getClienteEmail(),
					c.getClienteTelefone(),
					c.getClienteEndereco()
			});
		}
		
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
