package duke;
/**
 * Ui class that deals with user interactions.
 */

public class Ui {

    /**
     * Constructor for Ui.
     */
    public Ui() {

    }

    /**
     * Method that greets the user when bot is first run.
     *
     */
    protected void greet() {
        String greetingMessage = "What's up! I'm Duke! What can I help you with?";
        System.out.println(greetingMessage);

    }


    /**
     * Method that gives basic instructions on how to use the Bot.
     */
    protected void instruct() {
        System.out.println("Here's the commands you can input for me!\n"
                + "\"list\" : lists the tasks in your list with their type and status (Done or not)\n"
                + "\"done _\" : enter done followed by a whitespace and the"
                + " number of the task you want to be set as done\n"
                + "\"delete _\" : enter delete followed by a whitespace and "
                + "the number of the task you wish to delete from the list!\n"
                + "\"todo __\" : enter todo followed by the task you wish to add to the list\n"
                + "\"deadline __ /by yyyy-mm-dd HHmm\" enter deadline followed by "
                + "the task name and deadline in the format stated.\n"
                + "\"event __ /at yyyy-mm-dd HHmm\" enter deadline followed by the"
                + " task name and event time in the format stated.");

    }

    /**
     * Method that gives the goodbye message to the user.
     */
    protected void goodbye() {
        String exitMessage = "Leaving just like that? Fine. See you soon I guess.";
        System.out.println(exitMessage);
    }
}

