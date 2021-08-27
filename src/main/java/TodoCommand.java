package main.java;
import java.util.ArrayList;

public class TodoCommand extends Command {

    protected String input;

    protected ArrayList<Task> list;

    TodoCommand(String input, ArrayList<Task> list) {
        this.input = input;
        this.list = list;
    }

    public String reply() throws DukeException {

        String newTask = input.substring(5);
        if (newTask.length() == 0) {
            throw new DukeException("The description of a todo cannot be empty. Please try again!");
        } else {
            list.add(new TodoTask(newTask));
            return addTask(newTask, 1, list.size() - 1, "");
        }
    }
}
