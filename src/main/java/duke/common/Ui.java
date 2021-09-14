package duke.common;

import java.util.ArrayList;

import duke.common.task.Task;

public class Ui {
    /**
     * Displays default Duke startup banner.
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
     * Displays bye message.
     */
    public String terminate() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays failure message.
     */
    public String fail() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    public String printUpdateFail() {
        return "Sorry, there does not seem to be such a field in this task.";
    }

    /**
     * Prints tasks found from find query
     *
     * @param tasks tasks found by taskList
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
     */
    public String printResponse(String response) {
        return response;
    }


}
