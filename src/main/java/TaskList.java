import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private final List<Task> tasks;

    protected TaskList() {
        tasks = new ArrayList<>();
    }

    protected TaskList(List<Task> taskList) {
        tasks = taskList;
    }

    protected int size() {
        return tasks.size();
    }

    protected List<Task> getList() {
        return tasks;
    }

    protected void addTodoTask(String userInput, Ui ui) {
        ToDo todo = new ToDo(userInput.substring(5));
        tasks.add(todo);
        ui.showTodoMessage(todo, tasks);
    }

    protected void addDeadlineTask(String userInput, Ui ui) throws DateTimeParseException {
        int index = userInput.indexOf('/');
        String by = userInput.substring(index + 4);
        LocalDate localDate = LocalDate.parse(by);
        Deadline deadline = new Deadline(userInput.substring(9, index),
                localDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
        tasks.add(deadline);
        ui.showDeadlineMessage(deadline, tasks);
    }

    protected void addEventTask(String userInput, Ui ui) {
        int index = userInput.indexOf('/');
        String at = userInput.substring(index + 4);
        Event event = new Event(userInput.substring(6, index), at);
        tasks.add(event);
        ui.showEventMessage(event, tasks);
    }

    protected void deleteTask(String userInput, Ui ui) {
        int index = Integer.parseInt(userInput.split(" ")[1]);
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        ui.showDeleteMessage(task, tasks);
    }

    protected void printTaskList() {
        int counter = 1;
        for (Task t : tasks) {
            System.out.println(counter + "." + t.toString());
            counter++;
        }
    }

    protected void markTaskAsDone(String userInput, Ui ui) {
        int index = Integer.parseInt(userInput.split(" ")[1]);
        tasks.get(index - 1).markAsDone();
        Task task = tasks.get(index - 1);
        ui.showDoneMessage(task);
    }
}
