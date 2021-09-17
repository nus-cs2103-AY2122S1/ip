package duke.components;
import duke.task.Task;

public class Ui {
    private final String lineBreak;

    public Ui() {
        this.lineBreak = "------------------------------";
    }

    public void displayWelcomeMessage() {
        System.out.println(lineBreak
                + "\n"
                + "Hello! I'm Duke.\n"
                + "How can I help you today?\n"
                + lineBreak + "\n"
                + "You can create Tasks here.\n"
                + "To add a Todo, \n simply key in `todo` followed by the name;\n"
                + "To add a Deadline, \n simply key in `deadline` followed by its name, `/by` and the deadline;\n"
                + "To add an Event, \n simply key in `event` followed by its name, `/at` and its time.\n"
                + "Please key in the date and time in the format of `26/08/2021 20:20`.\n"
                + lineBreak + "\n"
                + "To display the current list, \n simply enter 'list'.\n"
                + "To delete a task, \n simply enter 'delete' and space, followed by the task index.\n"
                + "To mark a task as done, \n simple enter 'done' and space, followed by the task index.\n"
                + "To exit, \n simply enter 'bye'.\n"
                + lineBreak + "\n"
        );
    }

    public void displayAddTaskMessage(Task newTask) {
        System.out.println("Roger! Added the task: ");
        System.out.println("    " + newTask.toString());
    }

    public void displayTaskNumber(TaskList taskList) {
        System.out.println("Now you have " + taskList.getSize() + " tasks in your list.");
    }

    public void displayLinebreak() {
        System.out.println(lineBreak + "\n");
    }

    public void displayTodoEmptyMessage() {
        System.out.println(" OOPS!!! The description of a todo cannot be empty.");
    }

    public void displayMarkDoneMessage(String taskMarkDone) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(taskMarkDone);
    }

    public void displayExitMessage() {
        System.out.println("     Bye! See you next time! :)" + "\n" + lineBreak + "\n");
    }

    public void displayOtherInputsMessage() {
        System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(lineBreak + "\n");
    }
}
