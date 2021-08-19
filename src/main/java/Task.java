public class Task {
    private String desc;
    private boolean isComplete;

    public Task(String desc) throws Exception {
        this.desc = desc.trim();
        if (desc.trim().length() == 0) {
            throw new DukeException.EmptyDescriptionException();
        }
        this.isComplete = false;
    }

    public void markAsComplete() {
        this.isComplete = true;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", this.isComplete ? 'X' : ' ', this.desc);
    }
}