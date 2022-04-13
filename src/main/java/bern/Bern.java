package bern;

import java.util.ArrayList;

import bern.functionalities.Ui;
import bern.model.Task;

/**
 * This class is where the main method resides.
 */
public class Bern {
    private Ui ui;

    /**
     * Constructor for class bern.Bern.
     */
    public Bern() {
        ui = new Ui();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input, ArrayList<Task> arListTask) {
        return ui.getReplyUniversal(input, arListTask);
    }
}
