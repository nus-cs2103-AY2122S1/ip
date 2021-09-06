package duke.util;

import java.util.ArrayList;

import duke.task.Task;

public class Ui {

    public String greetingMessage() {
        return "Hello! I'm Ashy! What can I do for you?";
    }

    public String exitMessaage() {
        return "ByeBye! Hope to see you again!";
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    public String listTasksOutput(ArrayList<Task> commands) {
        String output = "Here are the tasks in your list: \n";
        for (int i = 0; i < commands.size(); i++) {
            output += (i + 1) + ". " + commands.get(i) + "\n";
        }
        return output;
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    public String doneOutput(Task task, int tasksRemaining) {
        String output = "Good job! I've marked this task as completed: \n" + task
                + "\n You now have " + tasksRemaining + " tasks to complete";
        return output;
    }

    public void dottedLine() {
        System.out.println("............................................................");
    }

    public void line() {
        System.out.println("------------------------------------------------------------");
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    public String removeOutput(Task task, int tasksRemaining) {
        String output = "Okay! I have removed this task for you: \n" + task
        + "\n You now have " + tasksRemaining + " tasks in your list.";
        return output;
    }
}
