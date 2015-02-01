package persistence;

public abstract class DAOAbstractFactory {

	private static DAOAbstractFactory instance;
	
	public synchronized static DAOAbstractFactory getInstance(){
		if (instance == null){
			return new MysqlDAOFactory();
		} else return instance;
	}
	
	public abstract I_CatalogueDAO createCatalogueDAO();
	public abstract I_ProduitDAO createProduitDAO();

}
