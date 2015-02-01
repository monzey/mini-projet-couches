package graphics;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.CtrAchatVente;
import controllers.CtrCatalogue;

public class FenetreAchat extends JFrame implements ActionListener {

	private JButton btAchat;
	private JTextField txtQuantite;
	private JComboBox<String> combo;
	private CtrAchatVente ctrlAchVent;

	public FenetreAchat(String[] lesProduits, CtrCatalogue ctr) {

		this.ctrlAchVent = ctr.createCtrAchVent();
		setTitle("Achat");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btAchat = new JButton("Achat");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantité achetée"));
		contentPane.add(txtQuantite);
		contentPane.add(btAchat);

		btAchat.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		this.ctrlAchVent.acheterProduit((String)combo.getSelectedItem(), Integer.valueOf(txtQuantite.getText()));
		this.dispose();
	}

}
