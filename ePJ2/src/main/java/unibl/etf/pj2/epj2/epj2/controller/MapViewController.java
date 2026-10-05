package unibl.etf.pj2.epj2.epj2.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import unibl.etf.pj2.epj2.epj2.map.Simulation;

import java.net.URL;
import java.util.ResourceBundle;
/**
 * Controller class for the map view GUI in the application.
 * This class handles the initialization and interaction with the map view,
 * including starting the simulation and updating the GUI components.
 */
public class MapViewController implements Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
    private GridPane gridPane;
    @FXML
    private Label dateLabel;

    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded.
     *
     * @param url the location used to resolve relative paths for the root object
     * @param resourceBundle the resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for (var node : gridPane.getChildren()) {
            if (node instanceof Label) {
                Label label = (Label) node;
                label.setPrefWidth(gridPane.getPrefWidth());
                label.setPrefHeight(gridPane.getPrefHeight());
                label.setWrapText(true);
            }
        }

        Platform.runLater(() -> startSimulation());

    }

    /**
     * Starts the simulation by creating and running a Simulation task on a new thread.
     */
    public void startSimulation() {
        Simulation simulationTask = new Simulation(this);

        Thread simulationThread = new Thread(simulationTask);
        simulationThread.setDaemon(true);
        simulationThread.start();
    }


    /**
     * Updates the date label with the given date and time string.
     *
     * @param dateString the new date and time to display
     */
    public void setDate(String dateString) {
            dateLabel.setText(dateString);

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

    /**
     * Updates a cell in the grid pane at the specified row and column with the given text, color, and font size.
     *
     * @param row the row index of the cell to update
     * @param column the column index of the cell to update
     * @param text the text to display in the cell
     * @param color the background color of the cell
     * @param fontSize the font size of the text in the cell
     */
    public void updateCell(int row, int column, String text, String color, double fontSize) {
        Platform.runLater(() -> {
            for (javafx.scene.Node node : gridPane.getChildren()) {
                if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                    if (node instanceof Label) {
                        Label label = (Label) node;
                        label.setText(text);
                        label.setStyle("-fx-background-color: " + color + ";");
                        label.setFont(new Font(fontSize));
                    }
                    break;
                }
            }
        });
    }
}
