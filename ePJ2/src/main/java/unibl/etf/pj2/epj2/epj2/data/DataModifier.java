package unibl.etf.pj2.epj2.epj2.data;

import unibl.etf.pj2.epj2.epj2.exception.InvalidFormatException;
import unibl.etf.pj2.epj2.epj2.exception.NonExistingVehicleException;
import unibl.etf.pj2.epj2.epj2.map.Simulation;
import unibl.etf.pj2.epj2.epj2.rental.Customer;
import unibl.etf.pj2.epj2.epj2.rental.Failure;
import unibl.etf.pj2.epj2.epj2.rental.Receipt;
import unibl.etf.pj2.epj2.epj2.rental.Rental;
import unibl.etf.pj2.epj2.epj2.vehicle.Bicycle;
import unibl.etf.pj2.epj2.epj2.vehicle.Car;
import unibl.etf.pj2.epj2.epj2.vehicle.Scooter;
import unibl.etf.pj2.epj2.epj2.vehicle.Vehicle;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Handles data management operations such as reading from files, parsing data, and saving receipts.
 * This class provides methods for retrieving vehicle data, rental data, failure data, and receipt information, as well as methods for serialization and deserialization of vehicles.
 */
public class DataModifier {

    private static  String FILE_VEHICLES;
    private static  String FILE_RENTALS;
    private static  String FILE_RECEIPTHEADER;
    private static  String FILE_RECEIPTSEP;
    private static String RECEIPT_FILES_PATH;
    private static String FILE_SERIALIZATION_PATH;
    private static String FILE_SERIALIZATION_CAR;
    private static String FILE_SERIALIZATION_BIKE;
    private static String FILE_SERIALIZATION_SCOOTER;
    private static Double DISTANCE_NARROW;
    private static Double DISTANCE_WIDE;
    private static Double DISCOUNT;
    private static Double DISCOUNT_PROM;
    private static Double CAR_UNIT_PRICE;
    private static Double BIKE_UNIT_PRICE;
    private static Double SCOOTER_UNIT_PRICE;
    private static final Double CAR_REPAIR_COST = 0.07;
    private static final Double BIKE_REPAIR_COST = 0.04;
    private static final Double SCOOTER_REPAIR_COST = 0.02;


