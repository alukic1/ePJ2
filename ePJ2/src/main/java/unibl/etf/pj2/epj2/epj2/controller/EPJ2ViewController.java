package unibl.etf.pj2.epj2.epj2.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import unibl.etf.pj2.epj2.epj2.map.Simulation;

/**
 * Controller class for the main window GUI in the application.
 * This class handles navigation between different views within the application.
 */
public class EPJ2ViewController {

    private Stage stage;
    private Scene scene;

    /**
     * Navigates to the map view.
     *
     * @param event the action event triggered by user interaction
     */
    public void goMap(ActionEvent event) {
        try{

            FXMLLoader loader= new FXMLLoader(getClass().getResource("/unibl/etf/pj2/epj2/epj2/map-view.fxml"));
            Parent root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * Navigates to the vehicles view.
     *
     * @param event the action event triggered by user interaction
     */
    public void goVehiclesView(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/unibl/etf/pj2/epj2/epj2/vehicles-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();}
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    /**
     * Navigates to the failures view if the simulation is complete.
     *
     * @param event the action event triggered by user interaction
     */
    public void goFailures(ActionEvent event) {
        if(Simulation.simulationDone) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/unibl/etf/pj2/epj2/epj2/failures-view.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    /**
     * Navigates to the business reports view if the simulation is complete.
     *
     * @param event the action event triggered by user interaction
     */
    public void goBusinessReports(ActionEvent event) {
        if(Simulation.simulationDone){
            try{
                Parent root = FXMLLoader.load(getClass().getResource("/unibl/etf/pj2/epj2/epj2/businessreports-view.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();}
            catch(Exception ex){
                ex.printStackTrace();
            }
        }

    }
    /**
     * Navigates to the highest expense vehicles view if the simulation is complete.
     *
     * @param event the action event triggered by user interaction
     */
    public void goHighestExpenseVehicles(ActionEvent event) {
        if(Simulation.simulationDone){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/unibl/etf/pj2/epj2/epj2/highestexpense-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();}
        catch(Exception ex){
            ex.printStackTrace();
        }
    }}
}
