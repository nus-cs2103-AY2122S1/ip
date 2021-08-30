package task;

import duke.DukeException;
import duke.Parser;

public abstract class Task {

    private String name;
    private boolean done;

    public Task(String name) throws DukeException {
        if (name.isBlank()) {
            throw new DukeException("Please specify name");
        }
        this.name = name;
        this.done = false;
    }

    /**
     * Returns Task created by user input.
     *
     * @param type First word of user input.
     * @param rest Rest of user input.
     * @return Task created.
     * @throws DukeException If type of task is invalid.
     */
    public static Task createTask(String type, String rest) throws DukeException {
        Task newTask;
        String[] name_delimit;
        switch (type) {
        case "todo":
            newTask = new ToDo(rest);
            break;

        case "deadline":
            name_delimit = Parser.parseArgs(rest, "/by");
            newTask = new Deadline(name_delimit[0], name_delimit[1]);
            break;

        case "event":
            name_delimit = Parser.parseArgs(rest, "/at");
            newTask = new Event(name_delimit[0], name_delimit[1]);
            break;

        default:
            throw new DukeException("command not found");
        }
        return newTask;
    }

    /**
     * Returns Task stored in storage.
     *
     * @param s Line in storage.
     * @return Task stored in storage.
     */
    public static Task getTask(String s) {
        String[] parts = Parser.parseStorage(s);
        Task t = createTask(parts[0], parts[1]);
        if (parts[2].equals("1")) {
            t.markDone();
        }
        return t;
    }

    /**
     * Marks this task as done
     */
    public void markDone() {
        this.done = true;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return done;
    }

    /**
     * Returns string representing task to be saved to text file.
     *
     * @return string representing task.
     */
    public abstract String saveTask();

    /**
     * Overrides toString method to add a check box beside task name.
     *
     * @return Done status and task name.
     */
    @Override
    public String toString() {
        String check = done ? "X" : " ";
        return "[" + check + "] " + name;
    }
}
