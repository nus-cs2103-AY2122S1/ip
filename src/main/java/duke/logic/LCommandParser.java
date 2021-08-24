package duke.logic;

import duke.DukeException;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TasksEnum;

/**
 * The logic for parsing commands typed by the user.
 */
public class LCommandParser {
    private boolean willExit;
    private static final String INVALID_COMMAND = "Invalid input. Type \"help\" for more information.";
    private static final String EMPTY_INPUT_MESSAGE = "Input is empty. Type \"help\" for more information.";
    private static final String TOO_LITTLE_ARGUMENTS_MESSAGE = "Too little arguments. Type \"help\" " +
            "followed by the command for more information.";
    private static final String FULL_TASKLIST_MESSAGE = "Unable to add task. List is full. Consider deleting" +
            " some tasks";
    private static final String INVALID_NUMBER_MESSAGE = "Please input a valid task number after the command.";




    /**
     * Creates a new command parser for the input.
     *
     * @param input The string input from the user.
     * @param storage The storage logic that allows the command parser to write the task list data to it.
     * @param taskList The list of tasks.
     */
    public LCommandParser(String input, TaskList taskList, LStorage storage, Ui ui) {
        if (input == null || input.equals("")) {
            throw new DukeException(EMPTY_INPUT_MESSAGE);
        }
        String[] inputArr = input.split(" ", 2);
        LCommandsEnum commandEnum;
        try {
            commandEnum = LCommandsEnum.valueOf(inputArr[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException(INVALID_COMMAND);
        }
        this.willExit = false;
        switch (commandEnum) {
        case BYE:
            ui.sayGoodBye();
            this.willExit = true;
            break;
        case LIST:
            ui.printAllTasks(taskList.getTasks());
            break;
        case UPCOMING:
            ui.printUpcomingTasks(taskList.getTasks());
            break;
        case HELP:
            try {
                ui.displayHelp(inputArr.length == 1 ? null : LCommandsEnum.valueOf(inputArr[1].toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new DukeException(INVALID_COMMAND);
            }
            break;
        default:
            if (inputArr.length < 2) {
                throw new DukeException(TOO_LITTLE_ARGUMENTS_MESSAGE);
            }
            switch (commandEnum) {
            case TODO:
            case EVENT:
            case DEADLINE:
                ui.addTaskMessage(addTask(commandEnum.name(), inputArr[1], taskList), taskList.size());
                break;
            default:
                int taskNumber;
                try {
                    taskNumber = Integer.parseInt(inputArr[1]);
                } catch (NumberFormatException e) {
                    throw new DukeException(INVALID_NUMBER_MESSAGE);
                }
                switch (commandEnum) {
                case DONE:
                    if (!taskList.markAsDone(taskNumber)) { // task already marked as done
                        throw new DukeException("You have already marked this task (%s) as done",
                                taskList.getTask(taskNumber));
                    }
                    ui.markAsDoneMessage(taskList.getTask(taskNumber));
                    break;
                case DELETE:
                    ui.removeTaskMessage(taskList.removeTask(taskNumber), taskList.size());
                    break;
                default:
                    throw new DukeException(INVALID_COMMAND);
                }
            }
            storage.updateDukeTextFile();

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

//    private void processInput() {
//        String[] splitInput = commandEnum.split(" ", 2);
//        if (splitInput[0].equals("done") || splitInput[0].equals("delete")) {
//            int taskNumber;
//            if (splitInput.length != 2) {
//                throw new DukeException("Please key in %s [number].", splitInput[0]);
//            } else {
//                try {
//                    taskNumber = Integer.parseInt(splitInput[1]);
//                } catch (NumberFormatException e) {
//                    throw new DukeException("Please enter a number after " + splitInput[0]);
//                }
//                if (splitInput[0].equals("done")) {
//                    if (!taskList.markAsDone(taskNumber)) { // task already marked as done
//                        throw new DukeException("You have already marked this task (%s) as done",
//                                taskList.getTask(taskNumber));
//                    }
//                    System.out.println("Nice! I've marked this task as done: ");
//                    System.out.println("    " + taskList.getTask(taskNumber));
//                } else { // first word is delete
//                    Task task = taskList.removeTask(taskNumber);
//                    System.out.println("Noted. I've removed this task:");
//                    System.out.println("    " + task);
//                    System.out.println("Now you have " + taskList.size() + " task"
//                            + (taskList.size() <= 1 ? " in the list" : "s in the list"));
//                }
//            }
//        } else if (splitInput.length == 2) {
//            Task task = addTask(splitInput[0], splitInput[1], taskList);
//            System.out.println("Got it. I have added this task:");
//            System.out.println("    " + task);
//            System.out.println("Now you have " + taskList.size() + " task"
//                    + (taskList.size() <= 1 ? " in the list" : "s in the list"));
//        } else {
//            throw new DukeException(INVALID_COMMAND);
//        }
//        storage.updateDukeTextFile();
//    }

    /**
     * Attempts to add the task to the tasklist based on the user command.
     * @param action the action of either todo, event or deadline
     * @param otherInput the rest of the string without the action
     * @param taskList the list of task to be added to
     * @return the task that is added.
     */
    private static Task addTask(String action, String otherInput, TaskList taskList) {
        TasksEnum tasksEnum = TasksEnum.valueOf(action);
        Task result = tasksEnum.getTask(otherInput);
        if (!taskList.addTask(result)) { // if adding task is unsuccessful
            throw new DukeException(FULL_TASKLIST_MESSAGE);
        }
        return result;
    }

}