    /**
     * Sets all constant values used throughout the application.
     * Loads configuration properties from the "app.properties" file.
     */
    public static void setAllConstants(){

        Properties appProps = new Properties();
        try(InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties")) {
            if(input == null) {
                System.out.println("Properties file not found");
                return;
            }
            appProps.load(input);
                String carUnitString = appProps.getProperty("CAR_UNIT_PRICE");
                CAR_UNIT_PRICE= Double.parseDouble(carUnitString);


                String bikeUnitString = appProps.getProperty("BIKE_UNIT_PRICE");
                BIKE_UNIT_PRICE = Double.parseDouble(bikeUnitString);

                String scooterUnitString = appProps.getProperty("SCOOTER_UNIT_PRICE");
                SCOOTER_UNIT_PRICE = Double.parseDouble(scooterUnitString);

                DISCOUNT = Double.parseDouble(appProps.getProperty("DISCOUNT"));

                DISCOUNT_PROM = Double.parseDouble(appProps.getProperty("DISCOUNT_PROM"));

                DISTANCE_WIDE = Double.parseDouble(appProps.getProperty("DISTANCE_WIDE"));

                DISTANCE_NARROW = Double.parseDouble(appProps.getProperty("DISTANCE_NARROW"));

                RECEIPT_FILES_PATH = "." + File.separator + appProps.getProperty("RECEIPT_FILES_PATH") + File.separator;

                FILE_VEHICLES = "." + File.separator + appProps.getProperty("FILE_VEHICLES");

                FILE_RENTALS = "." + File.separator  + appProps.getProperty("FILE_RENTALS");

                FILE_RECEIPTHEADER = "." + File.separator + appProps.getProperty("FILE_RECEIPTHEADER");

                FILE_RECEIPTSEP = "." + File.separator  + appProps.getProperty("FILE_RECEIPTSEP");

                FILE_SERIALIZATION_PATH = "." + File.separator + appProps.getProperty("FILES_SERIALIZATION");

                FILE_SERIALIZATION_CAR = FILE_SERIALIZATION_PATH + File.separator + appProps.getProperty("FILE_SERIALIZATION_CAR");

                FILE_SERIALIZATION_BIKE=FILE_SERIALIZATION_PATH + File.separator + appProps.getProperty("FILE_SERIALIZATION_BIKE");

                FILE_SERIALIZATION_SCOOTER = FILE_SERIALIZATION_PATH + File.separator + appProps.getProperty("FILE_SERIALIZATION_SCOOTER");


        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all car objects from the vehicles file.
     *
     * @return a list of Car objects parsed from the vehicles file
     */
    public static List<Car> getAllCars(){
    List<Car> cars = new ArrayList<>();
    List<String> lines = getAllLines(FILE_VEHICLES);
    Car car = new Car();
    try {
        for (String line : lines) {
            try {
                if (line.endsWith("automobil")) {
                    Car car2 = car.parse(line);
                    if (!cars.contains(car2))
                        cars.add(car2);
                }
            }
            catch(InvalidFormatException e){
                e.printStackTrace();
            }
        }
    }
    catch(NullPointerException e){
        e.printStackTrace();
    }
         return cars;
    }

    /**
     * Retrieves all bicycle objects from the vehicles file.
     *
     * @return a list of Bicycle objects parsed from the vehicles file
     */
    public static List<Bicycle> getAllBicycles(){
        List<Bicycle> bicycles = new ArrayList<>();
        List<String> lines = getAllLines(FILE_VEHICLES);
        Bicycle bicycle = new Bicycle();
        try{
            for(String line : lines){
                if(line.endsWith("bicikl")){
                    try {
                        Bicycle bicycle2 = bicycle.parse(line);
                        if (!bicycles.contains(bicycle2))
                            bicycles.add(bicycle2);
                    }
                    catch(InvalidFormatException e){
                        e.printStackTrace();
                    }
                }
            }
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }
        return bicycles;
    }

    /**
     * Retrieves all scooter objects from the vehicles file.
     *
     * @return a list of Scooter objects parsed from the vehicles file
     */
    public static List<Scooter> getAllScooters(){
        List<Scooter> scooters = new ArrayList<>();
        List<String> lines = getAllLines(FILE_VEHICLES);
        Scooter scooter = new Scooter();
        try {
            for (String line : lines) {
                if (line.endsWith("trotinet")){
                    try {
                        Scooter scooter2 = scooter.parse(line);
                        if (!scooters.contains(scooter2))
                            scooters.add(scooter2);
                    }
                    catch(InvalidFormatException e){
                        e.printStackTrace();
                    }
                }
            }
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }
        return scooters;
    }


    /**
     * Retrieves all lines from the specified file.
     *
     * @param name the name of the file to read
     * @return a list of strings representing each line in the file
     */
    public static List<String> getAllLines(String name){
        List<String> lines=null;
        try {
            lines = Files.readAllLines(Paths.get(name));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return lines;
    }


    /**
     * Retrieves all vehicles (cars, bicycles, and scooters) from the files.
     *
     * @return a list of all vehicles parsed from the files
     */
    public static List<Vehicle> getAllVehicles(){
        List<Vehicle> vehicles = new ArrayList<>();
        List<Car> cars = getAllCars();
        List<Bicycle> bicycles = getAllBicycles();
        List<Scooter> scooters = getAllScooters();

        for(Car car : cars) {
            vehicles.add(car);
        }
        for(Bicycle bicycle : bicycles) {
            vehicles.add(bicycle);
        }
        for(Scooter scooter : scooters) {
            vehicles.add(scooter);
        }

        return vehicles;
    }

    /**
     * Retrieves the vehicle with the specified ID.
     *
     * @param id the ID of the vehicle to retrieve
     * @return the vehicle object with the specified ID, or null if not found
     */
    public static Vehicle getVehicleById(String id){
        for(Vehicle vehicle : Simulation.vehicles){
            if(vehicle.getId().equals(id))
                return vehicle;
        }
        return null;
    }

    /**
     * Retrieves all rentals from the rentals file.
     *
     * @return a list of all rentals parsed from the rentals file
     */
    public static List<Rental> getAllRentals(){
        List<String> lines = getAllLines(FILE_RENTALS);
        List<Rental> rentals = new ArrayList<>();
        Rental rental = new Rental();
        for(int i=1; i<lines.size(); i++){
            try {
                Rental thread = rental.parse(lines.get(i));
                thread.setDaemon(true);
                if (!rentals.contains(thread))
                    rentals.add(thread);
            }
            catch(InvalidFormatException | NonExistingVehicleException e){
                e.printStackTrace();
            }
        }

        rentals.sort(Rental::compareTo);

        return rentals;
    }

    /**
     * Retrieves all failures from the active rentals.
     *
     * @return a list of all failures detected during active rentals
     */
    public static List<Failure> getAllFailures(){

        List<Failure> failures = new ArrayList<>();

        for(Rental rental: Simulation.rentals){
            if(rental.isVehicleFailure()){
                failures.add(rental.getFailure());
            }
        }
        return failures;
    }

    /**
     * Retrieves the vehicle type (car, bicycle, or scooter) based on the provided ID.
     *
     * @param id the ID of the vehicle
     * @return a string representing the vehicle type, or null if the ID does not match any vehicle
     */
    public static String getVehicleTypeById(String id){
        String type=null;
        List<Vehicle> vehicles = getAllVehicles();
        for(Vehicle vehicle : vehicles){
            if(vehicle.getId().equals(id)){
                if(vehicle instanceof Car)
                    type = "automobil";
                else if(vehicle instanceof Bicycle)
                    type = "bicikl";
                else if(vehicle instanceof Scooter)
                    type = "trotinet";
                break;
            }
        }
        return type;
    }

    /**
     * Retrieves the header information for receipts.
     *
     * @return a list of strings representing the header lines for receipts
     */
    public static List<String> getReceiptHeader(){
        return getAllLines(FILE_RECEIPTHEADER);
    }

    /**
     * Checks if the customer is eligible for a tenth rental discount.
     *
     * @param customer the customer object
     * @return true if the customer is eligible for the discount, false otherwise
     */
    public static boolean isTenthDiscount(Customer customer){
        if(customer.getNumRentals()%10==0 && customer.getNumRentals()!=0)
            return true;

        return false;
    }

    /**
     * Retrieves the promotional discount value.
     *
     * @return the promotional discount value
     */
    public static Double getPromDiscount(){
      return DISCOUNT_PROM;
    }

    /**
     * Retrieves the standard discount value.
     *
     * @return the standard discount value
     */
    public static Double getDiscount(){
        return DISCOUNT;
    }

    /**
     * Retrieves the narrow distance price.
     *
     * @return the narrow distance price
     */
    public static Double getDistanceNarrow(){
        return DISTANCE_NARROW;
    }

    /**
     * Retrieves the wide distance price.
     *
     * @return the wide distance price
     */
    public static Double getDistanceWide(){
        return DISTANCE_WIDE;
    }

    /**
     * Retrieves the unit price for the specified vehicle type.
     *
     * @param type the type of vehicle (car, bicycle, scooter)
     * @return the unit price for the specified vehicle type
     */
    public static Double getUnitPrice(String type){

            if(type.equals("automobil")){
               return CAR_UNIT_PRICE;
            }
            else if(type.equals("bicikl")){

                return BIKE_UNIT_PRICE;
            }
            else if(type.equals("trotinet")){

                return SCOOTER_UNIT_PRICE;
            }
            return 0.0;

    }


    /**
     * Retrieves the receipt separator line.
     *
     * @return the receipt separator line
     */
    private static String getReceiptSeparator(){
        List<String> lines = getAllLines(FILE_RECEIPTSEP);
        return lines.get(0);
    }

    /**
     * Saves a receipt to a text file.
     *
     * @param receipt the receipt object to be saved
     */
    public static void saveReceipt(Receipt receipt){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String formattedDateTime = receipt.getDateTime().format(formatter);

        String price = String.format("%.2f",receipt.getPrice());
        String discount = String.format("%.2f",receipt.getDiscount());
        String prom = String.format("%.2f",receipt.getPromDiscount());
        String totalPrice = String.format("%.2f",receipt.getTotalPrice());
        String yes = "da";
        String no = "ne";

        String receiptFileName = RECEIPT_FILES_PATH  + receipt.getCustomer() + "_" + receipt.getVehicle().getId() + "_" + formattedDateTime + ".txt";
        List<String> header = getReceiptHeader();

        try{
            PrintWriter writer = new PrintWriter(new FileWriter(receiptFileName));
            for(String line : header)
                writer.println(line);
            writer.println("Korisnik: " + getCustomerString(receipt.getCustomer(), receipt.getVehicle()));
            writer.println("Vozilo: " + receipt.getVehicle().getId());
            writer.println("Kvar: " + (receipt.isFailure() ? yes : no));
            writer.println("Siri dio grada: " + (receipt.isWideDistance() ? yes : no));
            writer.println("Cijena: " + price);
            writer.println("Popust: " + discount);
            writer.println("Promocija: " + prom);
            writer.println("Ukupan iznos za placanje: " + totalPrice);
            writer.println(getReceiptSeparator());
            writer.println(receipt.getDateTime().toString());
            writer.println("Izdao: Ana");
            writer.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    /**
     * Retrieves the customer information as a formatted string.
     *
     * @param customer the customer object
     * @param v        the vehicle object
     * @return the formatted string containing customer information
     */
    private static String getCustomerString(Customer customer, Vehicle v){

        if(v instanceof Car){
            String cus = customer.getName();
            String doc = customer.isForeignCitizen() ? " Pasos: " : " Licna karta: ";
            cus+=doc;
            cus+=customer.getId();
            cus+=" Vozacka dozvola: ";
            cus+=customer.getLicence();
            return cus;
        }
        else
            return customer.getName();
    }

    /**
     * Retrieves the customer object by its name.
     *
     * @param name the name of the customer
     * @return the customer object if found, null otherwise
     */
    public static Customer getCustomerByName(String name){
        for(Customer customer : Simulation.customers){
            if(customer.getName().equals(name))
                return customer;
        }
        return null;
    }

    /**
     * Retrieves all receipts saved in the system.
     *
     * @return the list of all receipts
     */
    public static List<Receipt> getAllReceipts(){
        File file = new File(RECEIPT_FILES_PATH);
        File[] files=null;
        List<String> receiptNames = new ArrayList<>();
        List<Receipt> receipts  = new ArrayList<>();
        Receipt r = new Receipt();
        if(file.isDirectory()){
            files = file.listFiles();
        }
        try{
        for(File f : files){
            if(f.isFile()){
                String fileName = RECEIPT_FILES_PATH + f.getName();
                receiptNames.add(fileName);
            }
        }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        for(String receipt : receiptNames){
            receipts.add(r.parse(receipt));
        }

        return receipts;
    }

    /**
     * Retrieves the repair cost for the given receipt.
     *
     * @param r the receipt object
     * @return the repair cost if applicable, otherwise 0.0
     */
    public static Double getRepairCost(Receipt r){
        Double cost = 0.0;
        if(r.isFailure()){
            cost = getRepairCoeff(r.getVehicle());
            cost*=r.getVehicle().getPrice();
        }
        return cost;
    }

    /**
     * Retrieves the repair coefficient based on the vehicle type.
     *
     * @param v the vehicle object
     * @return the repair coefficient
     */
    private static Double getRepairCoeff(Vehicle v){

        if(v instanceof Car){
            return CAR_REPAIR_COST;
        }
        else if(v instanceof Bicycle){
            return BIKE_REPAIR_COST;
        }
        else return SCOOTER_REPAIR_COST;
    }


    /**
     * Serializes vehicle objects.
     *
     * @param car     the car object to be serialized
     * @param bike    the bicycle object to be serialized
     * @param scooter the scooter object to be serialized
     */
    public static void serializeVehicles(Car car, Bicycle bike, Scooter scooter){

        try(ObjectOutputStream  oos = new ObjectOutputStream(new FileOutputStream(new File(FILE_SERIALIZATION_CAR)))){
            oos.writeObject(car);
        }
        catch(IOException e){
            e.printStackTrace();
        }

        try(ObjectOutputStream  oos = new ObjectOutputStream(new FileOutputStream(new File(FILE_SERIALIZATION_BIKE)))){
            oos.writeObject(bike);
        }
        catch(IOException e){
            e.printStackTrace();
        }

        try(ObjectOutputStream  oos = new ObjectOutputStream(new FileOutputStream(new File(FILE_SERIALIZATION_SCOOTER)))){
            oos.writeObject(scooter);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }


    /**
     * Deserializes the car object.
     *
     * @return the deserialized car object
     */
    public static Car deserializeCar(){
        Car c=null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(FILE_SERIALIZATION_CAR)))){
             c = (Car)ois.readObject();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return c;
    }

    /**
     * Deserializes the bicycle object.
     *
     * @return the deserialized bicycle object
     */
    public static Bicycle deserializeBicycle(){
        Bicycle b=null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(FILE_SERIALIZATION_BIKE)))){
            b= (Bicycle)ois.readObject();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return b;
    }

    /**
     * Deserializes the scooter object.
     *
     * @return the deserialized scooter object
     */
    public static Scooter deserializeScooter(){
        Scooter s=null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(FILE_SERIALIZATION_SCOOTER)))){
            s = (Scooter) ois.readObject();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return s;
    }
}
