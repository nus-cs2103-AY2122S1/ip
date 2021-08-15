import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static boolean isDoneOps(String input) {
        if (input == null) {
            return false;
        }
        int length = input.length();
        if (length < 6) {
            return false;
        }
        if (input.startsWith("done ")) {
            try {
                Integer.parseInt(input.substring(5));
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) throws DukeException {
        System.out.println("____________________________________________________________\n"
        + "Heyllo! Jackie here\n"
        + "What can I do for you?\n"
        + "____________________________________________________________\n");

        boolean bye = false;
        Scanner myObj = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();


        while (!bye) {
            try {
                String temp = myObj.nextLine();
                if (temp.equals("bye")) {
                    System.out.println("____________________________________________________________\n"
                            + "Bye bye. Love you\n"
                            + "____________________________________________________________\n");
                    bye = true;
                } else if (temp.equals("list")) {
                    System.out.println("____________________________________________________________\n"
                            + "Darling, here are the tasks in your list:\n");
                    for (int i = 0; i < taskList.size(); i++) {
                        Task task = taskList.get(i);
                        String entry = String.format("%d. %s",
                                i+1, task.toString());
                        System.out.println(entry);
                    }
                    System.out.println("____________________________________________________________\n");
                } else if (isDoneOps(temp)) {
                    int index = Integer.parseInt(temp.substring(5));
                    taskList.get(index - 1).doneTask();
                    System.out.println("____________________________________________________________\n"
                            + "Noice! I've marked this task as done: \n");
                    String entry = String.format("[X] %s",
                            taskList.get(index - 1).getContent());
                    System.out.println(entry);
                    System.out.println("____________________________________________________________\n");
                } else if (temp.startsWith("todo")) {
                    if (temp.trim().equals("todo")) {
                        throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        Todo task = new Todo(temp);
                        taskList.add(task);
                        System.out.println("____________________________________________________________\n"
                                + "Gotcha my dear. I've added this task for you: \n" + task.toString()
                                + "\nNow you have " + taskList.size() + " tasks in the list.\n"
                                + "____________________________________________________________\n");
                    }

                } else if (temp.startsWith("deadline")) {
                    if (temp.trim().equals("deadline")) {
                        throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                    } else if (temp.split("/", 2).length == 1) {
                        throw new DukeException(" ☹ OH MY DEAR!!! Please enter a time after / following the deadline description");
                    } else {
                        Deadline task = new Deadline(temp);
                        taskList.add(task);
                        System.out.println("____________________________________________________________\n"
                                + "Gotcha my dear. I've added this task for you: \n" + task.toString()
                                + "\nNow you have " + taskList.size() + " tasks in the list.\n"
                                + "____________________________________________________________\n");
                    }
                } else if (temp.startsWith("event")) {
                    if (temp.trim().equals("event")) {
                        throw new DukeException(" ☹ OOPS!!! The description of a event cannot be empty.");
                    } else if (temp.split("/", 2).length == 1) {
                        throw new DukeException(" ☹ OH MY DEAR!!! Please enter a date after / following the event description");
                    } else {
                        Event task = new Event(temp);
                        taskList.add(task);
                        System.out.println("____________________________________________________________\n"
                                + "Gotcha my dear. I've added this task for you: \n" + task.toString()
                                + "\nNow you have " + taskList.size() + " tasks in the list.\n"
                                + "____________________________________________________________\n");
                    }
                } else {
                    throw new DukeException("☹ oopsie!!! I'm sorry my dear, but I can't do that for you");
                }
            } catch (DukeException e) {
                System.out.println("____________________________________________________________\n"
                        + e.getMessage()
                        + "\n"
                        + "____________________________________________________________\n");
            }
            

        }
    }
}
