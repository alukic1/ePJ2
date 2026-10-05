package unibl.etf.pj2.epj2.epj2.rental;

import javafx.scene.chart.PieChart;
import unibl.etf.pj2.epj2.epj2.data.DataModifier;
import unibl.etf.pj2.epj2.epj2.data.Parser;
import unibl.etf.pj2.epj2.epj2.exception.InvalidFormatException;
import unibl.etf.pj2.epj2.epj2.vehicle.Vehicle;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *  a receipt for a vehicle rental.
 *
 * <p>This class holds all the necessary information about a rental transaction
 * required for payment, including customer details, vehicle details, discounts,
 * promotions, and the total price.</p>
 */
public class Receipt implements Parser<Receipt> {

    private Vehicle vehicle;
    private Customer customer;
    private Double discount;
    private Double promDiscount;
    private Double price;
    private Double totalPrice;
    LocalDateTime dateTime;
    private boolean failure;
    private boolean wideDistance;

    /**
     * Default constructor.
     */
    public Receipt(){}

    /**
     *
     * Parameterized constructor.
     * @param vehicle  the vehicle that was rented,
     * @param customer  the customer that rented the vehicle,
     * @param discount  the amount of discount if applicable,
     * @param promDiscount  the amount of promotion discount if applicable,
     * @param price  the price of the rental without any discounts,
     * @param totalPrice  the total price of the rental,
     * @param dateTime  the date and time of the rental.
     */
    public Receipt(Vehicle vehicle, Customer customer, Double discount, Double promDiscount, Double price, Double totalPrice, LocalDateTime dateTime) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.discount = discount;
        this.promDiscount = promDiscount;
        this.price = price;
        this.totalPrice = totalPrice;
        this.dateTime = dateTime;
    }

    /**
     * Gets the vehicle.
     * @return the vehicle that was rented.
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Sets the vehicle.
     * @param vehicle  the vehicle that was rented.
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Gets the customer.
     * @return the customer that rented the vehicle.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the customer.
     * @param customer  the customer that rented the vehicle.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Gets the discount.
     * @return the amount of discount if applicable.
     */
    public Double getDiscount() {
        return discount;
    }

    /**
     * Sets the discount.
     * @param discount  the amount of discount if applicable.
     */
    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    /**
     * Gets the promotion discount.
     * @return the amount of promotion discount if applicable.
     */
    public Double getPromDiscount() {
        return promDiscount;
    }

    /**
     * Sets the promotion discount.
     * @param promDiscount  the amount of promotion discount if applicable.
     */
    public void setPromDiscount(Double promDiscount) {
        this.promDiscount = promDiscount;
    }

    /**
     * Gets the total price.
     * @return the total price of the rental.
     */
    public Double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the total price.
     * @param totalPrice  the total price of the rental.
     */
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Gets the date and time of the rental.
     * @return the date and time of the rental.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Gets the date of the rental.
     * @return the date of the rental.
     */
    public LocalDate getDate(){
        return dateTime.toLocalDate();
    }

    /**
     * Sets the date and time of the rental.
     * @param dateTime  the date and time of the rental.
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Gets the information whether there has been a failure during the rental.
     * @return whether there has been a failure during the rental.
     */
    public boolean isFailure() {
        return failure;
    }

    /**
     * Sets the information whether there has been a failure during the rental.
     * @param failure  whether there has been a failure during the rental.
     */
    public void setFailure(boolean failure) {
        this.failure = failure;
    }

    /**
     * Gets the price without any discounts.
     * @return the price of the rental without any discounts.
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets the price without any discounts.
     * @param price  the price of the rental without any discounts.
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Gets the information whether the vehicle has been in the wide area of the town during the rental.
     * @return whether the vehicle has been in the wide area of the town during the rental.
     */
    public boolean isWideDistance() {
        return wideDistance;
    }

    /**
     * Sets the information whether the vehicle has been in the wide area of the town during the rental.
     * @param wideDistance  whether the vehicle has been in the wide area of the town during the rental.
     */
    public void setWideDistance(boolean wideDistance) {
        this.wideDistance = wideDistance;
    }

    /**
     * Parses a file to a new Receipt object.
     * @param line  the name of the file that needs to be parsed to Receipt object.
     * @return new Receipt object parsed from the file.
     */
    @Override
    public Receipt parse(String line) {
        List<String> lines = DataModifier.getAllLines(line);
        Receipt receipt = new Receipt();
        List<String> header = DataModifier.getReceiptHeader();
        for(int i=0; i<header.size();i++)
            lines.removeFirst();

        String[] customerInfo = lines.get(0).split(":");
        String[] customerName = customerInfo[1].split(" ");
        String customerNameFinal = customerName[1];

        Customer c = DataModifier.getCustomerByName(customerNameFinal);
        receipt.setCustomer(c);
        lines.removeFirst();

        String[] vehicleInfo = lines.get(0).split(":");
        String[] vehicleId = vehicleInfo[1].split(" ");
        String vehicleIdFinal = vehicleId[1];
        lines.removeFirst();

        Vehicle v = DataModifier.getVehicleById(vehicleIdFinal);
        receipt.setVehicle(v);

        String[] failureInfo = lines.get(0).split(":");
        String[] failure = failureInfo[1].split(" ");
        boolean failureHappened = "da".equals(failure[1]);
        receipt.setFailure(failureHappened);
        lines.removeFirst();

        String[] wideDistanceInfo = lines.get(0).split(":");
        String[] wideDistance = wideDistanceInfo[1].split(" ");
        boolean wideDistanceHappened = "da".equals(wideDistance[1]);
        receipt.setWideDistance(wideDistanceHappened);
        lines.removeFirst();

        String[] basePriceInfo = lines.get(0).split(":");
        String[] basePrice = basePriceInfo[1].split(" ");
        Double priceFinal = Double.parseDouble(basePrice[1]);
        receipt.setPrice(priceFinal);
        lines.removeFirst();

        String[] discountInfo = lines.get(0).split(":");
        String[] discount = discountInfo[1].split(" ");
        Double discountFinal = Double.parseDouble(discount[1]);
        receipt.setDiscount(discountFinal);
        lines.removeFirst();

        String[] promDiscountInfo = lines.get(0).split(":");
        String[] promDiscount = promDiscountInfo[1].split(" ");
        Double promDiscountFinal = Double.parseDouble(promDiscount[1]);
        receipt.setPromDiscount(promDiscountFinal);
        lines.removeFirst();

        String[] totalPriceInfo = lines.get(0).split(":");
        String[] totalPrice = totalPriceInfo[1].split(" ");
        Double totalPriceFinal = Double.parseDouble(totalPrice[1]);
        receipt.setTotalPrice(totalPriceFinal);
        lines.removeFirst();

        lines.removeFirst();
        LocalDateTime localDateTime = LocalDateTime.parse(lines.get(0));

        receipt.setDateTime(localDateTime);

        return receipt;
    }
}
