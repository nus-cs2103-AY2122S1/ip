public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void doneTask() {
        this.isDone = true;
    }

    public static void isFirstWordValid(String input, String expectedTaskName) throws DukeException {
        String firstWord = input.split(" ", 2)[0];
        if (!firstWord.equals(expectedTaskName)) {
            throw new DukeException(firstWord, DukeException.ErrorType.INVALID_INPUT);
        }
    }

    public static boolean isDescriptionEmpty(String input) {
        String removedSpace = input.replaceAll("\\s", "");
        return removedSpace.equals(input);
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}