package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import flight.FlightSearch;

class FlightSearchTest {
	// all cases valid
	@Test
	void allValid() {
		FlightSearch fs = new FlightSearch();
		assertTrue(fs.runFlightSearch("12/12/2025", "mel", true, "13/12/2025", "cdg", "economy", 9, 0, 0));
		assertEquals("12/12/2025", fs.getDepartureDate());
		assertEquals("mel", fs.getDepartureAirportCode());
		assertEquals(true, fs.getEmergencyRowSeating());
		assertEquals("13/12/2025", fs.getReturnDate());
		assertEquals("cdg", fs.getDestinationAirportCode());
		assertEquals("economy", fs.getSeatingClass());
		assertEquals(9, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
		
		fs = new FlightSearch();
		assertTrue(fs.runFlightSearch("12/12/2025", "mel", false, "13/12/2025", "cdg", "business", 1, 2, 0));
		assertEquals("12/12/2025", fs.getDepartureDate());
		assertEquals("mel", fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals("13/12/2025", fs.getReturnDate());
		assertEquals("cdg", fs.getDestinationAirportCode());
		assertEquals("business", fs.getSeatingClass());
		assertEquals(1, fs.getAdultPassengerCount());
		assertEquals(2, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
		
		fs = new FlightSearch();
		assertTrue(fs.runFlightSearch("12/12/2025", "mel", false, "13/12/2025", "cdg", "first", 4, 0, 4));
		assertEquals("12/12/2025", fs.getDepartureDate());
		assertEquals("mel", fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals("13/12/2025", fs.getReturnDate());
		assertEquals("cdg", fs.getDestinationAirportCode());
		assertEquals("first", fs.getSeatingClass());
		assertEquals(4, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(4, fs.getInfantPassengerCount());
		
		fs = new FlightSearch();
		assertTrue(fs.runFlightSearch("12/12/2025", "DOH", false, "16/12/2025", "PVG", "Premium Economy", 4, 2, 3));
		assertEquals("12/12/2025", fs.getDepartureDate());
		assertEquals("DOH", fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals("16/12/2025", fs.getReturnDate());
		assertEquals("PVG", fs.getDestinationAirportCode());
		assertEquals("Premium Economy", fs.getSeatingClass());
		assertEquals(4, fs.getAdultPassengerCount());
		assertEquals(2, fs.getChildPassengerCount());
		assertEquals(3, fs.getInfantPassengerCount());
		
		fs = new FlightSearch();
		assertTrue(fs.runFlightSearch("10/11/2025", "DOH", false, "13/12/2025", "PVG", "premium economy", 4, 2, 3));
		assertEquals("10/11/2025", fs.getDepartureDate());
		assertEquals("DOH", fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals("13/12/2025", fs.getReturnDate());
		assertEquals("PVG", fs.getDestinationAirportCode());
		assertEquals("premium economy", fs.getSeatingClass());
		assertEquals(4, fs.getAdultPassengerCount());
		assertEquals(2, fs.getChildPassengerCount());
		assertEquals(3, fs.getInfantPassengerCount());
	}
	
	// passenger count under 1
	@Test
	void passengerMinimumNotMet() {
		FlightSearch fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("12/12/2025", "mel", true, "13/12/2025", "cdg", "economy", 0, 0, 0));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
		
		fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("12/12/2025", "mel", true, "13/12/2025", "cdg", "economy", -3, 0, 0));
		assertFalse(fs.runFlightSearch("12/12/2025", "mel", true, "13/12/2025", "cdg", "economy", 0, 0, 0));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
	}
	
	// passenger count over 9
	@Test
	void passengerOverMax() {
		FlightSearch fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("12/12/2025", "mel", false, "13/12/2025", "cdg", "economy", 6, 3, 1));
		assertFalse(fs.runFlightSearch("12/12/2025", "mel", true, "13/12/2025", "cdg", "economy", 0, 0, 0));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
		
		fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("12/12/2025", "mel", false, "13/12/2025", "cdg", "economy", 10, 3, 1));
		assertFalse(fs.runFlightSearch("12/12/2025", "mel", true, "13/12/2025", "cdg", "economy", 0, 0, 0));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
	}
	
	// children in invalid seating
	@Test
	void childrenInvalidSeats() {
		FlightSearch fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("12/12/2025", "mel", true, "13/12/2025", "cdg", "economy", 6, 3, 0));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
		
		fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("12/12/2025", "mel", false, "13/12/2025", "cdg", "first", 6, 3, 0));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
	}
	
	// testing infants in invalid seating
	@Test
	void InfantsInvalidSeats() {
		FlightSearch fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("12/12/2025", "mel", true, "13/12/2025", "cdg", "economy", 4, 0, 3));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
		
		fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("12/12/2025", "mel", false, "13/12/2025", "cdg", "business", 4, 0, 3));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
	}
	
	// testing more than 2 children per adult
	@Test
	void notEnoughAdultsToChildren() {
		FlightSearch fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("12/12/2025", "mel", false, "13/12/2025", "cdg", "business", 1, 3, 0));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
		
		fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("12/12/2025", "mel", false, "13/12/2025", "cdg", "business", 2, 6, 0));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
	}
	
	// testing more infants than adults
	@Test
	void notEnoughAdultsToInfants() {
		FlightSearch fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("12/12/2025", "mel", false, "13/12/2025", "cdg", "business", 0, 0, 1));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
		
		fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("12/12/2025", "mel", false, "13/12/2025", "cdg", "business", 4, 0, 5));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
	}
	
	// departure date in the past
	@Test
	void pastDepartureDate() {
		FlightSearch fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("15/10/2025", "mel", false, "10/11/2025", "lax", "premium economy", 3, 1, 2));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
		
		fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("10/12/2022", "mel", false, "10/11/2025", "lax", "premium economy", 3, 1, 2));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
	}
	
	// incorrect date format
	@Test
	void incorrectDateFormat() {
		// tests leap year, date that doesnt exist, dashes and 26 vs 2026
		FlightSearch fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("29/02/2026", "mel", false, "04/03/2026", "lax", "premium economy", 3, 1, 2));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
		
		fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("10/01/2026", "mel", false, "32/01/2026", "lax", "premium economy", 3, 1, 2));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
		
		fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("20-12-2025", "mel", false, "31/01/2026", "lax", "premium economy", 3, 1, 2));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
		
		fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("20/12/2025", "mel", false, "20/04/26", "lax", "premium economy", 3, 1, 2));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
	}
	
	// return date before departure date
	@Test
	void returnBeforeDeparture() {
		FlightSearch fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("20/01/2026", "mel", false, "19/01/2026", "lax", "premium economy", 3, 1, 2));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
		
		fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("25/04/2027", "mel", false, "10/03/2026", "lax", "premium economy", 3, 1, 2));
	}
	
	// testing what happens if not two way
	@Test
	void twoWayTest() {
		FlightSearch fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("12/12/2025", "mel", false, "13/12/2025", null, "economy", 4, 1, 2));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
		
		fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("12/12/2025", null, false, "13/12/2025", "cdg", "economy", 4, 1, 2));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
		
		fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("12/12/2025", "", false, "13/12/2025", "cdg", "economy", 4, 1, 2));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
	}
	
	// testing invalid seating class names
	@Test
	void invalidSeatingClasses() {
		FlightSearch fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("12/12/2025", "mel", false, "13/12/2025", "cdg", "premiumeconomy", 4, 1, 2));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
		
		fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("12/12/2025", "mel", false, "13/12/2025", "cdg", "econmy", 4, 1, 2));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
	}
	
	// testing emergency row on non economy flights
	@Test
	void emergencyRowNonEconomy() {
		FlightSearch fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("12/12/2025", "mel", true, "13/12/2025", "cdg", "first", 4, 0, 0));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
		
		fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("12/12/2025", "mel", true, "13/12/2025", "cdg", "premium economy", 4, 0, 0));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
		
		fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("12/12/2025", "mel", true, "13/12/2025", "cdg", "business", 4, 0, 0));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
	}
	
	// testing non valid airport codes
	@Test
	void invalidAirportCodes() {
		FlightSearch fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("12/12/2025", "sid", false, "13/12/2025", "mel", "first", 4, 0, 4));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
		
		fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("12/12/2025", "pvg", false, "13/12/2025", "lex", "first", 4, 0, 4));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
	}
	
	// testing if plane departs and lands in same spot
	@Test
	void sameDestinationDeparture() {
		FlightSearch fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("12/12/2025", "mel", false, "13/12/2025", "mel", "first", 4, 0, 4));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
		
		fs = new FlightSearch();
		assertFalse(fs.runFlightSearch("12/12/2025", "cdg", false, "13/12/2025", "CDG", "premium economy", 4, 1, 4));
		assertEquals(null, fs.getDepartureDate());
		assertEquals(null, fs.getDepartureAirportCode());
		assertEquals(false, fs.getEmergencyRowSeating());
		assertEquals(null, fs.getReturnDate());
		assertEquals(null, fs.getDestinationAirportCode());
		assertEquals(null, fs.getSeatingClass());
		assertEquals(0, fs.getAdultPassengerCount());
		assertEquals(0, fs.getChildPassengerCount());
		assertEquals(0, fs.getInfantPassengerCount());
	}
	
	
	
	
}
