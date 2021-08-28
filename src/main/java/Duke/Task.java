package duke;

abstract public class Task {
    private final String name;
    private boolean isComplete;

    Task(String name) {
        this.name = name;
        this.isComplete = false;
    }

    Task(String name, boolean isComplete) {
        this.name = name;
        this.isComplete = isComplete;
    }

    public String getName() {
        return name;
    }

    public boolean isComplete() {
        return this.isComplete;
    }

    public void markComplete() {
        this.isComplete = true;
    }

    /**
     * Returns a Task that is represented by the input string s.
     *
     * @param s input string that represents task
     * @return new Task that corresponds to its string format
     * @throws IllegalArgumentException if string does not follow format
     */
    public static Task strToObj(String s) throws IllegalArgumentException {
        if (!s.matches("[T] [|] [10] [|] [\\w\\s]+$")
                && !s.matches("[DE] [|] [10] [|] [\\w\\s]+ [|] [0-9]{2}/[0-9]{2}/[0-9]{4} [0-9]{4}")) {
            throw new IllegalArgumentException();
        }
        String[] tmp =  s.split(" [|] ");
        boolean isComplete = tmp[1].equals("1");
        switch (tmp[0]) {
        case "T":
            return new Todo(tmp[2], isComplete);
        case "D":
            return new Deadline(tmp[2], tmp[3], isComplete);
        default:
            return new Event(tmp[2], tmp[3], isComplete);
        }
    }

    /**
     * Returns a string representation of Task conforming to file format.
     *
     * @return string representation of Task
     */
    public String toFile() {
        return String.format("%d | %s", isComplete ? 1 : 0, name);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isComplete ? "X" : " ", name);
    }
}
