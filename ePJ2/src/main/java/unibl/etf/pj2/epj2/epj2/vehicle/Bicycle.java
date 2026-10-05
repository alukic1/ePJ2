package unibl.etf.pj2.epj2.epj2.vehicle;

import unibl.etf.pj2.epj2.epj2.exception.InvalidFormatException;


/**
 * Specific implementation of abstract class Vehicle. Represents electric bicycles.
 * */
public class Bicycle extends Vehicle {

   private Integer reach;

    /**
     * Gets the reach.
     * @return reach value.
     */
    public Integer getReach() {
        return reach;
    }

    /**
     * Sets the reach.
     * @param reach  reach value.
     */
    public void setReach(Integer reach) {
        this.reach = reach;
    }

    /**
     * Default constructor.
     */
    public Bicycle() {
        super();
    }

    /**
     * Parameterized constructor.
     * @param id  the unique identifier of the vehicle,
     * @param model  the model of the vehicle,
     * @param manufacturer  the manufacturer of the vehicle,
     * @param price  the purchase price of the vehicle.
     * @param reach  the reach value.
     */
    public Bicycle(String id, String model, String manufacturer, Double price, Integer reach){
        super(id, model, manufacturer, price);
        this.reach = reach;
        this.setMultiplePeopleAllowed(false);
    }

    /**
     * Parses the line to a new Bicycle object.
     * @param line  line from file that needs to be parsed to Bicycle object.
     * @return new Bicycle object parsed from the line if possible.
     * @throws InvalidFormatException if line cannot be parsed to Bicycle object.
     */
    @Override
    public Bicycle parse(String line) throws InvalidFormatException {
        try {
            String[] parts = line.split(",");
            Bicycle bike = new Bicycle(parts[0], parts[2], parts[1], Double.valueOf(parts[4]), Integer.parseInt(parts[5]));
            return bike;
        }
        catch(Exception e){
            throw new InvalidFormatException();
        }
    }

}
