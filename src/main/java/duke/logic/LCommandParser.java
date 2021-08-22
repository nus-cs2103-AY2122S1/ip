package duke.logic;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TasksEnum;

/**
 * The logic for parsing commands typed by the user.
 */
public class LCommandParser {
    private final String input;
    private final TaskList taskList;
    private final LStorage storage;
    private boolean willExit;
    private static final String INVALID_ADD_TASK_MESSAGE = "Invalid input. Please enter the action, followed by the description.\n" +
            "If action is \"deadline\", add a date in the format:\"/by dd/mm/yyyy hh:mm\".\n" +
            "If action is \"event\", add a date in the format: \"/at dd/mm/yyyy hh:mm\".\n" +
            "For example: todo Buy a gift for mum\n" +
            "For example: deadline CS2103T individual project /by 26/08/2021 23:59\n" +
            "For example: event CS2103T lecture /at 19/08/2021 16:00";

    /**
     * Creates a new command parser for the input.
     *
     * @param input The string input from the user.
     * @param storage The storage logic that allows the command parser to write the task list data to it.
     * @param taskList The list of tasks.
     */
    public LCommandParser(String input, TaskList taskList, LStorage storage) {
        this.input = input;
        this.taskList = taskList;
        this.storage = storage;
        this.willExit = false;
        switch (input) {
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            this.willExit = true;
            break;
        case "list":
            LPrintTask.printAllTasks(taskList.getTasks());
            break;
        case "upcoming":
            LPrintTask.printUpcomingTasks(taskList.getTasks());
            break;
        default:
            processInput();
        }
    }

    /**
     * Checks if the command given by the user is to exit.
     *
     * @return true if and only if the command is to exit.
     */
    public boolean willExit() {
        return willExit;
    }

    private void processInput() {
        String[] splitInput = input.split(" ", 2);
        if (splitInput[0].equals("done") || splitInput[0].equals("delete")) {
            int taskNumber;
            if (splitInput.length != 2) {
                throw new DukeException("Please key in %s [number].", splitInput[0]);
            } else {
                try {
                    taskNumber = Integer.parseInt(splitInput[1]);
                } catch (NumberFormatException e) {
                    throw new DukeException("Please enter a number after " + splitInput[0]);
                }
                if (splitInput[0].equals("done")) {
                    if (!taskList.markAsDone(taskNumber)) { // task already marked as done
                        throw new DukeException("You have already marked this task (%s) as done",
                                taskList.getTask(taskNumber));
                    }
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println("    " + taskList.getTask(taskNumber));
                } else { // first word is delete
                    Task task = taskList.removeTask(taskNumber);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("    " + task);
                    System.out.println("Now you have " + taskList.size() + " task"
                            + (taskList.size() <= 1 ? " in the list" : "s in the list"));
                }
            }
        } else if (splitInput.length == 2) {
            Task task = addTask(splitInput[0], splitInput[1], taskList);
            System.out.println("Got it. I have added this task:");
            System.out.println("    " + task);
            System.out.println("Now you have " + taskList.size() + " task"
                    + (taskList.size() <= 1 ? " in the list" : "s in the list"));
        } else {
            throw new DukeException(INVALID_ADD_TASK_MESSAGE);
        }
        storage.updateDukeTextFile();
    }

    /**
     * Attempts to add the task to the tasklist based on the user command.
     * @param action the action of either todo, event or deadline
     * @param otherInput the rest of the string without the action
     * @param taskList the list of task to be added to
     * @return the task that is added.
     */
    private static Task addTask(String action, String otherInput, TaskList taskList) {
        TasksEnum tasksEnum;
        boolean addTaskIsSuccessful;
        try {
            tasksEnum = TasksEnum.valueOf(action.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("Only todo, event or deadline allowed.");
        }
        Task result = tasksEnum.getTask(otherInput);
        addTaskIsSuccessful = taskList.addTask(result);
        if (!addTaskIsSuccessful) {
            throw new DukeException("Unable to add task. List is full. Consider deleting some tasks");
        }
        return result;
    }

}
