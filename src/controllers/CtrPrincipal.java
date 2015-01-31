package controllers;

import entities.Catalogue;
import entities.I_Catalogue;

public class CtrPrincipal{

	private I_Catalogue cat;
	
	public CtrPrincipal() {
		// TODO Auto-generated constructor stub
		cat = new Catalogue();
	}
	
	public CtrProduit createCtrProduit(){
		return new CtrProduit(this.cat);
	}
	
	public CtrStocks createCtrStcks(){
		return new CtrStocks(this.cat);
	}
	
	public CtrAchatVente createCtrAchVent(){
		return new CtrAchatVente(this.cat);
	}
	
	public I_Catalogue getCatalogue(){
		return this.cat;
	}
	
	public void setCatalogue(I_Catalogue catalogue){
		this.cat = catalogue;
	}
	

}
