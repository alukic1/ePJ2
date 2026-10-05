package unibl.etf.pj2.epj2.epj2.map;

import javafx.application.Platform;
import javafx.concurrent.Task;
import unibl.etf.pj2.epj2.epj2.controller.MapViewController;
import unibl.etf.pj2.epj2.epj2.data.DataModifier;
import unibl.etf.pj2.epj2.epj2.rental.Customer;
import unibl.etf.pj2.epj2.epj2.rental.Rental;
import unibl.etf.pj2.epj2.epj2.vehicle.Vehicle;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Manages the simulation of vehicle rentals, controlling the process of displaying
 * all rentals in the system.
 *
 * <p>The {@code Simulation} class extends {@link Task} and is responsible for interacting
 * with the GUI to visualize the movement of rented vehicles on a map according to their
 * rental times.</p>
 *
 * <p>The {@code Simulation} class works closely with other classes in the system to ensure
 * a realistic and accurate representation of the rental process.</p>
 */
public class Simulation extends Task<Void> {
    public static final Integer ROW = 20;
    public static final Integer COL = 20;


    public static List<Rental>[][] map;
    public static List<Vehicle> vehicles = null;
    public static List<Rental> rentals=null;
    public static List<Customer> customers = new ArrayList<>();

    public static  MapViewController mapView=null;

    public static boolean simulationDone = false;

    /**
     * Constructor.
     * @param controller  the controller for the GUI representation of the map view.
     */
    public Simulation(MapViewController controller) {
        mapView = controller;
    }

    /**
     * Updates the display of a cell on the map.
     * @param row  the current row coordinate
     * @param column  the current column coordinate
     * @param text  the text that needs to be displayed
     * @param color  the color of the cell
     * @param fontSize  the font size of the text in the cell.
     */
    private void updateCell(int row, int column, String text, String color, double fontSize) {
        Platform.runLater(() -> {
            mapView.updateCell(row, column, text, color, fontSize);
        });
    }

    /**
     * Displays current date and time on the map.
     * @param date  String representation of current date and time.
     */
    private void setDate(String date) {
        Platform.runLater(() -> {
            mapView.setDate(date);
        });
    }

    /**
     *
     * The main logic of the simulation. This method is called when the simulation task is executed.
     *
     * <p>It allows the movement of vehicles, i.e., the rental process on the map.</p>
     */
    @Override
    protected Void call() {


        map = (List<Rental>[][]) new List[ROW][COL];

        for(int i=0; i<ROW; i++) {
            for(int j=0; j<COL; j++) {
                map[i][j] = new ArrayList<Rental>();
            }
        }

        if(mapView != null) {

            for(int i=0; i<Simulation.ROW; i++){
                for(int j=0; j<Simulation.COL; j++){
                    updateCell(i, j, "", "white", 12.0);
                }
            }
            setDate("");

            vehicles = DataModifier.getAllVehicles();

             rentals = DataModifier.getAllRentals();

            Map<LocalDateTime, List<Rental>> rentalsByDate = rentals.stream().collect(Collectors.groupingBy(Rental::getRentalDate));

            Map<LocalDateTime, List<Rental>> sortedRentalsByDate = new TreeMap<>(rentalsByDate);

            for (Map.Entry<LocalDateTime, List<Rental>> entry : sortedRentalsByDate.entrySet()) {
                LocalDateTime date = entry.getKey();
                List<Rental> rentalsOnDate = entry.getValue();
                List<Rental> tempRentalsOnDate = new ArrayList<>();
                tempRentalsOnDate.addAll(rentalsOnDate);
                System.out.println(date.toString());

                setDate(date.toString());


                while(!tempRentalsOnDate.isEmpty()){
                    for (Rental rental : rentalsOnDate) {
                        if(!rental.isFinished() && !rental.getVehicle().isOccupied()){
                            rental.getVehicle().setOccupied(true);
                            rental.start();
                            tempRentalsOnDate.remove(rental);
                        }
                    }
                    try{
                        Thread.sleep(300);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }

                for (Rental rental : rentalsOnDate) {
                    try {
                        rental.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for(int i=0; i<Simulation.ROW; i++){
                    for(int j=0; j<Simulation.COL; j++){
                        updateCell(i, j, "", "white", 12.0);
                    }
                }

                setDate("");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            setDate("Simulacija zavrsena.");
            simulationDone = true;

        }
        return null;
    }
}
