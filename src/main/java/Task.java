public abstract class Task {
    private String description;
    private char symbol;
    private boolean isDone;

    public Task(String description, char symbol) {
        this.description = description;
        this.symbol = symbol;
        this.isDone = false;
    }

    public static Task createTask(String[] inputs) throws DukeException {
        String keyword = inputs[0];

        switch (keyword) {
        case "todo":
            return new Todo(inputs[1]);
        case "deadline":
            inputs = inputs[1].split(" /by ", 2);
            TaskCenter.checkMissingArguments(inputs,
                    String.format("Please specify the date/time of your deadline.\n", keyword));
            return new Deadline(inputs[0], inputs[1]);
        case "event":
            inputs = inputs[1].split(" /at ", 2);
            TaskCenter.checkMissingArguments(inputs, String.format("Please specify a time for your event.\n", keyword));
            return new Event(inputs[0], inputs[1]);
        default:
            throw new InvalidArgumentException();
        }
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", symbol, getStatusIcon(), description);
    }
}
