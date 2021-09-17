package duke.main;

import java.util.function.BiFunction;
import java.util.stream.Stream;

import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.exception.InvalidParamException;
import duke.exception.NoDescriptionException;
import duke.exception.OutOfBoundsOfTaskListException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Encapsulates methods that handle and interpret user input.
 */
public class Parser {

    /** The TaskList that will be altered based on user input */
    private TaskList taskList;

    /**
     * Enum that holds information about acting on a task, not including actions that result in new Tasks in the list.
     * Task actions include deleting a task (DELETE) and marking a Task as done (DONE).
     */
    private enum TaskAction {
        DELETE("delete", 7, "deleted", TaskList::removeTask),
        DONE("done", 5, "marked as done", TaskList::markDoneInTaskList);

        /** Name of the task action */
        private String name;
        /** Index to substring the input so as to remove the first word (command word) */
        private int substringIndex;
        /** Message fragment when the action is successfully completed */
        private String successMessage;
        /** The method to act on the Task */
        private BiFunction<TaskList, Integer, Task> actionOnTask;

        /**
         * Constructs a TaskAction.
         *
         * @param name Name of the TaskAction.
         * @param substringIndex Index to substring the input so as to remove the first word (command word).
         * @param successMessage Message fragment when the action is successfully completed.
         * @param actionOnTask Method used to complete the action on a Task.
         */
        TaskAction(String name, int substringIndex, String successMessage,
                   BiFunction<TaskList, Integer, Task> actionOnTask) {
            this.name = name;
            this.substringIndex = substringIndex;
            this.successMessage = successMessage;
            this.actionOnTask = actionOnTask;
        }
    }

    /**
     * Enum that holds information about a Task attempting to be added.
     */
    private enum TaskToAdd {
        TODO("todo ", 5, Todo::setTodo),
        DEADLINE("deadline ", 9, Deadline::setDeadline),
        EVENT("event ", 6, Event::setEvent);

        /**
         * Acts similar to Java 8's functional interface `Function`, but its method throws an InvalidParamException.
         *
         * @param <T> The type that is input into the method
         * @param <R> The type that is returned by the method
         */
        @FunctionalInterface
        public interface CheckedFunction<T, R> {
            R apply(T t) throws InvalidParamException;
        }

        /** What the user input should start with to refer to this Task */
        private String inputPrefix;
        /** Index to substring the user input with */
        private int substringIndex;
        /** The CheckedFunction used to return the Task to be added */
        private CheckedFunction<String, Task> setTask;

        /**
         * Constructs the TaskToAdd.
         *
         * @param inputPrefix String that the input should start with to indicate what Task to add.
         * @param substringIndex Index to substring the input so as to remove the first word (command word).
         * @param setTask CheckedFunction used to return the Task to be added.
         */
        TaskToAdd(String inputPrefix, int substringIndex, CheckedFunction<String, Task> setTask) {
            this.inputPrefix = inputPrefix;
            this.substringIndex = substringIndex;
            this.setTask = setTask;
        }
    }

