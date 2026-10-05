package unibl.etf.pj2.epj2.epj2.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import unibl.etf.pj2.epj2.epj2.data.DataModifier;
import unibl.etf.pj2.epj2.epj2.vehicle.Bicycle;
import unibl.etf.pj2.epj2.epj2.vehicle.Car;
import unibl.etf.pj2.epj2.epj2.vehicle.Scooter;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller class for the vehicles view GUI in the application.
 * This class handles the initialization and interaction with the vehicles view,
 * including retrieving all available vehicles and displaying information about them.
 */
public class VehiclesViewController implements Initializable {
    private Stage stage;
    private Scene scene;

    //cars
    @FXML
    private TableView<Car> carsTable;
    @FXML
    private TableColumn carId;
    @FXML
    private TableColumn carManufacturer;
    @FXML
    private TableColumn carModel;
    @FXML
    private TableColumn carPurchaseDate;
    @FXML
    private TableColumn carPrice;
    @FXML
    private TableColumn carDescription;


    //bicycles
    @FXML
    private TableView<Bicycle> bicyclesTable;
    @FXML
    private TableColumn bicycleId;
    @FXML
    private TableColumn bicycleManufacturer;
    @FXML
    private TableColumn bicycleModel;
    @FXML
    private TableColumn bicycleReach;
    @FXML
    private TableColumn bicyclePrice;


    //scooters
    @FXML
    private TableView<Scooter> scootersTable;
    @FXML
    private TableColumn scooterId;
    @FXML
    private TableColumn scooterManufacturer;
    @FXML
    private TableColumn scooterModel;
    @FXML
    private TableColumn scooterMaxSpeed;
    @FXML
    private TableColumn scooterPrice;


    private List<Car> cars = null;
    private List<Bicycle> bicycles = null;
    private List<Scooter> scooters = null;

    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded.
     *
     * @param url the location used to resolve relative paths for the root object
     * @param resourceBundle the resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        cars = DataModifier.getAllCars();
        bicycles = DataModifier.getAllBicycles();
        scooters = DataModifier.getAllScooters();

        //prikaz auta
        carId.setCellValueFactory(new PropertyValueFactory<Car, String>("id"));
        carManufacturer.setCellValueFactory(new PropertyValueFactory<Car, String>("manufacturer"));
        carModel.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
        carPrice.setCellValueFactory(new PropertyValueFactory<Car, String>("price"));
        carPurchaseDate.setCellValueFactory(new PropertyValueFactory<Car, String>("purchaseDate"));
        carDescription.setCellValueFactory(new PropertyValueFactory<Car, String>("description"));

        try {
            carsTable.getItems().setAll(cars);
        }
        catch(NullPointerException e) {
            e.printStackTrace();
        }
        //prikaz bicikala
        bicycleId.setCellValueFactory(new PropertyValueFactory<Bicycle, String>("id"));
        bicycleManufacturer.setCellValueFactory(new PropertyValueFactory<Bicycle, String>("manufacturer"));
        bicycleModel.setCellValueFactory(new PropertyValueFactory<Bicycle, String>("model"));
        bicyclePrice.setCellValueFactory(new PropertyValueFactory<Bicycle, String>("price"));
        bicycleReach.setCellValueFactory(new PropertyValueFactory<Bicycle, String>("reach"));

        try {
            bicyclesTable.getItems().setAll(bicycles);
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
        }

        //prikaz trotineta
        scooterId.setCellValueFactory(new PropertyValueFactory<Scooter, String>("id"));
        scooterManufacturer.setCellValueFactory(new PropertyValueFactory<Scooter, String>("manufacturer"));
        scooterModel.setCellValueFactory(new PropertyValueFactory<Scooter, String>("model"));
        scooterPrice.setCellValueFactory(new PropertyValueFactory<Scooter, String>("price"));
        scooterMaxSpeed.setCellValueFactory(new PropertyValueFactory<Scooter, String>("maxSpeed"));

        try {
            scootersTable.getItems().setAll(scooters);
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }
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
