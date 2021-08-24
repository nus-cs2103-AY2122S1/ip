package jarvis.task;

import java.util.ArrayList;

import jarvis.exception.InvalidDateTimeInputException;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public Todo addTodo(String todoDescription) {
        Todo todo = new Todo(todoDescription);
        taskList.add(todo);
        return todo;
    }

    public Deadline addTaskWithDeadline(String taskDescription, String deadline) throws InvalidDateTimeInputException {
        Deadline taskWithDeadline = new Deadline(taskDescription, deadline);
        taskList.add(taskWithDeadline);
        return taskWithDeadline;
    }

    public Event addEvent(String eventDescription, String eventTime) {
        Event event = new Event(eventDescription, eventTime);
        taskList.add(event);
        return event;
    }

    public Task markAsDone(int index) {
        Task task = taskList.get(index);
        task.markAsDone();
        return task;
    }

    public Task deleteTask(int index) {
        return taskList.remove(index);
    }

    public int getTaskListSize() {
        return taskList.size();
    }

    public String taskListSummary() {
        return String.format("Now you have %s task(s) in the list.", taskList.size());
    }

    @Override
    public String toString() {
        if (taskList.size() == 0) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            String task = String.format(
                    "%s. %s%s",
                    i + 1,
                    taskList.get(i).toString(),
                    i < taskList.size() - 1 ? "\n" : ""
            );
            stringBuilder.append(task);
        }

        return stringBuilder.toString();
    }

    public String toStorageFormatString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Task task : taskList) {
            stringBuilder.append(task.toStorageFormatString());
            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }
}
