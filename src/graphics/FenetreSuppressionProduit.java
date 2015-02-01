package graphics;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.CtrCatalogue;
import controllers.CtrProduit;

public class FenetreSuppressionProduit extends JFrame implements ActionListener {

	private JButton btSupprimer;
	private JComboBox<String> combo;
	
	private CtrProduit ctrlProd;
	
	public FenetreSuppressionProduit(String lesProduits[], CtrCatalogue ctr) {
		
		this.ctrlProd = ctr.createCtrProduit();
		setTitle("Suppression produit");
		setBounds(500, 500, 200, 105);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btSupprimer = new JButton("Supprimer");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(btSupprimer);

		btSupprimer.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		this.ctrlProd.supprimerProduit((String)combo.getSelectedItem());
		this.dispose();
	}

}
