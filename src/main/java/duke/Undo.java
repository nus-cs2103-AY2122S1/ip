package duke;

import java.io.IOException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class Undo {
    private TaskList items;

    public Undo(TaskList items) {
        this.items = items;
    }

    public String undoDelete(int index, String task) throws DukeException, IOException {
        String[] parseTask = task.split(" \\| ");
        String taskType = parseTask[0];
        String output = "";
        switch (taskType) {
        case "D":
            Deadline deadline = new Deadline(parseTask[2], parseTask[3]);
            output = items.addDeletedTask(index, deadline);
            break;
        case "E":
            Event event = new Event(parseTask[2], parseTask[3]);
            output = items.addDeletedTask(index, event);
            break;
        case "T":
            Todo todo = new Todo(parseTask[2]);
            output = items.addDeletedTask(index, todo);
            break;
        }
        if (parseTask[1].equals("1")) {
            items.markDone(index);
        }
        output += "\n" + task;
        return output;
    }

    public String undoDone(int index) throws DukeException {
        return items.markUndone(index);
    }

    public String deleteTask() throws DukeException {
        return items.deleteLatestTask();
    }
}
