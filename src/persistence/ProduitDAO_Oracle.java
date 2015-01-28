package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entities.I_Produit;

public class ProduitDAO_Oracle  implements I_ProduitDAO{

	public static final int ORACLE = 10;
	public static final int XML = 20;

	private Connection cn;
	private PreparedStatement pst;
	private ResultSet result;


	public ProduitDAO_Oracle() {

		try {
			String url,login,password;
			Class.forName("oracle.jdbc.OracleDriver");

			url = "jdbc:oracle:thin:@gimli:1521:iut";
			login = "thiburj";
			password = "0802023468P";

			cn = DriverManager.getConnection(url, login, password);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}


	@Override
	public boolean creer(I_Produit p) {
		try {
			pst = cn.prepareStatement("INSERT INTO Produit (idProduit, nomProduit, stockProduit  prixHTProduit) Values ( Select max(idProduit)+1 From Produit , ? , ? , ?)");
			pst.setString(1, p.getNom());
			pst.setInt(2, p.getQuantite());
			pst.setDouble(3, p.getPrixUnitaireHT());
			
			if(pst.executeUpdate() == 1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public boolean maj(I_Produit p) {
		try {
			pst = cn.prepareStatement("UPDATE Produit" +
									  "SET (stockProduit=? , prixHTProduit=?)" +
									  "WHERE (nomProduit = ?");
			pst.setString(3, p.getNom());
			pst.setInt(1, p.getQuantite());
			pst.setDouble(2, p.getPrixUnitaireHT());
			
			
			
			if(pst.executeUpdate() == 1) return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public boolean supprimer(I_Produit p) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public I_Produit lire(String nom) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<I_Produit> lireTous() {
		// TODO Auto-generated method stub
		return null;
	}
}
