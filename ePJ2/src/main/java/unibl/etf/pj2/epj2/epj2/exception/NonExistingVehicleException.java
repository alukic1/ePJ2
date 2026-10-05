package unibl.etf.pj2.epj2.epj2.exception;


/**
 * Thrown to indicate that an attempt was made to rent a vehicle that does not exist.
 *
 * <p>This exception is used to signal errors that occur when attempting to parse data
 * and it is found that the vehicle being rented does not exist in the system.</p>

 */
public class NonExistingVehicleException extends Exception{

    /**
     * Default constructor. Constructs an {@code NonExistingVehicleException} with the default message.
     */
    public NonExistingVehicleException(){
        super("Ne postoji vozilo sa datim ID-em.");
    }

    /**
     *
     * Constructs a {@code NonExistingVehicleException} with the specified detail message.
     *
     * @param msg the detail message
     */
    public NonExistingVehicleException(String msg){
        super(msg);
    }
}
