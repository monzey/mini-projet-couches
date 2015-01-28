package persistence;

import java.util.List;

import entities.I_Produit;

public interface I_ProduitDAO {
	
	public boolean creer(I_Produit p);
	public boolean maj(I_Produit p);
	public boolean supprimer(I_Produit p);
	public I_Produit lire(String nom);
	public List<I_Produit> lireTous();
}
