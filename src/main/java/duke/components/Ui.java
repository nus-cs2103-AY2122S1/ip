package duke.components;

import duke.task.Task;

/**
 * Represents the UI that the user will be interacting with.
 * It displays messages to users after their actions.
 */
public class Ui {
    private final String lineBreak;

    public Ui() {
        this.lineBreak = "------------------------------";
    }

    /**
     * Display the welcome message when Duke starts up.
     */
    public void displayWelcomeMessage() {
        System.out.println(lineBreak
                + "\n"
                + "Hello! I'm Duke.\n"
                + "How can I help you today?\n"
                + lineBreak + "\n"
                + "You can create Tasks here.\n"
                + "To add a Todo, \n"
                + " simply key in `todo` followed by the name;\n"
                + "To add a Deadline, \n"
                + " simply key in `deadline` followed by its name, `/by` and the deadline;\n"
                + "To add an Event, \n"
                + " simply key in `event` followed by its name, `/at` and its time.\n"
                + "Please key in the date and time in the format of `26/08/2021 20:20`.\n"
                + lineBreak + "\n"
                + "To display the current list, \n"
                + " simply enter 'list'.\n"
                + "To delete a task, \n"
                + " simply enter 'delete' and space, followed by the task index.\n"
                + "To mark a task as done, \n"
                + " simple enter 'done' and space, followed by the task index.\n"
                + "To exit, \n"
                + " simply enter 'bye'.\n"
                + lineBreak + "\n"
        );
    }

    /**
     * Display a message when user successfully adds a task.
     *
     * @param newTask The new task user added.
     */
    public String displayAddTaskMessage(Task newTask) {
        return "Roger! Added the task: \n" + "    " + newTask.toString();
    }

    /**
     * Display the number of tasks in task list.
     *
     * @param taskList The task list that user is enquiring.
     */
    public String displayTaskNumber(TaskList taskList) {
        return "Now you have " + taskList.getSize() + " tasks in your list.";
    }

    /**
     * Display a linebreak.
     */
    public String displayLinebreak() {
        return lineBreak + "\n";
    }

    /**
     * Display a message when description of a todo is empty.
     */
    public String displayTodoEmptyMessage() {
        return " OOPS!!! The description of a todo cannot be empty.";
    }

    /**
     * Display a message when user marks a task as done.
     *
     * @param taskMarkDone The task that is done.
     */
    public String displayMarkDoneMessage(String taskMarkDone) {
        return "Nice! I've marked this task as done: \n" + taskMarkDone;
    }

    /**
     * Display a message when user is exiting Duke.
     */
    public String displayExitMessage() {
        return "     Bye! See you next time! :)" + "\n" + lineBreak + "\n";
    }

    /**
     * Display a message when user input is not standard.
     */
    public String displayOtherInputsMessage() {
        return " OOPS!!! I'm sorry, but I don't know what that means :-( \n" + lineBreak + "\n";
    }
}
