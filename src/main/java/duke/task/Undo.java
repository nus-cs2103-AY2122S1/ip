package duke.task;

import duke.DukeException;
import duke.Items;

public class Undo {
    // event, deadline, todo: just delete the last task
    // done: mark as undone
    // delete: add task at same index.
    // find, list: no undo available.

    private Items items;

    public Undo(Items items) {
        this.items = items;
    }

    public String undoDelete(int index, String task) throws DukeException {
        String[] parseTask = task.split(" \\| ");
        String taskType = parseTask[0];
        String output = "";
        switch (taskType) {
        case "D":
            System.out.println("entered deadline");
            output = items.addDeletedTask(index, new Deadline(parseTask[2], parseTask[3]));
            break;
        case "E":
            System.out.println("entered event");
            output = items.addDeletedTask(index, new Event(parseTask[2], parseTask[3]));
            break;
        case "T":
            output = items.addDeletedTask(index, new Todo(parseTask[2]));
            break;
        }
        if (parseTask[1].equals("1")) {
            items.markDone(index);
        }
        output += "\n" + task;
        return output;
    }

    public String undoDone(int index) {
        return items.markUndone(index);
    }

    public String deleteTask() {
        return items.deleteLatestTask();
    }
}
