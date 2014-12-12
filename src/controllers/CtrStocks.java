package controllers;

import entities.Catalogue;
import entities.I_Catalogue;

public class CtrStocks {

	private I_Catalogue cat;
	
	public CtrStocks(I_Catalogue cat) {
		// TODO Auto-generated constructor stub
		this.cat = cat;
	}
	
	public String afficherStocks(){
		return this.cat.toString();
	}
	
}
