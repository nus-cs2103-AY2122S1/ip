package duke.parser;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.storage.Storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * <code>Parser</code> class contains main bot logic to parse user commands
 * The constructor does not take in any parameters, and the Storage and taskList
 * attributes are initialised automatically upon creation.
 *
 * Parser contains several backend methods such as deleteTask(int index) or makeDeadline(int index)
 * for specific user commands/inputs
 */

public class Parser{

    private final String FILEPATH = "./duke.txt";
    private final Storage storage = new Storage(FILEPATH);
    private final TaskList tasks = new TaskList(storage.load());

    /**
     * <code>help()</code> shows a list of usable commands that the user can enter into the bot.
     *
     * @return helpDialog, a string which shows the list of possible user commands
     */
    private String help(){

        String helpDialog = "I'm Mr Meeseeks look at me! :)\n"
                + "Type in \"todo\", \"deadline\" or \"event\" and I will keep track of a task!\n"
                + "Type \"list\" to show all tasks so far\n"
                + "Type \"done\" to mark a task as done\n"
                + "Type \"find\" to search for a task by keywords\n"
                + "Type \"bye\" to exit";

        return helpDialog;
    }
    /**
     * <code>list()</code> reads the current TaskList and shows a list of tasks the user has so far.
     *
     * @return listDialog, a string which shows the current list of tasks
     */
    private String list(){
        String listDialog = "Okay! Here's a list of your tasks so far:\n";
        listDialog += tasks.returnAllTasks();
        return listDialog;
    }

    /**
     * <code>markTaskAsDone(String input)</code> marks a task at a specific index as done
     *
     * @param input input string in the GUI, should contain the index for the task to be marked as done
     *
     * @return a dialog string showing the task marked as done,
     * shows an error message if there are no tasks or task number is invalid.
     */
    private String markTaskAsDone(String input){

        try{
            assert tasks.getSize() > 0;
        } catch (AssertionError e){
            return "Oops! There are no tasks to mark as done...";
        }

        try {
            int index = Integer.parseInt(input.substring(5));
            tasks.markDone(index-1);
            storage.save(tasks.getList());
            return "Can Do! I've marked the below task as done\n"
                + tasks.getTask(index-1).toString();
        } catch (Exception e) {
            return "Oops! Please enter a valid task number to mark as done\n"
                    + "from 1 to " + tasks.getSize();
        }

    }
    /**
     * <code>deleteTask(String input)</code> deletes a task at a specific index
     *
     * @param input input string in the GUI, should contain the index for the task to be deleted
     *
     * @return a dialog string showing the task that got deleted,
     * shows an error message if there are no tasks or task number is invalid.
     */
    private String deleteTask(String input){
        try{
            assert tasks.getSize() > 0;
        } catch (AssertionError e){
            return "Oops! There are no tasks to delete...";
        }
        try {
            int index = Integer.parseInt(input.substring(7));
            String deletedTask = tasks.getTask(index-1).toString();
            tasks.delete(index-1);
            storage.save(tasks.getList());
            return "Okay Boom! The below task is gone\n" + deletedTask
                    + "\n There are now " + tasks.getSize() + " tasks";
        } catch (Exception e) {
            return "Oops! Please enter a valid task number to delete\n"
                    + "from 1 to " + tasks.getSize();
        }
    }

    /**
     * <code>makeToDo(String input)</code> creates a ToDo task from user input
     *
     * @param input input string in the GUI, should contain a description for the new todo task
     * @return a dialog string showing the new todo task created,
     * shows an error message if there is no description in the input.
     */
    private String makeToDo(String input){
        try {
            String description = input.substring(5);
            Task todo = new ToDo(description, false);
            tasks.add(todo);
            storage.save(tasks.getList());
            return "Can Do! I've created the todo task below:\n"
                    + todo.toString() + "\n"
                    +"There are now " + tasks.getSize() + " tasks.";
        } catch (Exception e) {
            return "Oops! Please enter a description for the task";
        }
    }

