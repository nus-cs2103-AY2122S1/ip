package duke.main;

import duke.exception.DukeException;
import duke.exception.NoDescriptionException;
import duke.exception.InvalidParamException;
import duke.task.Storage;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

public class Parser {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private enum TaskAction {
        DELETE("delete", 7, "deleted"),
        DONE("done", 5, "marked as done");

        // Name of the task action
        private final String name;

        // At what index to substring the input so as to remove the first word
        private final int substringIndex;

        // The message fragment when the action is successfully completed
        private final String successMessage;

        TaskAction(String name, int substringIndex, String successMessage) {
            this.name = name;
            this.substringIndex = substringIndex;
            this.successMessage = successMessage;
        }
    }

    public Parser(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Takes user input and handles it appropriately.
     *
     * @param input the input string from the Scanner
     */
    public boolean handleInput(String input) {
        ui.printSingleDivider();

        if (input.equals("bye")) {

            return false;

        } else if (input.equals("list")) {

            System.out.println("Output: This is your current list!\n");
            taskList.printList();

        } else if (input.startsWith("done")) {

            alterTask(input, Parser.TaskAction.DONE);

        } else if (input.startsWith("delete")) {

            // If successfully deleted task, then print current number of tasks.
            if (alterTask(input, Parser.TaskAction.DELETE)) {
                taskList.printNumberOfTasks();
            }

        } else {
            vetoTask(input);
        }

        storage.writeToFile(taskList);
        ui.printDoubleDivider();
        return true;
    }


    /**
     * Alters the task depending on the TaskAction given, which could either be delete or done.
     *
     * @param input the input string from the Scanner.
     * @param action the TaskAction to be done.
     * @return true if the task is successfully altered, false otherwise.
     */
    private boolean alterTask(String input, Parser.TaskAction action) {
        try {
            Task taskToBeAltered;

            int index = Integer.parseInt(input.substring(action.substringIndex)) - 1;

            if (action == Parser.TaskAction.DELETE) {
                // If delete
                taskToBeAltered = taskList.removeTask(index);
            } else {
                // If done
                taskToBeAltered = taskList.markDoneInTaskList(index);
            }

            System.out.println("Output:\n\nThis task is successfully " + action.successMessage + ":\n\n"
                    + "    " + taskToBeAltered);
            return true;
        } catch (StringIndexOutOfBoundsException | NumberFormatException e1) {

            System.out.println("Output: Please specify which task you would like to have\n"
                    + action.successMessage + " by adding a single number after '" + action.name + "'!\n"
                    + "i.e. " + action.name + " 1");
            return false;

        } catch (IndexOutOfBoundsException e2) {

            System.out.println("Output: There is no task under that number!");
            return false;

        }
    }

    /**
     * Checks the task that is attempting to be added into the task list.
     * If the input contains the appropriate information, the task is added.
     *
     * @param input the input string from the Scanner.
     */
    private void vetoTask(String input) {
        Task newTask = null;

        try {
            if (!input.startsWith("todo") && !input.startsWith("deadline") && !input.startsWith("event")) {
                throw new duke.exception.InvalidInputException();
            } else {
                // If it passes the if block, means it is a task-setting command

                // Check if a task description is present
                checkDescription(input);

                if (input.startsWith("todo")) {

                    // Set the to-do
                    newTask = setTodo(input.substring(5));

                } else if (input.startsWith("deadline")) {

                    // Set the deadline
                    newTask = setDeadline(input.substring(9));

                } else if (input.startsWith("event")) {

                    // Set the event
                    newTask = setEvent(input.substring(6));

                }
            }
        } catch (DukeException e1) {
            System.out.println("Output: " + e1.getMessage());
        }

        // If there was no error, then add task. Else, skip this to get input again.
        if (newTask != null) {
            taskList.addTask(newTask);
            System.out.println("Output:\n\nYou have successfully added the following task:\n\n" +
                    "    " + newTask);
            taskList.printNumberOfTasks();
        }
    }

    /**
     * Checks if the input contains the description for the task to be added.
     * Throws a NoDescriptionException otherwise.
     *
     * @param input the input string from the Scanner.
     *
     * @throws NoDescriptionException if the description of the task is empty.
     */
    private void checkDescription(String input) throws NoDescriptionException {
        String[] inputArray = input.split(" ");
        if (inputArray.length == 1) throw new NoDescriptionException(inputArray[0]);
    }

    /**
     * Returns a to-do task based on the given description.
     *
     * @param input the string containing the to-do task description.
     * @return the to-do task constructed from the given description.
     */
    public Task setTodo(String input) {
        Task todo = new Todo(input);
        return todo;
    }

    /**
     * Returns a deadline task based on the given description.
     *
     * @param input the string containing the deadline task description.
     * @return the deadline task constructed from the given description.
     * @throws InvalidParamException if the description does not contain the appropriate information.
     */
    private Task setDeadline(String input) throws InvalidParamException {
        String[] deadlineParams = input.split(" /by ");
        if (deadlineParams.length != 2) {
            throw new InvalidParamException("Please include the deadline of the task after\n"
                    + "a task description using a '/by' (only once).\n"
                    + "i.e. deadline return book /by 2021-12-25");
        }
        Task deadline = new Deadline(deadlineParams[0], deadlineParams[1]);
        return deadline;
    }

    /**
     * Returns an event task based on the given description.
     *
     * @param input the string containing the event task description.
     * @return the event task constructed from the given description.
     * @throws InvalidParamException if the description does not contain the appropriate information.
     */
    private Task setEvent(String input) throws InvalidParamException {
        String[] eventParams = input.split(" /at ");
        if (eventParams.length != 2) {
            throw new InvalidParamException("Please include the time of the event after\n"
                    + "a task description using an '/at' (only once).\n"
                    + "i.e. event project meeting /at Aug 6th 2-4pm");
        }
        Task event = new Event(eventParams[0], eventParams[1]);
        return event;
    }

}
