import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Duke {

    //List of important keywords
    enum Keywords {
        list,
        bye,
        add,
        done,
    }

    //Logo
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    //Prints a divider
    static void printDivider() {
        String divider = "____________________________________________________________";
        System.out.println(divider);
    }

    //Initial Greeting from Duke
    static void greetings() {
        System.out.println(logo);
        printDivider();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        printDivider();
    }

    //Goodbye from Duke
    static void close() {
        printDivider();
        System.out.println("Bye. Hope to see you again soon!\n");
        printDivider();
    }

    //Prints list of task
    //Input: List
    static void printList(List<Task> lst) {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < lst.size(); i++) {
            Task task = lst.get(i);
            System.out.println((i+1) + ".[" + task.getStatusIcon() + "] " + task.getDescription());
        }
        printDivider();
    }

    //Marks task as Done
    //Input: Task
    static void markTask(Task task) {
        printDivider();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + "[" + task.getStatusIcon() + "] " + task.getDescription());
        printDivider();
    }

    //Checks if String is an int
    //Input: String
    static boolean checkForInt(String str) {
        try {
            int test = parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static void main(String[] args) {
        List<Task> list = new ArrayList<Task>();
        greetings();
        Scanner userInput = new Scanner(System.in);

        //Waits for userInput
        while (true) {
            String input = userInput.nextLine();
            String[] words = input.split(" ");
            Keywords key = Keywords.add;
            int index = 0;

            //Looks for keywords
            if (input.equals("bye")) {
                key = Keywords.bye;
            } else if (input.equals("list")) {
                key = Keywords.list;
            } else if (words.length > 1) {
                if (words[0].equals("done")) {
                    if (checkForInt(words[1])) {
                        index = parseInt(words[1]) - 1;
                        key = Keywords.done;
                    }
                }
            }

            //Performs the Appropriate Action
            switch (key) {
                case bye:
                    close();
                    System.exit(0);
                    break;
                case list:
                    printList(list);
                    break;
                case add:
                    list.add(new Task(input));
                    printDivider();
                    System.out.println("added: " + input);
                    printDivider();
                    break;
                case done:
                    list.get(index).markAsDone();
                    markTask(list.get(index));
                    break;
            }
        }
    }
}
