package unibl.etf.pj2.epj2.epj2.rental;

import javafx.application.Platform;
import unibl.etf.pj2.epj2.epj2.data.DataModifier;
import unibl.etf.pj2.epj2.epj2.data.Parser;
import unibl.etf.pj2.epj2.epj2.exception.InvalidFormatException;
import unibl.etf.pj2.epj2.epj2.exception.NonExistingVehicleException;
import unibl.etf.pj2.epj2.epj2.map.Simulation;
import unibl.etf.pj2.epj2.epj2.vehicle.Vehicle;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Random;

/**
 * This class  a vehicle rental in the application.
 * It extends the {@code Thread} class to allow for concurrent execution
 * of rental-related tasks, such as processing rental transactions.
 *
 * <p>The {@code Rental} class implements the {@code Parser<Rental>} interface
 * to provide functionality for parsing rental data from a file, and
 * the {@code Comparable<Rental>} interface to allow rentals to be compared
 * based on specific criteria - rental date and time.</p>
 *
 * <p>Each {@code Rental} object contains information about the rental,
 * including the vehicle being rented, the customer renting the vehicle,
 * the rental start date and time, the duration of the rental, the position
 * of the rental start and the position of the rental end, as well as
 * information needed for receipt generating.</p>
 *
 * @see java.lang.Thread
 * @see java.lang.Comparable
 * @see unibl.etf.pj2.epj2.epj2.data.Parser
 */
public class Rental extends Thread implements Parser<Rental>, Comparable<Rental> {

    private static final Integer DISCHARGE_LEVEL = 2;
    private LocalDateTime rentalDate;
    private Customer customer;
    private Vehicle vehicle;
    private Integer durationSeconds;
    private boolean vehicleFailure;
    private Failure failure;
    private boolean promDiscount;

    private Integer startRow;
    private Integer startCol;
    private Integer endRow;
    private Integer endCol;
    private boolean finished;
    private boolean wideDistance;

    /**
     * Default constructor.
     */
    public Rental(){}

    /**
     *
     * Parameterized constructor.
     * @param rentalDate  the date and time of the rental,
     * @param customerName  the name of the customer renting a vehicle,
     * @param vehicle  the vehicle being rented,
     * @param durationSeconds  the duration of the rental in seconds,
     * @param vehicleFailure  whether there has been a failure during the rental,
     * @param promDiscount  whether there is a promotion discount for the rental,
     * @param startRow  the row coordinate of the pick-up place,
     * @param startCol  the column coordinate of the pick-up place,
     * @param endRow  the row coordinate of the drop-off place,
     * @param endCol  the column coordinate of the drop-off place.
     */
    public Rental(LocalDateTime rentalDate, Customer customerName, Vehicle vehicle, Integer durationSeconds, boolean vehicleFailure, boolean promDiscount, Integer startRow, Integer startCol, Integer endRow, Integer endCol) {
        this.rentalDate = rentalDate;
        this.customer = customerName;
        this.vehicle = vehicle;
        this.durationSeconds = durationSeconds;
        this.vehicleFailure = vehicleFailure;
        this.promDiscount = promDiscount;
        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;
        finished=false;

        if(isWide(startRow, startCol) || isWide(endRow, endCol))
            wideDistance = true;
        else
            wideDistance=false;

        if(vehicleFailure){
            String id = vehicle.getId();
            String desc = "Desio se kvar tokom iznajmljivanja od strane " + getCustomerName();
            failure = new Failure(DataModifier.getVehicleTypeById(id), id, getRentalDate(), desc);
           }
        else failure=null;
    }

    /**
     * Gets the information whether the vehicle has been in wide area of the town.
     * @return whether the vehicle has been in wide area of the town.
     */
    public boolean isWideDistance() {
        return wideDistance;
    }

