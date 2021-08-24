package duke.general;

import duke.error.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

public class Tasklist {
    private ArrayList<Task> list;

    public Tasklist(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds task based on the Type given and input to the tasklist, returns the task formed
     * @param t Type of task
     * @param inputSplit Additional info such as description, completion status etc.
     * @return duke.task.Task that was added to the duke.general.Tasklist
     */
    public Task addTask(TaskType t, String[] inputSplit) throws DukeException {
        Task temp;
        if (inputSplit.length < 2) {
            throw new DukeException("Description of task cannot be empty!");
        }
        String input = inputSplit[1];
        switch (t) {
        case TODO:
            temp = new ToDo(input);
            break;
        case DEADLINE:
            if (input.split(" /by ", 2).length < 2) {
                throw new DukeException("duke.task.Deadline not specified!");
            }
            String desc = input.split(" /by ", 2)[0];
            String dead = input.split(" /by ", 2)[1];
            temp = new Deadline(desc, dead);
            break;
        case EVENT:
            if (input.split(" /at ", 2).length < 2) {
                throw new DukeException("Date of event not specified!");
            }
            String name = input.split(" /at ", 2)[0];
            String at = input.split(" /at ", 2)[1];
            temp = new Event(name, at);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + t);
        }
        list.add(temp);
        return temp;
    }

    public Task doneTask(String[] inputSplit) throws DukeException {
        Task task = null;
        if (inputSplit.length < 2) {
            throw new DukeException("Please enter the number of task to mark as completed!");
        }
        try {
            int index = Integer.parseInt(inputSplit[1]);
            if (index > list.size() || index <= 0) {
                throw new DukeException("That number is not in the list!");
            }
            task = list.get(index - 1);
            task.toggleCompleted();
        } catch (NumberFormatException e) {
            System.out.println("Please input a proper number pls");
        }
        return task;
    }

    public Task deleteTask(String[] inputSplit) {
        Task t = null;
        try {
            int i = Integer.parseInt(inputSplit[1]);
            t = list.get(i - 1);
            list.remove(i - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That is an invalid index!!");
        }
        return t;
    }

    public int size() {
        return list.size();
    }

    public Task get(int i) {
        return list.get(i);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }
}
