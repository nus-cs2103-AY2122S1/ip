package duke;

public class Ui {

    private String message = "";

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Howdy! The name's\n" + logo + "\nWhat can I do for ya?");
    }

    public void showFarewell() {
        System.out.println("Seeya!");
    }

    /**
     * Prints a given String to console.
     * @param message message to be printed to console.
     */
    public void setMessage(String message) {
        this.message = message;
    }


    public String getMessage() {
        return this.message;
    }

    /**
     * Shows a given exception error message to user.
     * @param errorMessage Error message to show to user.
     */
    public void showError(String errorMessage) {
        setMessage(errorMessage);
    }

}