package unibl.etf.pj2.epj2.epj2.vehicle;

import unibl.etf.pj2.epj2.epj2.data.Parser;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Abstract parent class that   vehicles with all attributes and methods in common. Implements interfaces Parser and Serializable.
 */
public abstract class Vehicle implements Parser<Vehicle>, Serializable {

    private String id;
    private String model;
    private String manufacturer;
    private Double price;
    private Integer currentBatteryLevel;
    private boolean multiplePeopleAllowed;
    transient private boolean occupied;
    transient private Double expense;

    /**
     * Gets the information if the vehicle is occupied.
     * @return true if the vehicle is in use at the moment or false if not.
     */
    public boolean isOccupied() {
        return occupied;
    }

    /**
     * Sets the information if the vehicle is occupied.
     * @param occupied true or false - true if the vehicle is in use and false afterwards when it is free again.
     */
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    /**
     * Gets the ID.
     * @return the unique identifier of the vehicle.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID.
     * @param id the unique identifier of the vehicle.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the model.
     * @return the model of the vehicle.
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the model.
     * @param model the model of the vehicle.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Gets the manufacturer.
     * @return manufacturer of the vehicle.
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Sets the manufacturer.
     * @param manufacturer the manufacturer of the vehicle.
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Gets the purchase price.
     * @return the purchase price of the vehicle.
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets the purchase price.
     * @param price the purchase price of the vehicle.
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Gets the current battery level.
     * @return current battery level.
     */
    public Integer getCurrentBatteryLevel() {
        return currentBatteryLevel;
    }

    /**
     * Sets the current battery level.
     * @param currentBatteryLevel   the current battery level.
     */
    public void setCurrentBatteryLevel(Integer currentBatteryLevel) {
        this.currentBatteryLevel = currentBatteryLevel;
    }

    /**
     * Gets the expense.
     * @return the amount of the expense the vehicle has given to the store.
     */
    public Double getExpense() {
        return expense;
    }

    /**
     * Sets the expense.
     * @param expense   the amount of the expense the vehicle has given to the store.
     */
    public void setExpense(Double expense) {
        this.expense = expense;
    }

    /**
     * Gets the information if multiple people are allowed.
     * @return boolean value whether multiple people are allowed on a vehicle.
     */
    public boolean isMultiplePeopleAllowed() {
        return multiplePeopleAllowed;
    }

    /**
     * Sets the information if multiple people are allowed.
     * @param multiplePeopleAllowed whether multiple people are allowed on a vehicle.
     */
    public void setMultiplePeopleAllowed(boolean multiplePeopleAllowed) {
        this.multiplePeopleAllowed = multiplePeopleAllowed;
    }

    /**
     * Default constructor.
     */
    public Vehicle(){}

    /**
     * Parameterized constructor.
     * @param id   the unique identifier of the vehicle,
     * @param model   the model of the vehicle,
     * @param manufacturer   the manufacturer of the vehicle,
     * @param price   the purchase price of the vehicle.
     */
    public Vehicle(String id, String model, String manufacturer, Double price) {
        this.id = id;
        this.model = model;
        this.manufacturer = manufacturer;
        this.price = price;
        this.currentBatteryLevel = 100;
        this.expense=0.0;

    }

    /**
     * Charges the battery. Sets the current battery level to 100.
     */
    public void chargeBattery(){
        currentBatteryLevel=100;
    }

    /**
     * Gets the String representation of the vehicle.
     * @return String representation of the vehicle in format "ID(Current battery level)".
     */
    @Override
    public String toString(){
        return  this.id + "(" + this.currentBatteryLevel + ") ";
    }

    /**
     * Gets the information if the objects are equal by ID.
     * @param oth the object to be compared with.
     * @return boolean value if the objects are equal compared by ID.
     */
    @Override
    public boolean equals(Object oth){
        if(oth!=null && oth instanceof Vehicle){
            if(this.id.equals(((Vehicle)oth).id))
                return true;
        }
        return false;
    }

    /**
     * Adds new amount of expense to the total amount.
     * @param e new expense to add to the total expense.
     */
    public void addExpense(Double e){
        this.expense+=e;
    }

}
