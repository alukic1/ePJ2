package unibl.etf.pj2.epj2.epj2.rental;

import java.time.LocalDateTime;

/**
 *  a failure of a vehicle.
 *
 * <p>This class holds information about a vehicle failure, including the
 * type of vehicle, the vehicle ID, the time of the failure, and a description
 * of the failure.</p>
 */
public class Failure {
    private String vehicleType;
    private String vehicleId;
    private LocalDateTime failureDateTime;
    private String failureDescription;

    /**
     * Default constructor.
     */
    public Failure(){}

    /**
     * Parameterized constructor.
     * @param vehicleType  the type of the vehicle,
     * @param vehicleId  the ID of the vehicle,
     * @param failureDateTime  the date and time when the failure has happened,
     * @param failureDescription  the description of the failure.
     */
    public Failure(String vehicleType, String vehicleId, LocalDateTime failureDateTime, String failureDescription) {
        this.vehicleType = vehicleType;
        this.vehicleId = vehicleId;
        this.failureDateTime = failureDateTime;
        this.failureDescription = failureDescription;
    }

    /**
     * Gets the vehicle type.
     * @return the vehicle type.
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * Sets the vehicle type.
     * @param vehicleType  the vehicle type.
     */
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * Gets the vehicle ID.
     * @return the vehicle ID.
     */
    public String getVehicleId() {
        return vehicleId;
    }

    /**
     * Sets the vehicle ID.
     * @param vehicleId  the vehicle ID.
     */
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * Gets the date and time of the failure.
     * @return the date and time of the failure.
     */
    public LocalDateTime getFailureDateTime() {
        return failureDateTime;
    }

    /**
     * Sets the date and time of the failure.
     * @param failureDateTime  the date and time of the failure.
     */
    public void setFailureDateTime(LocalDateTime failureDateTime) {
        this.failureDateTime = failureDateTime;
    }

    /**
     * Gets the description of the failure.
     * @return the description of the failure.
     */
    public String getFailureDescription() {
        return failureDescription;
    }

    /**
     * Sets the description of the failure.
     * @param failureDescription  the description of the failure.
     */
    public void setFailureDescription(String failureDescription) {
        this.failureDescription = failureDescription;
    }
}
