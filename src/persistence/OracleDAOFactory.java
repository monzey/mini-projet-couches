package persistence;

public class OracleDAOFactory extends DAOAbstractFactory {

	public OracleDAOFactory() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public I_CatalogueDAO createCatalogueDAO() {
		return CatalogueDAOFactory.createCatalogueDAO(CatalogueDAOFactory.ORACLE);
	}

	@Override
	public I_ProduitDAO createProduitDAO() {
		return ProduitDAOFactory.createProduitDAO(ProduitDAOFactory.MYSQL);
	}

}
