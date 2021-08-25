package pib.pibexception;

import pib.Ui;

/**
 * PibException class that represents all the exceptions that can be thrown by the Pib class functionalities.
 */
public class PibException extends Exception {
    private String errorType;

    /**
     * Public constructor for this class
     *
     * @param e error message to be used to call the printError method in Ui class
     */
    public PibException(String e) {
        super(e);
        this.errorType = e;
    }

    /**
     * Calls Ui.printError method to print the error message to user
     */
    public void print() {
        Ui.printError(errorType);
    }
}
