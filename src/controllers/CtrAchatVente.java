package controllers;

import entities.I_Catalogue;

public class CtrAchatVente{


	private I_Catalogue cat;
	
	
	public CtrAchatVente(I_Catalogue cat) {
		// TODO Auto-generated constructor stub
		this.cat = cat;
	}
	
	public boolean acheterProduit(String nomProduit, int qteAchetee){
		return this.cat.acheterStock(nomProduit, qteAchetee);
	}

}
