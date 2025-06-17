// Name: Ernesto Morales Carrasco
// Email: emoralescarras@cnm.edu
// Assignment: Hurricane Data
/** Purpose:
 * Main must read in the data from the file and save each row of data into a new HurricaneRowData object, which are further 
 * organized into an ArrayList. Main also must contain a private static method that takes the ArrayList of data as input and 
 * returns the year in which the ACE index (second column) was maximal. Display out the year and maximum ACE value BOTH on the 
 * command prompt and also output the information to a text file. 
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<HurricaneRowData> hurricaneData = new ArrayList<>();
        
        // Read the CSV file
        try (BufferedReader br = new BufferedReader(new FileReader("ace.csv"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int year = Integer.parseInt(data[0].trim());
                int aceIndex = Integer.parseInt(data[1].trim());
                int tropicalStorms = Integer.parseInt(data[2].trim());
                int hurricanes = Integer.parseInt(data[3].trim());
                int majorHurricanes = Integer.parseInt(data[4].trim());
                
                HurricaneRowData row = new HurricaneRowData(year, aceIndex, tropicalStorms, hurricanes, majorHurricanes);
                hurricaneData.add(row);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        // Find the year with the maximum ACE index
        int maxAceYear = findMaxAceYear(hurricaneData);
        int maxAceValue = 0;
        for (HurricaneRowData row : hurricaneData) {
            if (row.getYear() == maxAceYear) {
                maxAceValue = row.getAceIndex();
                break;
            }
        }

        // Output to console
        System.out.println("Year with maximum ACE: " + maxAceYear + ", ACE Value: " + maxAceValue);

        // Output to text file
        try (PrintWriter writer = new PrintWriter(new FileWriter("maxAceOutput.txt"))) {
            writer.println("Year with maximum ACE: " + maxAceYear + ", ACE Value: " + maxAceValue);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Method to find the year with the maximum ACE index
     * @param data Array list with all Hurricane Data
     * @return Year with maximum ACE index
     */
    private static int findMaxAceYear(ArrayList<HurricaneRowData> data) {
        int maxAce = 0;
        int maxYear = 0;
        
        for (HurricaneRowData row : data) {
            if (row.getAceIndex() > maxAce) {
                maxAce = row.getAceIndex();
                maxYear = row.getYear();
            }
        }
        
        return maxYear;
    }
}