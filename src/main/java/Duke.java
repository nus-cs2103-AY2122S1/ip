import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> lst = new ArrayList<>();

        System.out.println(greet());
        String input = sc.nextLine();
        while(!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(printList(lst));
            } else if (input.substring(0,Math.min(input.length(), 5)).equals("done ")) {
                String index = input.split(" ", 2)[1];
                System.out.println(doTask(index, lst));
            } else if (input.substring(0,Math.min(input.length(), 7)).equals("delete ")) {
                String index = input.split(" ", 2)[1];
                System.out.println(deleteTask(index, lst));
            } else {
                System.out.println(addList(input, lst));
            }
            input = sc.nextLine();
        }

        System.out.println(exit());
    }

    public static String greet() {
        String output = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n";

        return output;
    }

    /**
     * Returns the message that shows which task is marked as completed.
     * Mark the task indicated by index as completed.
     * @param index the index of task to be marked
     * @param lst the list containing all tasks
     * @return the messages
     */
    public static String doTask(String index, ArrayList<Task> lst) {
        int idx;
        try {
            idx = Integer.parseInt(index);
            lst.get(idx - 1);
        } catch (NumberFormatException nfe) {
            return "Please check the format of the index.";
        } catch (IndexOutOfBoundsException e) {
            return "The task does not exist in task list.";
        }


        StringBuilder s = new StringBuilder();
        s.append("    ____________________________________________________________\n");
        s.append("    Nice! I've marked this task as done: \n");

        lst.get(idx - 1).setDone();
        s.append("       " + lst.get(idx - 1).toString() + "\n");

        s.append("    ____________________________________________________________\n");

        return s.toString();
    }

    /**
     * Returns a string that contains all the elements in the list.
     * @param lst the list to be printed
     * @return the string
     */
    public static String printList(ArrayList<Task> lst) {
        StringBuilder s = new StringBuilder();
        s.append("    ____________________________________________________________\n");
        for (int i = 0; i < lst.size(); i++) {
            s.append(String.format("     %d. %s\n", i + 1, lst.get(i).toString()));
        }
        s.append("    ____________________________________________________________\n");

        return s.toString();
    }

    /**
     * Returns error messages or a string showing the added task.
     * The input string must follow input format, otherwise error messages will be return.
     * The new task will be created and added to lst.
     * @param input input message
     * @param lst the list containing all tasks
     * @return a string showing the added task and number of tasks
     */
    public static String addList(String input, ArrayList<Task> lst) {
        ArrayList<String> validType = new ArrayList<>(
                Arrays.asList("deadline", "event", "todo"));
        String type = input.split(" ", 2)[0];
        String content;
        if (!validType.contains(type)) {
            return "I'm sorry, but I don't know what that means :-(";
        }
        try {
            content = input.split(" ", 2)[1];
        } catch (IndexOutOfBoundsException e) {
            return "The description of a todo cannot be empty.";
        }

        if (type.equals("todo")) {
            lst.add(new Todo(content));
        } else if (type.equals("deadline")) {
            String[] strings = content.split(" /by ");
            if (strings.length != 2) {
                return "Please check the format of your deadline.";
            }
            lst.add(new Deadline(content.split(" /by ")[0], content.split( " /by ")[1]));
        } else if (type.equals("event")) {
            String[] strings = content.split(" /at ");
            if (strings.length != 2) {
                return "Please check the format of your event.";
            }
            lst.add(new Event(content.split(" /at ")[0], content.split(" /at ")[1]));
        }

        String output = "    ____________________________________________________________\n"
                + "     Got it. I've added this task: \n"
                + "      " + lst.get(lst.size() - 1).toString() + "\n"
                + "     Now you have " + lst.size() +" tasks in the list. \n"
                + "    ____________________________________________________________\n";
        return output;
    }


    public static String deleteTask(String index, ArrayList<Task> lst) {
        int idx;
        try {
            idx = Integer.parseInt(index);
            lst.get(idx - 1);
        } catch (NumberFormatException nfe) {
            return "Please check the format of the index.";
        } catch (IndexOutOfBoundsException e) {
            return "The task does not exist in task list.";
        }

        Task currTask = lst.get(idx - 1);
        lst.remove(currTask);

        String output = "    ____________________________________________________________\n"
                + "     Noted. I've removed this task: \n"
                + "      " + currTask.toString() + "\n"
                + "     Now you have " + lst.size() +" tasks in the list. \n"
                + "    ____________________________________________________________\n";
        return output;
    }
    /**
     * return the goodbye message
     * @return a string containing the goodbye message
     */
    public static String exit() {
        String output = "    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________\n";
        return output;
    }
}
