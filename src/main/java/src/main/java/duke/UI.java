package src.main.java.duke;

import java.util.List;

/**
 * Represents the src.main.java.duke.UI part of the chat bot and contains all operations relating to the
 * interactions with the user
 */

public class UI {

    /**
     * intance of src.main.java.duke.TaskList to manipulate the list
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
    String markAsDone(int n) throws DukeException {
        list.markAsDone(n);
        storage.writeToFile();
        String output = "";
        System.out.println("Nice! I have marked this task as done:");
        output += "Nice! I have marked this task as done:\n";
        System.out.println(list.get(n - 1).toString());
        output += list.get(n - 1).toString();
        return output;
    }

    /**
     * Method to mark the task as done
     *
     * @param n the task number entered by the user
     */
    String deleteTask(int n) throws DukeException {
        String deletedTask = list.remove(n);
        storage.writeToFile();
        String output = "";
        System.out.println("Noted. I've removed this task:");
        output += "Noted. I've removed this task:";
        System.out.println(deletedTask);
        output += deletedTask + "\n";
        System.out.println("Now you have " + list.getSize() + " tasks in the list.");
        output += "Now you have " + list.getSize() + " tasks in the list.";
        return output;
    }


    /**
     * Method to add the input to the list
     *
     * @param input String input from the user
     */
    String addToList(Task input) {
        //Task task = new Task(input, false);
        list.add(input);
        storage.writeToFile();
        String output = "";
        System.out.println("Got it. I've added this task:");
        output += "Got it. I've added this task:\n";
        System.out.println(input.toString());
        output += input.toString() + '\n';
        System.out.println("Now you have " + list.getSize() + " tasks in the list.");
        output += "Now you have " + list.getSize() + " tasks in the list.\n";
        return output;
    }

    /**
     * method to create an Event as a Task and add to
     * the task list
     *
     * @param input String task name
     * @param time  String time of event
     */
    String createEvent(String input, String time) {
        Event event = new Event(input, false, time);
        return addToList(event);
    }

    /**
     * method to create a Task with no time and
     * add it to the task list
     *
     * @param input String task name
     */
    String createTodo(String input) {
        Todo todo = new Todo(input, false);
        return addToList(todo);
    }

    /**
     * method to create a task with a given deadline and
     * add to the task list
     *
     * @param input String task name
     * @param time  String time of deadline
     */
    String createDeadline(String input, String time) {
        Deadline deadline = new Deadline(input, false, time);
        return addToList(deadline);
    }

    /**
     * method to greet the user in the beginning of the session and also show
     * the tasks in the list
     */
    String greet() {
        System.out.println("Hello, I'm Duke");
        String text = "Hello, I'm Duke\n";
        text = text + "Here are the tasks in your list:\n";
        text = text + printList();
        text = text + "What can I do for you";
        System.out.println("What can I do for you");
        return text;
    }

    /**
     * method to find the specified and print the result to the user screen
     *
     * @param text the keyword to be searched
     */
    String find(String text) {
        StringBuilder output = new StringBuilder();
        List<Task> content = list.find(text);
        System.out.println("Here are the matching tasks in your list:");
        output.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < content.size(); i++) {
            System.out.println((i + 1) + ". " + content.get(i).toString());
            output.append(i + 1).append(". ").append(content.get(i).toString()).append("\n");
        }
        return output.toString();
    }

    String update(String task, String time) throws DukeException {
        String[] split = task.split(" ", 2);
        if (!(split[0].equals("event") || split[0].equals("deadline"))) {
            throw new TimeNotFoundException("Given task doesn't have time");
        }
        int index = Integer.parseInt(split[1]);
        String output = "The task has been updated:\n";
        output += list.update(index, time, split[0]);
        return output;
    }

    /**
     * method to invoke print method of the Tasklist class and show the tasks in the
     * list
     */
    String printList() {
        System.out.println("Here are the tasks in your list:");
        return list.printList();
    }
}