    /**
     * Constructs a Parser instance that acts on the given TaskList.
     *
     * @param taskList TaskList that the Parser will alter or act on.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Takes user input to interpret and acts on the TaskList appropriately.
     *
     * @param input Input string from the user.
     */
    public void handleInput(String input) {
        try {
            if (input.equals("bye")) {

                Duke.exit();

            } else if (input.equals("list")) {

                this.taskList.printList();

            } else if (input.startsWith("find ")) {

                findTask(input);

            } else if (input.startsWith("done ")) {

                alterTask(input, Parser.TaskAction.DONE);

            } else if (input.startsWith("delete ")) {

                alterTask(input, Parser.TaskAction.DELETE);
                this.taskList.printNumberOfTasks();

            } else if (input.startsWith("update ")) {

                updateTaskInTaskList(input);

            } else if (isTaskCommand(input)) {

                vetoTask(input);

            } else {

                throw new InvalidInputException();

            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Finds and prints the tasks that match the user input.
     *
     * @param input Input string from the user.
     */
    private void findTask(String input) {
        String searchTerm = input.split(" ", 2)[1];
        System.out.println("These tasks have descriptions that contain the phrase: " + "'" + searchTerm + "'\n");
        this.taskList.printAllContains(searchTerm);
    }

    /**
     * Alters the TaskList depending on the TaskAction given, which could either be DELETE or DONE.
     *
     * @param input Input string from the user.
     * @param action TaskAction to be done.
     * @throws InvalidParamException If the information of the action in the input is in the wrong format.
     * @throws OutOfBoundsOfTaskListException If the Task index given is out of the range of Tasks in the TaskList.
     */
    private void alterTask(String input, Parser.TaskAction action)
            throws InvalidParamException, OutOfBoundsOfTaskListException {
        try {

            Task taskToBeAltered;

            // For user, the list starts at 1. However, our list index starts at 0
            int taskIndex = Integer.parseInt(input.substring(action.substringIndex)) - 1;

            // Apply a delete or done function specified in the enum depending on the action
            taskToBeAltered = action.actionOnTask.apply(this.taskList, taskIndex);
            assert taskToBeAltered != null : "taskToBeAltered should not be null";
            System.out.println("This task is successfully " + action.successMessage + "!\n\n"
                    + "    " + taskToBeAltered);

        } catch (StringIndexOutOfBoundsException | NumberFormatException e1) {

            throw new InvalidParamException("Please specify which task you would like to have\n"
                    + action.successMessage + " by adding a single number after '" + action.name + "'!\n"
                    + "i.e. " + action.name + " 1");

        } catch (IndexOutOfBoundsException e2) {

            throw new OutOfBoundsOfTaskListException();

        }
    }

    /**
     * Checks the input and updates a specific task if the inputs are appropriate.
     *
     * @param input Input string from the user.
     * @throws NoDescriptionException If the description of the task is empty.
     * @throws InvalidParamException If the information of the task is in the wrong format.
     * @throws OutOfBoundsOfTaskListException If the Task index given is out of the range of Tasks in the TaskList.
     */
    private void updateTaskInTaskList(String input) throws
            NoDescriptionException, InvalidParamException, OutOfBoundsOfTaskListException {

        String paramExceptionMessage = "Please use update in the following format:\n"
                + "update (task number) (task + description + needed info)\n"
                + "i.e update 2 event Career fair /at 2021-09-06";

        try {
            // Split the input in 3 params: command word, task number, task description
            final int NUMBER_OF_PARAMS = 3;
            String[] params = input.split(" ", NUMBER_OF_PARAMS);
            checkUpdateParams(params, paramExceptionMessage);

            // Initialise variables to hold significant params
            int taskIndex = Integer.parseInt(params[1]) - 1;
            String taskDescription = params[2];

            // Instantiate and update the TaskList with the new Task
            Task newTask = instantiateTask(taskDescription);
            Task oldTask = this.taskList.updateTask(taskIndex, newTask);
            System.out.println("This Task is successfully updated from:\n\n"
                    + "    " + oldTask + "\n\n"
                    + "to:\n\n"
                    + "    " + newTask);

        } catch (NumberFormatException e1) {

            throw new InvalidParamException(paramExceptionMessage);

        } catch (IndexOutOfBoundsException e2) {

            throw new OutOfBoundsOfTaskListException();

        }
    }

    /**
     * Checks whether the parameters of the update command are valid.
     *
     * @param params String array obtained from splitting the user input into parameters.
     * @param paramExceptionMessage Message displayed if there is an InvalidParamException thrown.
     * @throws InvalidParamException If the information of the task is in the wrong format.
     * @throws NoDescriptionException If the description of the task is empty.
     */
    private void checkUpdateParams(String[] params, String paramExceptionMessage)
            throws InvalidParamException, NoDescriptionException {
        String taskDescription;

        // If the input does not have 3 params, or if the third param is not a task command, throw an exception
        if (params.length < 3 || !isTaskCommand(taskDescription = params[2])) {
            throw new InvalidParamException(paramExceptionMessage);
        }

        checkDescription(taskDescription);
    }

    /**
     * Looks through all possible TaskToAdd and check if the input has a prefix that coincides with any of them.
     *
     * @param input Input string from the user.
     * @return True if the input is a command to add a Task, false otherwise.
     */
    private boolean isTaskCommand(String input) {
        return Stream.of(TaskToAdd.values())
                .anyMatch(x -> input.startsWith(x.inputPrefix));
    }

    /**
     * Checks the task that is being attempted to be added into the task list.
     * If the input contains the appropriate information, the task is added.
     *
     * @param input Input string from the user.
     * @throws NoDescriptionException If the description of the task is empty.
     * @throws InvalidParamException If the information of the task is in the wrong format.
     */
    private void vetoTask(String input) throws NoDescriptionException, InvalidParamException {
        checkDescription(input);
        addIntoTaskList(instantiateTask(input));
    }

    /**
     * Checks if the input contains the description for the task to be added.
     * Throws a NoDescriptionException otherwise.
     *
     * @param input Input string from the user.
     * @throws NoDescriptionException If the description of the task is empty.
     */
    private void checkDescription(String input) throws NoDescriptionException {
        String[] inputArray = input.split(" ");
        if (inputArray.length == 1) {
            throw new NoDescriptionException(inputArray[0]);
        }
    }

    /**
     * Looks through the possible tasks that can be added and check if the input matches.
     * Once found, instantiate and return it.
     *
     * @param input Input string from the user.
     * @return Task to be added into the TaskList.
     * @throws InvalidParamException If the information of the task is in the wrong format.
     */
    private Task instantiateTask(String input) throws InvalidParamException {
        Task newTask = null;
        for (TaskToAdd task : TaskToAdd.values()) {
            if (input.startsWith(task.inputPrefix)) {
                newTask = task.setTask.apply(input.substring(task.substringIndex));
                break;
            }
        }
        assert newTask != null : "newTask should not be null";
        return newTask;
    }

    /**
     * Adds the given Task into the TaskList and prints the confirmation.
     *
     * @param task Task to add into the TaskList.
     */
    private void addIntoTaskList(Task task) {
        this.taskList.addTask(task);
        System.out.println("You have successfully added the following task!\n\n"
                + "    " + task);
        this.taskList.printNumberOfTasks();
    }
}
