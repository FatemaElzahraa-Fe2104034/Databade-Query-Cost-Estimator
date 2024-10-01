package project;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class selectWindow extends JFrame {
    private JPanel contentPane;
    private JTextField valueTF;
    private DefaultTableModel tableModel;
    private JTextField valueTF2;
    private JComboBox tableCB;
    private JComboBox fieldCB;
    private JComboBox conditionCB;
    private JRadioButton andRB;
    private JButton calculateB;
    private JComboBox conditionCB2;
    private JComboBox fieldCB2;
    private Dbase db = new Dbase();
    private JTextField tableTF2;
    private JRadioButton orRB;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    selectWindow frame = new selectWindow();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public selectWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 709, 531);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Select Operation");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblNewLabel.setBounds(245, 11, 206, 46);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Table");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(104, 97, 82, 22);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Condition");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_1.setBounds(310, 97, 97, 22);
        contentPane.add(lblNewLabel_1_1);
        
        fieldCB = new JComboBox();
        fieldCB.setBounds(499, 119, 139, 22);
        contentPane.add(fieldCB);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Field");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_1_1.setBounds(542, 97, 66, 22);
        contentPane.add(lblNewLabel_1_1_1);
        
        valueTF = new JTextField();
        valueTF.setBounds(273, 178, 191, 20);
        contentPane.add(valueTF);
        valueTF.setColumns(10);
        
        JLabel lblNewLabel_1_2 = new JLabel("Value");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_2.setBounds(197, 173, 66, 22);
        contentPane.add(lblNewLabel_1_2);
        
        String[] conditions = {"=", ">", "<"};
        conditionCB = new JComboBox(conditions);
        conditionCB.setBounds(281, 119, 139, 22);
        contentPane.add(conditionCB);
        
        
        String[] tables = {"Instructor", "Section"};
        tableCB = new JComboBox(tables);
        tableCB.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		andRB.setSelected(false);
    			orRB.setSelected(false);
    			tableTF2.setText("");
    			fieldCB2.removeAllItems();
        		
        		if (tableCB.getSelectedItem().equals("Instructor")) {
        			fieldCB.removeAllItems();
        			fieldCB.addItem("ID");
        			fieldCB.addItem("Rank");
        			fieldCB.addItem("IName");
        			fieldCB.addItem("IOffice");
        			fieldCB.addItem("IPhone");
        			fieldCB.addItem("I_DCode");
        		}
                else if (tableCB.getSelectedItem().equals("Section")) {
                	fieldCB.removeAllItems();
                	fieldCB.addItem("secId");
                	fieldCB.addItem("secNo");
                	fieldCB.addItem("sem");
                	fieldCB.addItem("Year");
                	fieldCB.addItem("Blgd");
                	fieldCB.addItem("RoomNo");
                	fieldCB.addItem("DaysTime");
                	fieldCB.addItem("Sec_CCode");
                	fieldCB.addItem("Instructor_id");
                }
                
        		
        	}
        });
        tableCB.setBounds(58, 119, 139, 22);
        contentPane.add(tableCB);
  
        
        calculateB = new JButton("Calculate Cost");
        calculateB.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String table1 = (String)tableCB.getSelectedItem();
        		String condition1 = (String)conditionCB.getSelectedItem();
        		String field1 = (String)fieldCB.getSelectedItem();
        		String value1 = (String)valueTF.getText();
        		System.out.println(table1+condition1+field1+value1);
        		
        		costWindow costw = new costWindow();
    			costw.setVisible(true);
    			costw.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    			
    			String query = "select * from "+table1+" where "+field1+" "+condition1+" "+value1;
    			costw.setTextF(query);
    			
    			
    			ArrayList<String[]> scenarios1 = new ArrayList<>();
        		ArrayList<String[]> scenarios2 = new ArrayList<>();
        		
        		if (andRB.isSelected() || orRB.isSelected()) {
        			String table2 = (String)tableTF2.getText();
            		String condition2 = (String)conditionCB2.getSelectedItem();
            		String field2 = (String)fieldCB2.getSelectedItem();
            		String value2 = (String)valueTF2.getText();
            		
            		if (andRB.isSelected()) {
            			query += " and "+field2+" "+condition2+" "+value2;
            		}
            		else if (orRB.isSelected()) {
            			query += " or "+field2+" "+condition2+" "+value2;
            		}
            		
            		costw.setTextF(query);
            		
            		String binary2 = db.binarySearch(table2, field2, condition2, value2);
            		if (!binary2.equals("")) {
            			String[] scenario = {"Binary Search", binary2};
            			scenarios2.add(scenario);
            		}
            		
            		String linear2 = db.linearSearch(table2, field2, condition2);
            		if (!linear2.equals("")) {
            			String[] scenario = {"Linear Search", linear2};
            			scenarios2.add(scenario);
            		}
            		
            		String primary2 = db.primaryIndex(table2, field2, condition2);
            		if (!primary2.equals("")) {
            			String[] scenario = {"Using Primary Index to retreive single record", primary2};
            			scenarios2.add(scenario);
            			
            		}
            		
            		String ordering2 = db.orderingIndex(table2, field2, condition2);
            		if (!ordering2.equals("")) {
            			String[] scenario = {"Using Ordering Index to retreive multiple records", ordering2};
            			scenarios2.add(scenario);
            		}
            		
            		String tree2 = db.Btree(table2, field2, condition2);
            		if (!tree2.equals("")) {
            			String[] scenario = {"Using Secondary B+ tree", tree2};
            			scenarios2.add(scenario);
            		}
            		
            		
            		            		            		           		
        		}
        		
        		String binary1 = db.binarySearch(table1, field1, condition1, value1);
        		if (!binary1.equals("")) {
        			String[] scenario = {"Binary Search", binary1};
        			scenarios1.add(scenario);
        		}
        		
        		String linear1 = db.linearSearch(table1, field1, condition1);
        		if (!linear1.equals("")) {
        			String[] scenario = {"Linear Search", linear1};
        			scenarios1.add(scenario);
        		}
        		
        		String primary1 = db.primaryIndex(table1, field1, condition1);
        		if (!primary1.equals("")) {
        			String[] scenario = {"Using Primary Index to retreive single record", primary1};
        			scenarios1.add(scenario);
        			
        		}
        		
        		String ordering1 = db.orderingIndex(table1, field1, condition1);
        		if (!ordering1.equals("")) {
        			String[] scenario = {"Using Ordering Index to retreive multiple records", ordering1};
        			scenarios1.add(scenario);
        		}
        		
        		String tree1 = db.Btree(table1, field1, condition1);
        		if (!tree1.equals("")) {
        			String[] scenario = {"Using Secondary B+ tree", tree1};
        			scenarios1.add(scenario);
        		}
        		
        		if (scenarios2.isEmpty()) {
        			for(int i=0; i<scenarios1.size(); i++) {
        				String[] scenario = scenarios1.get(i);
            			costw.AddRow(new String[] {
    					
    					scenario[0],
    					scenario[1]
    			});
        			}
        		}
        		else {
        			for (int i=0; i<scenarios1.size(); i++) {
        				String[] scenario1 = scenarios1.get(i);
        				for (int j=0; j<scenarios2.size(); j++) {
        					String[] scenario2 = scenarios2.get(j);
        					costw.AddRow(new String[] {
        	    					
        	    					scenario1[0]+" and "+scenario2[0],
        	    					scenario1[1]+" + "+scenario2[1]
        	    			});
        				}
        			}
        		}
        		
        		JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
	            currentFrame.dispose();
        		
        		
        		
        		
        	}
        });
        calculateB.setFont(new Font("Tahoma", Font.PLAIN, 20));
        calculateB.setBounds(231, 430, 228, 34);
        contentPane.add(calculateB);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(0, 218, 695, 11);
        contentPane.add(separator);
        
        andRB = new JRadioButton("AND");
        andRB.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (andRB.isSelected()) {
        			orRB.setSelected(false);
        			tableTF2.setText((String) tableCB.getSelectedItem());	
        		}
        		if (tableTF2.getText().equals("Instructor")) {
        			fieldCB2.removeAllItems();
        			fieldCB2.addItem("ID");
        			fieldCB2.addItem("Rank");
        			fieldCB2.addItem("IName");
        			fieldCB2.addItem("IOffice");
        			fieldCB2.addItem("IPhone");
        			fieldCB2.addItem("I_DCode");
        		}
        		else if (tableTF2.getText().equals("Section")) {
        			fieldCB2.removeAllItems();
                	fieldCB2.addItem("secId");
                	fieldCB2.addItem("secNo");
                	fieldCB2.addItem("sem");
                	fieldCB2.addItem("Year");
                	fieldCB2.addItem("Blgd");
                	fieldCB2.addItem("RoomNo");
                	fieldCB2.addItem("DaysTime");
                	fieldCB2.addItem("Sec_CCode");
                	fieldCB2.addItem("Instructor_id");
        		}
        		
        	}
        });
        andRB.setForeground(new Color(220, 20, 60));
        andRB.setFont(new Font("Tahoma", Font.BOLD, 18));
        andRB.setBounds(310, 244, 82, 23);
        contentPane.add(andRB);
        
        JLabel lblNewLabel_2 = new JLabel("*Optional");
        lblNewLabel_2.setForeground(new Color(220, 20, 60));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblNewLabel_2.setBounds(138, 240, 142, 26);
        contentPane.add(lblNewLabel_2);
        
        
        conditionCB2 = new JComboBox(conditions);
        conditionCB2.setBounds(281, 306, 139, 22);
        contentPane.add(conditionCB2);
        
        fieldCB2 = new JComboBox();
        fieldCB2.setBounds(499, 306, 139, 22);
        contentPane.add(fieldCB2);
        
        JLabel lblNewLabel_1_3 = new JLabel("Table");
        lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_3.setBounds(104, 284, 82, 22);
        contentPane.add(lblNewLabel_1_3);
        
        JLabel lblNewLabel_1_1_2 = new JLabel("Condition");
        lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_1_2.setBounds(310, 284, 97, 22);
        contentPane.add(lblNewLabel_1_1_2);
        
        JLabel lblNewLabel_1_1_1_1 = new JLabel("Field");
        lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_1_1_1.setBounds(542, 284, 66, 22);
        contentPane.add(lblNewLabel_1_1_1_1);
        
        JLabel lblNewLabel_1_2_1 = new JLabel("Value");
        lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_2_1.setBounds(197, 360, 66, 22);
        contentPane.add(lblNewLabel_1_2_1);
        
        valueTF2 = new JTextField();
        valueTF2.setColumns(10);
        valueTF2.setBounds(273, 365, 191, 20);
        contentPane.add(valueTF2);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(0, 408, 695, 11);
        contentPane.add(separator_1);
        
        tableTF2 = new JTextField();
        tableTF2.setColumns(10);
        tableTF2.setBounds(62, 308, 155, 20);
        contentPane.add(tableTF2);
        
        orRB = new JRadioButton("OR");
        orRB.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (orRB.isSelected()) {
        			andRB.setSelected(false);
        			tableTF2.setText((String) tableCB.getSelectedItem());	
        		}
        		if (tableTF2.getText().equals("Instructor")) {
        			fieldCB2.removeAllItems();
        			fieldCB2.addItem("ID");
        			fieldCB2.addItem("Rank");
        			fieldCB2.addItem("IName");
        			fieldCB2.addItem("IOffice");
        			fieldCB2.addItem("IPhone");
        			fieldCB2.addItem("I_DCode");
        		}
        		else if (tableTF2.getText().equals("Section")) {
        			fieldCB2.removeAllItems();
                	fieldCB2.addItem("secId");
                	fieldCB2.addItem("secNo");
                	fieldCB2.addItem("sem");
                	fieldCB2.addItem("Year");
                	fieldCB2.addItem("Blgd");
                	fieldCB2.addItem("RoomNo");
                	fieldCB2.addItem("DaysTime");
                	fieldCB2.addItem("Sec_CCode");
                	fieldCB2.addItem("Instructor_id");
        		}
     
        		
        		
        		
        	}
        });
        orRB.setForeground(new Color(220, 20, 60));
        orRB.setFont(new Font("Tahoma", Font.BOLD, 18));
        orRB.setBounds(439, 247, 82, 23);
        contentPane.add(orRB);
        
        // Create a custom DefaultTableModel and set it for the JTable
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Table");
        tableModel.addColumn("Condition");
        tableModel.addColumn("Field");
        tableModel.addColumn("Value");
    }
}
