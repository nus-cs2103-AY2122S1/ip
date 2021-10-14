package pib.pibexception;

import pib.utility.Ui;

/**
 * PibException class that represents all the exceptions that can be thrown by the Pib class functionalities.
 */
public class PibException extends Exception {
    private String errorType;

    /**
     * Constructs a new PibException with an error message
     *
     * @param e error message to be used to call the printError method in Ui class
     */
    public PibException(String e) {
        super(e);
        this.errorType = e;
    }

    /**
     * Calls Ui.printError method to print the error message to user
     *
     * @return String containing response to be printed to user
     */
    public String print() {
        return Ui.printError(errorType);
    }
}
