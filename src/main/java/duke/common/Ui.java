package duke.common;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import duke.common.task.Task;
import javafx.application.Platform;

/**
 * UI handles processes string responses from Duke before passing them on to be displayed in the app.
 */
public class Ui {
    /**
     * Displays default Duke startup banner.
     *
     * @return initialisation banner
     */
    public String init() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo
               + "What can I do for you?";
    }

    /**
     * Displays bye message, then terminates the programme.
     *
     * @return bye message
     */
    public String terminate() {
        // @@author Vaibhav G
        // Reused from https://stackoverflow.com/questions/35512648/adding-a-timer-to-my-program-javafx
        // with minor modifications
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                Platform.exit();
            }
        };
        timer.schedule(task,500);
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays failure message.
     *
     * @return failure message
     */
    public String fail() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Displays update failure message.
     *
     * @return update failure message
     */
    public String printUpdateFail() {
        return "Sorry, there does not seem to be such a field in this task.";
    }

    /**
     * Prints tasks found from find query
     *
     * @param tasks tasks found by taskList
     * @return formatted list of tasks
     */
    public String printFind(ArrayList<Task> tasks) {
        String response = "";
        response += "____________________________________________________________\n"
                    + "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            response += (i + 1) + ". " + tasks.get(i) + "\n";
        }
        response += "____________________________________________________________";
        return response;
    }

    /**
     * Prints data-reliant response to a command. Responses are fed by Parser
     *
     * @param response string reply to command
     * @return response
     */
    public String printResponse(String response) {
        return response;
    }


}
