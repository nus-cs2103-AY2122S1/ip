package duke;
import duke.Ui.Ui;

import java.util.ArrayList;
import java.util.List;

import static javafx.application.Application.launch;

/**
 * Duke is the main entity coordinating the user interface, program state, persistent storage and list of tasks.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke() throws java.io.IOException {
        this.ui = new Ui();
        this.storage = new Storage( "data/tasks.txt");
        this.tasks = new TaskList();
    }

    public void init() throws DukeException, java.io.IOException {
        ArrayList<String> lines = this.storage.readFile();
        for (String line : lines) {
            this.tasks.addTaskFromRepr(line);
        }
    }

    public String getResponse(String userInput) {
        Task t;
        int i;
        try {
            Pair p = Parser.parseInput(userInput);
            Command cmd = p.getCommand();
            List<String> varargs = p.getList();
            switch (cmd) {
            case EXIT:
                return "Bye. Hope to see you again soon!";
            case READ:
                return this.tasks.toString();
            case FIND:
                TaskList filteredTasks = this.tasks.filter(varargs.get(0));
                return String.format("Here are the matching tasks in your list:\n%s",
                    filteredTasks.toString());
            case UPDATE_MARKASDONE:
                i = Integer.parseInt(varargs.get(0));
                t = this.tasks.markAsComplete(i);
                this.storage.writeFile(this.tasks.toRepr());
                return String.format("Nice! I've marked this task as done:\n%s", t);
            case DELETE:
                i = Integer.parseInt(varargs.get(0));
                t = this.tasks.deleteTask(i);
                this.storage.writeFile(this.tasks.toRepr());
                return String.format("Noted. I've removed this task:\n%s", t);
            case SNOOZE:
                i = Integer.parseInt(varargs.get(0));
                int numberOfDays = Integer.parseInt(varargs.get(1));
                t = this.tasks.snoozeTask(i, numberOfDays);
                this.storage.writeFile(this.tasks.toRepr());
                return String.format("Noted. I've snoozed this task:\n%s\nfor %d days", t, numberOfDays);
            case CREATE_DEADLINE:
                t = new Task.Deadline(false, varargs.get(0), varargs.get(1));
                this.tasks.addTask(t);
                this.storage.appendFile(t.getRepr());
                return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list\n",
                        t, tasks.numberOfTasks());
            case CREATE_EVENT:
                t = new Task.Event(false, varargs.get(0), varargs.get(1));
                this.tasks.addTask(t);
                this.storage.appendFile(t.getRepr());
                return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list\n",
                        t, tasks.numberOfTasks());
            case CREATE_TODO:
                t = new Task.Todo(false, varargs.get(0));
                this.tasks.addTask(t);
                this.storage.appendFile(t.getRepr());
                return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list\n",
                        t, tasks.numberOfTasks());
            default:
                throw new DukeException.UnknownInputException();
            }
        } catch (DukeException | java.io.IOException | IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }
}


