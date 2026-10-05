package unibl.etf.pj2.epj2.epj2.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import unibl.etf.pj2.epj2.epj2.data.DataModifier;
import unibl.etf.pj2.epj2.epj2.map.Simulation;
import unibl.etf.pj2.epj2.epj2.vehicle.Bicycle;
import unibl.etf.pj2.epj2.epj2.vehicle.Car;
import unibl.etf.pj2.epj2.epj2.vehicle.Scooter;
import unibl.etf.pj2.epj2.epj2.vehicle.Vehicle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller class for the view displaying the vehicles with the highest expenses.
 * This class handles the initialization and interaction with the GUI components
 * to display the details of the most expense produced car, bicycle, and scooter.
 */
public class HighestExpenseViewController implements Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
    private Label carId;
    @FXML
    private Label carModel;
    @FXML
    private Label bikeId;
    @FXML
    private Label bikeModel;
    @FXML
    private Label scooterId;
    @FXML
    private Label scooterModel;


    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded. Finds the vehicles with most expenses,
     * serializes them, deserializes and displays the information.
     *
     * @param url the location used to resolve relative paths for the root object
     * @param resourceBundle the resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<Car> cars = new ArrayList<>();
        List<Bicycle> bicycles = new ArrayList<>();
        List<Scooter> scooters = new ArrayList<>();

        for(Vehicle v: Simulation.vehicles){
            if(v instanceof Car)
                cars.add((Car)v);
            else if(v instanceof Bicycle)
                bicycles.add((Bicycle)v);
            else
                scooters.add((Scooter)v);
        }

        Car highestExpenseCar = cars.getFirst();
        Bicycle highestExpenseBicycle = bicycles.getFirst();
        Scooter highestExpenseScooter = scooters.getFirst();

        for(Car car : cars){
            if(car.getExpense() > highestExpenseCar.getExpense())
                highestExpenseCar = car;
        }

        for(Bicycle bicycle : bicycles){
            if(bicycle.getExpense() > highestExpenseBicycle.getExpense())
                highestExpenseBicycle = bicycle;
        }

        for(Scooter scooter : scooters){
            if(scooter.getExpense() > highestExpenseScooter.getExpense())
                highestExpenseScooter = scooter;
        }


        DataModifier.serializeVehicles(highestExpenseCar, highestExpenseBicycle, highestExpenseScooter);

        Car deserializedCar = DataModifier.deserializeCar();
        carId.setText(deserializedCar.getId());
        carModel.setText(deserializedCar.getModel());

        Bicycle deserializedBicycle = DataModifier.deserializeBicycle();
        bikeId.setText(deserializedBicycle.getId());
        bikeModel.setText(deserializedBicycle.getModel());

        Scooter deserializedScooter = DataModifier.deserializeScooter();
        scooterId.setText(deserializedScooter.getId());
        scooterModel.setText(deserializedScooter.getModel());

    }

    /**
     * Handles the action of going back to the main view.
     *
     * @param event the action event triggered by user interaction
     */
    public void goBack(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/unibl/etf/pj2/epj2/epj2/epj2-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();}
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
