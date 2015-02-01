package graphics;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controllers.CtrAchatVente;
import controllers.CtrCatalogue;
import controllers.CtrProduit;

public class FenetreNouveauProduit extends JFrame implements ActionListener {

	private JTextField txtPrixHT;
	private JTextField txtNom;
	private JTextField txtQte;
//	private JComboBox<String> combo;
	private JButton btValider;

	private CtrProduit ctrlProd;
	

//	public FenetreNouveauProduit(String[] lesCategories) {
	public FenetreNouveauProduit(CtrCatalogue ctr) {	

		this.ctrlProd = ctr.createCtrProduit();
		setTitle("Creation Produit");
		setBounds(500, 500, 200, 250);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());

		JLabel labNom = new JLabel("Nom produit");
		JLabel labPrixHT = new JLabel("Prix Hors Taxe");
		JLabel labQte = new JLabel("Quantité en stock");
//		JLabel labCategorie = new JLabel("Categorie");
		contentPane.add(labNom);
		txtNom = new JTextField(15);
		contentPane.add(txtNom);
		contentPane.add(labPrixHT);
		txtPrixHT = new JTextField(15);
		contentPane.add(txtPrixHT);
		contentPane.add(labQte);
		txtQte = new JTextField(15);
		contentPane.add(txtQte);

//		combo = new JComboBox<String>(lesCategories);
//		combo.setPreferredSize(new Dimension(100, 20));
//		contentPane.add(labCategorie);
//		contentPane.add(combo);

		
		btValider = new JButton("Valider");
		contentPane.add(btValider);

		btValider.addActionListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		try{
			this.ctrlProd.creerProduit(txtNom.getText(), Integer.valueOf(txtPrixHT.getText()), Integer.valueOf(txtQte.getText()));
		} catch(NumberFormatException ex){
			System.out.println("Ce n'est pas un nombre");
		}
		this.dispose();
	}

}