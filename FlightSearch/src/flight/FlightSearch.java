package flight;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlightSearch {
   private String  departureDate;
   private String  departureAirportCode;
   private boolean emergencyRowSeating;
   private String  returnDate;
   private String  destinationAirportCode; 
   private String  seatingClass;
   private int     adultPassengerCount;
   private int     childPassengerCount;
   private int     infantPassengerCount;
   
   public boolean runFlightSearch(String departureDate,    String departureAirportCode,   boolean emergencyRowSeating, 
                                  String returnDate,       String destinationAirportCode, String seatingClass, 
                                  int adultPassengerCount, int childPassengerCount,       int infantPassengerCount) {
      boolean valid = true;
      
      // first condition, 1-9 inclusive passengers only
      valid = validPassengerCount(adultPassengerCount, childPassengerCount, infantPassengerCount);
    
      // second condition, children cant be in emergency row or first class, assuming first class has a space
      valid = valid && childrenInValidAreas(childPassengerCount, emergencyRowSeating, seatingClass);
     
      // third condition
      valid = valid && infantsInValidAreas(infantPassengerCount, emergencyRowSeating, seatingClass);
    
      // fourth condition
      valid = valid && childrenHaveAnAdult(adultPassengerCount, childPassengerCount);
   
      // 5th condition 
      valid = valid && infantHaveAnAdult(adultPassengerCount, infantPassengerCount);
  
      // 6th and 7th and partial 8th condition
      try {
    	  valid = valid && validDate(departureDate, returnDate);
      }
      catch (ParseException e) {
    	  valid = false;
    	  System.err.println("could not parse one or more dates");
      }
  
      // 9th condition
      valid = valid && validClassSeating(seatingClass);
   
      // 10th condition
      valid = valid && economyEmergencyOnly(seatingClass, emergencyRowSeating);
    
      // 11th and partial 8th condition
      valid = valid && airportValidation(departureAirportCode, destinationAirportCode);
     
      return valid;
   }
   
   // 1st. returns true if between 1-9
   	private boolean validPassengerCount(int adultPassengerCount, int childPassengerCount,int infantPassengerCount) {
   		boolean valid = true;
   		int totalPassengers = adultPassengerCount + childPassengerCount + infantPassengerCount;
   		
        if (totalPassengers < 1 || totalPassengers > 9) {
      	  valid = false;
        }
        
        return valid;
   	}
   	
   	// 2nd. checks if children are in valid areas
   	private boolean childrenInValidAreas(int childPassengerCount, boolean emergencyRowSeating, String seatingClass) {
   		boolean valid = true;
   		
   		// criteria doesnt mention whether seating class is formatted as "f c", "f_c" or "fc" so assuming it is "f c"
	   	if (childPassengerCount > 0 && (emergencyRowSeating || seatingClass.toLowerCase().equals("first class"))) {
	   	  valid = false;
	    }
   		
   		return valid;
   	}
   	
   	// 3rd. checks if infants are in valid areas
   	private boolean infantsInValidAreas(int infantPassengerCount, boolean emergencyRowSeating, String seatingClass) {
   		boolean valid = true;
   		
   		// criteria doesnt mention whether seating class is formatted as "f c", "f_c" or "fc" so assuming it is "f c"
	   	if (infantPassengerCount > 0 && (emergencyRowSeating || seatingClass.toLowerCase().equals("business class"))) {
	   	  valid = false;
	    }
   		
   		return valid;
   	}
   	
   	// 4th. checks if children are next to an adult
   	private boolean childrenHaveAnAdult(int adultPassengerCount, int childPassengerCount) {
   		boolean valid = false;
   		
   		if (adultPassengerCount >= (2 * childPassengerCount)) {
   			valid = true;
   		}
   		
   		return valid;
   	}
   	
   	// 5th. checks if children are next to an adult
   	private boolean infantHaveAnAdult(int adultPassengerCount, int infantPassengerCount) {
   		boolean valid = false;
   		
   		if (adultPassengerCount >= infantPassengerCount) {
   			valid = true;
   		}
   		
   		return valid;
   	}
   	
   	// 6th and 7th and partial 8th. date validation
   	private boolean validDate(String departureDateString, String returnDateString) throws ParseException {
   		boolean valid = true;
   		
		// set format
   		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
   		format.setLenient(false);
   		
   		// current date to string
   		LocalDate localDate = LocalDate.now();
   		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
   		String formattedString = localDate.format(formatter);
   		
   		Date currDate = format.parse(formattedString);
		
   		Date departureDate = format.parse(departureDateString);
   		Date returnDate = format.parse(returnDateString);
		
   		long diffInMillies = departureDate.getTime() - currDate.getTime();
   	    long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
   		
   	    if (diffInDays < 0) {
   	    	valid = false;
   	    }
   	    
   	    diffInMillies = returnDate.getTime() - departureDate.getTime();
   	    diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
   	    if (diffInDays < 0) {
	    	valid = false;
	    }

   	    return valid;
   	}
   	
   	// 9th. seating class validation
   	private boolean validClassSeating(String seatingClass) {
   		boolean valid = false;
   		List<String> validClasses = Arrays.asList("economy", "premium economy", "business", "first");
   		
   		for (String seat : validClasses) {
   			if (seatingClass.toLowerCase().equals(seat)) {
   				valid = true;
   			}
   		}
   		
   		return valid;
   	}
   	
   	// 10th condition 
   	private boolean economyEmergencyOnly(String seatingClass, boolean emergencyRowSeating) {
   		boolean valid = true;
   		
   		if (!seatingClass.toLowerCase().equals("economy") && emergencyRowSeating == true) {
   			valid = false;
   		}
   		
   		return valid;
   	}
   	
   	// (partial 8th) and 11th condition airports validation
   	private boolean airportValidation(String departureCode, String destinationCode) {
   		boolean valid = true;
   		boolean validDeparture = false;
   		boolean validDestination = false;
   		List<String> airportCodes = Arrays.asList("syd", "mel", "lax", "cdg", "del", "pvg", "doh");
   		
   		for (String code : airportCodes) {
   			if (departureCode.toLowerCase().equals(code)) {
   				validDeparture = true;
   			}
   			if (destinationCode.toLowerCase().equals(code)) {
   				validDestination = true;
   			}
   		}
   		
   		if (validDeparture == false || validDestination == false || departureCode.equalsIgnoreCase(destinationCode)) {
   			valid = false;
   		}
   		
   		return valid;
   	}
}