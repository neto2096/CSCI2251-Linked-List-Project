
// Name: Ernesto Morales Carrasco
// Email: emoralescarras@cnm.edu
// Assignment: Linked List
/** Purpose:
 * Main must read in the data from the file and save each row of data into a new HurricaneRowData object, which are further 
 * organized into a DoublyLinkedSortedList. Main also must contain a private static method that takes the DoublyLinkedSortedList 
 * of data as input and returns the year in which the ACE index (second column) was maximal. Display out the year and maximum 
 * ACE value BOTH on the command prompt and also output the information to a text file. 
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        DoublyLinkedSortedList data = new DoublyLinkedSortedList();
        // Read the CSV file
        try (BufferedReader br = new BufferedReader(new FileReader("ace.csv"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                int year = Integer.parseInt(row[0].trim());
                int aceIndex = Integer.parseInt(row[1].trim());
                int tropicalStorms = Integer.parseInt(row[2].trim());
                int hurricanes = Integer.parseInt(row[3].trim());
                int majorHurricanes = Integer.parseInt(row[4].trim());

                HurricaneRowData hurricaneRow = new HurricaneRowData(year, aceIndex, tropicalStorms, hurricanes,
                        majorHurricanes);
                data.insert(hurricaneRow);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        // Find the year with the maximum ACE index
        DoublyLinkedSortedList link = data.getFirst();
        HurricaneRowData dat = link.getValue();
        int maxYear = dat.getYear();
        System.out.println("Year of max ace: " + maxYear);
        System.out.println("All data in order of Ace:");
        System.out.println(data);

        // Output to text file
        try (PrintWriter writer = new PrintWriter(new FileWriter("maxAceOutput.txt"))) {
            writer.println("Year of max ace: " + maxYear);
            writer.println("All data in order of Ace:");
            writer.println(data);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}