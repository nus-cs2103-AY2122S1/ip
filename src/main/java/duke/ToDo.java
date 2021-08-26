package duke;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public String getTaskIcon() {
        return "T";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ToDo)) {
            return false;
        }
        ToDo e = (ToDo) o;
        return this.description.equals(e.description);
    }

    @Override
    public String toString() {
        return this.getTaskIcon() + "//" + this.getStatusIcon() + "//" + description;
    }
}
