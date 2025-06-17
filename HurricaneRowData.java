// Name: Ernesto Morales Carrasco
// Email: emoralescarras@cnm.edu
// Assignment: Hurricane Data
/** Purpose:
 * HurricaneRowData must have 5 private instance variables (one for each of the columns of data in ace.csv). 
 * The class must also have a constructor and a toString method. You should add any getter or setter methods that you need. 
 * May optionally create a class to store the ArrayList of HurricaneRowData objects and manage its access.
*/

public class HurricaneRowData {
    private int year;
    private int aceIndex;
    private int tropicalStorms;
    private int hurricanes;
    private int majorHurricanes;

    public HurricaneRowData(int year, int aceIndex, int tropicalStorms, int hurricanes, int majorHurricanes) {
        this.year = year;
        this.aceIndex = aceIndex;
        this.tropicalStorms = tropicalStorms;
        this.hurricanes = hurricanes;
        this.majorHurricanes = majorHurricanes;
    }

    // Getters
    public int getYear() {
        return year;
    }

    public int getAceIndex() {
        return aceIndex;
    }

    public int getTropicalStorms() {
        return tropicalStorms;
    }

    public int getHurricanes() {
        return hurricanes;
    }

    public int getMajorHurricanes() {
        return majorHurricanes;
    }

    // toString method
    @Override
    public String toString() {
        return "Year: " + year + ", ACE Index: " + aceIndex + ", Tropical Storms: " + tropicalStorms +
               ", Hurricanes: " + hurricanes + ", Major Hurricanes: " + majorHurricanes;
    }
}