    /**
     * Sets the information whether there is the appearance of the vehicle in wide area of the town.
     * @param wideDistance whether there is the appearance of the vehicle in wide area of the town.
     */
    public void setWideDistance(boolean wideDistance) {
        this.wideDistance = wideDistance;
    }


    /**
     * Gets the information whether the rental has been finished.
     * @return whether the rental has been finished.
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * Sets the information whether the rental has been finished.
     * @param finished  whether the rental has been finished.
     */
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    /**
     * Gets the rental date and time.
     * @return rental date and time.
     */
    public LocalDateTime getRentalDate() {
        return rentalDate;
    }

    /**
     * Gets the rental date.
     * @return rental date.
     */
    public LocalDate getDate(){
        return rentalDate.toLocalDate();
    }

    /**
     * Sets the rental date and time.
     * @param rentalDate  rental date and time.
     */
    public void setRentalDate(LocalDateTime rentalDate) {
        this.rentalDate = rentalDate;
    }

    /**
     * Gets the customer name.
     * @return name of the customer renting a vehicle.
     */
    public String getCustomerName() {
        return customer.getName();
    }

    /**
     * Gets the customer.
     * @return customer renting a vehicle.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the customer.
     * @param customer  the customer renting a vehicle.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Gets the vehicle.
     * @return the vehicle being rented.
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Sets the vehicle.
     * @param vehicle  the vehicle being rented.
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Gets the duration.
     * @return duration of the rental in seconds.
     */
    public Integer getDurationSeconds() {
        return durationSeconds;
    }

