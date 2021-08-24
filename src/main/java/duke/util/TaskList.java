package duke.util;

import duke.exception.DukeException;
import duke.task.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this(new ArrayList<>());
    }

    public String addTask(Task task) {
        StringBuilder msg = new StringBuilder();
        this.tasks.add(task);
        msg.append("Got it. I've added this task:\n");
        msg.append("\t" + task + "\n");
        msg.append("Now you have " + tasks.size() + " tasks in the list.\n");

        return msg.toString();
    }

    public String deleteTask(int idx) throws DukeException {
        try {
            StringBuilder msg = new StringBuilder();
            Task curr = this.tasks.remove(idx-1);
            msg.append("Noted. I've removed this task:\n");
            msg.append("\t" + curr + "\n");
            msg.append("Now you have " + tasks.size() + " tasks in the list.\n");

            return msg.toString();
        } catch(IndexOutOfBoundsException e) {
            throw new DukeException("The task index is invalid!");
        }
    }

    public String markTaskDone(int idx) throws DukeException {
        try {
            StringBuilder msg = new StringBuilder();
            Task curr = this.tasks.get(idx - 1);
            curr.markDone();
            msg.append("Nice! I've marked this task as done:\n");
            msg.append("\t" + curr + "\n");

            return msg.toString();
        } catch(IndexOutOfBoundsException e) {
           throw new DukeException("The task index is invalid!");
        }
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Here are the tasks in your list:\n");
        for (int i = 1; i <= this.tasks.size(); i++) {
            str.append(String.format("%d.%s\n", i, tasks.get(i-1)));
        }
        return str.toString();
    }

    public void saveToFile(Storage storage) throws IOException {
        String encoded = Parser.encode(this.tasks);
        storage.save(encoded);
    }

    public void loadFromFile(Storage storage) throws FileNotFoundException {
        List<Task> decoded = Parser.decode(storage.load());
        this.tasks = decoded;
    }
}
