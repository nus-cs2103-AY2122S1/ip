package petal.components;

/**
 * The Ui is responsible for handling the output, and interactions
 * with the user
 */
public class Ui {

    /**
     * Terminates the Petal instance
     */
    public void sayGoodbye() {
        output(Responses.GOODBYE);
    }

    /**
     * Outputs given message with lines above and below the message
     *
     * @param message Response that is converted to string that then displayed
     */
    public void output(Responses message) {
        System.out.println(message.toString());
    }

    /**
     * Outputs given message with lines above and below the message
     *
     * @param message String to be printed
     */
    public void output(String message) {
        System.out.println(message);
    }

}
