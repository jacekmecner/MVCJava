package pl.polsl.main;

import pl.polsl.controller.*;
//import pl.polsl.model.Model;
//import pl.polsl.view.View;

/**
 *
 * 
 * @author jacekmecner
 */
public class Main {
    /**
     * The main method of the application.
     * @param args Command-line arguments - expects CSV file path as the first argument.
     */
    
    public static void main(String[] args) {
        String csvPath = "";
        if(args.length > 0) {
            csvPath = args[0];
        }
        
        Controller controller = new Controller();
//        View view = new View();
//        Model model = new Model();
        controller.loadCSVFile(csvPath);
        controller.showData();
    }
}

//Aplikacja powinna:
// - Obliczać korelację Pearsona pomiędzy długością da na danej planecie a okresem obrotu
// - Sortować planety według masy
// - Wyświetlać, w jaki sposób [%] zmienia się średnia temperatura w zależności od odległości od Słońca
// - Wyświetlać planety posiadające globalne pola magnetyczne
