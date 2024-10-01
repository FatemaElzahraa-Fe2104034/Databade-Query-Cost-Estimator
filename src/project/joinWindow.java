package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Scrollbar;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;

public class joinWindow extends JFrame {

	private JPanel contentPane;
	private JComboBox conditionCB;
	private JTextField table1TF;
	private JTextField condition1TF;
	private JTextField field1TF;
	private JTextField field2TF;
	private JTextField condition2TF;
	private JTextField table2TF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					joinWindow frame = new joinWindow();
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
	public joinWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 614, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton calculateCost = new JButton("Calculate Cost");
		calculateCost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JoinCost joinw = new JoinCost();
				joinw.setVisible(true);
				joinw.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
	            currentFrame.dispose();
			}
			
		}); 
		
		calculateCost.setFont(new Font("Tahoma", Font.PLAIN, 15));
		calculateCost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		calculateCost.setBounds(217, 418, 163, 36);
		contentPane.add(calculateCost);
		calculateCost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JoinCost jw = new JoinCost();
				jw.setVisible(true);
				jw.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
	            currentFrame.dispose();
			}
			
		});
		
		JLabel lblNewLabel = new JLabel("Join Operation");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(217, 11, 153, 23);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 405, 600, 2);
		contentPane.add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("Table");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(86, 126, 51, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Condition");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(259, 126, 83, 28);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Field");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(457, 126, 45, 28);
		contentPane.add(lblNewLabel_3);
		
		table1TF = new JTextField();
		table1TF.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table1TF.setText("Instructor");
		table1TF.setBounds(54, 166, 121, 23);
		contentPane.add(table1TF);
		table1TF.setColumns(10);
		
		condition1TF = new JTextField();
		condition1TF.setText("=");
		condition1TF.setFont(new Font("Tahoma", Font.PLAIN, 16));
		condition1TF.setColumns(10);
		condition1TF.setBounds(238, 164, 121, 25);
		contentPane.add(condition1TF);
		
		field1TF = new JTextField();
		field1TF.setText("ID");
		field1TF.setFont(new Font("Tahoma", Font.PLAIN, 16));
		field1TF.setColumns(10);
		field1TF.setBounds(416, 165, 121, 24);
		contentPane.add(field1TF);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 225, 600, 2);
		contentPane.add(separator_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Table");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(86, 308, 51, 28);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Condition");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2_1.setBounds(259, 308, 83, 28);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("Field");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3_1.setBounds(457, 308, 45, 28);
		contentPane.add(lblNewLabel_3_1);
		
		field2TF = new JTextField();
		field2TF.setText("Instructor_id");
		field2TF.setFont(new Font("Tahoma", Font.PLAIN, 16));
		field2TF.setColumns(10);
		field2TF.setBounds(416, 347, 121, 28);
		contentPane.add(field2TF);
		
		condition2TF = new JTextField();
		condition2TF.setText("=");
		condition2TF.setFont(new Font("Tahoma", Font.PLAIN, 16));
		condition2TF.setColumns(10);
		condition2TF.setBounds(238, 348, 121, 27);
		contentPane.add(condition2TF);
		
		table2TF = new JTextField();
		table2TF.setText("Section");
		table2TF.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table2TF.setColumns(10);
		table2TF.setBounds(54, 348, 121, 23);
		contentPane.add(table2TF);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 45, 600, 2);
		contentPane.add(separator_2);
		
		JLabel lblNewLabel_4 = new JLabel("Table 1");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(259, 58, 71, 23);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("Table 2");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4_1.setBounds(259, 250, 71, 23);
		contentPane.add(lblNewLabel_4_1);
		
	}
}
