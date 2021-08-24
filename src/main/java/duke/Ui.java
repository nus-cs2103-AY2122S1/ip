package duke;

import duke.exception.WrongCommandFormatException;

public class Ui {

    /**
     * Method to print out the welcome message when the bot starts.
     */
    public static void welcomeMessage() {
        System.out.println(
                "Yo! duke.Duke here \n"
                        + "What did you call me for? \n"
                        + "It better be something useful or else... \n"
        );
    }

    /**
     * Method to print out the error message when loading of tasks fails
     */
    public static void loadingError() {
        System.out.println("Error loading duke.tasks");
    }

    public static void invalidIndexMessage() {
        System.out.println("Invalid index, please try again");
    }

    public static void formatExceptionMessage(WrongCommandFormatException e) {
        System.out.println(e.getMessage());
    }

    public static void formatUpdatedMessage() {
        System.out.println(
                "Date format has been updated to: "
                        + Duke.getFormat()
        );
    }

    public static void unacceptableFormatMessage() {
        System.out.println("Not an acceptable format. Please try again");
    }

    public static void currentDateFormatMessage() {
        System.out.println("Current format " + Duke.getFormat());
    }

    public static void noSpecificCmdMessage() {
        System.out.println("No specific command specified. Please try again");
    }

    public static void botShutdownMessage() {
        System.out.println("Good riddance! Time to continue my beauty sleep :)");
    }
}
