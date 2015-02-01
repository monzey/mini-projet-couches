package persistence;

import java.util.List;

import entities.I_Catalogue;
import entities.I_Produit;

public interface I_ProduitDAO {
	
	public boolean creerProduit(I_Produit p, I_Catalogue catalogue);
	public boolean miseAjourProduit(I_Produit p, I_Catalogue catalogue);
	public boolean supprimerProduit(I_Produit p, I_Catalogue catalogue);
	public I_Produit recupererProduit(String nom, I_Catalogue catalogue);
	public List<I_Produit> recupererProduits(I_Catalogue catalogue);
}
