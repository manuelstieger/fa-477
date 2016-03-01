package ch.csbe.ifa.partei.dbo;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ch.csbe.ifa.partei.dao.Database;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DatabaseTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		//Database.getInstance().closeConnection();
		System.out.println("Datenbank geschlossen");
	}

	@Test
	public void testAGetInstance() {
		assertNotNull(Database.getInstance());
	}

	@Test
	public void testBOpenSession() {
		Database.getInstance().openSession();
		Session s = Database.getInstance().getSession();
		assertTrue(s.isOpen());
	}

	@Test
	public void testCGetSession() {
		Database.getInstance().openSession();
		Session s = Database.getInstance().getSession();
		assertNotNull(s);
	}

	@Test
	public void testDCloseSession() {
		Database.getInstance().closeSession();
		assertFalse(Database.getInstance().getSession().isOpen());
	}

	@Test
	public void testECloseConnection() {
		Session s = Database.getInstance().getSession();
		Database.getInstance().closeConnection();
		assertFalse(s.isConnected());
	}

}
