package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class metadataWindow extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					metadataWindow frame = new metadataWindow();
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
	public metadataWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 840, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("METADATA");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel.setBounds(341, 11, 150, 52);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 74, 806, 334);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				
			}
		));
		scrollPane.setViewportView(table);
		
		Connection conn;
		Statement stmt;
		String sql = "select * from metadata";
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@coestudb.qu.edu.qa:1521/STUD.qu.edu.qa",
						"fe2104034","fe2104034");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		    ResultSetMetaData rsmd = rs.getMetaData();
		    
		    int col = rsmd.getColumnCount();
		    String[] colName = new String[col];
		    for (int i=0 ; i<col; i++) {
		    	colName[i] = rsmd.getColumnName(i+1);
				    }
		    model.setColumnIdentifiers(colName);
		    String tableN;
		    String colN;
		    String colT;
		    String keyT;
		    String indexT;
		    String blevel;
		    String ndv;
		    String max;
		    String min;
		    
		    while (rs.next()) {
		        tableN = rs.getString(1);
		        colN = rs.getString(2);
		        colT = rs.getString(3);
		        keyT = rs.getString(4);
		        indexT = rs.getString(5);
		        blevel = String.valueOf(rs.getInt(6));  // Use getInt() for integers
		        ndv = String.valueOf(rs.getInt(7));    // Use getInt() for integers
		        max = String.valueOf(rs.getInt(8));
		        min = String.valueOf(rs.getInt(9));
		        System.out.println(colN+ blevel);

		        String[] row = { tableN, colN, colT, keyT, indexT, blevel, ndv, max, min };
		        model.addRow(row);
		    }

		    	
		       

					} catch (SQLException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
			
		
	}
}
