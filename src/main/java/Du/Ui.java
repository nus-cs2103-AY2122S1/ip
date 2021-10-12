package Du;

public class Ui {

    /**
     * Public constructor for Ui
     */
    public Ui() {}

    /**
     * Shows error when there is one
     */
    public void showLoadingError() {
        System.out.println("Oops, there is an error somewhere, I'm not sure where it is exactly as well");
    }

    /**
     * Greets when the user first start the programme
     */
    public String greet() {
        String logo = " ____\n"
                + "|  _ \\ _   _\n"
                + "| | | | | | |\n"
                + "| |_| | |_| |\n"
                + "|____/ \\__,_|\n";
        return "Hello from\n" + logo  + "Hello! I'm Du, your personal assistant chatbot!:)";
    }

    /**
     * Ends programme when user inputs "bye"
     */
    public void close_programme() {
        System.out.println("Bye. Hope to not see you again:P");
    }
}
