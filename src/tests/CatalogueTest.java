package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.omg.PortableInterceptor.SUCCESSFUL;

import entities.*;

public class CatalogueTest {

	I_Catalogue cat;
	
	@Before
	public void setUp() {
		cat = new Catalogue();
//		Si votre Catalogue est un Singleton, il faut changer la ligne pr�c�dente puis vider le Catalogue avec la m�thode clear() comme indiqu� � la ligne suivante
//		cat.clear();
	}
	
	@Test
	public void testCatalogue() {
		assertNotNull("créer catalogue", cat);
	}

	@Test
	public void testAddProduitIProduit_null() {
		I_Produit p0 = null;
		assertFalse("ajout produit null", cat.addProduit(p0));
	}
	
	@Test
	public void testAddProduitIProduit_premierProduit() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		assertTrue("ajout premier produit", cat.addProduit(p1));
	}
	
	@Test
	public void testAddProduitIProduit_secondProduit() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Treets", 10, 1);
		assertTrue("ajout autre produit", cat.addProduit(p2));
	}
	
	@Test
	public void testAddProduitIProduit_deuxFoisMemeProduitFin() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Treets", 10, 1);
		cat.addProduit(p2);
		assertFalse("ajout deux fois même produit fin", cat.addProduit(p2));
	}
	
	@Test
	public void testAddProduitIProduit_deuxFoisMemeProduitDebut() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Treets", 10, 1);
		cat.addProduit(p2);
		assertFalse("ajout deux fois même produit début", cat.addProduit(p1));
	}
	
	@Test
	public void testAddProduitIProduit_deuxFoisProduitMemeNomFin() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Treets", 10, 1);
		cat.addProduit(p2);
		I_Produit p3 = createProduit("Treets", 15, 2);
		assertFalse("ajout deux produit même nom fin", cat.addProduit(p3));
	}
	
	@Test
	public void testAddProduitIProduit_deuxFoisProduitMemeNomDebut() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Treets", 10, 1);
		cat.addProduit(p2);
		I_Produit p3 = createProduit("Mars", 15, 2);
		assertFalse("ajout deux produit même nom debut", cat.addProduit(p3));
	}
	
	@Test
	public void testAddProduitIProduit_stockNegatif() {
		I_Produit p4 = createProduit("Raider", 10, -1);
		assertFalse("ajout produit stock négatif", cat.addProduit(p4));
	}

	@Test
	public void testAddProduitIProduit_stockNul() {
		I_Produit p7 = createProduit("Snickers", 1, 0);
		assertTrue("ajout produit stock nul", cat.addProduit(p7));
	}
	
	@Test
	public void testAddProduitIProduit_prixNul() {
		I_Produit p5 = createProduit("Lion", 0, 3);
		assertFalse("ajout produit prix nul", cat.addProduit(p5));
	}	
		
	@Test
	public void testAddProduitIProduit_prixNegatif() {
		I_Produit p6 = createProduit("Bounty", -5, 4);
		assertFalse("ajout produit prix négatif", cat.addProduit(p6));
	}	

	
	@Test
	public void testAddProduitStringDoubleInt_premierProduit() {
		assertTrue("ajout premier produit", cat.addProduit("Mars", 10, 1));
	}
	
	@Test
	public void testAddProduitStringDoubleInt_secondProduit() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		assertTrue("ajout autre produit", cat.addProduit("Treets", 10, 1));
	}
	
	@Test
	public void testAddProduitStringDoubleInt_deuxFoisMemeNomFin() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Treets", 10, 1);
		cat.addProduit(p2);
		assertFalse("ajout deux fois même produit fin", cat.addProduit("Treets", 10, 1));
	}
	
	@Test
	public void testAddProduitStringDoubleInt_deuxFoisMemeNomDebut() {
		I_Produit p1 = createProduit("Mars", 10, 1);
		cat.addProduit(p1);
		I_Produit p2 = createProduit("Treets", 10, 1);
		cat.addProduit(p2);
		assertFalse("ajout deux fois même produit debut", cat.addProduit("Mars", 10, 1));
	}
	
	@Test
	public void testAddProduitStringDoubleInt_stockNegatif() {
		assertFalse("ajout produit stock négatif", cat.addProduit("Raider", 10, -1));
	}

	@Test
	public void testAddProduitStringDoubleInt_stockNul() {
		assertTrue("ajout produit stock nul", cat.addProduit("Snickers", 1, 0));
	}

	@Test
	public void testAddProduitStringDoubleInt_prixNul() {
		assertFalse("ajout produit prix nul", cat.addProduit("Lion", 0, 3));
	}	
	
	@Test
	public void testAddProduitStringDoubleInt_prixNegatif() {
		assertFalse("ajout produit prix n�gatif", cat.addProduit("Bounty", -5, 4));
	}	

	@Test
	public void testAddProduits_null() {
		List<I_Produit> l0 = null;
		assertEquals("ajout liste nulle", 0, cat.addProduits(l0));
	}
	
	@Test
	public void testAddProduits_vide() {
		List<I_Produit> l1 = new ArrayList<I_Produit>();
		assertEquals("ajout liste vide", 0, cat.addProduits(l1));
	}	

	@Test
	public void testAddProduits_produitsSansDoublonsAvecCatalogueVide() {
		List<I_Produit> l2 = new ArrayList<I_Produit>();
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		l2.add(p1);
		l2.add(p2);
		assertEquals("ajout liste pleine",2, cat.addProduits(l2));
	}	
	
	@Test
	public void testAddProduits_produitsSansDoublonsAvecCatalogueDejaRempli() {
		I_Produit p4 = createProduit("Twix", 10, 6);
		cat.addProduit(p4);
		List<I_Produit> l2 = new ArrayList<I_Produit>();
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		l2.add(p1);
		l2.add(p2);
		assertEquals("ajout liste pleine",2, cat.addProduits(l2));
	}
	
	@Test
	public void testAddProduits_produitsAvecDoublons() {
		I_Produit p1 = createProduit("Mars", 10, 4);
		I_Produit p2 = createProduit("Treets", 11, 2);
		cat.addProduit(p1);
		cat.addProduit(p2);
		List<I_Produit> l3 = new ArrayList<I_Produit>();
		I_Produit p3 = createProduit("Mars", 15, 2);
		I_Produit p4 = createProduit("Twix", 10, 6);
		I_Produit p5 = createProduit("M&M's", 8, 1);
		I_Produit p6 = createProduit("Bounty", 4, 2);
		l3.add(p3);
		l3.add(p4);
		l3.add(p5);
		l3.add(p6);
		l3.add(p2);
		l3.add(p4);
		assertEquals("ajout liste avec doublons",3, cat.addProduits(l3));
	}
		
	@Test
	public void testAddProduits_avecStocksNegatifs() {
		List<I_Produit> l4 = new ArrayList<I_Produit>();
		I_Produit p3 = createProduit("Mars", 15, 2);
		I_Produit p8 = createProduit("Kit Kat", 8, -3);
		I_Produit p9 = createProduit("Lion", 4, 6);
		l4.add(p3);
		l4.add(p8);
		l4.add(p9);
		assertEquals("ajout liste avec stock négatif",2, cat.addProduits(l4));
	}
		
	@Test
	public void testAddProduits_avecStocksNull() {
		List<I_Produit> l4 = new ArrayList<I_Produit>();
		I_Produit p3 = createProduit("Mars", 15, 2);
		I_Produit p7 = createProduit("Snickers", 1, 0);
		I_Produit p9 = createProduit("Lion", 4, 6);
		l4.add(p3);
		l4.add(p7);
		l4.add(p9);
		assertEquals("ajout liste avec stock nul",3, cat.addProduits(l4));
	}
	
	@Test
	public void testAddProduits_avecPrixNul() {
		List<I_Produit> l5 = new ArrayList<I_Produit>();
		I_Produit p10 = createProduit("Nuts", 0, 1);
		l5.add(p10);
		assertEquals("ajout liste avec prix nul",0, cat.addProduits(l5));
	}
		
	@Test
	public void testAddProduits_avecPrixNegatif() {
		List<I_Produit> l6 = new ArrayList<I_Produit>();
		I_Produit p11 = createProduit("Topset", -8, 3);
		I_Produit p12 = createProduit("Nuts", 4, 6);
		l6.add(p11);
		l6.add(p12);
		assertEquals("ajout liste avec prix n�gatif",1, cat.addProduits(l6));
	}

	@Test
	public void testRemoveProduit_existe() {
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Treets", 10, 1);
		cat.addProduit("Raider", 12, 2);
		//assertEquals(3, cat.getNomProduits().length);
		assertTrue("suppression produit existant", cat.removeProduit("Mars"));
		//assertEquals(2, cat.getNomProduits().length);
	}	
	
	@Test
	public void testRemoveProduit_existePas() {
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Treets", 10, 1);
		cat.addProduit("Raider", 12, 2);
		//assertEquals(3, cat.getNomProduits().length);
		assertFalse("suppression produit qui n'existe pas", cat.removeProduit("Lion"));
		//assertEquals(3, cat.getNomProduits().length);
	}
		
	@Test
	public void testRemoveProduit_null() {
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Treets", 10, 1);
		cat.addProduit("Raider", 12, 2);
		//assertEquals(3, cat.getNomProduits().length);
		assertFalse("suppression avec un nom null", cat.removeProduit(null));
		//assertEquals(2, cat.getNomProduits().length);
	}	
		
	@Test
	public void testAcheterProduit_existePas() {
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Treets", 10, 1);
		cat.addProduit("Raider", 12, 2);
		assertFalse("acheter produit qui n'existe pas", cat.acheterStock("Nuts", 3));
	}
	
	@Test
	public void testAcheterProduit_existe() {
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Treets", 10, 1);
		cat.addProduit("Raider", 12, 2);
		assertTrue("acheter produit qui existe", cat.acheterStock("Raider", 3));
	}
		
	@Test
	public void testAcheterProduit_quantiteNegative() {
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Treets", 10, 1);
		cat.addProduit("Raider", 12, 2);
		assertFalse("acheter quantit� n�gative", cat.acheterStock("Mars", -4));
	}	
		
	@Test
	public void testAcheterProduit_quantiteNulle() {
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Treets", 10, 1);
		cat.addProduit("Raider", 12, 2);
		assertFalse("acheter quantit� nulle", cat.acheterStock("Treets", 0));
	}	

	@Test
	public void testVendreProduit_existePas() {
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 0);
		cat.addProduit("Raider", 12, 3);
		assertFalse("vendre produit qui n'existe pas", cat.vendreStock("Nuts", 3));
	}	
	
	@Test
	public void testVendreProduit_existe() {
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 0);
		cat.addProduit("Raider", 12, 3);
		assertTrue("vendre produit qui existe", cat.vendreStock("Raider", 1));
	}

	@Test
	public void testVendreProduit_quantiteNegative() {
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 0);
		cat.addProduit("Raider", 12, 3);
		assertFalse("vendre quantit� n�gative", cat.vendreStock("Mars", -4));
	}	
	
	@Test
	public void testVendreProduit_quantiteNulle() {
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 0);
		cat.addProduit("Raider", 12, 3);
		assertFalse("vendre quantit� nulle", cat.vendreStock("Treets", 0));
	}
	
	@Test
	public void testVendreProduit_stockNul() {
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 0);
		cat.addProduit("Raider", 12, 3);
		assertFalse("vendre produit sans stock, cat.vendreProduit", cat.vendreStock("Treets", 4));
	}
	
	@Test
	public void testVendreProduit_stockInsuffisant() {
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 0);
		cat.addProduit("Raider", 12, 3);
		assertFalse("vendre produit pas suffisament stock", cat.vendreStock("Raider", 10));
	}
	
	@Test
	public void testGetNomProduits_vide() {
		String[] tab0 = new String[0];
		assertArrayEquals("recup�re noms produits catalogue vide", tab0, cat.getNomProduits());
	}
	
	@Test
	public void testGetNomProduits_unProduit() {
		String[] tab1 = {"Mars"};
		cat.addProduit("Mars", 10, 1);
		assertArrayEquals("recup�re nom d'un seul produit", tab1, cat.getNomProduits());
	}
	
	@Test
	public void testGetNomProduits_deuxProduits() {
		String[] tab2 = {"Mars", "Treets"};
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Treets", 10, 1);
		assertArrayEquals("recup�re nom de deux produits", tab2, cat.getNomProduits());
	}
		
	@Test
	public void testGetNomProduits_plusieursProduitsInseresOrdreAlphabetique() {
		String[] tab3 = {"Mars", "Raider", "Treets"};
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Raider", 12, 2);
		cat.addProduit("Treets", 10, 1);
		assertArrayEquals("recup�re nom de plusieurs produits ajout�s dans ordre alphab�tique", tab3, cat.getNomProduits());
	}	
		
	@Test
	public void testGetNomProduits_plusieursProduitsInseresOrdreAleatoire() {
		String[] tab4 = {"Bounty", "Mars", "Raider", "Treets"};
		cat.addProduit("Mars", 10, 1);
		cat.addProduit("Treets", 10, 1);
		cat.addProduit("Raider", 12, 2);
		cat.addProduit("Bounty", 12, 2);
		assertArrayEquals("recup�re nom de plusieurs produits ajout�s dans ordre al�atoire", tab4, cat.getNomProduits());
	}
	
	@Test
	public void testMontantTotalTTC_catalogueVide() {
		assertEquals("montant TTC catalogue vide",0,cat.getMontantTotalTTC(),0);
	}
	
	@Test
	public void testMontantTotalTTC_pasDeStock() {
		cat.addProduit("Nuts", 1, 0);
		assertEquals("montant TTC sans stock",0,cat.getMontantTotalTTC(),0);
	}
	
	@Test
	public void testMontantTotalTTC_sansVirgule() {
		cat.addProduit("Mars", 100, 4);
		cat.addProduit("Raider", 20, 5);
		assertEquals("montant TTC sans virgule ",600,cat.getMontantTotalTTC(),0);
	}

	@Test
	public void testMontantTotalTTC_avecVirgule_SansArrondi_UnChiffreApresLaVirgule() {
		cat.addProduit("Mars", 10, 6);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 12);
		assertEquals("montant TTC avec virgule ",134.4,cat.getMontantTotalTTC(),0);
	}

	@Test
	public void testMontantTotalTTC_avecVirgule_SansArrondi_DeuxChiffresApresLaVirgule() {
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 10);
		cat.addProduit("Twix", 12.6, 1);
		assertEquals("montant TTC avec virgule ",135.12,cat.getMontantTotalTTC(),0);
	}

	@Test
	public void testMontantTotalTTC_avecVirgule_AvecArrondiInferieur_TroisChiffresApresLaVirgule() {
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 10);
		cat.addProduit("Twix", 12.66, 1);
		assertEquals("montant TTC avec virgule ; 135.192 doit �tre arrondi � 135.19",135.19,cat.getMontantTotalTTC(),0);
	}

	@Test
	public void testMontantTotalTTC_avecVirgule_AvecArrondiSuperieur_TroisChiffresApresLaVirgule() {
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 10);
		cat.addProduit("Twix", 12.69, 1);
		assertEquals("montant TTC avec virgule ; 135.228 doit �tre arrondi � 135.23",135.23,cat.getMontantTotalTTC(),0);
	}

	@Test
	public void testMontantTotalTTC_avecVirgule_AvecArrondiSuperieur_TroisChiffresApresLaVirgule_IlNeFautPasArrondirLePrixDuStockUnitaireMaisLePrixDuStockTotal() {
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 10);
		cat.addProduit("Twix", 12.67, 1);
		cat.addProduit("Nuts", 12.67, 1);
		assertEquals("c'est le montant total TTC qu'il faut arrondir, pas les prix TTC des diff�rents produits",150.41,cat.getMontantTotalTTC(),0);
	}
	
	@Test
	public void testToString_CatalogueVide() {
		String resultatAttendu = "\n" +
								 "Montant total TTC du stock : 0,00 €";
		assertEquals("toString catalogue vide", resultatAttendu, cat.toString());
	}
	
	@Test	
	public void testToString_CatalogueAvecDesProduits_TotalAvecAucunChiffreApresVirgule() {
		String resultatAttendu = "Mars - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 5" + "\n" +
								 "Treets - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 4" + "\n" +
								 "Raider - prix HT : 1,00 € - prix TTC : 1,20 € - quantité en stock : 10" + "\n" +
								 "\n" +
								 "Montant total TTC du stock : 120,00 €";
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 10);
		assertEquals("toString catalogue sans virgule", resultatAttendu, cat.toString());
	}
	
	@Test
	public void testToString_CatalogueAvecDesProduits_TotalAvecUnChiffreApresVirgule() {
		String resultatAttendu = "Mars - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 5" + "\n" +
								 "Treets - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 4" + "\n" +
								 "Raider - prix HT : 1,00 € - prix TTC : 1,20 € - quantité en stock : 10" + "\n" +
								 "Twix - prix HT : 10,45 € - prix TTC : 12,54 € - quantité en stock : 5" + "\n" +
								 "\n" +
								 "Montant total TTC du stock : 182,70 €";
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 10);
		cat.addProduit("Twix", 10.45, 5);
		assertEquals("toString catalogue avec un total d'un chiffre aprếs la virgule", resultatAttendu, cat.toString());
	}
	
	@Test
	public void testToString_CatalogueAvecDesProduits_TotalAvecDeuxChiffresApresVirgule() {
		String resultatAttendu = "Mars - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 5" + "\n" +
								 "Treets - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 4" + "\n" +
								 "Raider - prix HT : 1,00 € - prix TTC : 1,20 € - quantité en stock : 10" + "\n" +
								 "Twix - prix HT : 10,40 € - prix TTC : 12,48 € - quantité en stock : 1" + "\n" +
								 "\n" +
								 "Montant total TTC du stock : 132,48 €";
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 10);
		cat.addProduit("Twix", 10.4, 1);
		assertEquals("toString catalogue avec un total de deux chiffres apr�s virgule", resultatAttendu, cat.toString());
	}

	
	@Test
	public void testToString_CatalogueAvecDesProduits_TotalAvecTroisChiffresApresVirguleArrondiInferieur() {
		String resultatAttendu = "Mars - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 5" + "\n" +
								 "Treets - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 4" + "\n" +
								 "Raider - prix HT : 1,00 € - prix TTC : 1,20 € - quantité en stock : 10" + "\n" +
								 "Twix - prix HT : 10,47 € - prix TTC : 12,56 € - quantité en stock : 1" + "\n" +
								 "\n" +
								 "Montant total TTC du stock : 132,56 €";
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 10);
		cat.addProduit("Twix", 10.47, 1);
		assertEquals("on affiche que deux chiffres après la virgule dans le prix unitaires TTC, mais le montant total TTC du catalogue est calcul� avec les prix unitaires TTC non arrondis",resultatAttendu, cat.toString());
	}
	
	@Test
	public void testToString_CatalogueAvecDesProduits_TotalAvecTroisChiffresApresVirguleArrondiSuperieur() {
		String resultatAttendu = "Mars - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 5" + "\n" +
								 "Treets - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 4" + "\n" +
								 "Raider - prix HT : 1,00 € - prix TTC : 1,20 € - quantité en stock : 10" + "\n" +
								 "Twix - prix HT : 10,47 € - prix TTC : 12,56 € - quantité en stock : 2" + "\n" +
								 "\n" +
								 "Montant total TTC du stock : 145,13 €";
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 4);
		cat.addProduit("Raider", 1, 10);
		cat.addProduit("Twix", 10.47, 2);
		assertEquals("on affiche que deux chiffres après la virgule dans le prix unitaires TTC, mais le montant total TTC du catalogue est calcul� avec les prix unitaires TTC non arrondis",resultatAttendu, cat.toString());
	}
	
	@Test
	public void testClear() {
		cat.addProduit("Mars", 10, 5);
		cat.addProduit("Treets", 10, 4);
		cat.clear();
		assertNull(cat);
	}

	private I_Produit createProduit(String nom, double prixHT, int quantite) {
		try {
			return new Produit(nom,prixHT,quantite);
		}
		catch (Exception e) { return null; }
	}		
		
	
}
