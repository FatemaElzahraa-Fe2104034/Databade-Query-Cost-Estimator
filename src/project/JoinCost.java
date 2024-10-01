package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JTextField;

public class JoinCost extends JFrame {

	private JPanel cheapestTF;
	private static JTable table;
	private JScrollPane scrollPane;
	private JTextField textField;
	private JLabel lblNewLabel_1;

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
	public JoinCost() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 716, 477);
		cheapestTF = new JPanel();
		cheapestTF.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(cheapestTF);
		cheapestTF.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cost Estimation");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(229, 11, 256, 83);
		cheapestTF.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 150, 653, 260);
		cheapestTF.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		Dbase db = new Dbase();
		try {
			table.setModel(new DefaultTableModel(
				new Object[][] {
					{"Nested-loop", db.nestedLoop()},
					{"Sort-merge ", db.sortMerge()},
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
			
			textField = new JTextField();
			textField.setBounds(194, 105, 420, 20);
			cheapestTF.add(textField);
			textField.setColumns(10);
			textField.setText("select * form Instructor join Section on Instructor.ID = Section.Instructor_id");
			
			lblNewLabel_1 = new JLabel("Query");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblNewLabel_1.setBounds(129, 89, 55, 47);
			cheapestTF.add(lblNewLabel_1);
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.getColumnModel().getColumn(0).setPreferredWidth(195);
		

	}
	
	
//	public static void setTextF(String s) {
//		queryTF.setText(s);
//	}
	public static void AddRow(String[] row) {
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.addRow(row);
	}
}
