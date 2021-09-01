package bobcat.view;

import bobcat.exception.BobCatException;

/**
 * Represents the view of the application. Implements the "View" aspect of the MVC pattern. A <code>TextUi</code>
 * object dictates <i>how</i> information should be presented.
 */
public class TextUi {
    private static void hLine() {
        System.out.println("\t----------------------------------------------");
    }
    private static void display(String arg) {
        System.out.println("\t" + arg);
    }

    private void respondError(String errorMessage) {
        hLine();
        System.out.print("\t☹ OOPS!!! ");
        System.out.println(errorMessage);
        hLine();
    }

    /**
     * Displays error message to the user. This is used primarily to standardise formatting of
     * error messages, particularly BobCatException.
     *  @param errorObj a BobCatException which is thrown by ExecutionUnit
     */
    public void respondError(BobCatException errorObj) {
        respondError(errorObj.getMessage());
    }

    /**
     * Displays content to the user. This is used primarily to respond to user queries, and to standardise
     * the format of the content to be displayed. Elements in the array will be displayed with a newline in between.
     *  @param reply a String array, where each element is a string to be displayed to the user.
     */
    public void respond(String[] reply) {
        hLine();
        for (int i = 0; i < reply.length - 1; i++) {
            display(reply[i]);
        }
        display(reply[reply.length - 1]);
        hLine();
    }
}
