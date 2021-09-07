package duke.command;

import duke.Data;
import duke.Deadline;
import duke.DukeException;
import duke.Event;
import duke.TaskList;
import duke.Todo;
import duke.UI;

/**
 * Encapsulates the Command where something is added to the Task list (or dukeList).
 */
public class AddCommand extends Command {
    private final TaskList dukeList;
    private final Data data;
    private final String taskCategory;

    /**
     * Constructor for AddCommand
     * @param dukeList list of Tasks
     * @param data Data of Tasks
     * @param taskCategory Task category itself
     */
    public AddCommand(TaskList dukeList, Data data, String taskCategory) {
        this.dukeList = dukeList;
        this.data = data;

        assert taskCategory.matches("todo|event|deadline");

        this.taskCategory = taskCategory;
    }


    @Override
    public String getResponse(String input) {
        switch (taskCategory) {
        case "deadline":
            return addDeadline(input);
        case "event":
            return addEvent(input);
        case "todo":
            return addTodo(input);
        default:
            return "";
        }
    }

    /**
     * Adds a Deadline to all Tasks that Duke has stored.
     * @param input The entire String that the user has input i.e. "deadline Whatever /by Whenever".
     * @return a string of the final message after successfully adding a Deadline.
     */
    private String addDeadline(String input) {
        // First check if the user has only input the one word "deadline".
        if (input.split(" ", 2).length == 1) {
            return "Oops! Looks like you are missing the description and the deadline date! "
                    + "Try again :-)";
        }
        // If "deadline" is entered with more words, check information.
        String[] information = input.split(" ", 2);
        String [] description = information[1].split("/by ", 2);

        //In the case where date is not entered.
        if (description.length == 1) {
            return "Oops! Looks like you are missing the deadline date! Try again :-)";
        }
        Deadline newDL = new Deadline(description[0], description[1]);
        try {
            Data.writeToFile(newDL);
        } catch (DukeException e) {
            return e.getMessage();
        }
        if (dukeList.contains(newDL)) {
            return "Oops, this task already exists";
        } else {
            dukeList.add(newDL);
        }
        return (UI.getAddedText()
                + newDL.toString()
                + "\nNow you have "
                + TaskList.numberOfTasks()
                + " tasks in the list");
    }

    /**
     * Adds an Event to all Tasks that Duke has stored.
     * @param input The entire String that the user has input i.e. "event Here /at There".
     * @return a string of the final message after successfully adding an Event.
     */
    private String addEvent(String input) {
        // First check if the user has only input the one word "event".
        if (input.split(" ", 2).length == 1) {
            return "Oops! Looks like you are missing the description and the event location!"
                    + " Try again :-)";
        }
        // If "event" is entered with more words, check information.
        String[] information = input.split(" ", 2);
        String [] description = information[1].split("/at ", 2);

        //In the case where location is not entered.
        if (description.length == 1) {
            return "Oops! Looks like you are missing the event location! Try again :-)";
        }
        Event newEV = new Event(description[0], description[1]);
        if (dukeList.contains(newEV)) {
            return "Oops, this task already exists";
        } else {
            dukeList.add(newEV);
        }

        return (UI.getAddedText()
                + newEV.toString()
                + "\nNow you have "
                + TaskList.numberOfTasks()
                + " tasks in the list");
    }

    /**
     * Adds a DukePakage.Todo to all Tasks that DukePakage.Duke has stored.
     * @param input The entire String that the user has input i.e. "todo This task".
     * @return a string of the final message after successfully adding a todo.
     */
    private String addTodo(String input) {
        // First check if the user has only input the one word "todo".
        if (input.split(" ", 2).length == 1) {
            return "Oops! Looks like you are missing the description! Try again :-)";
        }
        //If "todo" is entered with more words, use the information.
        String[] information = input.split(" ", 2);
        Todo newTD = new Todo(information[1]);
        try {
            Data.writeToFile(newTD);
        } catch (DukeException e) {
            return e.getMessage();
        }
        if (dukeList.contains(newTD)) {
            return "Oops, this task already exists";
        } else {
            dukeList.add(newTD);
        }
        return (UI.getAddedText()
                + newTD.toString()
                + "\nNow you have "
                + TaskList.numberOfTasks()
                + " tasks in the list");
    }
}
