package duke;

import java.util.List;
import java.util.stream.IntStream;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * Encapsulates a response of the bot.
 */
public class UserInterface {
    private Duke app;

    /**
     * Creates a interface of bot
     *
     * @param app
     */
    public UserInterface(Duke app) {
        this.app = app;
    }

    /**
     * Greets the user in the console.
     */
    public void greet() {
        String greetings = "Hello from Duke!\n" + "What can I do for you?";
        app.printResponse(new Response(greetings).toString());
    }

    /**
     * Bids farewell to the user.
     */
    public void farewell() {
        app.printResponse(new Response("Bye. Hope to see you again soon!").toString());
    }

    /**
     * Shows a given message to the user.
     *
     * @param message to be shown
     */
    public void showMessage(String message) {
        app.printResponse(new Response(message).toString());
    }

    /**
     * Shows a given list of tasks to the user.
     *
     * @param list of tasks to be shown
     */
    public void showTaskList(List<? extends Task> list) {
        String message = IntStream.range(0, list.size()).mapToObj(i -> (i + 1) + ". " + list.get(i))
                .reduce((str1, str2) -> str1 + "\n" + str2).orElse("Wow, such emptiness!");
        app.printResponse(new Response(message).toString());
    }

    /**
     * Shows the message of a thrown exception to the user.
     *
     * @param exception to be shown
     */
    public void showError(DukeException exception) {
        app.printResponse(exception.getMessage().toString());
    }

    /**
     * Shows the string representation of an object to the user.
     *
     * @param obj to be shown
     */
    public void showObject(Object obj) {
        app.printResponse(new Response(obj.toString()).toString());
    }

    private class Response {
        private static final String PARTITION = "–––––––––––––––––––––––––––––––––––––––";
        private final String message;

        private Response(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return PARTITION + "\n" + message + "\n" + PARTITION;
        }

    }

}
