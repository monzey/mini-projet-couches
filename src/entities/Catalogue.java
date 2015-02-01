package entities;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import persistence.CatalogueDAO_Mysql;
import persistence.DAOAbstractFactory;
import persistence.I_CatalogueDAO;
import persistence.I_ProduitDAO;
import persistence.ProduitDAOFactory;

public class Catalogue implements I_Catalogue {
	
	
	private static DecimalFormat df;
	
	private ArrayList<I_Produit> lesProduits;
	private String nom;
	
	private I_ProduitDAO daoPrd;
	private I_CatalogueDAO daoCat;
	
	public Catalogue(String nom) {
		this.daoPrd = DAOAbstractFactory.getInstance().createProduitDAO();
		this.daoCat = DAOAbstractFactory.getInstance().createCatalogueDAO();
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.FRANCE);
		df = new DecimalFormat("0.00", otherSymbols);
		this.nom = nom;
		//aggressive loading
		lesProduits = new ArrayList<I_Produit>(this.daoPrd.recupererProduits(this));
	}
	
	public ArrayList<I_Produit> getProduits(){
		return this.lesProduits;
	}

	@Override
	public boolean addProduit(I_Produit produit) {
		if (produitIsValid(produit)){
			if (!lesProduits.contains(produit)) {
				return this.daoPrd.creerProduit(produit, this) && lesProduits.add(produit);
			}
		}
		return false;
	}

	@Override
	public boolean addProduit(String nom, double prix, int qte) {

		I_Produit newProduit = new Produit(nom,prix,qte, this);
		if (produitIsValid(newProduit)){
			if (!lesProduits.contains(newProduit)) {
				return this.daoPrd.creerProduit(newProduit, this) && lesProduits.add(newProduit);
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
					this.daoPrd.creerProduit(i_Produit, this);
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
		int indexProdASuppr = getProduit(nom);
		if (indexProdASuppr >=0 ){
			this.daoPrd.supprimerProduit(lesProduits.get(indexProdASuppr), this);
			return (lesProduits.remove(indexProdASuppr)  != null ? true : false);
		}
		return false;

	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		
		int indexProd = getProduit(nomProduit);
		if (indexProd >= 0 ){
			this.daoPrd.miseAjourProduit(lesProduits.get(indexProd), this);
			return lesProduits.get(indexProd).ajouter(qteAchetee);
		}
		return false;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {

		int indexProd = getProduit(nomProduit);
		if (indexProd >= 0 ){
			this.daoPrd.miseAjourProduit(lesProduits.get(indexProd), this);
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

	@Override
	public String getNom() {
		return this.nom;
	}
	
	@Override
	public boolean equals(Object o){
		if (o instanceof Catalogue){
			if ( ((Catalogue)o).getNom().equals(this.getNom())){
				return true;
			}
		}
		return false;
	}

	@Override
	public int getNbProduits() {
		return this.daoCat.getNbProduits(this);
	}
	
	public I_CatalogueDAO getCatalogueDAO(){
		return this.daoCat;
	}
	
	public I_ProduitDAO getProduitDAO(){
		return this.daoPrd;
	}


}