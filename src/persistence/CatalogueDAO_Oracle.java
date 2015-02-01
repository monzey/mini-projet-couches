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

public class CatalogueDAO_Oracle implements I_CatalogueDAO {

	private Connection cn;
	private PreparedStatement pst;
	
	public CatalogueDAO_Oracle() {
		try {
			String url,login,password;
			Class.forName("oracle.jdbc.OracleDriver");

			url = "jdbc:oracle:thin:@162.38.222.146:1521";
			login = "bertrandm";
			password = "2805003653P";
			cn = DriverManager.getConnection(url, login, password);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public boolean creerCatalogue(I_Catalogue catalogue) {
		try {
			pst = cn.prepareStatement("INSERT INTO Catalogue (idCatalogue, nomCatalogue) VALUES (seqCat.NEXTVALUE, ?)");
			pst.setString(1, catalogue.getNom());
			
			if(pst.executeUpdate() == 1) return true;
		} catch (SQLException e) {
			if(e instanceof MySQLIntegrityConstraintViolationException) return false;
			else e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean supprimerCatalogue(I_Catalogue catalogue) {
		try {
			pst = cn.prepareStatement("DELETE FROM Catalogue WHERE nomCatalogue = ?");
			pst.setString(1, catalogue.getNom());
			
			if(pst.executeUpdate() == 1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<I_Catalogue> recupererCatalogues() {
		try {
			ResultSet rs;
			ArrayList<I_Catalogue> listCat = new ArrayList<I_Catalogue>();
			pst = cn.prepareStatement("SELECT * FROM Catalogue");
			rs = pst.executeQuery();
			while ( rs.next() ){
				String nomCat = rs.getString("nomCatalogue");
				Catalogue cat = new Catalogue(nomCat);
				listCat.add(cat);
			}
			return listCat;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public I_Catalogue recupererCatalogue(String nomCatalogue) {
		try {
			ResultSet rs;
			Catalogue cat;
			pst = cn.prepareStatement("SELECT * FROM Catalogue WHERE nomCatalogue = ? ");
			pst.setString(1, nomCatalogue);
			rs = pst.executeQuery();
			if ( rs.next() ){
				String nomCat = rs.getString("nomCatalogue");
				cat = new Catalogue(nomCat);
				return cat;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getIdCatalogue(I_Catalogue catalogue) {
		try {
			ResultSet rs;
			Catalogue cat;
			pst = cn.prepareStatement("SELECT * FROM Catalogue WHERE nomCatalogue = ? ");
			pst.setString(1, catalogue.getNom());
			rs = pst.executeQuery();
			if ( rs.next() ){
				return rs.getInt("idCatalogue");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int getNbProduits(I_Catalogue catalogue) {
		try {
			ResultSet rs;
			Catalogue cat;
			pst = cn.prepareStatement("SELECT COUNT(*) FROM Produit WHERE catalogue IN (SELECT idCatalogue FROM Catalogue WHERE nomCatalogue = ?) ");
			pst.setString(1, catalogue.getNom());
			rs = pst.executeQuery();
			if ( rs.next() ){
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

}
