package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidTaskException;
import duke.exception.MissingTaskException;
import duke.exception.MissingTimeException;

/**
 * This class handles commands dealing with the user's task list.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Performs action on task list based on the user's inputs.
     *
     * @param command The command to perform.
     * @param input The input from the user.
     * @return The String to return based on the action taken.
     * @throws InvalidCommandException If user command is unrecognised.
     * @throws InvalidTaskException If task cannot be found in user's list.
     * @throws MissingTaskException If task is unspecified after command.
     * @throws MissingTimeException If time is unspecified after command.
     * */
    public String performCommand(String command, String input)
            throws InvalidCommandException, InvalidTaskException, MissingTaskException, MissingTimeException {

        assert command.length() > 0 : "Command cannot be empty!";
        assert input.length() > 0 : "Input cannot be empty!";

        switch (command) {
        case "list":
            return printList();
        case "mark":
            int taskToMark = Integer.parseInt(input.substring(5));
            return markTaskAsDone(taskToMark);
        case "delete":
            int taskToDelete = Integer.parseInt(input.substring(7));
            return deleteTask(taskToDelete);
        case "todo":
            return addToDo(input);
        case "deadline":
            return addDeadline(input);
        case "event":
            return addEvent(input);
        case "find":
            return findTask(input);
        default:
            throw new InvalidCommandException("I don't quite understand what that means.\n"
                    + "Could you please rephrase that?\n");
        }
    }

    /**
     * Returns every task in the list.
     *
     * @return The String representing the tasks in the list.
     */
    public String printList() {
        StringBuilder output;
        if (taskList.size() == 0) {
            output = new StringBuilder("There are no tasks to be done! Hooray!\n");
        } else {
            output = new StringBuilder("Here is your list of tasks:\n");
            for (int i = 0; i < taskList.size(); i++) {
                String taskName = taskList.get(i).toString();
                output.append(i + 1).append(".").append(taskName).append("\n");
            }
        }
        return output.toString();
    }

    /**
     * Marks the corresponding task as done and returns confirmation.
     *
     * @param taskToMark The index of the task to be marked.
     * @return The String representing the confirmation message.
     * @throws InvalidTaskException If task cannot be found in user's list.
     */
    public String markTaskAsDone(int taskToMark) throws InvalidTaskException {
        if (taskToMark <= 0 || taskToMark > taskList.size()) {
            throw new InvalidTaskException("You might have mistyped the task number.\n"
                    + "Please recheck your task number and enter again.\n");
        }

        taskList.get(taskToMark - 1).markAsDone();

        return String.format("Great job!\n"
                + "The following task is marked as done:\n"
                + "\t%s\n",
                taskList.get(taskToMark - 1).toString());
    }

    /**
     * Deletes the corresponding task as done and returns confirmation.
     *
     * @param taskToDelete The index of the task to be deleted.
     * @return The String representing the confirmation.
     * @throws InvalidTaskException If task cannot be found in list.
     */
    public String deleteTask(int taskToDelete) throws InvalidTaskException {
        if (taskToDelete <= 0 || taskToDelete > taskList.size()) {
            throw new InvalidTaskException("You might have mistyped the task number.\n"
                    + "Please recheck your task number and enter again.\n");
        }

        String output = String.format("Done!\n"
                + "The following task has been removed:\n"
                + "\t%s\n"
                + "You now have %d "
                + (taskList.size() - 1 == 1 ? "task" : "tasks")
                + " left in your list!\n",
                taskList.get(taskToDelete - 1).toString(), taskList.size() - 1);

        taskList.remove(taskToDelete - 1);

        return output;
    }

    /**
     * Adds the to do entered by the user to the list and returns it.
     *
     * @param input The to do inputted by the user.
     * @return The String representing the added to do.
     * @throws MissingTaskException If task is unspecified after command.
     */
    public String addToDo(String input) throws MissingTaskException {
        if (input.length() < 6) {
            throw new MissingTaskException("You might have missed out on the task.\n"
                    + "Could you please enter it again?\n");
        }

        String taskName = input.substring(5);
        taskList.add(new ToDo(taskName));
        return printTaskAdded(taskName);
    }


    /**
     * Adds the deadline entered by the user to the list and returns it.
     *
     * @param input The deadline inputted by the user.
     * @return The String representing the added deadline.
     * @throws MissingTaskException If task is unspecified after command.
     * @throws MissingTimeException If time is unspecified after command.
     * @throws DateTimeParseException If date entered is of the wrong format.
     */
    public String addDeadline(String input)
            throws MissingTaskException, MissingTimeException, DateTimeParseException {
        int separation = input.indexOf(" /by ");

        if (separation == -1) {
            throw new MissingTimeException("You might have missed out on the time.\n"
                    + "Could you please enter it again?\n");
        }

        if (separation < 11) {
            throw new MissingTaskException("You might have missed out on the task.\n"
                    + "Could you please enter it again?\n");
        }

        String taskName = input.substring(9, separation);

        if (input.substring(separation + 6).length() < 1) {
            throw new MissingTimeException("Your date might not be in the correct format.\n"
                    + "Please ensure it is in the YYYY-MM-DD format.\n");
        }

        String timeFull = input.substring(separation + 5);
        int timeFullSeparation;

        if ((timeFullSeparation = timeFull.indexOf(" ")) != -1) {
            String time = timeFull.substring(timeFullSeparation + 1);
            LocalDate date = LocalDate.parse(timeFull.substring(0, timeFullSeparation));
            taskList.add(new Deadline(taskName, date, time));
        } else {
            LocalDate date = LocalDate.parse(timeFull);
            taskList.add(new Deadline(taskName, date));
        }

        return printTaskAdded(taskName);
    }

    /**
     * Adds the event entered by the user to the list and returns it.
     *
     * @param input The event inputted by the user.
     * @return The String representing the added event.
     * @throws MissingTaskException If task is unspecified after command.
     * @throws MissingTimeException If time is unspecified after command.
     * @throws DateTimeParseException If date entered is of the wrong format.
     */
    public String addEvent(String input)
            throws MissingTaskException, MissingTimeException, DateTimeParseException {
        int separation = input.indexOf(" /at ");

        if (separation == -1) {
            throw new MissingTimeException("You might have missed out on the time.\n"
                    + "Could you please enter it again?\n");
        }

        if (separation < 8) {
            throw new MissingTaskException("You might have missed out on the task.\n"
                    + "Could you please enter it again?\n");
        }
        String taskName = input.substring(6, separation);

        if (input.substring(separation + 6).length() < 1) {
            throw new MissingTimeException("Your date might not be in the correct format.\n"
                    + "Please ensure it is in the YYYY-MM-DD format.\n");
        }

        String timeFull = input.substring(separation + 5);
        int timeFullSeparation;

        if ((timeFullSeparation = timeFull.indexOf(" ")) != -1) {
            String time = timeFull.substring(timeFullSeparation + 1);
            LocalDate date = LocalDate.parse(timeFull.substring(0, timeFullSeparation));
            taskList.add(new Event(taskName, date, time));
        } else {
            LocalDate date = LocalDate.parse(timeFull);
            taskList.add(new Event(taskName, date));
        }

        assert taskList.size() > 0 : "Check if item was added properly to task list!";

        return printTaskAdded(taskName);
    }

    /**
     * Returns the confirmation of the addition of the last task.
     *
     * @param taskName The name of the task just added.
     * @return The String representing the added task.
     */
    public String printTaskAdded(String taskName) {
        return String.format("Gotcha! The following task has been added:\n"
                + "\t%s\n"
                + "You now have %d "
                + (taskList.size() == 1 ? "task" : "tasks")
                + " in your list!\n",
                taskName, taskList.size());
    }

    /**
     * Finds the tasks requested by the user and returns them.
     *
     * @param input The event inputted by the user.
     * @return The String representing the found tasks.
     * @throws MissingTaskException If task is unspecified after command.
     */
    public String findTask(String input) throws MissingTaskException {
        ArrayList<Task> toPrint = new ArrayList<>();
        StringBuilder output;

        if (input.length() < 6) {
            throw new MissingTaskException("You might have missed out on the task.\n"
                    + "Could you please enter it again?\n");
        }

        String taskName = input.substring(5);

        for (Task task : taskList) {
            String currentTask = task.toString();
            if (currentTask.contains(taskName)) {
                toPrint.add(task);
            }
        }

        if (toPrint.size() == 0) {
            output = new StringBuilder("It seems like there are no tasks matching your search.\n");
        } else {
            output = new StringBuilder("The matching "
                    + (toPrint.size() == 1
                    ? "task in your list is:"
                    : "tasks in your list are:")
                    + "\n");

            for (int i = 0; i < toPrint.size(); i++) {
                output.append(String.format("%d.%s\n", i + 1, toPrint.get(i)));
            }
        }

        assert output.length() > 0 : "Output should contain text!";

        return output.toString();
    }

    /**
     * Returns the tasks added to the task list.
     *
     * @return The array containing the tasks.
     */
    public ArrayList<Task> getTasks() {
        return taskList;
    }
}
