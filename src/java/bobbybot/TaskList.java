package bobbybot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;
    private final DateTimeFormatter DT_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm");
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Getter for task
     * @param i task number to get, index starts from 0
     * @return task chosen
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Getter for List of tasks
     * @return List of task
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Mark a task as done
     * @param taskNo bobbybot Task Number (starting from index 1)
     */
    public void markAsDone(int taskNo) {
        Task taskCompleted = getTask(taskNo - 1);
        taskCompleted.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + taskCompleted);
    }

    /**
     * Delete a task
     * @param taskNo bobbybot.Task Number (starting from index 1)
     */
    public void deleteTask(int taskNo) {
        if (taskNo > tasks.size() || taskNo < 1) {
            System.out.println("Cannot find task! Use list command to see available tasks");
            return;
        }
        Task taskToDelete = tasks.get(taskNo - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + taskToDelete);
        tasks.remove(taskToDelete);
        System.out.println("Now you have " + tasks.size() + " tasks in the list." );
    }

    /**
     * Creates a todo task
     * @param description description of task
     */
    public void createToDo(String description) {
        Task newToDo = new ToDo(description);
        tasks.add(newToDo);
        System.out.println("Got it. I've added this task:\n  " + newToDo + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Creates an event task
     * @param description description of task
     * @param at time period of bobbybot.Event (start-end)
     */
    public void createEvent(String description, String at) {
        Task newEvent = new Event(description, at);
        tasks.add(newEvent);
        System.out.println("Got it. I've added this task:\n  " + newEvent + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Creates a deadline task
     * @param description description of task
     * @param by date and time that the task should be completed by
     */
    public void createDeadline(String description, String by) {
        // convert string by to LocalDate
        try {
            LocalDateTime dateBy = LocalDateTime.parse(by, DT_FORMATTER);
            Task newDeadline = new Deadline(description, dateBy);
            tasks.add(newDeadline);
            System.out.println("Got it. I've added this task:\n  " + newDeadline + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.");
        } catch (DateTimeParseException e) {
            System.out.println("Please input deadline date in the following format: [dd-mm-yyyy hh:mm]");
        }
    }

    /**
     * Prints all task in TaskList
     */
    public void printList() {
        int i = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task: tasks) {
            System.out.print(i + ". ");
            System.out.println(task);
            i++;
        }
    }
}
