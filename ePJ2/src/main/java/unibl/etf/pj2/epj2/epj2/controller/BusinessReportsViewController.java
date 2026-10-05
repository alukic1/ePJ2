package unibl.etf.pj2.epj2.epj2.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import unibl.etf.pj2.epj2.epj2.data.DataModifier;
import unibl.etf.pj2.epj2.epj2.rental.Receipt;


import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Controller class for the business reports view GUI in the application.
 * This class handles the initialization and interaction with the business reports view,
 * including calculating the data and displaying them.
 */
public class BusinessReportsViewController implements Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
    private Label sumRevenue;
    @FXML
    private Label sumDiscount;
    @FXML
    private Label sumPromDiscount;
    @FXML
    private Label sumAmountAllRides;
    @FXML
    private Label sumMaintenanceCost;
    @FXML
    private Label sumRepairCost;
    @FXML
    private Label sumExpenses;
    @FXML
    private Label sumTax;
    @FXML
    private Label dayRevenue;
    @FXML
    private Label dayDiscount;
    @FXML
    private Label dayPromDiscount;
    @FXML
    private Label dayAmountAllRides;
    @FXML
    private Label dayMaintenanceCost;
    @FXML
    private Label dayRepairCost;
    @FXML
    private ChoiceBox<String> dateChoice;


    private List<String> availableDates = new ArrayList<>();
    public static String selectedDate;

    private static Map<LocalDate, List<Receipt>> sortedReceiptsByDate = null;

    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded. Calculates and displays the sum data.
     *
     * @param url the location used to resolve relative paths for the root object
     * @param resourceBundle the resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        double sumRevenueVal = 0, sumDiscountVal = 0, sumPromDiscountVal = 0, sumAmountAllRidesWideVal = 0, sumAmountAllRidesNarrowVal=0 ,sumMaintenanceCostVal = 0, sumRepairCostVal = 0, sumExpensesVal = 0, sumTaxVal = 0;


        List<Receipt> receiptList = DataModifier.getAllReceipts();

        Map<LocalDate, List<Receipt>> receiptsByDate = receiptList.stream().collect(Collectors.groupingBy(Receipt::getDate));
        sortedReceiptsByDate = new TreeMap<>(receiptsByDate);

        for (Map.Entry<LocalDate, List<Receipt>> entry : sortedReceiptsByDate.entrySet()) {
            LocalDate date = entry.getKey();
            availableDates.add(date.toString());
        }

        dateChoice.getItems().addAll(availableDates);
        dateChoice.setOnAction(this::getDate);

        for(Receipt receipt : receiptList) {
            sumRevenueVal+=receipt.getTotalPrice();
            sumDiscountVal+=receipt.getDiscount();
            sumPromDiscountVal+=receipt.getPromDiscount();

            if(receipt.isWideDistance())
                sumAmountAllRidesWideVal+=receipt.getTotalPrice();
            else
                sumAmountAllRidesNarrowVal+=receipt.getTotalPrice();

            sumRepairCostVal+=DataModifier.getRepairCost(receipt);

        }

        sumMaintenanceCostVal = sumRevenueVal*0.2;
        sumExpensesVal = sumRevenueVal*0.2;
        sumTaxVal = sumRevenueVal - sumMaintenanceCostVal;
        sumTaxVal-=sumRepairCostVal;
        sumTaxVal-=sumExpensesVal;
        sumTaxVal*=0.1;

        sumRevenue.setText(String.format("%.2f", sumRevenueVal));
        sumDiscount.setText(String.format("%.2f", sumDiscountVal));
        sumPromDiscount.setText(String.format("%.2f", sumPromDiscountVal));
        String sumAmount = "Uži: " + String.format("%.2f", sumAmountAllRidesNarrowVal) + System.lineSeparator()+
                "Širi: " + String.format("%.2f", sumAmountAllRidesWideVal);
        sumAmountAllRides.setText(sumAmount);
        sumMaintenanceCost.setText(String.format("%.2f", sumMaintenanceCostVal));
        sumRepairCost.setText(String.format("%.2f", sumRepairCostVal));
        sumExpenses.setText(String.format("%.2f", sumExpensesVal));
        sumTax.setText(String.format("%.2f", Math.abs(sumTaxVal)));


    }

    /**
     * Gets the selected date. Calculates and displays the data for the chosen date.
     * @param event the action event triggered by user interaction
     */
    public void getDate(ActionEvent event) {
        selectedDate = dateChoice.getValue();


        if(selectedDate!=null){
            double dayRevenueVal = 0, dayDiscountVal=0, dayPromDiscountVal=0, dayAmountAllRidesWideVal=0, dayAmountAllRidesNarrowVal=0,
                    dayMaintenanceCostVal=0, dayRepairCostVal=0;
            for (Map.Entry<LocalDate, List<Receipt>> entry : sortedReceiptsByDate.entrySet()) {
                LocalDate date = entry.getKey();
                if(date.equals(LocalDate.parse(selectedDate))){
                    List<Receipt> receiptsOnDate = entry.getValue();
                    for(Receipt receipt : receiptsOnDate){
                        dayRevenueVal+=receipt.getTotalPrice();
                        dayDiscountVal+=receipt.getDiscount();
                        dayPromDiscountVal+=receipt.getPromDiscount();
                        if(receipt.isWideDistance())
                            dayAmountAllRidesWideVal+=receipt.getTotalPrice();
                        else
                            dayAmountAllRidesNarrowVal+=receipt.getTotalPrice();

                        dayRepairCostVal+=DataModifier.getRepairCost(receipt);
                    }
                    dayMaintenanceCostVal=dayRevenueVal*0.2;
                }
            }
            dayRevenue.setText(String.format("%.2f", dayRevenueVal));
            dayDiscount.setText(String.format("%.2f", dayDiscountVal));
            dayPromDiscount.setText(String.format("%.2f", dayPromDiscountVal));
            String dayAmountRides="Uži: " + String.format("%.2f",dayAmountAllRidesNarrowVal) + System.lineSeparator()+
                    "Širi: " + String.format("%.2f",dayAmountAllRidesWideVal);
            dayAmountAllRides.setText(dayAmountRides);
            dayMaintenanceCost.setText(String.format("%.2f", dayMaintenanceCostVal));
            dayRepairCost.setText(String.format("%.2f", dayRepairCostVal));

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
