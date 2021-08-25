package WhoBot.Utils;

import WhoBot.Main.WhoBotException;
import WhoBot.Main.UI;
import WhoBot.Task.Task;
import WhoBot.Task.Event;
import WhoBot.Task.Deadline;
import WhoBot.Task.Todo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

/***
 * Class to Maintain the Task List
 */
public class TaskList {

    /** The Task List Arraylist */
    private final ArrayList<Task> LIST = new ArrayList<>();

    /***
     * Constructor for TaskList Class
     *
     * @param storage Storage where the task list will be stored
     * @throws WhoBotException if there is an error reading data
     */
    public TaskList(Storage storage) throws WhoBotException {
        storage.readData(LIST);
    }

    /***
     * Returns the Task List
     *
     * @return the task list
     */
    public ArrayList<Task> getLIST() {
        return LIST;
    }

    /***
     * Prints Task List
     *
     * @param ui UI to print list to
     */
    public void printList(UI ui) {
        if (LIST.isEmpty()) {
            ui.echo("There are currently no tasks in your list.", UI.TYPE.COMPLETE);
            return;
        }
        Collections.sort(LIST);
        String listString = "The tasks in your list are:\n";
        int i;
        for (i = 0; i < LIST.size() - 1; i++) {
            listString = listString.concat("\t\t\t" + (i + 1) + ". " + LIST.get(i) + "\n");
        }
        listString = listString.concat("\t\t\t" + (i + 1) + ". " + LIST.get(i));
        ui.echo(listString, UI.TYPE.COMPLETE);
    }

    /***
     * Marks Task as Done
     *
     * @param ind index of task to mark
     * @param ui UI to show output
     * @throws WhoBotException If any error
     */
    public void markAsDone(String ind, UI ui) throws WhoBotException {
        try {
            int index = Integer.parseInt(ind) - 1;
            if (index < LIST.size()) {
                LIST.get(index).markAsDone();
                ui.echo("Congrats! I have marked this task complete: \"" + LIST.get(index).getDescription() + "\"", UI.TYPE.COMPLETE);
            } else {
                throw new WhoBotException("Oops, The index you gave is out of bound. There are only " + LIST.size() + " tasks");
            }
        } catch (NumberFormatException  ex) {
            throw new WhoBotException("I didn't get what you meant. Ensure that the command is of the form \"done #index\"");
        }
    }

    /***
     * Marks Task as Not Done
     *
     * @param ind index of task to mark
     * @param ui UI to show output
     * @throws WhoBotException If any error
     */
    public void markAsUndone(String ind, UI ui) throws WhoBotException {
        try {
            int index = Integer.parseInt(ind) - 1;
            if (index < LIST.size()) {
                LIST.get(index).markAsUndone();
                ui.echo("I have marked this task incomplete: \"" + LIST.get(index).getDescription() + "\"", UI.TYPE.COMPLETE);
            } else {
                throw new WhoBotException("Oops, The index you gave is out of bound. There are only " + LIST.size() + " tasks");
            }
        } catch (NumberFormatException  ex) {
            throw new WhoBotException("I didn't get what you meant. Ensure that the command is of the form \"undo #index\"");
        }
    }

    /***
     * Deletes Task from the Task List
     *
     * @param ind index of task to delete
     * @param ui UI to show output
     * @throws WhoBotException If any error
     */
    public void deleteFromList(String ind, UI ui) throws WhoBotException {
        try {
            int index = Integer.parseInt(ind) - 1;
            if (index < LIST.size()) {
                Task temp = LIST.get(index);

                //Check for confirmation before deleting
                ui.echo("Are you sure you want to delete this task: \"" + temp.getDescription() + "\" ? (Yes/No)", UI.TYPE.COMPLETE);
                System.out.print(UI.COLOR_PURPLE + "> " + UI.COLOR_RESET);
                String confirm = UI.cmdReader.nextLine().trim();
                if (confirm.toLowerCase(Locale.ROOT).equals("yes")) {
                    LIST.remove(index);
                    ui.echo("I have deleted this task from the list: \"" + temp.getDescription() + "\"", UI.TYPE.START);
                    ui.echo("You now have " + LIST.size() + " task(s) in the list.", UI.TYPE.END);
                } else {
                    ui.echo("The deletion has been cancelled.", UI.TYPE.COMPLETE);
                }

            } else {
                throw new WhoBotException("Oops, The index you gave is out of bound. There are only " + LIST.size() + " tasks");
            }
        } catch (NumberFormatException  ex) {
            throw new WhoBotException("I didn't get what you meant. Ensure that the command is of the form \"delete #index\"");
        }
    }

