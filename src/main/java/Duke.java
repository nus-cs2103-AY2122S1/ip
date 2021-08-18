import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;


public class Duke {

    private static String welcomeLabel = "Hello! I'm Banana \n" + "     What can I do for you?";
    private static String byeLabel = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/

        System.out.println(displayLabel(welcomeLabel));

        ArrayList<String> items = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner userInput = new Scanner(System.in);
        String input = userInput.nextLine();

        try {
            while (!input.equals("bye")) {
                Level6(input, tasks);
                input = userInput.nextLine();
            }
            System.out.println(displayLabel(byeLabel));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

    }

    // displays the information keyed in with lines and indentation
    public static String displayLabel(String information) {
        String label = "    ____________________________________________________________\n" +
                "     " +
                information + "\n    " +
                "____________________________________________________________\n";
        return label;
    }

    // displays the information that was inputted,
    public static void Level1(String input) {
        System.out.println(displayLabel(input));
    }

    /* gets the items in the list when the user inputs list
       For level 2, the parameter was an ArrayList<String>, but from
       level 3 onwards, the parameter became ArrayList<? extends Object>
       to cater to Strings and Task/Subclasses of Task.
     */
    public static String getItems(ArrayList<? extends Object> items) {
        String collection = "";
        for (int index = 0; index < items.size(); index++) {
            if (index != 0) { collection += "     "; }
            String info = "";
            if (items.get(index) instanceof Task) {
                info = items.get(index).toString();
            } else {
                info = (String) (" " + items.get(index));
            }
            collection += Integer.toString(index + 1) + "." + info;
            if (index != items.size() - 1) {
                collection += "\n";
            }
        }
        return collection;
    }

    /* adds the information that is inputted,
     * and prints out all the information when list is inputted.
     */
    public static void Level2(String input, ArrayList<String> items) {
        if (!input.equals("list")) {
            items.add(input);
            Level1("added: " + input);
        } else {
            String itemCollection = (String) getItems(items);
            Level1(itemCollection);
        }
    }

    // Adding onto Level 2, marks tasks as done.
    public static void Level3(String input, ArrayList<Task> tasks) {
        if (input.contains("done")) {
            int index = Integer.parseInt(input.substring(5, 6)) - 1;
            tasks.get(index).setIsDone();
            Level1("Nice! I've marked this task as done: \n" +
                    "       " + tasks.get(index).toString());
        } else if (!input.equals("list")) {
            tasks.add(new Task(input));
            Level1("added: " + input);
        }  else {
            String itemCollection = getItems(tasks);
            Level1("Here are the tasks in your list: \n"
                    + "     " + itemCollection);
        }
    }

    // Adding onto Level 3, handles subclasses of Tasks.
    public static void Level4(String input, ArrayList<Task> tasks) {
        if (!input.equals("list") && !input.contains("done")) {
            if (input.contains("todo")) {
                String info = input.substring(5);
                tasks.add(new ToDo(info));
            } else if (input.contains("deadline")) {
                String[] info = input.substring(9).split("/by");
                tasks.add(new Deadline(info[0], info[1]));
            } else if (input.contains("event")) {
                String[] info = input.substring(6).split("/at");
                tasks.add(new Event(info[0], info[1]));
            } else {
                tasks.add(new Task(input));
            }
            Level1("Got it. I've added this task:  \n" +
                    "       " + tasks.get(tasks.size() - 1).toString() + "\n     Now you have "
                    + Integer.toString(tasks.size()) + " tasks in the list.");
        } else {
            Level3(input, tasks);
        }
    }

    // Adds onto Level 4, throws exceptions for inadequate words.
    public static void Level5(String input, ArrayList<Task> tasks) throws DukeException {
            if (input.equals("todo") || input.equals("event") || input.equals("deadline")) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            } else if (input.equals("blah")) {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } else {
                Level4(input, tasks);
            }
    }

    // Adds onto Level 5 by deleting items.
    public static void Level6(String input, ArrayList<Task> tasks) throws DukeException {
            if (input.contains("delete")) {
                int index = Integer.parseInt(input.substring(7, 8)) - 1;
                Task removedTask = tasks.get(index);
                tasks.remove(removedTask);
                System.out.println(displayLabel("Noted. I've removed this task:  \n" +
                        "       " + removedTask.toString() + "\n     Now you have "
                        + Integer.toString(tasks.size()) + " tasks in the list."));
            } else {
                Level5(input, tasks);
            }
    }
}


class DukeException extends Exception {

    private String text;

    public DukeException(String text) {
        this.text = text;
    }

    @Override
    public String getMessage() {
        return text;
    }

}

class Task {

    private String newTask;
    private boolean isDone;

    public Task(String newTask) {
        this.newTask = newTask;
    }

    public String getIsDone() {
        return isDone ? "[X]" : "[ ]";
    }

    public void setIsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return getIsDone() + " " + newTask;
    }

}

class ToDo extends Task {

    private String newTask;

    public ToDo(String newTask) {
        super(newTask);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}

class Deadline extends Task {

    private String newTask;
    private String deadLine;

    public Deadline(String newTask, String deadLine) {
        super(newTask);
        this.deadLine = deadLine;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + deadLine + ")";
    }

}

class Event extends Task {

    private String newTask;
    private String timing;

    public Event(String newTask, String timing) {
        super(newTask);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + timing + ")";
    }

}






