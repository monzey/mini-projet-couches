package entities;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Produit implements I_Produit {
	private static double tauxTVA = 0.2;
	private static DecimalFormat df;

	private String nom;
	private int quantiteStock;
	private double prixUnitaireHT;

	public Produit(String nomProduit, double prixUnitaireHTProduit,int quantiteInitiale) {
		this.nom = nomProduit;
		this.quantiteStock = quantiteInitiale;
		this.prixUnitaireHT = prixUnitaireHTProduit;
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.FRANCE);
		df = new DecimalFormat("0.00", otherSymbols);
	}
	
	public Produit(String nomProduit) {
		this.nom = nomProduit;
	}

	@Override
	public boolean ajouter(int qteAchetee) {
		if (qteAchetee > 0) {
			this.quantiteStock += qteAchetee;
			return true;
		}
		return false;
	}

	@Override
	public boolean enlever(int qteVendue) {
		if(qteVendue > 0 && qteVendue <= quantiteStock) {
			this.quantiteStock -= qteVendue;
			return true;
		}
		return false;
	}


	@Override
	public boolean equals(Object o){
		if (o instanceof Produit){
			if ( ((Produit)o).getNom() == this.nom){
				return true;
			}
		}
		return false;
	}


	@Override
	public String toString() {
		return getNom()+" - prix HT : "+df.format(getPrixUnitaireHT())+" € - prix TTC : "+df.format(getPrixUnitaireTTC())+" € - quantité en stock : "+getQuantite();
	}

	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public int getQuantite() {
		return quantiteStock;
	}

	@Override
	public double getPrixUnitaireHT() {
		return prixUnitaireHT;
	}

	@Override
	public double getPrixUnitaireTTC() {		
		return prixUnitaireHT+(prixUnitaireHT*tauxTVA);
	}

	@Override
	public double getPrixStockTTC() {
		return (getPrixUnitaireTTC()*quantiteStock);
	}

}
