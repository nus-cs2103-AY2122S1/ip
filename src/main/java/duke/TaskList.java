package duke;

import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidTaskException;
import duke.exception.OutOfBoundException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    //print a list
    public String printList() {
        System.out.println("Here are the tasks on your list: ");
        String str = "";
        for (int i = 1; i <= list.size(); i++) {
            str = str + i + ". " + list.get(i-1) + "\n";
        }
        System.out.println(str);
        return str;
    }

    //mark a task as done
    public Task markAsDone(String str) throws OutOfBoundException, NumberFormatException {
        String i = str.substring(str.length()-1);
        int index = Integer.parseInt(i);
        if (index < 0 || index > list.size()) {
            throw new OutOfBoundException();
        } else {
            Task task = list.get(index - 1);
            task.markAsDone();
            System.out.println("Nice! I have marked this task as done!");
            System.out.println(task);
            return task;
        }
    }

    //method to add a task to a list

    public Task addTask(String str) throws EmptyDescriptionException, InvalidTaskException, InvalidDeadlineException {
        //first check if the task only contain 1 word
        if (str.split(" ").length == 1) {
            //check if task if valid
            if (str.startsWith("todo") || str.startsWith("deadline") || str.startsWith("event")) {
                throw new EmptyDescriptionException(str);
            }

            //check for invalid task
            else {
                throw new InvalidTaskException();
            }
        }

        //task that contain more than 1 word.
        //check for invalid task. otherwise, add the correct task to the list.
        else {
            //check for invalid task
            if (!str.startsWith("todo") && !str.startsWith("deadline") && !str.startsWith("event")) {
                throw new InvalidTaskException();
            }

            //add correct task to the list
            else {
                Task task;
                if (str.startsWith("todo")) {
                    task = new ToDos(str.substring(5));
                    list.add(task);
                } else if (str.startsWith("deadline")) {
                    String[] message = str.split("/by ");
                    if (message.length == 1) {
                        throw new InvalidTaskException();
                    } else {
                        task = new Deadline(message[0].substring(9), message[1]);
                        list.add(task);
                    }
                } else {
                    String[] message = str.split("/at ");
                    if (message.length == 1) {
                        throw new InvalidTaskException();
                    } else {
                        task = new Events(message[0].substring(6), message[1]);
                        list.add(task);
                    }
                }

                System.out.println("Got it. I've added this task.");
                System.out.println(list.get(list.size() - 1));
                System.out.println("Now you have " + list.size()
                        + (list.size() == 1 ? " task in the list" : " tasks in the list."));
                return task;
            }
        }
    }

    //delete a task
    public Task deleteTask(String str) throws OutOfBoundException {
        String i = str.substring(str.length()-1);
        int index = Integer.parseInt(i);
        if (index < 0 || index > list.size()) {
            throw new OutOfBoundException();
        } else {
            Task task = list.get(index - 1);
            list.remove(index - 1);
            System.out.println("Noted. I've removed this task: ");
            System.out.println(task);
            System.out.println("Now you have " + list.size()
                    + (list.size() == 1 ? " task in the list" : " tasks in the list."));
            return task;
        }
    }

    public ArrayList<Task> getList() {
        return list;
    }
}
