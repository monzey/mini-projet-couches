package persistence;

import java.util.List;

import entities.I_Produit;

public class ProduitDAO_XMLAdapter implements I_ProduitDAO {

	private ProduitDAO_XML dao;
	
	public ProduitDAO_XMLAdapter() {
		this.dao = new ProduitDAO_XML();
	}

	@Override
	public boolean creerProduit(I_Produit p) {
		return this.dao.creer(p);
	}

	@Override
	public boolean miseAjourProduit(I_Produit p) {
		return this.dao.maj(p);
	}

	@Override
	public boolean supprimerProduit(I_Produit p) {
		return this.dao.supprimer(p);
	}

	@Override
	public I_Produit recupererProduit(String nom) {
		return this.dao.lire(nom);
	}

	@Override
	public List<I_Produit> recupererProduits() {
		return this.dao.lireTous();
	}

}
