package Utils;

import Duke.DukeException;
import Duke.UI;
import Task.Task;
import Task.Event;
import Task.Deadline;
import Task.Todo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class TaskList {

    private final ArrayList<Task> LIST = new ArrayList<>();
    private Storage storage;
    public TaskList(Storage storage) throws DukeException {
        this.storage = storage;
        storage.readData(LIST);
    }

    public ArrayList<Task> getLIST() {
        return LIST;
    }

    //Method to Print List
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

    // Method to Mark Task as Done
    public void markAsDone(String ind, UI ui) throws DukeException {
        try {
            int index = Integer.parseInt(ind) - 1;
            if (index < LIST.size()) {
                LIST.get(index).markAsDone();
                ui.echo("Congrats! I have marked this task complete: \"" + LIST.get(index).getDescription() + "\"", UI.TYPE.COMPLETE);
            } else {
                throw new DukeException("Oops, The index you gave is out of bound. There are only " + LIST.size() + " tasks");
            }
        } catch (NumberFormatException  ex) {
            throw new DukeException("I didn't get what you meant. Ensure that the command is of the form \"done #index\"");
        }
    }

    // Method to Mark Task as Not Done
    public void markAsUndone(String ind, UI ui) throws DukeException {
        try {
            int index = Integer.parseInt(ind) - 1;
            if (index < LIST.size()) {
                LIST.get(index).markAsUndone();
                ui.echo("I have marked this task incomplete: \"" + LIST.get(index).getDescription() + "\"", UI.TYPE.COMPLETE);
            } else {
                throw new DukeException("Oops, The index you gave is out of bound. There are only " + LIST.size() + " tasks");
            }
        } catch (NumberFormatException  ex) {
            throw new DukeException("I didn't get what you meant. Ensure that the command is of the form \"undo #index\"");
        }
    }

    // Method to Delete Task from List
    public void deleteFromList(String ind, UI ui) throws DukeException {
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
                throw new DukeException("Oops, The index you gave is out of bound. There are only " + LIST.size() + " tasks");
            }
        } catch (NumberFormatException  ex) {
            throw new DukeException("I didn't get what you meant. Ensure that the command is of the form \"delete #index\"");
        }
    }

    // Method to Add a ToDos type Task into List
    public void addTODO(String command, UI ui) throws DukeException {
        try {
            Todo task = new Todo(command.substring(5));
            if (LIST.add(task)) {
                ui.echo("I have added this ToDo Task to the list: \"" + task.getDescription() + "\"", UI.TYPE.START);
                ui.echo("You now have " + LIST.size() + " task(s) in the list.", UI.TYPE.END);
            } else {
                throw new DukeException("I am sorry. The task couldn't be added, please try again.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Ensure that the command is of the form \"todo #description\". The description can not be empty.");
        }
    }

    // Method to Add a Events UI.TYPE Task into List
    public void addEvent(String command, UI ui) throws DukeException {
        try {
            if (!command.contains("/at ")) {
                throw new DukeException("Ensure that the command is of the form \"event #description /at #timing\". The timing must be given.");
            }
            Event task = new Event(command.substring(6));
            if (LIST.add(task)) {
                ui.echo("I have added this Event Task to the list: \"" + task.getDescription() + "\"", UI.TYPE.START);
                ui.echo("You now have " + LIST.size() + " task(s) in the list.", UI.TYPE.END);
            } else {
                throw new DukeException("I am sorry. The task couldn't be added, please try again.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Ensure that the command is of the form \"event #description /at #timing\". The description can not be empty.");
        }
    }

    // Method to Add a Deadline type Task into List
    public void addDeadline(String command, UI ui) throws DukeException {
        try {
            if (!command.contains("/by ")) {
                throw new DukeException("Ensure that the command is of the form \"deadline #description /by #deadline\". The deadline must be given.");
            }
            Deadline task = new Deadline(command.substring(9));
            if (LIST.add(task)) {
                ui.echo("I have added this Deadline Task to the list: \"" + task.getDescription() + "\"", UI.TYPE.START);
                ui.echo("You now have " + LIST.size() + " task(s) in the list.", UI.TYPE.END);
            } else {
                throw new DukeException("I am sorry. The task couldn't be added, please try again.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Ensure that the command is of the form \"deadline #description /by #deadline\". The description can not be empty.");
        }
    }

    public void showOnDate(String command, UI ui) throws DukeException {
        try {
            if (!command.contains("/on ")) {
                throw new DukeException("Ensure that the command is of the form \"show /on #date\". The deadline must be given.");
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
            throw new DukeException("Ensure that the command is of the form \"show /on #date\". The description can not be empty.");
        }
    }

}
