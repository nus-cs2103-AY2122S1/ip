package duke;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) throws java.io.IOException {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();
    }

    public void run() {
        try {
            ArrayList<String> lines = this.storage.readFile();
            for (String line : lines) {
                this.tasks.addTaskFromRepr(line);
            }
        } catch (java.io.IOException | DukeException e) {
            this.ui.stderr(e.getMessage());
        }

        this.ui.init();
        Task t;
        int i;
        while (true) {
            try {
                Pair p = Parser.parseInput(this.ui.readInput());
                Command cmd = p.getCommand();
                List<String> varargs = p.getList();
                switch (cmd) {
                case EXIT:
                    this.ui.stdout("Bye. Hope to see you again soon!");
                    return;
                case READ:
                    this.ui.stdout(this.tasks.toString());
                    break;
                case UPDATE_MARKASDONE:
                    this.ui.stdout("Nice! I've marked this task as done:");
                    i = Integer.parseInt(varargs.get(0));
                    t = this.tasks.markAsComplete(i);
                    this.ui.stdout(t.toString());
                    this.storage.writeFile(this.tasks.toRepr());
                    break;
                case DELETE:
                    this.ui.stdout("Noted. I've removed this task:");
                    i = Integer.parseInt(varargs.get(0));
                    t = this.tasks.deleteTask(i);
                    this.ui.stdout(t.toString());
                    this.storage.writeFile(this.tasks.toRepr());
                    break;
                case CREATE_DEADLINE:
                    t = new Task.Deadline(false, varargs.get(0), varargs.get(1));
                    this.tasks.addTask(t);
                    this.storage.appendFile(t.getRepr());
                    this.ui.stdout(String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list\n",
                            t, tasks.numberOfTasks()));
                    break;
                case CREATE_EVENT:
                    t = new Task.Event(false, varargs.get(0), varargs.get(1));
                    this.tasks.addTask(t);
                    this.storage.appendFile(t.getRepr());
                    this.ui.stdout(String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list\n",
                            t, tasks.numberOfTasks()));
                    break;
                case CREATE_TODO:
                    t = new Task.Todo(false, varargs.get(0));
                    this.tasks.addTask(t);
                    this.storage.appendFile(t.getRepr());
                    this.ui.stdout(String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list\n",
                            t, tasks.numberOfTasks()));
                    break;
                }
            } catch (DukeException | java.io.IOException e) {
                this.ui.stderr(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws java.io.IOException {
        new Duke("data/tasks.txt").run();
    }
}