    /**
     * Sets the duration.
     * @param durationSeconds  the duration of the rental in seconds.
     */
    public void setDurationSeconds(Integer durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    /**
     * Gets the pick-up row index.
     * @return the row coordinate of the pick-up place.
     */
    public Integer getStartRow() {
        return startRow;
    }

    /**
     * Sets the pick-up row index.
     * @param startRow  the row coordinate of the pick-up place.
     */
    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    /**
     * Gets the pick-up column index.
     * @return the column coordinate of the pick-up place.
     */
    public Integer getStartCol() {
        return startCol;
    }

    /**
     * Sets the pick-up column index.
     * @param startCol  the column coordinate of the pick-up place.
     */
    public void setStartCol(Integer startCol) {
        this.startCol = startCol;
    }

    /**
     * Gets the drop-off row index.
     * @return the row coordinate of the drop-off place.
     */
    public Integer getEndRow() {
        return endRow;
    }

    /**
     * Sets the drop-off row index.
     * @param endRow  the row coordinate of the drop-off place.
     */
    public void setEndRow(Integer endRow) {
        this.endRow = endRow;
    }

    /**
     * Gets the drop-off column index.
     * @return the column coordinate of the drop-off place.
     */
    public Integer getEndCol() {
        return endCol;
    }

    /**
     * Sets the drop-off column index.
     * @param endCol  the column coordinate of the drop-off place.
     */
    public void setEndCol(Integer endCol) {
        this.endCol = endCol;
    }


    /**
     * Gets the information whether there has been a failure during the rental.
     * @return whether there has been a failure during the rental.
     */
    public boolean isVehicleFailure() {
        return vehicleFailure;
    }

    /**
     * Sets the information whether there has been a failure during the rental.
     * @param vehicleFailure  whether there has been a failure during the rental.
     */
    public void setVehicleFailure(boolean vehicleFailure) {
        this.vehicleFailure = vehicleFailure;
    }

    /**
     * Gets the information whether there is a promotion discount for the rental.
     * @return whether there is a promotion discount for the rental.
     */
    public boolean isPromDiscount() {
        return promDiscount;
    }

    /**
     * Sets the information whether there is a promotion discount for the rental.
     * @param promDiscount  whether there is a promotion discount for the rental.
     */
    public void setPromDiscount(boolean promDiscount) {
        this.promDiscount = promDiscount;
    }

    /**
     *  Gets the information about failure.
     * @return the information about failure.
     */
    public Failure getFailure() {
        return failure;
    }

    /**
     *
     * Sets the information about failure.
     * @param failure - information about failure.
     */
    public void setFailure(Failure failure) {
        this.failure = failure;
    }

    /**
     * Generates a receipt for the current rental.
     * This method assigns the necessary values to the {@code Receipt} object,
     * calculates the total price for payment, and then calls the
     * {@code DataModifier.saveReceipt} function to save and deliver the receipt
     * to the customer as a .txt file.
     *
     * @see Receipt
     * @see DataModifier#saveReceipt(Receipt)
     */
    public void generateReceipt() {


            Receipt receipt = new Receipt();
            receipt.setCustomer(this.customer);
            receipt.setVehicle(this.vehicle);
            receipt.setDateTime(this.rentalDate);
            receipt.setFailure(this.vehicleFailure);
            receipt.setWideDistance(this.wideDistance);

            double basePrice, totalPrice = 0, prom = 0, discount = 0;
            double unitPrice = DataModifier.getUnitPrice(DataModifier.getVehicleTypeById(this.vehicle.getId()));
            basePrice = unitPrice * this.durationSeconds;
            boolean isDiscountIncluded = false;

            isDiscountIncluded = DataModifier.isTenthDiscount(this.customer);

            if (vehicleFailure) basePrice = 0;
            if (this.promDiscount)
                prom = basePrice * (DataModifier.getPromDiscount()/100.00);

            if (isDiscountIncluded)
                discount = basePrice * (DataModifier.getDiscount()/100.00);

            if (wideDistance)
                basePrice *= DataModifier.getDistanceWide();
            else
                basePrice *= DataModifier.getDistanceNarrow();


            receipt.setPrice(basePrice);
            totalPrice = basePrice - prom;
            totalPrice -= discount;


            receipt.setDiscount(discount);
            receipt.setPromDiscount(prom);
            receipt.setTotalPrice(totalPrice);

            receipt.getVehicle().addExpense(totalPrice*0.2);
            if(this.vehicleFailure){
                receipt.getVehicle().addExpense(DataModifier.getRepairCost(receipt));
            }

            DataModifier.saveReceipt(receipt);

    }

    /**
     * Calculates whether the field is in wide area.
     * @param row the current row coordinate
     * @param col the current column coordinate
     * @return whether the field is in wide area of the town.
     */
    private boolean isWide(int row, int col){
        if((row >= 0 && row<=4) || (row>=15 && row<=19) || (col >= 0 && col<=4) || (col>=15 && col<=19))
            return true;
        else
            return false;
    }

    /**
     * Gets the String representation of the rental.
     * @return the string representation of the rental in format "VehicleID".
     */
    @Override
    public String toString() {
        return vehicle.getId();
    }


    /**
     * Executes the rental vehicle's movement on the map.
     *
     * <p>This method determines the number of steps, the speed of retention at
     * each field, and the path for the rental. It identifies the next field
     * for the vehicle to move to, checks whether the vehicle passes through
     * the wide area of the town, and updates the display of the rental on the map.</p>
     *
     * <p>The method is intended to be run in a separate thread, allowing
     * for concurrent execution of the vehicle's movement logic.</p>
     *
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {

        synchronized (this.vehicle) {

            Random rand = new Random();
            this.vehicle.setOccupied(true);

            System.out.println(this + "poceo");

            int steps = Math.abs(endRow - startRow) + Math.abs(endCol - startCol);
            double stayAtField = (double) durationSeconds / steps;

            int currentRow = startRow, currentCol = startCol;
            int nextRow=0, nextCol=0;
            int whenToBreak = steps+1;
            if(isVehicleFailure()){
                whenToBreak=rand.nextInt(steps);
            }

                  Simulation.map[currentRow][currentCol].add(this);
                  updateCell(currentRow, currentCol);
            try {
                Thread.sleep((long) (stayAtField * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            while(!finished){


            for(int i=0; i<steps && !finished; i++) {
                nextRow=currentRow; nextCol=currentCol;


                    if ((currentRow == endRow && currentCol == endCol) || (i==whenToBreak) ||
                    this.vehicle.getCurrentBatteryLevel()<=0) {
                        finished = true;

                        synchronized (this.customer) {
                            this.customer.setNumRentals(this.customer.getNumRentals() + 1);
                            generateReceipt();
                        }

                        try {
                           Thread.sleep((long) (stayAtField * 1000));
                            //    Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Simulation.map[currentRow][currentCol].remove(this);
                        updateCell(currentRow, currentCol);
                        this.vehicle.chargeBattery();
                        this.vehicle.setOccupied(false);
                        break;
                    }


                if(rand.nextBoolean()) {
                    if (currentRow < endRow)
                        nextRow = currentRow + 1;
                    else if (currentRow > endRow)
                        nextRow = currentRow - 1;
                    else {
                        if (currentCol < endCol)
                            nextCol = currentCol + 1;
                        else if (currentCol > endCol)
                            nextCol = currentCol - 1;
                    }
                }
                else{
                    if (currentCol < endCol)
                        nextCol = currentCol + 1;
                    else if (currentCol > endCol)
                        nextCol = currentCol - 1;
                    else{
                        if (currentRow < endRow)
                            nextRow = currentRow + 1;
                        else if (currentRow > endRow)
                            nextRow = currentRow - 1;
                    }
                }

                if(isWide(nextRow, nextCol))
                    wideDistance=true;

                    this.vehicle.setCurrentBatteryLevel(this.vehicle.getCurrentBatteryLevel() - DISCHARGE_LEVEL);

                        synchronized (Simulation.map){

                                Simulation.map[currentRow][currentCol].remove(this);

                                updateCell(currentRow, currentCol);

                                Simulation.map[nextRow][nextCol].add(this);
                                updateCell(nextRow, nextCol);
                                currentRow = nextRow;
                                currentCol = nextCol;

                        }
                            try {
                               Thread.sleep((long) (stayAtField * 1000));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                    }


        }
        this.getVehicle().setOccupied(false);
        System.out.println(this + "zavrsio");
        }
    }

    /**
     * Updates the cell information.
     * @param row the current row coordinate
     * @param col the current column coordinate
     *
     *<p> This method updates the display of the specified cell on the map.It ensures that the visual representation of the
     * cell on the map reflects the current state of the rental or vehicle movement.</p>
     *
     *<p>The method calls a helper method {@code updateCell(int row, int column, String text, String color, double fontSize)}
     *  to perform the actual update of the cell's properties.</p>
     */
    private void updateCell(int row, int col){
        double fontSize=12.0;
        String color = "yellow";
        String vehiclesOnField="";
        if(Simulation.map[row][col].isEmpty())
            color = "white";
        else{
            for(int i=0; i<Simulation.map[row][col].size(); i++){
                vehiclesOnField+=Simulation.map[row][col].get(i).vehicle+" ";
            }
        }

        if(Simulation.map[row][col].size()==2){
            fontSize=10.0;
        }
        else if(Simulation.map[row][col].size()>2)
            fontSize=8.0;

        updateCell(row, col, vehiclesOnField, color, fontSize);
    }

    /**
     * Helper method. Updates the cell with specific information.
     * @param row the current row coordinate
     * @param column the current column coordinate
     * @param text the text to be represented in the specified cell on the map
     * @param color the color of the cell
     * @param fontSize the font size of the text
     *
     */
    private void updateCell(int row, int column, String text, String color, double fontSize) {
        Platform.runLater(() -> Simulation.mapView.updateCell(row, column, text, color, fontSize));
    }


    /**
     * Parses the line to a new Rental object.
     * @param line  line from file that needs to be parsed to Rental object.
     * @return new Rental object parsed from the line if possible.
     * @throws InvalidFormatException if line cannot be parsed to Rental object because of invalid format.
     * @throws NonExistingVehicleException if there is no vehicle with specified ID to be rented.
     */
    @Override
    public Rental parse(String line) throws InvalidFormatException, NonExistingVehicleException {

        try {
            String[] parts = line.split(",");
            String dateString = parts[0];
            LocalDateTime date = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy HH:mm");
            try {
                date = LocalDateTime.parse(dateString, formatter);

            } catch (DateTimeParseException e) {
                throw new InvalidFormatException();
            }

            Vehicle v = DataModifier.getVehicleById(parts[2]);
            if(v==null) throw new NonExistingVehicleException();

            Integer firstRow, firstCol, lastRow, lastCol;
            boolean failure;
            boolean prom;
            Integer duration;

            String cleanedFirstRow = parts[3].replace("\"", "");
            String cleanedFirstCol, cleanedLastRow, cleanedLastCol;
            firstRow = Integer.parseInt(cleanedFirstRow);
 /*       if(firstRow == 0){
            firstCol = 0;
            cleanedLastRow = parts[4].replace("\"", "");
            cleanedLastCol = parts[5].replace("\"", "");
            lastRow = Integer.parseInt(cleanedLastRow);
            lastCol = Integer.parseInt(cleanedLastCol);
            failure = "da".equals(parts[7]);
            prom = "da".equals(parts[8]);
            duration = Integer.parseInt(parts[6]);
        }
        else { */
            cleanedFirstCol = parts[4].replace("\"", "");
            firstCol = Integer.parseInt(cleanedFirstCol);
            cleanedLastRow = parts[5].replace("\"", "");
            cleanedLastCol = parts[6].replace("\"", "");
            lastRow = Integer.parseInt(cleanedLastRow);
            lastCol = Integer.parseInt(cleanedLastCol);

            duration = Integer.parseInt(parts[7]);

            if(firstRow>=Simulation.ROW || firstCol>=Simulation.COL || lastRow>=Simulation.ROW || lastCol>= Simulation.COL)
                throw new InvalidFormatException();

            if(firstRow<0 || firstCol<0 || lastRow<0 || lastCol<0)
                throw new InvalidFormatException();


            if(!"ne".equals(parts[8]) && !"da".equals(parts[8]))
                throw new InvalidFormatException();

            if(!"ne".equals(parts[9]) && !"da".equals(parts[9]))
                throw new InvalidFormatException();


            failure = "da".equals(parts[8]);
            prom = "da".equals(parts[9]);
            //      }

            Customer cus = new Customer(parts[1]);
            if (!Simulation.customers.contains(cus))
                Simulation.customers.add(cus);
            else
                cus = DataModifier.getCustomerByName(parts[1]);
            return new Rental(date, cus, v, duration, failure, prom, firstRow, firstCol, lastRow, lastCol);

        }
        catch (NonExistingVehicleException ex){
            throw new NonExistingVehicleException();
        }
        catch(Exception e){
            throw new InvalidFormatException();
        }
    }

    /**
     * Compares the objects by date.
     * @param o the object to be compared.
     * @return -1 if the current rental is before the "o", 0 if they are at the same time, and 1 if it is after the "o" rental.
     */
    @Override
    public int compareTo(Rental o) {

        if(this.rentalDate.isAfter(o.getRentalDate()))
            return 1;
        if(this.rentalDate.isBefore(o.getRentalDate()))
            return -1;
        return 0;
    }



    /**
     * Gets the information if the objects are equal by vehicle and time.
     * @param oth the object to be compared with.
     * @return boolean value if the objects are equal compared by vehicle and rental date and time.
     */
    @Override
    public boolean equals(Object oth){
        if(oth!=null && oth instanceof Rental){
            Rental other = (Rental)oth;
            if(other.vehicle.equals(this.vehicle) && other.rentalDate.equals(this.rentalDate))
                return true;
        }
        return false;
    }
}
