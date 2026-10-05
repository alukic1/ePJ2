package unibl.etf.pj2.epj2.epj2.data;

import unibl.etf.pj2.epj2.epj2.exception.InvalidFormatException;
import unibl.etf.pj2.epj2.epj2.exception.NonExistingVehicleException;

/**
 *
 * Defines a contract for parsing objects from strings.
 *
 * <p>This interface provides a method for parsing objects from a given string.
 * Implementations of this interface should specify how the string is parsed and how the resulting object is constructed.</p>
 *
 * <p>The {@code parse} method can throw the following exceptions:</p>
 * <ul>
 *     <li>{@link InvalidFormatException} - if the format of the input string is invalid.</li>
 *     <li>{@link NonExistingVehicleException} - if the string refers to a vehicle that does not exist.</li>
 * </ul>
 *
 * @param <T> the type of object that this parser can parse
 *
 * @see InvalidFormatException
 * @see NonExistingVehicleException
 */
public interface Parser<T> {

    /**
     * Parses an object from the specified string.
     *
     * @param line the string to parse
     * @return the parsed object of type {@code T}
     * @throws InvalidFormatException if the format of the input string is invalid
     * @throws NonExistingVehicleException if the string refers to a vehicle that does not exist
     */
    abstract public T parse(String line) throws InvalidFormatException, NonExistingVehicleException;
}
