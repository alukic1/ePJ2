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
import unibl.etf.pj2.epj2.epj2.rental.Failure;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller class for the failures view GUI in the application.
 * This class handles the initialization and interaction with the failures view,
 * including retrieving all failures that have happened during simulation and
 * displaying information about them.
 */
public class FailuresViewController implements Initializable {
    private Stage stage;
    private Scene scene;


    @FXML
    private TableView<Failure> failuresTable;
    @FXML
    private TableColumn vehicleType;
    @FXML
    private TableColumn vehicleId;
    @FXML
    private TableColumn failureDateTime;
    @FXML
    private TableColumn failureDescription;

    private List<Failure> failures = null;

    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded.
     *
     * @param url the location used to resolve relative paths for the root object
     * @param resourceBundle the resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        failures = DataModifier.getAllFailures();
        vehicleType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        vehicleId.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
        failureDateTime.setCellValueFactory(new PropertyValueFactory<>("failureDateTime"));
        failureDescription.setCellValueFactory(new PropertyValueFactory<>("failureDescription"));

        failuresTable.getItems().setAll(failures);
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
