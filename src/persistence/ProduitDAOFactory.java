package persistence;

public class ProduitDAOFactory {
	
	static final int ORACLE = 10;
	static final int XML = 20;
	
	public static I_ProduitDAO getProduitDAO(int bdType){
		switch (bdType) {
		
		case ORACLE:
			return new ProduitDAO_Oracle();
			
		case XML:
			return new ProduitDAO_XML();
			
		default:
			return null;
		}
	}
}
