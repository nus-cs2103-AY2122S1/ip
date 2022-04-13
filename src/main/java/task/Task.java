package task;

import java.util.ArrayList;

import duke.DukeException;
import duke.Parser;

public abstract class Task {

    private String name;
    private boolean done;
    private ArrayList<String> tags;

    /**
     * Constructs a Todo Task instance.
     *
     * @param name Name of task.
     * @throws DukeException When name is empty.
     */
    public Task(String name) throws DukeException {
        if (name.isBlank()) {
            throw new DukeException("Please specify name");
        }
        this.name = name;
        this.done = false;
        this.tags = new ArrayList<>();
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
        String[] nameDelimit;
        switch (type) {
        case "todo":
            newTask = new ToDo(rest);
            break;

        case "deadline":
            nameDelimit = Parser.parseArgs(rest, "/by");
            newTask = new Deadline(nameDelimit[0], nameDelimit[1]);
            break;

        case "event":
            nameDelimit = Parser.parseArgs(rest, "/at");
            newTask = new Event(nameDelimit[0], nameDelimit[1]);
            break;

        default:
            throw new DukeException("command not found");
        }
        assert newTask != null;
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
        assert parts.length == 4;
        Task t = createTask(parts[0], parts[1]);
        if (parts[2].equals("1")) {
            t.markDone();
        }
        String[] tags = Parser.parseTags(parts[3]);
        for (String tag: tags) {
            if (!tag.equals("")) {
                t.addTag(tag);
            }
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

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public String getTags() {
        if (this.tags.size() == 0) {
            return "";
        }
        StringBuilder display = new StringBuilder("\n    Tags:");
        for (int i = 0; i < this.tags.size(); i++) {
            display.append(" #").append(this.tags.get(i));
        }
        return display.toString();
    }

    protected String saveTags() {
        if (this.tags.size() == 0) {
            return "#";
        }
        StringBuilder display = new StringBuilder("");
        for (int i = 0; i < this.tags.size(); i++) {
            display.append("#" + this.tags.get(i));
        }
        return display.toString();
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
