package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.I_Catalogue;
import entities.I_Produit;
import entities.Produit;

public class ProduitDAO_Oracle  implements I_ProduitDAO{

	private Connection cn;
	private PreparedStatement pst;

	public ProduitDAO_Oracle() {

		try {
			String url,login,password;
			Class.forName("oracle.jdbc.OracleDriver");

			url = "jdbc:oracle:thin:@162.38.222.146:1521";
//			login = "thiburj";
//			password = "0802023468P";
			login = "bertrandm";
			password = "2805003653P";

			cn = DriverManager.getConnection(url, login, password);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}


	@Override
	public boolean creerProduit(I_Produit p, I_Catalogue catalogue) {
		try {
			pst = cn.prepareStatement("INSERT INTO Produit (idProduit, nomProduit, stockProduit  prixHTProduit, catalogue) Values ( seqProd.NEXTVAL , ? , ? , ?)");
			pst.setString(1, p.getNom());
			pst.setInt(2, p.getQuantite());
			pst.setDouble(3, p.getPrixUnitaireHT());
			pst.setString(4, catalogue.getNom());
			
			if(pst.executeUpdate() == 1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public boolean miseAjourProduit(I_Produit p, I_Catalogue catalogue) {
		try {
			pst = cn.prepareStatement("UPDATE Produit " +
									  "SET stockProduit=? , prixHTProduit=? " +
									  "WHERE nomProduit = ? AND catalogue IN (SELECT idCatalogue FROM Catalogue WHERE nomCatalogue = ?)");
			pst.setInt(1, p.getQuantite());
			pst.setDouble(2, p.getPrixUnitaireHT());
			pst.setString(3, p.getNom());
			pst.setString(4, catalogue.getNom());
			
			if(pst.executeUpdate() == 1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public boolean supprimerProduit(I_Produit p, I_Catalogue catalogue) {
		try {
			pst = cn.prepareStatement("DELETE FROM Produit WHERE nomProduit = ? AND catalogue IN (SELECT idCatalogue FROM Catalogue WHERE nomCatalogue = ?)");
			pst.setString(1, p.getNom());
			pst.setString(2, catalogue.getNom());
			
			if(pst.executeUpdate() == 1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public I_Produit recupererProduit(String nom, I_Catalogue catalogue) {
		try {
			ResultSet rs;
			Produit prod;
			pst = cn.prepareStatement("SELECT * FROM Produit WHERE nomProduit = ? AND catalogue IN (SELECT idCatalogue FROM Catalogue WHERE nomCatalogue = ?)");
			pst.setString(1, nom);
			pst.setString(2, catalogue.getNom());
			rs = pst.executeQuery();
			if ( rs.next() ){
				String nomProd = rs.getString("nomProduit");
				int stockProd = rs.getInt("stockProduit");
				Double prixHT = rs.getDouble("prixHTProduit");
				prod = new Produit(nomProd, prixHT, stockProd, catalogue);
				return prod;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public List<I_Produit> recupererProduits(I_Catalogue catalogue) {
		try {
			ResultSet rs;
			ArrayList<I_Produit> listProd = new ArrayList<I_Produit>();
			pst = cn.prepareStatement("SELECT * FROM Produit WHERE catalogue IN (SELECT idCatalogue FROM Catalogue WHERE nomCatalogue = ?)");
			pst.setString(1, catalogue.getNom());
			rs = pst.executeQuery();
			while ( rs.next() ){
				String nomProd = rs.getString("nomProduit");
				int stockProd = rs.getInt("stockProduit");
				Double prixHT = rs.getDouble("prixHTProduit");
				Produit prod = new Produit(nomProd, prixHT, stockProd, catalogue);
				listProd.add(prod);
			}
			return listProd;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
