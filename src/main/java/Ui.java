import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Deals with interations with user
 */
public class Ui {

    public Ui() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void list(ArrayList<Task> savedInputs) {
        StringBuilder outputList = new StringBuilder();
        outputList.append("Here are the tasks in your list:\n");
        for (int i = 1; i <= savedInputs.size(); i++) {
            outputList.append(i + "." + savedInputs.get(i - 1).toString() + "\n");
        }
        System.out.println(outputList);
    }

    public void done(TaskList taskList, int donePos) {
        System.out.println("Nice! I've marked this task as done:\n  [X] " +
                taskList.getTasks().get(donePos - 1).description);
    }

    public void delete(TaskList taskList, int deletePos) {
        System.out.println("Noted. I've removed this task:\n  " + taskList.getTasks().get(deletePos - 1).toString());
        System.out.println("Now you have " + (taskList.getTasks().size() - 1) + " tasks in the list.");
    }

    public void checkDate(TaskList taskList, LocalDate localDate) {
        ArrayList<Task> matchingDates = taskList.checkDate(localDate);

        String output = "";
        for (int i = 1; i <= matchingDates.size(); i++) {
            output = output + i + ". " + matchingDates.get(i - 1).toString() + "\n";
        }
        System.out.println(output);
    }

    public void addEvent(TaskList taskList, Event event) {
        System.out.println("Got it. I've added this task:\n  " + event);
        System.out.println("Now you have " + taskList.getTasks().size() + " tasks in the list.");
    }

    public void addDeadline(TaskList taskList, Deadline deadline) {
        System.out.println("Got it. I've added this task:\n  " + deadline);
        System.out.println("Now you have " + taskList.getTasks().size() + " tasks in the list.");
    }

    public void addTodo(TaskList taskList, Todo todo) {
        System.out.println("Got it. I've added this task:\n  " + todo);
        System.out.println("Now you have " + taskList.getTasks().size() + " tasks in the list.");
    }

    public void invalidUserInput() throws DukeException {
        throw new DukeException("Oops! I'm sorry, but I don't know what that means :-(");
    }
}
