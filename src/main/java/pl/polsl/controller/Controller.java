package pl.polsl.controller;

import java.io.FileReader;
import java.io.IOException;
import pl.polsl.view.View;
import pl.polsl.model.Model;
import pl.polsl.model.ModelException;

/**
 * Controller class responsible for managing the interaction between Model and
 * View. It loads data from a CSV file into the Model and displays information
 * using the View.
 *
 * @author Jacek
 * @version 1.0
 */

public class Controller {

    private final Model model;
    private final View view;

    /**
     * Default constructor for the Controller class.
     *
     * Initializes a new instance of the Controller, creating an associated
     * Model and View. This constructor sets up the Controller by creating a
     * Model instance and a View instance that references this Model.
     */
    public Controller() {
        this.model = new Model();
        this.view = new View(model);
    }

    /**
     * Loads data from a CSV file into the Model.
     *
     * @param csvPath CSV file path.
     *
     */
    public void loadCSVFile(String csvPath) {
        String path = csvPath;
        while(true)
        try {
            this.model.loadCSVFile(new FileReader(path));
            break;
        } catch (ModelException | IOException e) {
            path = this.view.getPathFromUser();
        }
    }

    /**
     * Displays data sorted by infections and prints the country with the
     * highest number of deaths.
     */

    public void showData() {
        this.model.sortPlanetsAccordingToMass();
        this.view.printAllData();
        this.view.calculatePearsonCorrelation();
        this.view.displayIfHasGlobalMagneticField();
        this.view.displayTemperatureChangePercentage();
    }
}
