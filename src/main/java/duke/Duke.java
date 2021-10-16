package duke;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A human wannabe frog chat bot who can help keep track of tasks.
 */
public class Duke {

    private TaskList tasks;

    public Duke() {
        Ui.greeting();
        try {
            tasks = Storage.load();
        } catch (DukeException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String getResponse(String[] args) {
        switch (args[0]) {
        case "list":
            return Ui.showListMessage(tasks);
        case "done":
            int index = Integer.parseInt(args[1]) - 1;
            this.tasks.markDone(index);
            return Ui.showDoneMessage(tasks, index);
        case "delete":
            int idx = Integer.parseInt(args[1]) - 1;
            String description = tasks.get(idx).toString();
            this.tasks.delete(idx);
            return Ui.showDeleteMessage(tasks, description);
        case "find":
            ArrayList<Integer> matches = this.tasks.findTask(args[1]);
            return Ui.showFindMessage(tasks, matches);
        case "todo":
            tasks.add(new ToDo(args[1]));
            return Ui.addTaskMessage(tasks);
        case "event":
            tasks.add(new Event(args[1], args[2]));
            return Ui.addTaskMessage(tasks);
        case "deadline":
            LocalDate date = LocalDate.parse(args[2]);
            tasks.add(new Deadline(args[1], date, args[3]));
            return Ui.addTaskMessage(tasks);
        default:
            return "Jo does not recognise non-frog speak!";
        }
    }
}
