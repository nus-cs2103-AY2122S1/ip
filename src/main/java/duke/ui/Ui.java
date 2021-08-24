/**
 *
 * This class represents the User Interface object that
 * serves as the primary point of communication with the user.
 *
 * @author Rishabh Anand
 * @version CS2103 AY21/22 Semester 1
 *
 */

package duke.ui;

import java.io.IOException;
import java.util.Scanner;

public class Ui {
    public Ui() {}

    /**
     * Prints out the welcome greeting upon successful summoning.
     */
    public void greet() {
        System.out.println("Hello, I\'m  JARVIS");
        System.out.println("What can I do for you?");
    }

    /**
     *
     * Returns the user's input without any preprocessing.
     *
     * @return the raw string representation of the user's input to console.
     * @throws IOException
     */
    public String getUserInput() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        String input = sc.nextLine();

        return input;
    }

    /**
     *
     * Prints out messages from the chatbot to console.
     *
     * @param msg the exact message that needs to logged.
     */
    public void say(String msg) {
        System.out.println(msg);
    }
}