    /**
     * <code>makeDeadline(String input)</code> creates a Deadline task from user input
     *
     * @param input input string in the GUI, should contain a description,
     * as well as a Date and Time for the new Deadline task
     *
     * @return a dialog string showing the new Deadline task created,
     * shows an error message if there is no description in the input or the date/time is invalid
     */
    private String makeDeadline(String input){
        int endIndex = input.length()-1;

        try {
            String timeString = input.substring(endIndex-4);
            String dateString = input.substring(endIndex-15, endIndex-5);

            LocalTime time = LocalTime.from(DateTimeFormatter.ISO_LOCAL_TIME.parse(timeString));
            LocalDate date = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(dateString));

            String description = input.substring(9, endIndex-16);

            Task deadline = new Deadline(description, date,time,false);
            tasks.add(deadline);
            storage.save(tasks.getList());
            return "Can Do! I've created the deadline task below:\n"
                    + deadline.toString() + "\n"
                    +"There are now " + tasks.getSize() + " tasks.";
        } catch (Exception e) {
            return "Oops! Please follow the format:\n deadline (description) yyyy-mm-dd hh:ss\n"
                    + "Check if the date/time is valid and if there is any missing spaces/characters";
        }

    }

    /**
     * <code>makeEvent(String input)</code> creates an Event task from user input
     *
     * @param input input string in the GUI, should contain a description,
     * a Date, start Time and end Time for the new Event task
     *
     * @return a dialog string showing the new Event task created,
     * shows an error message if there is no description in the input or the date/time strings are invalid
     */

    private String makeEvent(String input){
        int endIndex = input.length()-1;

        try {
            String endTimeString = input.substring(endIndex-4);
            String startTimeString = input.substring(endIndex-10,endIndex-5);
            String dateString = input.substring(endIndex-21, endIndex-11);

            LocalTime endTime = LocalTime.from(DateTimeFormatter.ISO_LOCAL_TIME.parse(endTimeString));
            LocalTime startTime = LocalTime.from(DateTimeFormatter.ISO_LOCAL_TIME.parse(startTimeString));
            LocalDate date = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(dateString));

            String description = input.substring(6, endIndex-22);

            Task event = new Event(description, date, startTime, endTime,false);
            tasks.add(event);
            storage.save(tasks.getList());
            return "Can Do! I've created the event task below:\n"
                    + event.toString() + "\n"
                    +"There are now " + tasks.getSize() + " tasks.";
        } catch (Exception e) {
            return "Oops! Please follow the format:\n event (description) yyyy-mm-dd hh:ss hh:ss\n"
                    + "Check if the date/time is valid and if there is any missing spaces/characters";
        }
    }

    /**
     * <code>findTask(String input)</code> finds all tasks which match a target description in the input
     *
     * @param input input string in the GUI, should contain a target description for the method to search for
     *
     * @return a dialog string showing all tasks with description that match what the user keyed in,
     * shows an error message if there is no description in the input.
     */

    private String findTask(String input){
        try{
            assert tasks.getSize() > 0;
        } catch (AssertionError e){
            return "Oops! There are no tasks to search for...";
        }

        String target;

        try {
            target = input.substring(5);
        } catch(Exception e){
            return "Oops! Please enter a target description for me to find";
        }

        String output = "Ok! Here are the tasks that match your query description: \"" + target + "\"\n";

        for (int i = 0; i < tasks.getSize(); i++){
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(target)){
                output += task.toString();
                output += "\n";
            }
        }
        return output;
    }

    /**
     * Takes in an input string and processes it based on the command type (first word in the string)
     * Shows an error message if the command type is not recognised.
     *
     * @param input input String which corresponds to a user command,
     * plus other info which will be used in the backend methods
     *
     * @return string which shows an update of the program state and will be shown to the user in the GUI
     */

    public String parseCommand(String input) {

        if (input.equals("help")||input.equals("start")) {
            return help();
        } else if (input.equals("list")) {
            return list();
        } else if (input.startsWith("done")) {
            return markTaskAsDone(input);
        } else if (input.startsWith("delete")) {
            return deleteTask(input);
        } else if (input.startsWith("todo")) {
            return makeToDo(input);
        } else if (input.startsWith("deadline")) {
            return makeDeadline(input);
        } else if (input.startsWith("event")) {
            return makeEvent(input);
        } else if (input.startsWith("find")) {
            return findTask(input);
        } else {
            return "Oops! I didn't recognise that command!\n"
                + "Type \"help\" for a list of commands\n"
                + "Make sure to check for spaces and invalid characters/case";
        }

    }
}
