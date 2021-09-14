package whobot.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import whobot.main.UI;
import whobot.main.WhoBot;
import whobot.main.WhoBotException;
import whobot.task.Deadline;
import whobot.task.Event;
import whobot.task.Task;
import whobot.task.Todo;

/***
 * Class to Maintain the Task List
 */
public class TaskList {

    private static final String NO_TAG = "Others";

    /** The Task List Arraylist */
    private final ArrayList<Task> list = new ArrayList<>();

    private final HashMap<String, ArrayList<Task>> taggedList = new HashMap<>();

    private enum Anomaly { NONE, EXISTENT, CONFLICT };

    /***
     * Constructor for TaskList Class
     *
     * @param storage Storage where the task list will be stored
     * @throws WhoBotException if there is an error reading data
     */
    public TaskList(Storage storage) throws WhoBotException {
        assert storage != null;
        taggedList.put(NO_TAG, new ArrayList<>());
        storage.readData(list, taggedList);
    }

    /***
     * Returns the Task List
     *
     * @return the task list
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /***
     * Prints Task List
     *
     * @param ui UI to print list to
     */
    public void printList(UI ui) {
        assert ui != null;
        if (list.isEmpty()) {
            ui.echo("There are currently no tasks in your list.", UI.Type.COMPLETE);
            return;
        }
        Collections.sort(list);
        String listString = "The tasks in your list are:\n";
        int i = 65;
        List<String> keys = Arrays.asList(taggedList.keySet().toArray(new String[0]));
        Collections.sort(keys);
        for (String tag : keys) {
            Collections.sort(taggedList.get(tag));
            if (tag.equals(NO_TAG)) {
                continue;
            }
            ArrayList<Task> tagTasks = taggedList.get(tag);
            listString = listString.concat("\t\t\t" + (char) i + ". " + tag + ":\n");
            for (Task t : tagTasks) {
                listString = listString.concat("\t\t\t  " + (list.indexOf(t) + 1) + ". " + t + "\n");
            }
            i += 1;
        }
        if (taggedList.get(NO_TAG).size() > 0) {
            listString = listString.concat("\t\t\tOthers:\n");
            for (Task t : taggedList.get(NO_TAG)) {
                listString = listString.concat("\t\t\t  " + (list.indexOf(t) + 1) + ". " + t + "\n");
            }
        }
        ui.echo(listString.strip(), UI.Type.COMPLETE);
    }

    /***
     * Tags a Task with the given tag
     *
     * @param command the Tag command to get the index and tag from
     * @param ui UI to print output to
     * @throws WhoBotException if the command parameters are invalid or task already tagged
     */
    public void tagTask(String command, UI ui) throws WhoBotException {
        try {
            String ind = command.substring(4, command.indexOf(" /as"));
            int index = Integer.parseInt(ind) - 1;
            Task temp = list.get(index);
            String tag = command.split(" /as ")[1];
            if (temp.hasTag()) {
                throw new WhoBotException("This+ task is already tagged under the tag " + temp.getTag() + ".");
            }

            temp.setTag(tag);
            taggedList.get(NO_TAG).remove(temp);
            if (!taggedList.containsKey(tag)) {
                taggedList.put(tag, new ArrayList<>());
            }
            taggedList.get(tag).add(temp);
            ui.echo("The task " + temp.getDescription() + " has been tagged under the tag " + tag + ".",
                    UI.Type.COMPLETE);
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            throw new WhoBotException("I didn't get what you meant. "
                    + "Ensure that the command is of the form \"tag #index /as #tagname\"");
        }
    }

