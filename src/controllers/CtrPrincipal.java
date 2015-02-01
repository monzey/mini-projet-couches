package controllers;

import java.awt.List;
import java.util.ArrayList;

import entities.Catalogue;
import entities.EnsembleCatalogues;
import entities.I_Catalogue;

public class CtrPrincipal{

	private EnsembleCatalogues ensCatalogues;
	
	public CtrPrincipal() {
		this.ensCatalogues = new EnsembleCatalogues();
	}

	public String[] getNomsCatalogues() {
		return this.ensCatalogues.getNomsCatalogues();
	}

	public int getNbCatalogues() {
		return this.ensCatalogues.getNbCatalogues();
	}

	public String[] getNomsCataloguesAvecNbProduits() {
		return this.ensCatalogues.getNbCataloguesAvecNbProduits();
	}

	public ArrayList<I_Catalogue> getCatalogues() {
		return this.ensCatalogues.getCatalogues();
	}


	public boolean addCatalogue(String nomCatalogue) {
		return this.ensCatalogues.addCatalogue(nomCatalogue);
	}

	public CtrCatalogue createCtrCatalogue(String nomCatalogue) {
		I_Catalogue catalogue = new Catalogue(nomCatalogue);
		this.ensCatalogues.addCatalogue(catalogue);
		return new CtrCatalogue(this.ensCatalogues.getCatalogue(catalogue));
	}
	
	public I_Catalogue getCatalogue(String nomCatalogue){
		I_Catalogue catalogue = new Catalogue(nomCatalogue);
		return this.ensCatalogues.getCatalogue(catalogue);
	}

	public boolean removeCatalogue(String nomCatalogue) {
		return this.ensCatalogues.removeCatalogue(nomCatalogue);
	}

	
	

}
