package duke;

public class Task {
    private String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public Task(String name, Boolean done) {
        this.name = name;
        this.done = done;
    }

    public String getName() {
        return this.name;
    }

    public void markAsDone() {
        this.done = true;
    }

    public static Task create(String[] input) {
        if (input[0].equals("T")) {
            return new Todo(input);
        } else if (input[0].equals("E")) {
            return new Event(input);
        } else if (input[0].equals("D")) {
            return new Deadline(input);
        }
        return new Task("ERROR");
    }

    @Override
    public String toString() {
        return "[" + (this.done ? "X" : " ") + "] " + this.name;
    }

    public String toDataString() {
        return (this.done ? "T|" : "F|") + this.name;
    }
}
