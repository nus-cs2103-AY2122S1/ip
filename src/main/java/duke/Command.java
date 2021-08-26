package duke;

public class Command {
    private String operation;
    private int index;
    private String description;
    private String time;

    public Command(String operation) {
        this.operation = operation;
    }

    public Command(String operation, int index) {
        this.operation = operation;
        this.index = index;
    }

    public Command(String operation, String description) {
        this.operation = operation;
        this.description = description;
    }

    public Command(String operation, String description, String time) {
        this.operation = operation;
        this.description = description;
        this.time = time;
    }

    public String getOperation() {
        return operation;
    }

    public int getIndex() {
        return index;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }
}


