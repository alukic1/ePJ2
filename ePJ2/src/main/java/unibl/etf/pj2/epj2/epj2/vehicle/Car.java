package unibl.etf.pj2.epj2.epj2.vehicle;

import unibl.etf.pj2.epj2.epj2.exception.InvalidFormatException;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Specific implementation of abstract class Vehicle. Represents electric cars.
 * */
public class Car extends Vehicle{

    private LocalDate purchaseDate;
    private String description;

    /**
     * Gets the purchase date.
     * @return purchase date of the car.
     */
    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * Sets the purchase date.
     * @param purchaseDate  the purchase date of the car.
     */
    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     * Gets the description.
     * @return description of the car.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     * @param description  the description of the car.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Default constructor.
     */
    public Car(){
        super();
    }

    /**
     *
     * Parameterized constructor.
     * @param id  the unique identifier of the vehicle,
     * @param model  the model of the vehicle,
     * @param manufacturer  the manufacturer of the vehicle,
     * @param price  the purchase price of the vehicle,
     * @param purchaseDate  the purchase date of the vehicle,
     * @param description  the description of the vehicle.
     */
    public Car(String id, String model, String manufacturer, Double price, LocalDate purchaseDate, String description) {
        super(id, model, manufacturer, price);
        this.purchaseDate = purchaseDate;
        this.description = description;
        this.setMultiplePeopleAllowed(true);
    }

    /**
     * Parses the line to a new Car object.
     * @param line  line from file that needs to be parsed to Car object.
     * @return new Car object parsed from the line if possible.
     * @throws InvalidFormatException if line cannot be parsed to Car object.
     */
    @Override
    public Car parse(String line) throws InvalidFormatException {
        try{
        String[] parts = line.split(",");

        String dateString = parts[3];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy.");
        LocalDate date=null;
        try {
            date = LocalDate.parse(dateString, formatter);

        } catch (DateTimeParseException e) {
          throw new InvalidFormatException();
        }

        Car car = new Car(parts[0], parts[2], parts[1], Double.valueOf(parts[4]), date, parts[7]);
        return car;
        }
        catch(Exception e){
            throw new InvalidFormatException();
        }
    }
}
