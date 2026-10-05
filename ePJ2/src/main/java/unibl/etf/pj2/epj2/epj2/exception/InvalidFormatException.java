package unibl.etf.pj2.epj2.epj2.exception;

/**
 * Thrown to indicate that an object could not be parsed due to an invalid format in the input file.
 *
 * <p>This exception is used to signal errors that occur when attempting to parse data from an input file
 * where the data format does not match the expected format.</p>
 *
 */
public class InvalidFormatException extends Exception{

     /**
     * Constructs an {@code InvalidFormatException} with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidFormatException(String message) {
        super(message);
    }

    /**
     * Default constructor. Constructs an {@code InvalidFormatException} with the default message.
     */
    public InvalidFormatException() {
        super("Nevalidan format za parsiranje.");
    }
}
