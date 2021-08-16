public class TodoList {
    private final Task[] tasks = new Task[100];
    private int availableIndex = 0;
    public TodoList() { }

    public void addTodo(String taskName) {
        Todo todo = new Todo(taskName);
        tasks[availableIndex] = todo;
        availableIndex++;
        PrintResponse.print(String.format("Caan Do!\n\tadded: %s\n" +
                        "Look at me! " +
                        "%d tasks in the list now!",
                taskName,
                availableIndex));
    }

    public void list() {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < availableIndex; i++) {
            result.append(String.format("%d. %s\n", i + 1, tasks[i].toString()));
        }
        PrintResponse.print(
                String.format("Ooh yeah! Here are your %d tasks\n%s",
                        availableIndex,
                        result.toString()));
    }

    public void markAsDone(int taskNumber) throws DukeException{
        try {
            Task task = tasks[taskNumber - 1];
            task.markAsDone();
            String response = String.format("Ooh yeah! Task %d marked as done:\n  %s",
                    taskNumber,
                    task);
            PrintResponse.print(response);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new DukeException(String.format("Task number %d invalid", taskNumber));
        }
    }

    public void addDeadline(String name, String dateTime) {
        Deadline deadline = new Deadline(name, dateTime);
        tasks[availableIndex] = deadline;
        availableIndex++;
        PrintResponse.print(
                String.format("Caan Do!\n" +
                                "  added: %s\n" +
                                "Look at me! %d tasks in the list now!",
                        name,
                        availableIndex));
    }

    public void addEvent(String name, String dateTime) {
        Event event = new Event(name, dateTime);
        tasks[availableIndex] = event;
        availableIndex++;
        PrintResponse.print(
                String.format("Caan Do!\n" +
                        "  added: %s\n" +
                        "Look at me! %d in the list now!",
                        name,
                        availableIndex));
    }
}
