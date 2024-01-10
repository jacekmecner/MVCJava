package pl.polsl.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import pl.polsl.model.*;

/**
 * View class responsible for displaying data to the user.
 * It interacts with the Model to retrieve and print information.
 * @author Jacek Mecner
 * @version 1.0
 */

public class View{
    private final Model model;
    
    /**
    * Constructor for the View class that initializes with a Model reference.Constructs a new View object while establishing a connection to the provided Model.
    * @param model
    */
    
    public View(Model model){
        this.model = model;
    }
    
    /**
     * Asks the user for the path to the CSV file.
     *
     * @return The user-inputted path to the CSV file.
     */
    
    public String getPathFromUser() {
        try {
            System.out.println("Provide path to csv file:");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            return "";
        }
    }
    
    /**
     * Prints all data retrieved from the Model.
     */
    
    public void printAllData() {
        List<String[]> data = model.getAllDataFromCSV();
        
        System.out.println("PLANET\t\t\tDAY_LENGTH\t\tROTATION_PERIOD\t\tMASS\t\t\tAVERAGE_TEMPERATURE\tDISTANCE_FROM_SUN\tMAGNETIC_FIELD");
        for(String[] line : data) {
            for(String entry : line) {
                if(entry.length() > 0) {
                    System.out.print(entry + "\t\t\t");
                } else {
                    System.out.print("<No data>\t\t");
                }
            }
            System.out.println();
        }
    }    
    
    
    /**
    * Displays Pearson's correlation coefficient between total and serious/critical cases.
    */
    
    public void calculatePearsonCorrelation(){
        System.out.println();
        System.out.println("Pearson's coorelation between Day Lengths and Rotation Periods: " + 
        this.model.calculatePearsonCorrelation());
    }
    
    public void displayIfHasGlobalMagneticField(){
        System.out.println();
        System.out.println("Planets that have global magnetic field: ");
        this.model.printPlanetsWithGlobalMagneticField();
        
    }
    
    public void displayTemperatureChangePercentage(){
        System.out.println();
        System.out.println("Percentage change in average temperature based on distance from the Sun: ");
        this.model.displayTemperatureChangePercentage();
    }
}