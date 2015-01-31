package persistence;

public class ProduitDAOFactory {
	
	public static final int ORACLE = 10;
	public static final int XML = 20;
	public static final int MYSQL = 30;
	
	public static I_ProduitDAO createProduitDAO(int bdType){
		switch (bdType) {
		
		case ORACLE:
			return new ProduitDAO_Oracle();
			
		case XML:
			return new ProduitDAO_XMLAdapter();
			
		case MYSQL:
			return new ProduitDAO_Mysql();
		default:
			return null;
		}
	}
}
