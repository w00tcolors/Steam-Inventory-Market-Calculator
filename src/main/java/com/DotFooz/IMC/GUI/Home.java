package com.DotFooz.IMC.GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.DotFooz.IMC.Application;
import com.DotFooz.IMC.Static.Count;

/**
 * new EntryPoint
 * @author DotFooz
 *
 */
public class Home extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8229891059543838385L;
	private JPanel contentPane;
	private JTextField txtID;
	final JTextField txtMarket;
	final JTextField txtCompete;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setTitle("Steam Inventory market Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblSteamcommun = new JLabel("steamcommunity.com/id/");
		
		txtID = new JTextField();
		txtID.setColumns(10);
		
		JLabel lblSteamId = new JLabel("Steam ID:");
		
		JPanel panel = new JPanel();
		
		JLabel lblGames = new JLabel("Games:");
		
		JLabel lblMarketPricing = new JLabel("Market Pricing:");
		
		final JCheckBox chckbxTf = new JCheckBox("TF2");
		final JCheckBox chckbxCsgo = new JCheckBox("CS:GO");
		final JCheckBox chckbxSteamInventory = new JCheckBox("Steam inventory");

		
		txtMarket = new JTextField();
		txtMarket.setEditable(false);
		txtMarket.setColumns(10);
		
		JLabel lblCompetitorPricing = new JLabel("Competitor Pricing:");
		
		txtCompete = new JTextField();
		txtCompete.setEditable(false);
		txtCompete.setColumns(10);
		
		final JLabel lblStatus = new JLabel("Status: Awaiting.....");

		
		JButton btnCalc = new JButton("Calculate!");
		btnCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Runnable runner = new Runnable()
				{
					@Override
					public void run() {
						int max = 3;
						txtCompete.setText("");
						txtMarket.setText("");
						lblStatus.setText("Status: Calculating..... (1/"+max+")");
						Count.log.clear();
						String id = txtID.getText();
						System.out.println("ID: "+id);
						Application app = new Application();
						
						if(chckbxTf.isSelected())
							app.ReadInv(id,"440","2",false,"Team Fortress 2 backpack");
						lblStatus.setText("Status: Calculating..... (2/"+max+")");

						
						if(chckbxCsgo.isSelected())
							app.ReadInv(id, "730", "2",false,"CS:GO Inventory");
						lblStatus.setText("Status: Calculating..... (3/"+max+")");

						
						if(chckbxSteamInventory.isSelected())
							app.ReadInv(id, "753", "6",true, "Steam Inventory");
						
						
						txtCompete.setText("$"+app.finalCash(Count.totalLowest));
						txtMarket.setText("$"+app.finalCash(Count.totalAverage));
						lblStatus.setText("Status: Finished!");

					}
				};
				Thread run = new Thread(runner);
				run.start();

			}
		});
		

		
		JLabel lblResults = new JLabel("Results:");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblSteamId)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblSteamcommun)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtID, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
								.addComponent(lblGames)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblMarketPricing, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblCompetitorPricing, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(txtMarket, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtCompete, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)))
								.addComponent(lblResults)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(121)
							.addComponent(btnCalc))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(108)
							.addComponent(lblStatus)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSteamId)
						.addComponent(lblSteamcommun)
						.addComponent(txtID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblGames)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnCalc)
					.addGap(18)
					.addComponent(lblResults)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMarketPricing)
						.addComponent(txtMarket, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(txtCompete, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(21)
							.addComponent(lblCompetitorPricing)))
					.addGap(18)
					.addComponent(lblStatus)
					.addContainerGap(38, Short.MAX_VALUE))
		);
		
		panel.add(chckbxTf);
		
		panel.add(chckbxCsgo);
		
		panel.add(chckbxSteamInventory);
		contentPane.setLayout(gl_contentPane);
	}
}
