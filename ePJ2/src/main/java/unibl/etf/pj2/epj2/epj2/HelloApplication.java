package unibl.etf.pj2.epj2.epj2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import unibl.etf.pj2.epj2.epj2.data.DataModifier;
import unibl.etf.pj2.epj2.epj2.map.Simulation;
import unibl.etf.pj2.epj2.epj2.rental.Rental;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("epj2-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ePJ2");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        DataModifier.setAllConstants();
        launch();

    }
}