package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import flight.FlightSearch;

class FlightSearchTest {
	
	FlightSearch fs = null;
	@BeforeEach
	void createFlightSearch() {
		fs = new FlightSearch();
	}
	
	@After
	void tearDown() {
		fs = null;
	}
	
	@Test
	void testValidCases() {
		assertTrue(fs.runFlightSearch("12/12/2025", "mel", true, "13/12/2025", "cdg", "economy", 9, 0, 0));
		assertTrue(fs.runFlightSearch("12/12/2025", "mel", false, "13/12/2025", "cdg", "business", 1, 2, 0));
		assertTrue(fs.runFlightSearch("12/12/2025", "mel", false, "13/12/2025", "cdg", "first", 4, 0, 4));
		assertTrue(fs.runFlightSearch("12/12/2025", "DOH", false, "16/12/2025", "PVG", "Premium Economy", 4, 2, 3));
		assertTrue(fs.runFlightSearch("10/11/2025", "DOH", false, "13/12/2025", "PVG", "premium economy", 4, 2, 3));
	}

}
