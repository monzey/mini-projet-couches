package entities;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Catalogue implements I_Catalogue {
	private static DecimalFormat df = new DecimalFormat("0.00");
	private ArrayList<I_Produit> lesProduits;

	public Catalogue() {
		lesProduits = new ArrayList<I_Produit>();
	}

	@Override
	public boolean addProduit(I_Produit produit) {
		if (produitIsValid(produit)){
			if (!lesProduits.contains(produit)) {
				lesProduits.add(produit);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean addProduit(String nom, double prix, int qte) {

		I_Produit newProduit = new Produit(nom,prix,qte);
		if (produitIsValid(newProduit)){
			if (!lesProduits.contains(newProduit)) {
				lesProduits.add(newProduit);
				return true;
			}
		}

		return false;
	}

	@Override
	public int addProduits(List<I_Produit> l) {
		int i = 0;
		if (l !=null){
			for (I_Produit i_Produit : l) {
				if (produitIsValid(i_Produit)) {
					lesProduits.add(i_Produit);
					i++;
				}
			}

		}
		return i;
	}

	private boolean produitIsValid(I_Produit produit) {
		return produit !=null && !lesProduits.contains(produit) && produit.getQuantite()>=0 && produit.getPrixUnitaireHT()>0;
	}

	@Override
	public boolean removeProduit(String nom) {
		//TODO verifier degueullassité
		int indexProdASuppr = getProduit(nom);
		if (indexProdASuppr >=0 ){
			return (lesProduits.remove(indexProdASuppr) != null ? true : false);
		}
		return false;

	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		
		int indexProd = getProduit(nomProduit);
		if (indexProd >= 0 ){
			return lesProduits.get(indexProd).ajouter(qteAchetee);
		}
		return false;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {

		int indexProd = getProduit(nomProduit);
		if (indexProd >= 0 ){
			return lesProduits.get(indexProd).enlever(qteVendue);
		}
		return false;
	}

	@Override
	public String[] getNomProduits() {
		String[] listeProduits = new String[lesProduits.size()];
		int i=0;
		for (I_Produit prod : lesProduits) {
			listeProduits[i] = prod.getNom();
			i++;
		}
		Arrays.sort(listeProduits);
		return listeProduits;
	}

	@Override
	public double getMontantTotalTTC() {
		double totalTTC = 0;
		for (I_Produit prod : lesProduits) {
			totalTTC += prod.getPrixStockTTC();
		}
		totalTTC = (double)Math.round(totalTTC*100)/100;
		return totalTTC;
	}

	@Override
	public void clear() {
		lesProduits.removeAll(lesProduits);
	}

	@Override
	public String toString() {
		String superStringDuFutur = "";
		for (I_Produit produit : this.lesProduits) {
			superStringDuFutur += produit + "\n";
		}
		superStringDuFutur += "\n"+"Montant total TTC du stock : "+df.format(getMontantTotalTTC())+" €";
		return superStringDuFutur;
	}

	private int getProduit(String nom){
		I_Produit produit = new Produit(nom);
		return lesProduits.indexOf(produit);
	}

}
