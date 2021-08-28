package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.IntStream;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * Encapsulates a response of the bot.
 */
public class UserInterface {

    /**
     * Gets user's input from the console.
     *
     * @return user's input
     */
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

    /**
     * Greets the user in the console.
     */
    public void greet() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        String greetings = "Hello from\n" + logo + "\nWhat can I do for you?";
        new Response(greetings).print();
    }

    /**
     * Bids farewell to the user.
     */
    public void farewell() {
        new Response("Bye. Hope to see you again soon!").print();
    }

    /**
     * Shows the string representation of an object to the user.
     *
     * @param obj to be shown
     */
    public void showObject(Object obj) {
        new Response(obj.toString()).print();
    }

    /**
     * Shows the message of a thrown exception to the user.
     *
     * @param exception to be shown
     */
    public void showError(DukeException exception) {
        new Response(exception.getMessage()).print();
    }

    /**
     * Shows a given message to the user.
     *
     * @param message to be shown
     */
    public void showMessage(String message) {
        new Response(message).print();
    }

    /**
     * Shows a given list of tasks to the user.
     *
     * @param list of tasks to be shown
     */
    public void showTaskList(List<? extends Task> list) {
        String message = IntStream.range(0, list.size()).mapToObj(i -> (i + 1) + ". " + list.get(i))
                .reduce((str1, str2) -> str1 + "\n" + str2).orElse("Wow, such emptiness!");
        new Response(message).print();
    }

    private class Response {
        private static final String PARTITION = "––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––";
        private static final String ANSI_CYAN = "\u001B[36m";
        private static final String ANSI_RESET = "\u001B[0m";
        private final String message;

        private Response(String message) {
            this.message = message;
        }

        /**
         * Prints the response in the system's output.
         */
        private void print() {
            System.out.println(ANSI_CYAN + PARTITION + "\n" + message + "\n" + PARTITION + ANSI_RESET);
        }

    }

}