    /***
     * Adds a ToDo Task to the Task List
     *
     * @param command the command with the details for the task
     * @param ui UI to show output to
     * @throws WhoBotException If there is any error on addition
     */
    public void addTODO(String command, UI ui) throws WhoBotException {
        try {
            Todo task = new Todo(command.substring(5));
            if (LIST.add(task)) {
                ui.echo("I have added this ToDo Task to the list: \"" + task.getDescription() + "\"", UI.TYPE.START);
                ui.echo("You now have " + LIST.size() + " task(s) in the list.", UI.TYPE.END);
            } else {
                throw new WhoBotException("I am sorry. The task couldn't be added, please try again.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new WhoBotException("Ensure that the command is of the form \"todo #description\". The description can not be empty.");
        }
    }

    /***
     * Adds a Event Task to the Task List
     *
     * @param command the command with the details for the task
     * @param ui UI to show output to
     * @throws WhoBotException If there is any error on addition
     */
    public void addEvent(String command, UI ui) throws WhoBotException {
        try {
            if (!command.contains("/at ")) {
                throw new WhoBotException("Ensure that the command is of the form \"event #description /at #timing\". The timing must be given.");
            }
            Event task = new Event(command.substring(6));
            if (LIST.add(task)) {
                ui.echo("I have added this Event Task to the list: \"" + task.getDescription() + "\"", UI.TYPE.START);
                ui.echo("You now have " + LIST.size() + " task(s) in the list.", UI.TYPE.END);
            } else {
                throw new WhoBotException("I am sorry. The task couldn't be added, please try again.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new WhoBotException("Ensure that the command is of the form \"event #description /at #timing\". The description can not be empty.");
        }
    }

    /***
     * Adds a Deadline Task to the Task List
     *
     * @param command the command with the details for the task
     * @param ui UI to show output to
     * @throws WhoBotException If there is any error on addition
     */
    public void addDeadline(String command, UI ui) throws WhoBotException {
        try {
            if (!command.contains("/by ")) {
                throw new WhoBotException("Ensure that the command is of the form \"deadline #description /by #deadline\". The deadline must be given.");
            }
            Deadline task = new Deadline(command.substring(9));
            if (LIST.add(task)) {
                ui.echo("I have added this Deadline Task to the list: \"" + task.getDescription() + "\"", UI.TYPE.START);
                ui.echo("You now have " + LIST.size() + " task(s) in the list.", UI.TYPE.END);
            } else {
                throw new WhoBotException("I am sorry. The task couldn't be added, please try again.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new WhoBotException("Ensure that the command is of the form \"deadline #description /by #deadline\". The description can not be empty.");
        }
    }

    /***
     * Shows Tasks on a specific date
     * @param command the command with the date specified
     * @param ui UI to show output to
     * @throws WhoBotException If there is any error in the command
     */
    public void showOnDate(String command, UI ui) throws WhoBotException {
        try {
            if (!command.contains("/on ")) {
                throw new WhoBotException("Ensure that the command is of the form \"show /on #date\". The deadline must be given.");
            }
            LocalDate date = LocalDate.parse(command.split(" /on ")[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
            ArrayList<Task> tasksToDisplay = new ArrayList<>();
            for (Task tempTask : LIST) {
                if (tempTask instanceof Deadline) {
                    if (((Deadline) tempTask).getDeadline().toLocalDate().equals(date)) {
                        tasksToDisplay.add(tempTask);
                    }
                } else if (tempTask instanceof Event) {
                    if (((Event) tempTask).getTiming().toLocalDate().equals(date)) {
                        tasksToDisplay.add(tempTask);
                    }
                }
            }
            String dateString = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            if (tasksToDisplay.isEmpty()) {
                ui.echo("There are currently no tasks on " + dateString, UI.TYPE.COMPLETE);
                return;
            }
            Collections.sort(tasksToDisplay);
            String listString = "The tasks on " + dateString + " are:\n";
            int i;
            for (i = 0; i < tasksToDisplay.size() - 1; i++) {
                listString = listString.concat("\t\t\t" + (i + 1) + ". " + tasksToDisplay.get(i) + "\n");
            }
            listString = listString.concat("\t\t\t" + (i + 1) + ". " + tasksToDisplay.get(i));
            ui.echo(listString, UI.TYPE.COMPLETE);
        } catch (IndexOutOfBoundsException e) {
            throw new WhoBotException("Ensure that the command is of the form \"show /on #date\". The description can not be empty.");
        }
    }
    
    public void findTask(String text, UI ui) {
        String searchText = text.substring(6).toLowerCase(Locale.ROOT);
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : this.LIST) {
            if (task.getDescription().toLowerCase(Locale.ROOT).contains(searchText)) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            ui.echo("There are currently no tasks that match your search.", UI.TYPE.COMPLETE);
            return;
        }
        String listString = "The tasks in your list that match your search are:\n";
        int i;
        for (i = 0; i < matchingTasks.size() - 1; i++) {
            listString = listString.concat("\t\t\t" + (i + 1) + ". " + matchingTasks.get(i) + "\n");
        }
        listString = listString.concat("\t\t\t" + (i + 1) + ". " + matchingTasks.get(i));
        ui.echo(listString, UI.TYPE.COMPLETE);
    }
}
