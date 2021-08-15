import java.util.ArrayList;
import java.util.Locale;
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

    public static boolean isDeleteOps(String input) {
        if (input == null) {
            return false;
        }
        int length = input.length();
        if (length < 8) {
            return false;
        }
        if (input.startsWith("delete ")) {
            try {
                Integer.parseInt(input.substring(7));
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }
    
    public static enum Command {
        TODO("todo"), DEADLINE("deadline"), EVENT("event"), 
        DONE("done"), DELETE("delete"), BYE("bye"), LIST("list");
        private String value;
        private Command(String value) {
            this.value = value;
        }
    }

    public static void main(String[] args) throws DukeException {
        System.out.println("____________________________________________________________\n"
        + "Heyllo! Jackie here\n"
        + "What can I do for you?\n"
        + "____________________________________________________________\n");

        boolean bye = false;
        boolean executed = false;
        Scanner myObj = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();


        while (!bye) {
            try {
                String temp = myObj.nextLine();
                String commandHolder = temp.split(" ", 2)[0];
                for (Command c : Command.values()) {
                    if (c.value.equals(commandHolder)) {
                        switch (c) {
                            case BYE:
                                System.out.println("____________________________________________________________\n"
                                        + "Bye bye. Love you\n"
                                        + "____________________________________________________________\n");
                                bye = true;
                                break;

                            case LIST:
                                System.out.println("____________________________________________________________\n"
                                        + "Darling, here are the tasks in your list:\n");
                                for (int i = 0; i < taskList.size(); i++) {
                                    Task task = taskList.get(i);
                                    String entry = String.format("%d. %s",
                                            i+1, task.toString());
                                    System.out.println(entry);
                                }
                                System.out.println("____________________________________________________________\n");
                                executed = true;
                                break;

                            case DONE:
                                if (isDoneOps(temp)) {
                                    int index = Integer.parseInt(temp.substring(5));
                                    if (index > taskList.size())
                                        throw new DukeException("☹ oopsie!!! The specified task does not exit.");
                                    taskList.get(index - 1).doneTask();
                                    System.out.println("____________________________________________________________\n"
                                            + "Noice! I've marked this task as done: \n"
                                            + taskList.get(index - 1).toString()
                                            + "\n"
                                            + "____________________________________________________________\n");
                                    executed = true;
                                } else {
                                    throw new DukeException("☹ Would you specify the task for me my dear?");
                                }
                                break;

                            case DELETE:
                                if (isDeleteOps(temp)) {
                                    int index = Integer.parseInt(temp.substring(7));
                                    if (index > taskList.size())
                                        throw new DukeException("☹ oopsie!!! The specified task does not exit.");
                                    String holder = taskList.get(index - 1).toString();
                                    taskList.remove(index - 1);
                                    taskList.trimToSize();
                                    System.out.println("____________________________________________________________\n"
                                            + "okie! I've removed this annoying task: \n"
                                            + holder
                                            + "\nNow you have " + taskList.size() + " tasks in the list.\n"
                                            + "____________________________________________________________\n");
                                    executed = true;
                                } else {
                                    throw new DukeException("☹ Would you specify the task for me my dear?");
                                }
                                break;

                            case TODO:
                                if (temp.trim().equals("todo")) {
                                    throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
                                } else {
                                    Todo task = new Todo(temp);
                                    taskList.add(task);
                                    System.out.println("____________________________________________________________\n"
                                            + "Gotcha my dear. I've added this task for you: \n" + task.toString()
                                            + "\nNow you have " + taskList.size() + " tasks in the list.\n"
                                            + "____________________________________________________________\n");
                                    executed = true;
                                }
                                break;

                            case DEADLINE:
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
                                    executed = true;
                                }
                                break;

                            case EVENT:
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
                                    executed = true;
                                }
                                break;
                                
                        }
                    } 
                }
                if (!executed) {
                    throw new DukeException("☹ oopsie!!! I'm sorry my dear but I can't do that for you");
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
