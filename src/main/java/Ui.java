public class Ui {

    // ASCII DIVIDER to clean up the output
    final String DIVIDER = "---------------------------";

    public Ui() {
        // Intro message
        System.out.println(
                "Hello! I'm Duke" + "\n" +
                        "What can I do for you?" + "\n" +
                        DIVIDER);
    }

    public void showLoadingError() {
        System.out.println("An error occurred.");
    }

    public String doneTaskMessage(String task) {
        return (DIVIDER + "\n" + "Nice! I've marked this task as done: " + "\n" +
                task + "\n" +
                DIVIDER);
    }

    public String deletedTaskMessage(String task, int taskListSize) {
        return (DIVIDER + "\n" + "Nice! Noted. I've removed this task: " + "\n" +
                task + "\n" +
                "You now have " + taskListSize + " tasks remaining!" + "\n" +
                DIVIDER);
    }

    public String listMessage(TaskList tasks) {
        String output = "";
        output += DIVIDER + "\n" + "Here are the items in your task list: " + "\n";
        for (int i = 0; i < tasks.getLength(); i++) {
            output += (i + 1 + ". " + tasks.getTask(i) + "\n");
        }
        output += (DIVIDER) + "\n";
        return output;
    }

    public String byeMessage() {
        return DIVIDER + "\n" + "Bye. Hope to see you again soon!" + "\n" + DIVIDER;
    }

    public String addTaskMessage(TaskList tasks) {
        return DIVIDER + "\n" + "added: " + tasks.getTask(tasks.getLength() - 1) + "\n" +
                "now you have: " + tasks.getLength() + " tasks! type 'list' to see them!" + "\n" + DIVIDER;
    }

    public String dukeExceptionMessage(DukeException e) {
        return DIVIDER + "\n" + e.getMessage() + "\n" + DIVIDER;
    }

    public String exceptionMessage(Exception e) {
        return DIVIDER + "\n" + e.getMessage() + "\n" + DIVIDER;
    }
}
