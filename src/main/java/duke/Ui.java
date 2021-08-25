package duke;

/**
 * Ui is a class that encapsulates the attributes and behaviours of a User Interface.
 *
 * @author leezhixuan
 */
public class Ui {
    private String name;

    /**
     * Creates an instance of Ui.
     *
     * @param name Name of chat bot.
     */
    public Ui(String name) {
        this.name = name;
    }

    /**
     * Greets the user.
     */
    public void greet() {
        System.out.println("========== " + this.name + " ===========");
        System.out.println("Hello... I'm " + this.name + ":/");
        System.out.println("And how can I help you?");
        System.out.println("========== " + this.name + " ===========\n");
    }

    /**
     * Prints the message passed in nicely.
     *
     * @param message Message to be printed.
     */
    public void printProper(String message) {
        System.out.println("========== " + this.name + " ===========");
        System.out.println(message);
        System.out.println("========== " + this.name + " ===========\n");
    }

}
