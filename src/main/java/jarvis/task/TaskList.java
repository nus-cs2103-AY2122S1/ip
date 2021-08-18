package jarvis.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public Todo addTodo(String todoDescription) {
        Todo todo = new Todo(todoDescription);
        taskList.add(todo);
        return todo;
    }

    public Deadline addTaskWithDeadline(String taskDescription, String deadline) {
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

    public int getTaskListSize() {
        return taskList.size();
    }

    public String taskListSummary() {
        return String.format("Now you have %s task(s) in the list.", taskList.size());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Here are the tasks in your list:\n\t");

        if (taskList.size() == 0) {
            return stringBuilder.toString();
        }

        for (int i = 0; i < taskList.size() - 1; i++) {
            stringBuilder.append(i + 1).append(".").append(taskList.get(i).toString()).append("\n\t");
        }

        stringBuilder.append(taskList.size()).append(".").append(taskList.get(taskList.size() - 1));

        return stringBuilder.toString();
    }
}
