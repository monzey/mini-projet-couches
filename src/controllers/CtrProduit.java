package controllers;

import entities.Catalogue;
import entities.I_Catalogue;
import entities.I_Produit;

public class CtrProduit {

	private I_Catalogue cat;
	
	public CtrProduit(I_Catalogue cat) {
		// TODO Auto-generated constructor stub
		this.cat = cat;
	}
	
	public boolean creerProduit(String nom, int prix, int qte){
		return this.cat.addProduit(nom, prix, qte);
	}
	
	public boolean supprimerProduit(String nom){
		return this.cat.removeProduit(nom);
	}

}
