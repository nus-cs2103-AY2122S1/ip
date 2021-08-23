public abstract class Task {
    private String description;
    private boolean done = false;
    final public static String sep = "4%213";

    public static Task makeTask(String type, String description) throws DukeException {
        if (description.length() == 0) {
            throw new DukeException("The description cannot be empty");
        }
        switch (type) {
            case "todo": {
                return (ToDos.of(description));
            }
            case "deadline": {
                return (Deadline.of(description));
            }
            case "event": {
                return (Event.of(description));
            }
            default: {
                throw new DukeException("Sorry I don't understand");
            }
        }
    }

    protected Task(String description) {
        this.description = description;
    }

    public void markDone(Boolean toPrint) {
        this.done = true;
        if (toPrint) {
            System.out.println("Nice! I've marked this task as done:\n" + this.toString());
        }
    }

    @Override
    public String toString() {
        String doneIndicator = this.done
                ? "[X]"
                : "[ ]";
        return (
                doneIndicator + " " + this.description
                );
    }

    protected String toSaveInFile(String time) {
        return String.format("%s%s%s%s", this.description, time, Task.sep, this.done ? "1" : "0");
    }

    public abstract String typeString();
}
