package pib.pibexception;

import pib.Ui;

public class PibException extends Exception {
    private String errorType;

    public PibException(String e) {
        super(e);
        this.errorType = e;
    }

    public void print() {
        Ui.printError(errorType);
    }
}
