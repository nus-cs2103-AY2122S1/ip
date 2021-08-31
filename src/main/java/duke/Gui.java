package duke;

import javafx.scene.control.Label;

/**
 * Class responsible for displaying the output messages the bot gives.
 *
 * @author mrmrinal
 */
public class Gui {
    /**
     * Bot greeting which shows up whenever the bot is instantiated.
     * @return
     */
    public String dukeGreeting() {
        String name = "JARVIS";
        return "Hello I am " + name + ".\n"
                + "Is there anything I can do for you Sir?\n";
    }

    /**
     * Bot message whenever there is an unusual request.
     */
    public String unusualRequest() {
        return "That is a rather unusual looking request sir.\n"
                + "Perhaps you might want to specify "
                + "the kind of task you would like to add.\n";
    }

    /**
     * Bot message which will display the output the user wants to show.
     *
     * @param output String outputted from the CLI
     */
    public String dukeResponse(String output) {
        return output;
    }

    /**
     * Message shown when the bot exists.
     */
    public String farewellMessage() {
        return "Goodbye Sir! Will take good "
                + "care of your garden in the meantime.";
    }

}

