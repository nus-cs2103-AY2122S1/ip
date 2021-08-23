package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import duke.exception.DukeException;

/**
 * Encapsulates a response of the bot.
 */
public class UserInterface {

    public String getInput() {
        String input = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            input = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return input;
    }

    public void greet() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        String greetings = "Hello from\n" + logo + "\nWhat can I do for you?";
        new Response(greetings).print();
    }

    public void farewell() {
        new Response("Bye. Hope to see you again soon!").print();
    }

    public void showObject(Object obj) {
        new Response(obj.toString()).print();
    }

    public void showError(DukeException exception) {
        new Response(exception.getMessage()).print();
    }

    public void showMessage(String message) {
        new Response(message).print();
    }

    private class Response {
        private static final String PARTITION = "––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––";
        private static final String ANSI_CYAN = "\u001B[36m";
        private static final String ANSI_RESET = "\u001B[0m";
        private final String message;

        public Response(String message) {
            this.message = message;
        }

        /**
         * Prints the response in the system's output.
         */
        public void print() {
            System.out.println(ANSI_CYAN + PARTITION + "\n" + message + "\n" + PARTITION + ANSI_RESET);
        }

    }

}
