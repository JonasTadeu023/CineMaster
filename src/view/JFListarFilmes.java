package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.bean.Filme;
import model.dao.FilmeDAO;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;

public class JFListarFilmes extends JFrame {
	
	private JPanel contentPane;
	private JTable jtFilme;	
	private final Action action = new SwingAction();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFListarFilmes frame = new JFListarFilmes();
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
	public JFListarFilmes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 484, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel asd = new JLabel("Listar Filme");
		asd.setFont(new Font("Tahoma", Font.BOLD, 14));
		asd.setBounds(10, 10, 106, 21);
		contentPane.add(asd);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 41, 406, 149);
		contentPane.add(scrollPane);
		
		jtFilme = new JTable();
		jtFilme.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id","t\u00EDtulo", "categoria",  "tempo"
			}
		));
		scrollPane.setViewportView(jtFilme);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(20, 224, 106, 29);
		contentPane.add(btnCadastrar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(jtFilme.getSelectedRow() != -1) {
					JFAtualizarFilme af = new JFAtualizarFilme((int)jtFilme.getValueAt(jtFilme.getSelectedRow(), 0 ));
					af.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null,"Selecione uma linha");
				}
				readJtable();
			}
		});
		btnAlterar.setBounds(136, 224, 106, 29);
		contentPane.add(btnAlterar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(252, 224, 106, 29);
		contentPane.add(btnCancelar);
		
		readJtable();
	}
	
	public void readJtable() {
		DefaultTableModel modelo =(DefaultTableModel) jtFilme.getModel();
		modelo.setNumRows(0);
		FilmeDAO fdao = new FilmeDAO();
		for(Filme f: fdao.read()) {
			modelo.addRow(new Object[] {
					f.getIdFilme(),
					f.getTitulo(),
					f.getCategoria(),
					f.getTempo()
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