    /***
     * Untags a Task with the given index
     *
     * @param command the untag command to get the index from
     * @param ui UI to print output to
     * @throws WhoBotException if command parameter invalid or task not tagged previously
     */
    public void untagTask(String command, UI ui) throws WhoBotException {
        try {
            String ind = command.substring(6);
            int index = Integer.parseInt(ind) - 1;
            Task temp = list.get(index);
            String tag = temp.getTag();

            if (!temp.hasTag()) {
                throw new WhoBotException("This+ task hasn't been tagged, nothing to untag.");
            }

            taggedList.get(tag).remove(temp);
            temp.setTag("");
            if (taggedList.get(tag).isEmpty()) {
                taggedList.remove(tag);
            }
            taggedList.get(NO_TAG).add(temp);
            ui.echo("The task " + temp.getDescription() + " has been removed from the tag " + tag + ".",
                    UI.Type.COMPLETE);
        } catch (StringIndexOutOfBoundsException | NumberFormatException ex) {
            throw new WhoBotException("I didn't get what you meant. "
                    + "Ensure that the command is of the form \"untag #index\"");
        }
    }

    /***
     * Lists the Tasks under the specified Tag
     *
     * @param command String to extract tag name from
     * @param ui UI to show output in
     * @throws WhoBotException if invalid command or tag not present
     */
    public void listTagTask(String command, UI ui) throws WhoBotException {
        try {
            String tagName = command.substring(5);
            if (tagName.equals("Others")) {
                tagName = NO_TAG;
            }
            if (!taggedList.containsKey(tagName)) {
                throw new WhoBotException("The tag " + tagName + " does not exist.");
            }
            Collections.sort(taggedList.get(tagName));
            String listString = "";
            ArrayList<Task> tagTasks = taggedList.get(tagName);
            listString = listString.concat("The Tasks under the tag " + tagName + " are:\n");
            for (Task t : tagTasks) {
                listString = listString.concat("\t\t\t  " + (list.indexOf(t) + 1) + ". " + t + "\n");
            }
            ui.echo(listString, UI.Type.COMPLETE);
        } catch (IndexOutOfBoundsException ex) {
            throw new WhoBotException("I didn't get what you meant. "
                    + "Ensure that the command is of the form \"list #tagname\"");
        }
    }

