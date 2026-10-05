package unibl.etf.pj2.epj2.epj2.rental;

import java.util.Random;

/**
 *  a customer who rents a vehicle.
 *
 * <p>This class holds information about a customer, including their name
 * and identification documents.</p>
 */
public class Customer {
    private String name;
    private boolean foreignCitizen;
    private int id;
    private int licence;
    private int numRentals;

    /**
     * Default constructor.
     */
    public Customer(){}

    /**
     * Parameterized constructor.
     * @param name  the customer name.
     */
    public Customer(String name) {
        this.name = name;
        numRentals=0;

        Random rand=new Random();
        foreignCitizen=rand.nextBoolean();
        id=Math.abs(rand.nextInt());
        licence=Math.abs(rand.nextInt());
    }

    /**
     * Gets the customer name.
     * @return the customer name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the customer name.
     * @param name  the customer name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the information if the customer is a foreign citizen.
     * @return whether the customer is a foreign citizen.
     */
    public boolean isForeignCitizen() {
        return foreignCitizen;
    }

    /**
     * Sets the information if the customer is a foreign citizen.
     * @param foreignCitizen  whether the customer is a foreign citizen.
     */
    public void setForeignCitizen(boolean foreignCitizen) {
        this.foreignCitizen = foreignCitizen;
    }

    /**
     * Gets the ID card number.
     * @return the ID card number of the customer.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID card number.
     * @param id  the ID card number of the customer.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the Licence card number.
     * @return the Licence card number of the customer.
     */
    public int getLicence() {
        return licence;
    }

    /**
     * Sets the Licence card number.
     * @param licence  the Licence card number of the customer.
     */
    public void setLicence(int licence) {
        this.licence = licence;
    }

    /**
     * Gets the number of rentals.
     * @return the number of rentals customer has made.
     */
    public int getNumRentals() {
        return numRentals;
    }

    /**
     * Sets the number of rentals.
     * @param numRentals  the number of rentals customer has made.
     */
    public void setNumRentals(int numRentals) {
        this.numRentals = numRentals;
    }

    /**
     * Gets the information if the objects are equal by name.
     * @param oth the object to be compared with.
     * @return boolean value if the objects are equal compared by name.
     */
    @Override
    public boolean equals(Object oth){
        if(oth!=null && oth instanceof Customer){
            Customer other=(Customer)oth;
            if(other.getName().equals(this.getName())){
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the String representation of the customer.
     * @return String representation of the customer in format "Customer name".
     */
    @Override
    public String toString(){
        return this.name;
    }
}
