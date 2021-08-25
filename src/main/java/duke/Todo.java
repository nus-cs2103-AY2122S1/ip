package duke;

class Todo extends Task {
    Todo(String content) {
        super(content);
    }

    Todo(String content, boolean isDone) {
        super(content, isDone);
    }

    @Override
    String encoding() {
        return "T&&" + super.encoding();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}