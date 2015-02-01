package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import entities.Catalogue;
import entities.I_Catalogue;
import entities.I_Produit;
import entities.Produit;

public class ProduitDAO_Mysql implements I_ProduitDAO {

	private Connection cn;
	private PreparedStatement pst;
	
	public ProduitDAO_Mysql() {
		try {
			String url,login,password;
			Class.forName("com.mysql.jdbc.Driver");

			url = "jdbc:mysql://infolimon.iutmontp.univ-montp2.fr:3306/bertrandm";
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
			pst = cn.prepareStatement("INSERT INTO Produit (nomProduit, stockProduit, prixHTProduit, catalogue) Values ( ? , ? , ?, ?)");
			pst.setString(1, p.getNom());
			pst.setInt(2, p.getQuantite());
			pst.setDouble(3, p.getPrixUnitaireHT());
			pst.setInt(4, ((Catalogue)catalogue).getCatalogueDAO().getIdCatalogue(catalogue));
			
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
									  "WHERE nomProduit = ? AND catalogue = ?");
			pst.setInt(1, p.getQuantite());
			pst.setDouble(2, p.getPrixUnitaireHT());
			pst.setString(3, p.getNom());
			pst.setInt(4, ((Catalogue)catalogue).getCatalogueDAO().getIdCatalogue(catalogue));
			
			if(pst.executeUpdate() == 1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean supprimerProduit(I_Produit p, I_Catalogue catalogue) {
		try {
			pst = cn.prepareStatement("DELETE FROM Produit WHERE nomProduit = ? AND catalogue = ?");
			pst.setString(1, p.getNom());
			pst.setInt(2, ((Catalogue)catalogue).getCatalogueDAO().getIdCatalogue(catalogue));
			
			if(pst.executeUpdate() == 1) return true;
		} catch (SQLException e) {
			if(e instanceof MySQLIntegrityConstraintViolationException) return false;
			else e.printStackTrace();
		}
		return false;
	}

	@Override
	public I_Produit recupererProduit(String nom, I_Catalogue catalogue) {
		try {
			ResultSet rs;
			Produit prod;
			pst = cn.prepareStatement("SELECT * FROM Produit WHERE nomProduit = ? AND catalogue IN (SELECT idCatalogue FROM Catalogue WHERE nomCatalogue = ?) ");
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
