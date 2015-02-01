package entities;

import java.util.ArrayList;

import persistence.CatalogueDAOFactory;
import persistence.CatalogueDAO_Mysql;
import persistence.DAOAbstractFactory;
import persistence.I_CatalogueDAO;

public class EnsembleCatalogues {

	private ArrayList<I_Catalogue> catalogues;
	private I_CatalogueDAO daoCat;
	
	public EnsembleCatalogues() {
		this.catalogues = new ArrayList<I_Catalogue>();
		this.daoCat = DAOAbstractFactory.getInstance().createCatalogueDAO();
	}

	public String[] getNomsCatalogues() {
		//lazy loading
		this.catalogues = new ArrayList<I_Catalogue>(this.daoCat.recupererCatalogues());
		String[] nomsCatalogues = new String[this.catalogues.size()];
		int i = 0;
		for (I_Catalogue catalogue : this.catalogues) {
			nomsCatalogues[i] = catalogue.getNom();
			i++;
		}
		return nomsCatalogues;
	}

	public int getNbCatalogues() {
		return this.catalogues.size();
	}

	
	public String[] getNbCataloguesAvecNbProduits() {
		//lazy loading
		this.catalogues = new ArrayList<I_Catalogue>(this.daoCat.recupererCatalogues());
		String[] nomsCataloguesAvecProduits = new String[this.catalogues.size()];
		int i = 0;
		for (I_Catalogue catalogue : this.catalogues) {
			nomsCataloguesAvecProduits[i] = catalogue.getNom() + " : " + catalogue.getNbProduits() + " Produits";
			i++;
		}
		return nomsCataloguesAvecProduits;
	}

	public ArrayList<I_Catalogue> getCatalogues() {
		return this.catalogues;
	}

	public boolean addCatalogue(String nomCatalogue) {
		I_Catalogue catalogue = new Catalogue(nomCatalogue);
		return this.daoCat.creerCatalogue(catalogue) && this.catalogueIsValid(catalogue) && this.catalogues.add(catalogue);		
	}

	public boolean addCatalogue(I_Catalogue catalogue) {
		return this.daoCat.creerCatalogue(catalogue) && this.catalogueIsValid(catalogue) && this.catalogues.add(catalogue);
	}
	

	public I_Catalogue getCatalogue(I_Catalogue catalogue) {
		return this.catalogues.get(this.catalogues.indexOf(catalogue));
	}
	
	private boolean catalogueIsValid(I_Catalogue c){
		return !this.catalogues.contains(c);
	}

	public boolean removeCatalogue(String nomCatalogue) {
		I_Catalogue catalogue = new Catalogue(nomCatalogue);
		return this.daoCat.supprimerCatalogue(catalogue) && this.catalogues.remove(catalogue);
	}

}
