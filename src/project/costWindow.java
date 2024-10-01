package project;

import java.awt.EventQueue; 

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JTextField;

public class costWindow extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	private JScrollPane scrollPane;
	private static JTextField queryTF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					costWindow frame = new costWindow();
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
	public costWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 716, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cost Estimation");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(229, 11, 256, 83);
		contentPane.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 150, 653, 279);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Query ExecutionPlan", "Cost"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		queryTF = new JTextField();
		queryTF.setBounds(189, 106, 322, 20);
		contentPane.add(queryTF);
		queryTF.setColumns(10);
		table.getColumnModel().getColumn(0).setPreferredWidth(195);

	}
	
	
	public static void setTextF(String s) {
		queryTF.setText(s);
	}
	public static void AddRow(String[] row) {
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.addRow(row);
	}

}
