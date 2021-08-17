import java.util.*;


public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> lst = new ArrayList<>();

        System.out.println(greet());
        String input = sc.nextLine();
        while(!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(printList(lst));
            } else if (input.substring(0,Math.min(input.length(),5)).equals("done ")) {
                String index = input.split(" ", 2)[1];
                System.out.println(doTask(index, lst));
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

        lst.get(idx - 1).finishTask();
        s.append("       " + lst.get(idx - 1).toString() + "\n");

        s.append("    ____________________________________________________________\n");

        return s.toString();
    }



    public static String printList(ArrayList<Task> lst) {
        StringBuilder s = new StringBuilder();
        s.append("    ____________________________________________________________\n");
        for (int i = 0; i < lst.size(); i++) {
            s.append(String.format("     %d. %s\n", i + 1, lst.get(i).toString()));
        }
        s.append("    ____________________________________________________________\n");

        return s.toString();
    }

    public static String addList(String input, ArrayList<Task> lst) {
        String type = input.split(" ", 2)[0];
        String content;
        try {
            content = input.split(" ", 2)[1];
        } catch (IndexOutOfBoundsException e) {
            return "The description of a todo cannot be empty.";
        }

        if (type.equals("todo")) {
            lst.add(new Todo(content));
        } else if (type.equals("deadline")) {
            lst.add(new Deadline(content.split(" /by ")[0], content.split( " /by ")[1]));
        } else if (type.equals("event")) {
            lst.add(new Event(content.split(" /at ")[0], content.split(" /at ")[1]));
        } else {
            return "I'm sorry, but I don't know what that means :-(";
        }

        String output = "    ____________________________________________________________\n"
                + "     Got it. I've added this task: \n"
                + "      " + lst.get(lst.size() - 1).toString() + "\n"
                + "     Now you have " + lst.size() +" tasks in the list. \n"
                + "    ____________________________________________________________\n";
        return output;
    }

    public static String exit() {
        String output = "    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________\n";
        return output;
    }
}
