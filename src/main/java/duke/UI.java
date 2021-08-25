package duke;

import duke.tasks.Task;

import java.util.Scanner;

/**
 * Class that represents the UI of Duke.
 */
public class UI {

    /**
     * A nested class that encapsulates a typical command line
     * response from Duke, and deals with formatting.
     */
    public static class Response {

        /** The content of the message to be sent to the user */
        private String content;

        /**
         * A constructor for a Response object.
         *
         * @param content A String containing the content of the Response
         */
        private Response(String content) {
            this.content = content;
        }

        /**
         * An alternate constructor for a Response object.
         *
         * @param list a Tasklist of tasks to be shown to the user.
         */
        private Response(Tasklist list) {
            this.content = list.toString();
        }

        /**
         * Returns a Response for the event where a Task is added
         * to a Tasklist, and prints the Response to the console.
         *
         * @param tasklist The Tasklist where the task has been added to
         * @param task The Task which has been added.
         * @return A Response object detailing the addition of the Task.
         */
        public static Response added(Tasklist tasklist, Task task) {
            String msg = "Got it. I've added this task:\n\t" 
                + task.toString()
                + "\nNow you have "
                + tasklist.getTotalTasks()
                + (tasklist.getTotalTasks() == 1 ? " task " : " tasks ")
                + "in the list.";
    
            Response response = new Response(msg);
            System.out.println(response);
            return response;
        }

        /**
         * Returns a Response for the event where a Task has been marked
         * as complete, and prints the Response to the console.
         *
         * @param task The Task that has been completed
         * @return A Response object detailing the completion of the Task
         */
        public static Response completed(Task task) {
            String msg = "Nice! I've marked this task as done:\n" 
                + task.toString();

            Response response = new Response(msg);
            System.out.println(response);
            return response;
        }

        /**
         * Returns a Response for the event where a Task has been removed,
         * and prints the Response to the console.
         *
         * @param tasklist The Tasklist from which the Task was removed
         * @param task The Task that was removed
         * @return A Response detailing the removal of the Task
         */
        public static Response removed(Tasklist tasklist, Task task) {
            String msg = "Noted. I've removed this task:\n\t" 
                + task.toString() 
                + "\nNow you have " 
                + tasklist.getTotalTasks()
                + (tasklist.getTotalTasks() == 1 ? " task" : " tasks")
                + " in the list.";
    
            Response response = new Response(msg);
            System.out.println(response);
            return response;
        }

        /**
         * Returns a Response for the event where user requests to list
         * all Tasks in the Tasklist, and prints the Response to the console.
         *
         * @param tasklist The Tasklist containing the Tasks to be displayed to the user.
         * @return A Response detailing the list of all Tasks in the Tasklist
         */
        public static Response listAllItems(Tasklist tasklist) {
            Response response = new Response("Here are the tasks in your list:\n" 
                + tasklist.toString());

            System.out.println(response);
            return response;
        }

        /**
         * Returns a Response containing the start message for Duke, and
         * prints the Response to the Console.
         *
         * @return A Response containing the start message of Duke.
         */
        public static Response start() {
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n\n";
    
            Response response = new Response(logo 
                + "Hello there, I'm Duke!\nWhat can I do for you?");
            System.out.println(response);
            return response;
        }

        /**
         * Returns a Response containing the exit message for Duke, and
         * prints the Response to the console.
         *
         * @return A Response containing the exit message of Duke.
         */
        public static Response exit() {
            Response response = new Response("Bye! Come back again!");
            System.out.println(response);
            return response;
        }

        /**
         * Returns a Response containing an error message, and prints
         * the Response to the console.
         *
         * @param msg An error message to be formatted by Response
         * @return A Response object detailing the error message
         */
        public static Response error(String msg) {
            Response response = new Response(msg);
            System.out.println(response);
            return response;
        }

        /**
         * Returns a String representation of a Response with all formatting complete,
         * for use when printing to the console.
         *
         * @return A user-friendly, readable String representing a Response object
         */
        @Override
        public String toString() {
            String linebreak = "\t_______________________________________________\n";
            
            String[] splitByLines = this.content.split("\n");
            String rawContent = "";
    
            for (int i = 0; i < splitByLines.length; i++) {
                rawContent += ("\t" + splitByLines[i] + "\n"); 
            }
            
            String result = (linebreak + rawContent + linebreak);
            return result;
        }
    }

    /** The Scanner used for reading input from the command line */
    private Scanner inputScanner;

    /**
     * A Constructor for a UI object
     *
     * @param inputScanner A Scanner object used for reading input from the user
     */
    public UI(Scanner inputScanner) {
        this.inputScanner = inputScanner;
    }

    /**
     * Prints a formatted error message in the event that the
     * persistence file cannot be read or found.
     */
    public void showLoadError() {
        Response.error("Could not read your file :(");
    }

    /**
     * Prints a formatted start message to the user.
     */
    public void showStartMsg() {
        Response.start();
    }

    /**
     * Prints a formatted exit message to the user.
     */
    public void showExitMsg() {
        Response.exit();
    }

    /**
     * Prints a formatted message detailing a removed Task.
     *
     * @param taskList The Tasklist the Task was removed from
     * @param task The Task that was removed
     */
    public void showRemovedTask(Tasklist taskList, Task task) {
        Response.removed(taskList, task);
    }

    /**
     * Prints a formatted message listing all the current Tasks
     * in the Tasklist
     *
     * @param taskList The Tasklist of Tasks to be shown to the user.
     */
    public void listAllTasks(Tasklist taskList) {
        Response.listAllItems(taskList);
    }

    /**
     * Prints a formatted message detailing a completed Task.
     *
     * @param task The Task that was completed
     */
    public void showCompletedTask(Task task) {
        Response.completed(task);
    }

    /**
     * Prints a formatted message detailing an added Task.
     *
     * @param taskList The Tasklist where the Task was added to
     * @param task The Task that was added
     */
    public void showAddedTask(Tasklist taskList, Task task) {
        Response.added(taskList, task);
    }

    /**
     * Prints a formatted message detailing an error from a
     * DukeException
     *
     * @param e The DukeException to be printed
     */
    public void showErrorMsg(DukeException e) {
        Response.error(e.getMessage());
    }

    /**
     * Reads a String input from the user for use by the Parser
     *
     * @return A String input read from the user through the command line.
     */
    public String readCommand() {
        String fullCommand = this.inputScanner.nextLine();
        return fullCommand;
    }
}
