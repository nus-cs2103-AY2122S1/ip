package duke;

/**
 * Class responsible for displaying the output messages the bot gives
 *
 * @author mrmrinal
 */
public class Ui {
    /**
     * Bot greeting which shows up whenever whenever the bot is instantiated
     */
    public void dukeGreeting() {
        String name = "JARVIS";
        System.out.println("Hello I am " + name + ".\n"
                + "Is there anything I can do for you Sir?\n");
    }

    /**
     * Bot message whenever there is an unusual request
     */
    public void unusualRequest() {
        System.out.println("That is a rather unusual looking request sir.\n"
                + "Perhaps you might want to specify "
                + "the kind of task you would like to add.\n");
    }

    /**
     * Bot message which will display the output the user wants to show
     *
     * @param output String outputted from the CLI
     */
    public void dukeResponse(String output) {
        System.out.println(output);
    }

    /**
     * Message shown when the bot exists
     */
    public void farewellMessage() {
        System.out.println("Goodbye Sir! Will take good "
                + "care of your garden in the meantime.");
    }

}
