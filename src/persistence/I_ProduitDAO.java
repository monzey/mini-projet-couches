package persistence;

import java.util.List;

import entities.I_Produit;

public interface I_ProduitDAO {
	
	public boolean creerProduit(I_Produit p);
	public boolean miseAjourProduit(I_Produit p);
	public boolean supprimerProduit(I_Produit p);
	public I_Produit recupererProduit(String nom);
	public List<I_Produit> recupererProduits();
}
