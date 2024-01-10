package pl.polsl.model;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

/**
 * Model class handling data manipulation and logic. It loads data from a CSV
 * file, processes and sorts it, and provides access to information.
 *
 * @author Jacek Mecner
 * @version 1.0
 */
public class Model {

    private final List<PlanetData> dataArray;

    /**
     * Constructor initializing an empty list for dataArray.
     */
    public Model() {
        dataArray = new ArrayList<>();
    }  

    /**
     * Loads dataArray from a CSV file into the Model.
     *
     * @param content CSV stream
     * @throws pl.polsl.model.ModelException
     */
    public void loadCSVFile(Reader content) throws ModelException{
        try {
            String[] csvDataArray;
            CSVReader csvReader = new CSVReader(content);
            csvReader.readNext();
            while ((csvDataArray = csvReader.readNext()) != null) {
                PlanetData entryData = new PlanetData();
                entryData.set_planetName(csvDataArray[0]);
                entryData.set_mass(readDoubleAbsolute(csvDataArray[2]));
                entryData.set_dayLength(readDoubleAbsolute(csvDataArray[8]));
                entryData.set_rotationPeriod(readDoubleAbsolute(csvDataArray[7]));
                entryData.set_distanceFromSun(readDouble(csvDataArray[9]));
                entryData.set_averageTemperature(readDouble(csvDataArray[17]));
                entryData.set_hasGlobalMagneticField(readBoolean(csvDataArray[21]));
                dataArray.add(entryData);
            }
        } catch (IOException e) {
            throw new ModelException();
        }
    }

    /**
     * Parses a string value into a double after removing commas.
     *
     * @param value The string value to be parsed into a double.
     * @return Parsed double value. If the parsing fails, returns -1.
     */
    
    private double readDouble(String value) {
        try {
            return Double.parseDouble(value.replace(",", ""));
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private double readDoubleAbsolute(String value) {
        try {
            return Math.abs(Double.parseDouble(value.replace(",", "")));
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private String readBoolean(String value){
        if (value == null) {
            return "Wrong input";
        } else if (value.trim().toUpperCase().equals("YES")) {
            return "TRUE";
        }
        return "FALSE";
    }

    /**
     * Sorts countries according to the number of infections.
     */
    public void sortPlanetsAccordingToMass() {
        Collections.sort(dataArray);
    }

    /**
     * Retrieves all dataArray as a list of string arrays.
     *
     * @return List containing string arrays of planet dataArray.
     */
    public List<String[]> getAllDataFromCSV() {
        List<String[]> all = new ArrayList<>();
        for (PlanetData entryData : dataArray) {
            all.add(new String[]{
                entryData.get_planetName(),
                getString(entryData.get_dayLength()),
                getString(entryData.get_rotationPeriod()),
                getString(entryData.get_mass()),
                getString(entryData.get_averageTemperature()),
                getString(entryData.get_distanceFromSun()),
                entryData.get_hasGlobalMagneticField(),
            });
        }
        return all;
    }

    /**
     * Converts an integer value to a string representation.
     *
     * @param value The integer value to be converted.
     * @return The string representation of the integer value if it's
     * non-negative, otherwise returns an empty string.
     */
    private String getString(double value) {  
            return String.valueOf(value); 
    }

    /**
     * Calculates the Pearson correlation coefficient between two arrays between
 'distanceFromSun' and 'averageTemperature' attributes in the dataArray set.
     *
     * @return The Pearson correlation coefficient between 'distanceFromSun' and
     * 'averageTemperature'.
     */
    public double calculatePearsonCorrelation() {
        PearsonsCorrelation pearsonsCorrelation = new PearsonsCorrelation();

        double[] dayLengths = dataArray.stream().mapToDouble((entryData) -> (double) entryData.get_dayLength()).toArray();
        double[] rotationPeriods = dataArray.stream().mapToDouble((entryData) -> (double) entryData.get_rotationPeriod()).toArray();

        return pearsonsCorrelation.correlation(dayLengths, rotationPeriods);

    }
    
    public void printPlanetsWithGlobalMagneticField() {
        for (PlanetData entryData : dataArray) {
            if (entryData.get_hasGlobalMagneticField().equalsIgnoreCase("TRUE")) {
                System.out.println(entryData.get_planetName());
            }
        }
    }
    
    /**
     * Sorts planets according to the distance from the Sun.
     */
    public void sortPlanetsAccordingToDistance() {
        Collections.sort(dataArray, (planet1, planet2) -> Double.compare(planet1.get_distanceFromSun(), planet2.get_distanceFromSun()));
    }

    /**
     * Displays the percentage change in average temperature based on distance
     * from the Sun.
     */
    public void displayTemperatureChangePercentage() {
       sortPlanetsAccordingToDistance();

        double initialTemperature = -1;
        for (PlanetData entryData : dataArray) {
            double temperature = entryData.get_averageTemperature();

            if (initialTemperature == -1) {
                initialTemperature = temperature;
            } else {
                double percentageChange = ((temperature - initialTemperature) / initialTemperature) * 100;
                System.out.printf("%s: %.2f%%\n", entryData.get_planetName(), percentageChange);
            }
        }
    }
}
