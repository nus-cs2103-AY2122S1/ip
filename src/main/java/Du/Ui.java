package Du;

public class Ui {

    /**
     * public constructor for Ui
     */
    public Ui() {
    }

    /**
     * shows error when there is one
     */
    public void showLoadingError() {
        System.out.println("Oops, there is an error somewhere, I'm not sure where it is exactly as well");
    }

    /**
     * greets when the user first start the programme
     */
    public void greet() {
        String logo = " ____\n"
                + "|  _ \\ _   _\n"
                + "| | | | | | |\n"
                + "| |_| | |_| |\n"
                + "|____/ \\__,_|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Du, your personal assistant chatbot!:)");
    }

    /**
     * ends programme when user inputs "bye"
     */
    public void close_programme() {

        System.out.println("____________________________________________________________\n"
                + "Bye. Hope to not see you again:P" + "\n"
                + "____________________________________________________________");
    }
}
