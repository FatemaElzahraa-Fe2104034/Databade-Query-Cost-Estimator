package project;

import java.awt.Component; 
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class main_window extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main_window frame = new main_window();
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
	public main_window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Choose Operation");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 28));
		lblNewLabel.setBounds(135, 45, 270, 48);
		contentPane.add(lblNewLabel);
		
		JButton selectJB = new JButton("Select");
		selectJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectWindow selectw = new selectWindow();
				selectw.setVisible(true);
				selectw.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
	            currentFrame.dispose();
			}
			
		});
		selectJB.setFont(new Font("Tahoma", Font.BOLD, 18));
		selectJB.setBounds(75, 146, 153, 41);
		contentPane.add(selectJB);
		
		JButton joinJB = new JButton("Join");
		joinJB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joinWindow jw = new joinWindow();
				jw.setVisible(true);
				jw.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
	            currentFrame.dispose();
			}
		});
		joinJB.setFont(new Font("Tahoma", Font.BOLD, 18));
		joinJB.setBounds(303, 146, 153, 41);
		contentPane.add(joinJB);
		
		JButton btnShowMetadata = new JButton("Show Metadata");
		btnShowMetadata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				metadataWindow mdw = new metadataWindow();
				mdw.setVisible(true);
				mdw.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
	            currentFrame.dispose();
			}
		});
		btnShowMetadata.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnShowMetadata.setBounds(161, 211, 212, 41);
		contentPane.add(btnShowMetadata);
	}
}
