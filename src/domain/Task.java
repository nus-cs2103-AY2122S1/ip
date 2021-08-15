package domain;

public class Task {
    static enum TaskState {
        NEW(" "), DONE("X");

        private String representation;

        private TaskState(String representation) {
            this.representation = representation;
        }

        public String toString() {
            return String.format("[%s]", representation);
        }
    }

    private TaskState state;
    public String name;

    public Task(String name) {
        state = TaskState.NEW;
        this.name = name;
    }

    public void finish() {
        state = TaskState.DONE;
    }

    @Override
    public String toString() {
        return String.format("%s %s", state, name);
    }
}
