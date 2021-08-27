/**
 * Represents the UI part of the chat bot and contains all operations relating to the
 * interactions with the user
 */

public class UI {

    /**
     * intance of TaskList to manipulate the list
     */
    private TaskList list;
    /**
     * instance of storage to add data to the hardisk
     */
    private Storage storage;

    UI(TaskList list, Storage storage) {
        this.list = list;
        this.storage = storage;
    }

    /**
     * Method to mark the task as done
     *
     * @param n the task number entered by the user
     */
    void doneTask(int n) throws DukeException {
        list.doneTask(n);
        storage.writeToFile();
        System.out.println("Nice! I have marked this task as done:");
        System.out.println(list.get(n - 1).toString());
    }

    /**
     * Method to mark the task as done
     *
     * @param n the task number entered by the user
     */
    void deleteTask(int n) throws DukeException {
        String deletedTask = list.remove(n);
        storage.writeToFile();
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }


    /**
     * Method to add the input to the list
     *
     * @param input String input from the user
     */
    void addToList(Task input) {
        //Task task = new Task(input, false);
        list.add(input);
        storage.writeToFile();
        System.out.println("Got it. I've added this task:");
        System.out.println(input.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * method to create an Event as a Task and add to
     * the task list
     *
     * @param input String task name
     * @param time  String time of event
     */
    void createEvent(String input, String time) {
        Event event = new Event(input, false, time);
        addToList(event);
    }

    /**
     * method to create a Task with no time and
     * add it to the task list
     *
     * @param input String task name
     */
    void createTodo(String input) {
        Todo todo = new Todo(input, false);
        addToList(todo);
    }

    /**
     * method to create a task with a given deadline and
     * add to the task list
     *
     * @param input String task name
     * @param time  String time of deadline
     */
    void createDeadline(String input, String time) {
        Deadline deadline = new Deadline(input, false, time);
        addToList(deadline);
    }

    /**
     * method to greet the user in the beginning of the session and also show
     * the tasks in the list
     */
    void greet() {
        System.out.println("Hello, I'm Duke");
        printList();
        System.out.println("What can I do for you");
    }

    /**
     * method to invoke print method of the Tasklist class and show the tasks in the
     * list
     */
    void printList() {
        System.out.println("Here are the tasks in your list:");
        list.printList();
    }
}
