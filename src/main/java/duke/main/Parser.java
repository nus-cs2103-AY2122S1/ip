package duke.main;

import duke.exception.DukeException;
import duke.exception.NoDescriptionException;
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
     * Enum that holds information about acting on a task, not including actions that changes the size of the TaskList.
     * Task actions include deleting a task (DELETE) and marking a Task as done (DONE).
     */
    private enum TaskAction {
        DELETE("delete", 7, "deleted"),
        DONE("done", 5, "marked as done");

        /** Name of the task action */
        private String name;

        /** Index to substring the input so as to remove the first word (command word) */
        private int substringIndex;

        /** Message fragment when the action is successfully completed */
        private String successMessage;

        /**
         * Constructs a TaskAction.
         *
         * @param name Name of the TaskAction.
         * @param substringIndex Index to substring the input so as to remove the first word (command word).
         * @param successMessage Message fragment when the action is successfully completed.
         */
        TaskAction(String name, int substringIndex, String successMessage) {
            this.name = name;
            this.substringIndex = substringIndex;
            this.successMessage = successMessage;
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
    public boolean handleInput(String input) {
        Ui.printSingleDivider();
        if (input.equals("bye")) {

            return false;

        } else if (input.equals("list")) {

            System.out.println("Output: This is your current list!\n");
            taskList.printList();

        } else if (input.startsWith("find")) {

            findTask(input);

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

        Ui.printDoubleDivider();
        return true;
    }

    private void findTask(String input) {
        try {
            System.out.println("Output: These tasks have descriptions that contain the phrase '"
                    + input.substring(5) + "'!\n");
            taskList.printAllContains(input.substring(5));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Output: Please include a search term after the word 'find'.\n"
                    + "i.e. Find meeting");
        }
    }

    /**
     * Alters the TaskList depending on the TaskAction given, which could either be DELETE or DONE.
     *
     * @param input Input string from the user.
     * @param action TaskAction to be done.
     * @return True if the TaskList is successfully altered, false otherwise.
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
     * Checks the task that is being attempted to be added into the task list.
     * If the input contains the appropriate information, the task is added.
     *
     * @param input Input string from the user.
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
                    newTask = Todo.setTodo(input.substring(5));

                } else if (input.startsWith("deadline")) {

                    // Set the deadline
                    newTask = Deadline.setDeadline(input.substring(9));

                } else if (input.startsWith("event")) {

                    // Set the event
                    newTask = Event.setEvent(input.substring(6));

                }
            }
        } catch (DukeException e1) {
            System.out.println("Output: " + e1.getMessage());
        }

        // If there was no error, then add task.
        if (newTask != null) {
            taskList.addTask(newTask);
            System.out.println("Output:\n\nYou have successfully added the following task:\n\n"
                    + "    " + newTask);
            taskList.printNumberOfTasks();
        }
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
        if (inputArray.length == 1) throw new NoDescriptionException(inputArray[0]);
    }
}
