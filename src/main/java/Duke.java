import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> tasks = new ArrayList<>(100);
    private static int len = 0;
    private static Boolean going = true;

    private enum Type{
        DEADLINE, EVENT, TODO
    };

    /**
     * Pack a string so that it can always output in a specific format.
     *
     * @param string the content you want to print
     */
    public static void pack(String string) { // format the output
        System.out.println("------------------------------------------------------------");
        System.out.println(string);
        System.out.println("------------------------------------------------------------");
    }

    /**
     * Greeting to the user.
     */
    public static void greet(){
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?";
        pack(greeting);
    }

    /**
     * Scan the next line of input and add to the list.
     *
     * @throws DukeException
     */
    public static void scan(String input) throws DukeException{
        if(input.equals("bye")) {
            going = false;
            return;
        }
        if(input.equals("List") || input.equals("list")) {
            readList();
            return;
        }

        if(input.startsWith("deadline")) {
            int indexOfTime = input.indexOf("/by");
            if(indexOfTime == -1) {
                throw new DukeException("OOPS!!! The timeline of a deadline cannot be empty.");
            }
            String item = input.substring(9, indexOfTime);
            String by = input.substring(indexOfTime+4);
            addList(Type.DEADLINE, item, by);
        } else if(input.startsWith("event")) {
            int indexOfTime = input.indexOf("/at");
            if(indexOfTime == -1) {
                throw new DukeException("OOPS!!! The timeline of a event cannot be empty.");
            }
            String item = input.substring(6, indexOfTime);
            String at = input.substring(indexOfTime+4);
            addList(Type.EVENT, item, at);
        } else if (input.startsWith("todo")) {
            try {
                String item = input.substring(5);
                addList(Type.TODO, item, null);
            } catch(StringIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
            }
        } else if (input.startsWith("done")) {
            try {
                char temp = input.charAt(5);
                if (Character.isDigit(temp)) {
                    int item = Integer.parseInt(input.substring(5, 6));
                    done(tasks.get(item - 1));
                    return;
                }
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! The target of finished task cannot be empty.");
            }
        } else if (input.startsWith("delete")) {
            try {
                char temp = input.charAt(7);
                if (Character.isDigit(temp)) {
                    int item = Integer.parseInt(input.substring(7, 8));
                    deleteItem(tasks.get(item - 1));
                    return;
                }
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! The target of deleting task cannot be empty.");
            }
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Add a task to the task list.
     *
     * @param type the type of input task
     * @param item the description of the input task
     * @param time task timeline or null
     */
    public static void addList(Type type, String item, String time) {
        switch (type) {
            case DEADLINE:
                tasks.add(new Deadline(item, time));
                len++;
                break;
            case EVENT:
                tasks.add(new Event(item, time));
                len++;
                break;
            case TODO:
                tasks.add(new Todo(item));
                len++;
                break;
        }

        String added = "Got it. I've added this task:\n  " + tasks.get(len-1).toString() + "\nNow you have " + len;
        if(len > 1) {
            added += " tasks in the list.";
        } else {
            added += " task in the list.";
        }
        pack(added);
    }

    /**
     * Read the task list and print it out.
     *
     */
    public static void readList(){
        String out = "Here are the tasks in your list:";
        for(int i = 1; i <= len; i++) {
            out += "\n" + i + "." + tasks.get(i-1).toString();
        }
        pack(out);
    }

    /**
     * Delete an item from the task list.
     *
     * @param task the task that is going to be deleted
     */
    public static void deleteItem(Task task) {
        String out = "Noted. I've removed this task:\n  " + task.toString() + "\n";
        tasks.remove(task);
        len--;
        if (len > 1) {
            out += "Now you have " + len + " tasks in the list.";
        } else {
            out += "Now you have " + len + " task in the list.";
        }
        pack(out);
    }

    /**
     * Mark an existing task as Done.
     *
     * @param task the task that users have done
     */
    public static void done(Task task) {
        String out = "Nice! I've marked this task as done:\n  ";
        task.markAsDone();
        out += task.toString();
        pack(out);
    }

    /**
     * Exit from the system.
     */
    public static void exit(){
        String bye = "Bye. Hope to see you again soon!";
        pack(bye);
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s;

        greet();
        while(going && scan.hasNextLine()) {
            try {
                s = scan.nextLine().trim();
                scan(s);
            } catch (DukeException e) {
                pack(e.getMessage());
            }
        }
        exit();
    }
}
