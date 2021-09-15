package src.main.java.duke;

import java.util.List;

/**
 * Represents the src.main.java.duke.UI part of the chat bot and contains all operations relating to the
 * interactions with the user.
 */

public class UI {

    /**
     * instance of src.main.java.duke.TaskList to manipulate the list.
     */
    private TaskList list;

    /**
     * instance of storage to add data to the hard disk.
     */
    private Storage storage;

    UI(TaskList list, Storage storage) {
        this.list = list;
        this.storage = storage;
    }

    /**
     * Method to mark the task as done.
     *
     * @param n the task number entered by the user
     * @return
     * @throws DukeException
     */
    String markAsDone(int n) throws DukeException {
        list.markAsDone(n);
        storage.writeToFile();
        String output = "";
        output += "Nice! I have marked this task as done:\n";
        output += list.get(n - 1).toString();
        return output;
    }

    /**
     * Method to delete a task from the list.
     *
     * @param n the task number entered by the user.
     * @return
     * @throws DukeException
     */
    String deleteTask(int n) throws DukeException {
        String deletedTask = list.remove(n);
        storage.writeToFile();
        String output = "";
        output += "Noted. I've removed this task:\n";
        output += deletedTask + "\n";
        output += "Now you have " + list.getSize() + " tasks in the list.";
        return output;
    }


    /**
     * Method to add the input to the list
     *
     * @param input String input from the user
     * @return
     */
    String addToList(Task input) {
        list.add(input);
        storage.writeToFile();
        String output = "";
        output += "Got it. I've added this task:\n";
        output += input.toString() + '\n';
        output += "Now you have " + list.getSize() + " tasks in the list.\n";
        return output;
    }

    /**
     * method to create an Event as a Task and add to
     * the task list.
     *
     * @param input String task name.
     * @param time  String time of event.
     * @return
     */
    String createEvent(String input, String time) {
        Event event = new Event(input, false, time);
        return addToList(event);
    }

    /**
     * method to create a Task with no time and
     * add it to the task list.
     *
     * @param input String task name.
     * @return
     */
    String createTodo(String input) {
        Todo todo = new Todo(input, false);
        return addToList(todo);
    }

    /**
     * method to create a task with a given deadline and
     * add to the task list.
     *
     * @param input String task name.
     * @param time  String time of deadline.
     * @return
     */
    String createDeadline(String input, String time) throws DukeException {
        Deadline deadline = new Deadline(input, false, time);
        return addToList(deadline);
    }

    /**
     * method to greet the user in the beginning of the session and also show
     * the tasks in the list.
     *
     * @return
     */
    String greet() {
        String text = "Hello, I'm Duke\n";
        text = text + "Here are the tasks in your list:\n";
        text = text + printList();
        text = text + "What can I do for you";
        return text;
    }

    /**
     * method to find the specified and print the result to the user screen.
     *
     * @param text the keyword to be searched.
     * @return
     */
    String find(String text) {
        StringBuilder output = new StringBuilder();
        List<Task> content = list.find(text);
        output.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < content.size(); i++) {
            output.append(i + 1).append(". ").append(content.get(i).toString()).append("\n");
        }
        return output.toString();
    }

    /**
     * method to update the time of a task of type "event" or "deadline".
     *
     * @param task type of task.
     * @param time new time to be set.
     * @return
     * @throws DukeException
     */
    String update(String task, String time) throws DukeException {
        int index = Integer.parseInt(task);
        String output = "The task has been updated:\n";
        output += list.update(index, time);
        storage.writeToFile();
        return output;
    }

    /**
     * method to invoke print method of the Tasklist class and show the tasks in the
     * list.
     *
     * @return
     */
    String printList() {
        return list.printList();
    }
}
