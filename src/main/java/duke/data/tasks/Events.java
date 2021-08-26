package duke.data.tasks;

public class Events extends Task {
    private final String date;

    public Events(String name, String date) {
        super(name);
        this.date = date;
    }

    public Events(boolean completed, String name, String deadline) {
        super(completed, name);
        this.date = deadline;
    }

    public String getDate() {
        return this.date;
    }

    @Override
    public String getSaveData() {
        if (this.isCompleted()) {
            return String.format("E~1~%s~%s", this.getName(), this.date);
        } else {
            return String.format("E~0~%s~%s", this.getName(), this.date);
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Events)) return false;

        Events event = (Events) o;

        return this.getName().equals(event.getName()) &&
                this.isCompleted() == event.isCompleted() &&
                this.date.equals(event.getDate());
    }
}
