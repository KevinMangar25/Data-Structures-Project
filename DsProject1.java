import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class DsProject1 {
	public static void main(String[] args) {
		LinkedList<WeatherData> unsortedList = new LinkedList<>();
		
		Scanner scnr = new Scanner(System.in);
		System.out.println("Enter the file path: ");
        String csvFile = scnr.nextLine(); // specify your CSV file path
        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            String currentLine;
            int rowNumber = 1;
            reader.readLine(); // Skip first line because its the header

            while ((currentLine = reader.readLine()) != null) {
                // Split the line into columns based on commas
                String[] columns = currentLine.split(",");

                // Ensure there are enough columns (we need at least 52)
                if (columns.length >= 52) {
                    // Column 2 (index 1)
                    String date = columns[1];

                    try {
                    	/*
                    	 * assigns the indexed column to the given variable if the column is
                    	 * not empty, if it is empty assign it a default value
                    	 */
                        float hourlyAltimeter = !columns[42].trim().isEmpty() ? Float.parseFloat(columns[42].trim().replace("\"", "")): 0.0f;
                        int hourlyDewPoint = !columns[43].trim().isEmpty() ? Integer.parseInt(columns[43].trim().replace("\"", "")): 0;
                        int hourlyDryBulb = !columns[44].trim().isEmpty() ? Integer.parseInt(columns[44].trim().replace("\"", "")): 0;
                        
                        // Column 52 (index 51) - No need to parse
                        String hourlySky = columns[51];  
                        
                        //Creates a weatherData object and adds it to the linkedlist
                        WeatherData weatherData = new WeatherData(date, hourlyAltimeter, hourlyDewPoint, hourlyDryBulb, hourlySky);
                        unsortedList.add(weatherData);
                        
                    } catch (NumberFormatException e) {
                        // Handle invalid number formats
                        System.out.print("");
                    }
                }
                rowNumber++;
            }

            reader.close();  // Close the reader when done

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
        
        //while condition to ask if the user wants to continue to run the program
        boolean continueToRun = true;
        int sortedComparisons = 0;
        int unsortedComparisons = 0;
        
        /*
         * first while loop is to ask the user for input and to ask the user if they want
         * to run the program again or terminate it
         */
	
        
        while(continueToRun) {
        	System.out.println("Enter the first column name to filter by (HourlyAltimeterSetting, HourlyDewPointTemperature, HourlyDryBulbTemperature, HourlySkyConditions): ");
        	String column1 = scnr.nextLine();
        	
        	System.out.println("Enter the second column name to filter by (HourlyAltimeterSetting, HourlyDewPointTemperature, HourlyDryBulbTemperature, HourlySkyConditions): ");
        	String column2 = scnr.nextLine();
        
        	
        	System.out.println("Select a value for " + column1);
        	 
        	String value1 = scnr.nextLine();
        	    	
        	System.out.println("Select a value for " + column2);
        	String value2 = scnr.nextLine();
        	
    		LinkedList<WeatherData> sortedList = quickSort(unsortedList, column1);
        	Node<WeatherData> current = sortedList.first.getNext();
        	Node<WeatherData> current2 = unsortedList.first.getNext();
        	
        	System.out.println("Column1: ");
        	//Sorted List
        	while(current.getNext() != null) {        		
        		if(column1.equals("HourlyAltimeterSetting")) {
            		//System.out.println("HourlyAltimeterSetting: " + current.getItem().getHourlyAltimeter());
        	        float altimeterValue = Float.parseFloat(value1); // Convert value1 to float
        			if(altimeterValue == current.getItem().getHourlyAltimeter() && current.getItem().getHourlyAltimeter() <= current.getNext().getItem().getHourlyAltimeter()) {
        				System.out.println("Hourly Altimeter Setting value: " + current.getItem().getHourlyAltimeter());
                		sortedComparisons++;

        			}
        			
        		}
  
        		else if (column1.equals("HourlyDewPointTemperature")) {
            		//System.out.println("HourlyDewPointTemperature: " + current.getItem().getHourlyDewPoint());

    				int dewPoint = Integer.parseInt(value1);
        			if(dewPoint == current.getItem().getHourlyDewPoint() && current.getItem().getHourlyDewPoint() <= current.getNext().getItem().getHourlyDewPoint())
        				System.out.println("Hourly Dew Point value: " + current.getItem().getHourlyDewPoint());
            			sortedComparisons++;
        				
        		} 
        		
        		else if (column1.equals("HourlyDryBulbTemperature")) {
            		//System.out.println("HourlyDryBulbTemperature: " + current.getItem().getHourlyDryBulb());
        			
    				int dryBulb = Integer.parseInt(value1);
        			if(dryBulb == current.getItem().getHourlyDryBulb() && current.getItem().getHourlyDryBulb() <= current.getNext().getItem().getHourlyDryBulb())
        				System.out.println("Hourly Dry Bulb value: " + current.getItem().getHourlyDryBulb());
            			sortedComparisons++;

        				
        		}
        		
        		else if (column1.equals("HourlySkyConditions")) {
            		//System.out.println("HourlySkyConditions: " + current.getItem().getHourlySky());
        			
        			if(value1.equals(current.getItem().getHourlySky().trim()) && current.getItem().getHourlySky().compareTo(current.getNext().getItem().getHourlySky()) <= 0)
        				System.out.println("Hourly Sky Conditions value: " + current.getItem().getHourlySky());
            			sortedComparisons++;

        				
        		}
        		
        		current = current.getNext();
        		
        		
        	}
        	
        	System.out.println("\nSortedComparisons: " + sortedComparisons + "\n");
        	
        	System.out.println("Column2: ");
        	
        	//unsorted list
        	while(current2.getNext() != null) {
        		if (column2.equals("HourlyAltimeterSetting")) {
            		//System.out.println("HourlyAltimeterSetting: " + current2.getItem().getHourlyAltimeter());
        			
        			float altimeterValue = Float.parseFloat(value2); // Convert value1 to float
        			if(altimeterValue == current2.getItem().getHourlyAltimeter()) 
        				System.out.println("Hourly Altimeter Setting value: " + current2.getItem().getHourlyAltimeter());
        			
        			
        		}
        	
        		else if (column2.equals("HourlyDewPointTemperature")) {
            		//System.out.println("HourlyDewPointTemperature: " + current2.getItem().getHourlyDewPoint());

        			
    				int dewPoint = Integer.parseInt(value2);
        			if(dewPoint == current2.getItem().getHourlyDewPoint())
        				System.out.println("Hourly Dew Point value: " + current2.getItem().getHourlyDewPoint());
        				
        		} 
        		
        		else if (column2.equals("HourlyDryBulbTemperature")) {
            		//System.out.println("HourlyDryBulbTemperature: " + current2.getItem().getHourlyDryBulb());
        			
    				int dryBulb = Integer.parseInt(value2);
        			if(dryBulb == current2.getItem().getHourlyDryBulb())
        				System.out.println("Hourly Dry Bulb value: " + current2.getItem().getHourlyDryBulb());
        				
        		}
        		
        		else if (column2.equals("HourlySkyConditions")) {
            		//System.out.println("HourlySkyConditions: " + current2.getItem().getHourlySky());
        			
        			if(value2.equals(current2.getItem().getHourlySky().trim()))
        				System.out.println("Hourly Sky Conditions value: " + current2.getItem().getHourlySky());
        				
        		}        		
        		//changes current to the next node
        		current2 = current2.getNext();
        		unsortedComparisons++;
        	}
        		
        	System.out.println("unsortedComparisons: " + unsortedComparisons + "\n");

        	
        	/*
        	 * continuation of the first while loop asking 
        	 * the user if they want to try again or terminate
        	 */
        	
        	System.out.println("Would you like to run the program again or stop?");
            System.out.println("Type 'T' to try again or 'Yes' to terminate.");
            String userChoice = scnr.nextLine();

            if (userChoice.equals("Yes")) {
                continueToRun = false;  // Exit the loop and terminate
            } 
            else if (!userChoice.equals("T")) {
                System.out.println("Invalid input. Please enter 'y' to try again or 'n' to terminate.");
            }
            else
            	continueToRun = true;
        }

        System.out.println("Program stopped, thanks for using it :).");

}
	
	public static LinkedList<WeatherData> quickSort(LinkedList<WeatherData> list1, String sortBy) {
	    if (list1.size() <= 1) {
	        return list1;
	    }

	    Node<WeatherData> current = list1.first;
	    WeatherData pivot = list1.last();
	    
	    LinkedList<WeatherData> lessPivot = new LinkedList<>();
	    LinkedList<WeatherData> morePivot = new LinkedList<>();
	    
	    // Partition the list around the pivot
	    while (current != null) { 
	        if (current.getItem() != pivot) {
	        	if(sortBy.equals("HourlyAltimeterSetting")) { 
	        		if (current.getItem().getHourlyAltimeter() < (pivot.getHourlyAltimeter())) 
	        			lessPivot.add(current.getItem());
	             	else 
	             		morePivot.add(current.getItem());
	        	}
	        	else if(sortBy.equals("HourlyDewPointTemperature")) { 
	        		if (current.getItem().getHourlyDewPoint() < (pivot.getHourlyDewPoint())) 
	        			lessPivot.add(current.getItem());
	             	else 
	             		morePivot.add(current.getItem());
	        	}
	        	else if(sortBy.equals("HourlyDryBulbTemperature")) { 
	        		if (current.getItem().getHourlyDryBulb() < (pivot.getHourlyDryBulb())) 
	        			lessPivot.add(current.getItem());
	             	else 
	             		morePivot.add(current.getItem());
	        	}
	        	else if(sortBy.equals("HourlySkyConditions")) { 
	        		if (current.getItem().getHourlySky().compareTo(pivot.getHourlySky()) < 0) 
	        			lessPivot.add(current.getItem());
	             	else 
	             		morePivot.add(current.getItem());
	        	}
	        }
	        current = current.getNext();
	    }

	    // Recursively sort the partitions
    		LinkedList<WeatherData> sortedLessPivot = quickSort(lessPivot, sortBy);
	    	LinkedList<WeatherData> sortedMorePivot = quickSort(morePivot, sortBy);  
	    
	    // Combine the sorted lists with the pivot
	    LinkedList<WeatherData> sortedList = new LinkedList<>();
	    sortedList.addAll(sortedLessPivot);
	    sortedList.add(pivot);
	    sortedList.addAll(sortedMorePivot);
	    
	    return sortedList;
	}


	
}
