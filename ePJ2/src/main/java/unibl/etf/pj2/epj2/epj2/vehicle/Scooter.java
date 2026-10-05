package unibl.etf.pj2.epj2.epj2.vehicle;

import unibl.etf.pj2.epj2.epj2.exception.InvalidFormatException;

/**
 * Specific implementation of abstract class Vehicle. Represents electric scooters.
 * */
public class Scooter extends Vehicle{

    private Integer maxSpeed;

    /**
     * Gets the max speed.
     * @return max speed of the scooter.
     */
    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * Sets the max speed.
     * @param maxSpeed  the max speed of the scooter.
     */
    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * Default constructor.
     */
    public Scooter() {
        super();
    }

    /**
     * Parameterized constructor.
     * @param id  the unique identifier of the vehicle,
     * @param model  the model of the vehicle,
     * @param manufacturer  the manufacturer of the vehicle,
     * @param price  the purchase price of the vehicle,
     * @param maxSpeed  the max speed of the scooter.
     */
    public Scooter(String id, String model, String manufacturer, Double price, Integer maxSpeed){
        super(id, model, manufacturer, price);
        this.maxSpeed = maxSpeed;
        this.setMultiplePeopleAllowed(false);
    }

    /**
     * Parses the line to a new Scooter object.
     * @param line  line from file that needs to be parsed to Scooter object.
     * @return new Scooter object parsed from the line if possible.
     * @throws InvalidFormatException if line cannot be parsed to Scooter object.
     */
    @Override
    public Scooter parse(String line) throws InvalidFormatException {
        try {
            String[] parts = line.split(",");
            Scooter scooter = new Scooter(parts[0], parts[2], parts[1], Double.valueOf(parts[4]), Integer.parseInt(parts[6]));
            return scooter;
        }
        catch (Exception e) {
            throw new InvalidFormatException();
        }
    }
}
