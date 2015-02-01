package persistence;

public class CatalogueDAOFactory {
	
	public static final int ORACLE = 10;
	public static final int MYSQL = 30;
	
	public static I_CatalogueDAO createCatalogueDAO(int bdType){
		switch (bdType) {
		
		case ORACLE:
			return new CatalogueDAO_Oracle();
		case MYSQL:
			return new CatalogueDAO_Mysql();
		default:
			return null;
		}
	}

}
