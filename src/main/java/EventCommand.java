package main.java;
import java.util.ArrayList;

public class EventCommand extends Command {

    protected String input;

    protected ArrayList<Task> list;

    EventCommand(String input, ArrayList<Task> list) {
        this.input = input;
        this.list = list;
    }

    public String reply() throws DukeException {

        int position = input.indexOf("/at");
        String newTask = input.substring(6, position);
        String newInfo = input.substring(position + 4);
        if (newTask.length() == 0) {
            throw new DukeException("The description of an event cannot be empty. Please try again!");
        } else if (newInfo.length() == 0) {
            throw new DukeException("The date of an event cannot be empty. Please try again!");
        } else {
            list.add(new EventTask(newTask, newInfo));
            return addTask(newTask, 3, list.size() - 1, newInfo);
        }
    }
}