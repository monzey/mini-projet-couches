package persistence;

public class MysqlDAOFactory extends DAOAbstractFactory {

	public MysqlDAOFactory() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public I_CatalogueDAO createCatalogueDAO() {
		return CatalogueDAOFactory.createCatalogueDAO(CatalogueDAOFactory.MYSQL);
	}

	@Override
	public I_ProduitDAO createProduitDAO() {
		return ProduitDAOFactory.createProduitDAO(ProduitDAOFactory.MYSQL);
	}

}
