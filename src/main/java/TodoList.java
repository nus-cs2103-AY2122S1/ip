import java.util.LinkedList;

public class TodoList {
    private final LinkedList<Task> tasks = new LinkedList<>();
    public TodoList() { }

    public void addTodo(String taskName) {
        Todo todo = new Todo(taskName);
        tasks.add(todo);
        PrintResponse.print(String.format("Caan Do!\n  added: %s\n" +
                        "Look at me! " +
                        "%d tasks in the list now!",
                taskName,
                tasks.size()));
    }

    public void list() {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < tasks.size(); i++) {
            result.append(String.format("%d. %s\n", i + 1, tasks.get(i).toString()));
        }
        PrintResponse.print(
                String.format("Ooh yeah! Here are your %d tasks\n%s",
                        tasks.size(),
                        result.toString()));
    }

    public void markAsDone(int taskNumber) throws DukeException{
        try {
            Task task = tasks.get(taskNumber - 1);
            task.markAsDone();
            String response = String.format("Ooh yeah! Task %d marked as done:\n  %s",
                    taskNumber,
                    task);
            PrintResponse.print(response);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new DukeException(String.format("Task number %d invalid.", taskNumber));
        }
    }

    public void addDeadline(String name, String dateTime) {
        Deadline deadline = new Deadline(name, dateTime);
        tasks.add(deadline);
        PrintResponse.print(
                String.format("Caan Do!\n" +
                                "  added: %s\n" +
                                "Look at me! %d tasks in the list now!",
                        deadline,
                        tasks.size()));
    }

    public void addEvent(String name, String dateTime) {
        Event event = new Event(name, dateTime);
        tasks.add(event);
        PrintResponse.print(
                String.format("Caan Do!\n" +
                        "  added: %s\n" +
                        "Look at me! %d in the list now!",
                        event,
                        tasks.size()));
    }

    public void deleteTask(int taskNumber) throws DukeException{
        try {
            Task task = tasks.remove(taskNumber - 1);
            String response = String.format("Ooh yeah! Task %d deleted:\n  %s" +
                            "\nNow you have %d tasks in the list.",
                    taskNumber,
                    task,
                    tasks.size());
            PrintResponse.print(response);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new DukeException(String.format("Task number %d invalid.", taskNumber));
        }
    }
}
