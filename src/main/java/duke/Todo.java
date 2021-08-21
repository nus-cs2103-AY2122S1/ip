package duke;

class Todo extends Task {
    Todo(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("[T][%c] %s", isDone ? 'X' : ' ', getDescription());
    }
}
