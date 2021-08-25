package duke.task;

import java.util.List;

public class TaskList {

    private List<Task> tasksList;

    public TaskList(List<Task> tasks) {
        this.tasksList = tasks;
    }

    /**
     * Display all items in the list.
     */
    public void displayAllItems() {
        System.out.println("    * * * * * * * * * * * * * * * * * * * *");
        System.out.println("    Here are the tasks in your list: ");
        for (int i = 0; i < this.tasksList.size(); i++) {
            String item = "    " + (i + 1) + "." +  this.tasksList.get(i);
            System.out.println(item);
        }
        System.out.println("    * * * * * * * * * * * * * * * * * * * *\n");
    }

    public int length() {
        return this.tasksList.size();
    }

    public Task getTask(int index) {
        return this.tasksList.get(index);
    }

    public void addTask(Task task) {
        this.tasksList.add(task);
    }

    public void deleteTask(int index) {
        this.tasksList.remove(index);
    }

    public Todo createTodo(String description) {
        Todo todo = new Todo(description);
        this.addTask(todo);
        return todo;
    }

    public Deadline createDeadline(String description, String date) {
        Deadline dl = new Deadline(description, date);
        this.addTask(dl);
        return dl;
    }

    public Event createEvent(String description, String date, String time) {
        Event event = new Event(description, date, time);
        this.tasksList.add(event);
        return event;
    }

    /**
     * Checks if description is given for Todo, Deadline and Event tasks.
     * @param userInput the user input provided by scanner.
     * @return true if description is provided; false otherwise.
     */
    public boolean isDescExists(String userInput) {
        String[] arr = userInput.split(" ");
        return arr.length >= 2;
    }

    public boolean isTaskExists(int taskNumber) {
        return taskNumber <= this.tasksList.size();
    }

    public List<Task> getAllTasks() {
        return this.tasksList;
    }

}
