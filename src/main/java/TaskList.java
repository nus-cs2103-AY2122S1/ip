package duke;

import duke.Deadline;
import duke.Event;
import duke.Storage;
import duke.Task;
import duke.ToDo;

import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> tasks;
    public int counter = 0;
    static String dash = "__________________________________";

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void list() {
        System.out.println(dash);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < counter; i++) {
            Task currTask = tasks.get(i);
            System.out.println(i+1 + "." + currTask.toString());
        }
        System.out.println(dash);
    }

    public void markDone(String userInput) {
        if (userInput.substring(4).length() == 0) {
            System.out.println(dash + '\n' + "Sorry, which task do you wish to mark as completed?" + '\n'+ dash);
            // throw new DukeException("User has not indicated task to mark as complete.");

        } else {
            Integer index = Integer.parseInt(userInput.substring(5));
            Task currTask = tasks.get(index - 1);
            currTask.completeTask();

            System.out.println(dash);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(currTask.toString());
            System.out.println(dash);
        }
    }

    public void addDone(int num) {
        Task currTask = tasks.get(num - 1);
        currTask.completeTask();
    }

    public void delete(String userInput) {
        if (userInput.substring(6).length() == 0) {
            System.out.println(dash + '\n' + "Sorry, which task do you wish to delete?" + '\n'+ dash);
            // throw new DukeException("User has not indicated task to delete.");

        } else {
            Integer index = Integer.parseInt(userInput.substring(7));
            Task currTask = tasks.get(index - 1);
            tasks.remove(currTask);

            System.out.println(dash);
            System.out.println("Noted. I've removed this task:");
            System.out.println(currTask.toString());

            counter -= 1;
            System.out.println("You now have " + counter + " task(s) in your list!");
            System.out.println(dash);
        }
    }

    public void makeTodo(String userInput) {
        if (userInput.substring(4).length() == 0) {
            System.out.println(dash + '\n' + "YIKES!! The description of a todo cannot be empty!" + '\n'+ dash);
            // throw new DukeException("YIKES!! The description of a todo cannot be empty!");
        } else {
            tasks.add(new ToDo(userInput.substring(5)));

            System.out.println(dash);
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks.get(counter).toString());
            counter += 1;

            System.out.println("You now have " + counter + " task(s) in the list.");
            System.out.println(dash);
        }
    }

    public void addTodo(String userInput) {
        tasks.add(new ToDo(userInput));
        counter += 1;
    }


    public void makeEvent(String userInput) {
        if (userInput.substring(5).length() == 0) {
            System.out.println(dash + '\n' + "YIKES!! The description of an Event cannot be empty!" + '\n'+ dash);
            // throw new DukeException("YIKES!! The description of an Event cannot be empty!");
        } else {
            String output = userInput.substring(6);
            String[] info = output.split("/");
            tasks.add(new Event(info[0], info[1].substring(3)));

            System.out.println(dash);
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks.get(counter).toString());
            counter += 1;

            System.out.println("You now have " + counter + " task(s) in the list.");
            System.out.println(dash);
        }
    }

    public void addEvent(String userInput) {
        String[] info = userInput.split("/");
        tasks.add(new Event(info[0], info[1].substring(3)));
        counter += 1;
    }

    public void makeDeadline(String userInput) {
        if (userInput.substring(8).length() == 0) {
            System.out.println(dash + '\n' + "YIKES!! The description of a Deadline cannot be empty!" + '\n'+ dash);
            // throw new DukeException("YIKES!! The description of a Deadline cannot be empty!");
        } else {
            String output = userInput.substring(9);
            String[] info = output.split("/");
            tasks.add(new Deadline(info[0], info[1].substring(3)));

            System.out.println(dash);
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks.get(counter).toString());
            counter += 1;

            System.out.println("You now have " + counter + " task(s) in the list.");
            System.out.println(dash);
        }
    }

    public void addDeadline(String userInput) {
        String[] info = userInput.split("/");
        tasks.add(new Deadline(info[0], info[1].substring(3)));
        counter += 1;
    }

    public void error() {
        System.out.println(dash + '\n' + "OOPS!! I don't know how to respond to this command! :-(" + '\n'+ dash);
        // throw new DukeException("OOPS!! I don't know how to respond to this command! :-(");
    }


}