    /***
     * Marks Task as Done
     *
     * @param ind index of task to mark
     * @param ui UI to show output
     * @throws WhoBotException If any error
     */
    public void markAsDone(String ind, UI ui) throws WhoBotException {
        assert ui != null;
        try {
            int index = Integer.parseInt(ind) - 1;
            if (index < list.size()) {
                list.get(index).markAsDone();
                ui.echo("Congrats! I have marked this task complete: \"" + list.get(index).getDescription() + "\"",
                        UI.Type.COMPLETE);
            } else {
                throw new WhoBotException("Oops, The index you gave is out of bound. "
                        + "There are only " + list.size() + " tasks");
            }
        } catch (NumberFormatException ex) {
            throw new WhoBotException("I didn't get what you meant. "
                    + "Ensure that the command is of the form \"done #index\"");
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
        assert ui != null;
        try {
            int index = Integer.parseInt(ind) - 1;
            if (index < list.size()) {
                list.get(index).markAsUndone();
                ui.echo("I have marked this task incomplete: \"" + list.get(index).getDescription() + "\"",
                        UI.Type.COMPLETE);
            } else {
                throw new WhoBotException("Oops, The index you gave is out of bound. There are only "
                        + list.size() + " tasks");
            }
        } catch (NumberFormatException ex) {
            throw new WhoBotException("I didn't get what you meant. "
                    + "Ensure that the command is of the form \"undo #index\"");
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
        assert ui != null;
        try {
            int index = Integer.parseInt(ind) - 1;
            if (index < list.size()) {
                Task temp = list.get(index);

                if (!WhoBot.isGui()) {
                    ui.echo("Are you sure you want to delete this task: \"" + temp.getDescription() + "\" ? (Yes/No)",
                            UI.Type.COMPLETE);
                    System.out.print(UI.COLOR_PURPLE + "> " + UI.COLOR_RESET);
                    String confirm = UI.CMD_READER.nextLine().trim();
                    if (confirm.toLowerCase(Locale.ROOT).equals("yes")) {
                        list.remove(index);
                        String tag = temp.hasTag() ? temp.getTag() : NO_TAG;
                        taggedList.get(tag).remove(temp);
                        if (!tag.equals(NO_TAG) && taggedList.get(tag).size() == 0) {
                            taggedList.remove(tag);
                        }
                        ui.echo("I have deleted this task from the list: \"" + temp.getDescription() + "\"",
                                UI.Type.START);
                        ui.echo("You now have " + list.size() + " task(s) in the list.", UI.Type.END);
                    } else {
                        ui.echo("The deletion has been cancelled.", UI.Type.COMPLETE);
                    }
                } else {
                    list.remove(index);
                    String tag = temp.hasTag() ? temp.getTag() : NO_TAG;
                    taggedList.get(tag).remove(temp);
                    if (!tag.equals(NO_TAG) && taggedList.get(tag).size() == 0) {
                        taggedList.remove(tag);
                    }
                    ui.echo("I have deleted this task from the list: \"" + temp.getDescription() + "\"", UI.Type.START);
                    ui.echo("You now have " + list.size() + " task(s) in the list.", UI.Type.END);
                }
            } else {
                throw new WhoBotException("Oops, The index you gave is out of bound. "
                        + "There are only " + list.size() + " tasks");
            }
        } catch (NumberFormatException ex) {
            throw new WhoBotException("I didn't get what you meant. "
                    + "Ensure that the command is of the form \"delete #index\"");
        }
    }

    /***
     * Checks for anomalies before adding a task
     *
     * @param task The task to check for
     * @return
     */
    private Anomaly checkAnomalies(Task task) {
        if (list.contains(task)) {
            return Anomaly.EXISTENT;
        }
        if (task instanceof Event) {
            for (Task temp : list) {
                if (temp instanceof Event && ((Event) task).getTiming().equals(((Event) temp).getTiming())) {
                    return Anomaly.CONFLICT;
                }
            }
        }
        return Anomaly.NONE;
    }

    /***
     * Adds a ToDo Task to the Task List
     *
     * @param command the command with the details for the task
     * @param ui UI to show output to
     * @throws WhoBotException If there is any error on addition
     */
    public void addTodo(String command, UI ui) throws WhoBotException {
        assert ui != null;
        try {
            Todo task = new Todo(command.substring(5));
            Anomaly anomalies = checkAnomalies(task);
            if (anomalies == Anomaly.EXISTENT) {
                throw new WhoBotException("This event already exists in your list!");
            }
            if (list.add(task)) {
                taggedList.get(NO_TAG).add(task);
                ui.echo("I have added this ToDo Task to the list: \"" + task.getDescription() + "\"", UI.Type.START);
                ui.echo("You now have " + list.size() + " task(s) in the list.", UI.Type.END);
            } else {
                throw new WhoBotException("I am sorry. The task couldn't be added, please try again.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new WhoBotException("Ensure that the command is of the form \"todo #description\"."
                    + " The description can not be empty.");
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
        assert ui != null;
        try {
            if (!command.contains("/at ")) {
                throw new WhoBotException("Ensure that the command is of the form \"event #description /at #timing\"."
                        + " The timing must be given.");
            }
            Event task = new Event(command.substring(6));
            Anomaly anomalies = checkAnomalies(task);
            if (anomalies == Anomaly.EXISTENT) {
                throw new WhoBotException("This event already exists in your list!");
            } else if (anomalies == Anomaly.CONFLICT) {
                throw new WhoBotException("There is a conflict in your schedule, you already have another event at "
                        + task.getDateTimeFormatted() + " :(");
            }
            if (list.add(task)) {
                taggedList.get(NO_TAG).add(task);
                ui.echo("I have added this Event Task to the list: \"" + task.getDescription() + "\"", UI.Type.START);
                ui.echo("You now have " + list.size() + " task(s) in the list.", UI.Type.END);
            } else {
                throw new WhoBotException("I am sorry. The task couldn't be added, please try again.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new WhoBotException("Ensure that the command is of the form \"event #description /at #timing\"."
                    + " The description can not be empty.");
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
        assert ui != null;
        try {
            if (!command.contains("/by ")) {
                throw new WhoBotException("Ensure that the command is of the form "
                        + "\"deadline #description /by #deadline\"."
                        + " The deadline must be given.");
            }
            Deadline task = new Deadline(command.substring(9));
            Anomaly anomalies = checkAnomalies(task);
            if (anomalies == Anomaly.EXISTENT) {
                throw new WhoBotException("This event already exists in your list!");
            }
            if (list.add(task)) {
                taggedList.get(NO_TAG).add(task);
                ui.echo("I have added this Deadline Task to the list: \"" + task.getDescription() + "\"",
                        UI.Type.START);
                ui.echo("You now have " + list.size() + " task(s) in the list.", UI.Type.END);
            } else {
                throw new WhoBotException("I am sorry. The task couldn't be added, please try again.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new WhoBotException("Ensure that the command is of the form \"deadline #description /by #deadline\"."
                    + " The description can not be empty.");
        }
    }

    /***
     * Shows Tasks on a specific date
     * @param command the command with the date specified
     * @param ui UI to show output to
     * @throws WhoBotException If there is any error in the command
     */
    public void showOnDate(String command, UI ui) throws WhoBotException {
        assert ui != null;
        try {
            if (!command.contains("/on ")) {
                throw new WhoBotException("Ensure that the command is of the form \"show /on #date\"."
                        + " The deadline must be given.");
            }
            LocalDate date = LocalDate.parse(command.split(" /on ")[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
            ArrayList<Task> tasksToDisplay = new ArrayList<>();
            for (Task tempTask : list) {
                if (tempTask instanceof Deadline && ((Deadline) tempTask).getDeadline().toLocalDate().equals(date)) {
                    tasksToDisplay.add(tempTask);
                } else if (tempTask instanceof Event && ((Event) tempTask).getTiming().toLocalDate().equals(date)) {
                    tasksToDisplay.add(tempTask);
                }
            }
            String dateString = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            if (tasksToDisplay.isEmpty()) {
                ui.echo("There are currently no tasks on " + dateString, UI.Type.COMPLETE);
                return;
            }
            Collections.sort(tasksToDisplay);
            String listString = "The tasks on " + dateString + " are:\n";
            int i;
            for (i = 0; i < tasksToDisplay.size() - 1; i++) {
                listString = listString.concat("\t\t\t" + (i + 1) + ". " + tasksToDisplay.get(i) + "\n");
            }
            listString = listString.concat("\t\t\t" + (i + 1) + ". " + tasksToDisplay.get(i));
            ui.echo(listString, UI.Type.COMPLETE);
        } catch (IndexOutOfBoundsException e) {
            throw new WhoBotException("Ensure that the command is of the form \"show /on #date\"."
                    + " The description can not be empty.");
        }
    }

    /***
     * Finds Tasks containing the specific keyword
     *
     * @param text Keyword to search for
     * @param ui UI to show output to
     */
    public void findTask(String text, UI ui) {
        assert ui != null;
        String searchText = text.substring(6).toLowerCase(Locale.ROOT);
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : this.list) {
            if (task.getDescription().toLowerCase(Locale.ROOT).contains(searchText)) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            ui.echo("There are currently no tasks that match your search.", UI.Type.COMPLETE);
            return;
        }
        String listString = "The tasks in your list that match your search are:\n";
        int i;
        for (i = 0; i < matchingTasks.size() - 1; i++) {
            listString = listString.concat("\t\t\t" + (i + 1) + ". " + matchingTasks.get(i) + "\n");
        }
        listString = listString.concat("\t\t\t" + (i + 1) + ". " + matchingTasks.get(i));
        ui.echo(listString, UI.Type.COMPLETE);
    }
}
