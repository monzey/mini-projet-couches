package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controllers.CtrCatalogue;
import controllers.CtrPrincipal;
import entities.Catalogue;

public class CtrPrincipalTest {

	private CtrPrincipal ctr;
	
	@Before
	public void setUp() throws Exception {
		this.ctr = new CtrPrincipal();
		CtrCatalogue ctrCatalogue = new CtrCatalogue(new Catalogue("La Redoute"));
	}

	@Test
	public void testCatalogue() {
		assertNotNull("catalogues créés", this.ctr.getCatalogues());
	}
	

}
