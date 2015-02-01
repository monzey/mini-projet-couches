package persistence;

import java.util.List;

import entities.I_Catalogue;

public interface I_CatalogueDAO {

	public boolean creerCatalogue(I_Catalogue catalogue);
	public boolean supprimerCatalogue(I_Catalogue catalogue);
	public List<I_Catalogue> recupererCatalogues();
	public I_Catalogue recupererCatalogue(String nomCatalogue);
	public int getIdCatalogue(I_Catalogue catalogue);
	public int getNbProduits(I_Catalogue catalogue);
	
}
