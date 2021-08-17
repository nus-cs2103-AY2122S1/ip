import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Duke {

    //List of important keywords
    enum Keywords {
        list,
        bye,
        todo,
        deadline,
        event,
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
            System.out.println((i+1) + "." + task.toString());
        }
        printDivider();
    }

    //Marks task as Done
    //Input: Task
    static void markTask(Task task) {
        printDivider();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + task.toString());
        printDivider();
    }

    static void printTask(List<Task> lst) {
        int lastItem = lst.size() - 1;
        printDivider();
        System.out.println("Got it. I've added this task: \n"
                + "\t" + lst.get(lastItem).toString() + "\n"
        + "Now you have " + lst.size() + " tasks in the list.");
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

    //Processed String into Desc and Time
    static List<String> processDesc(String divider, String[] input) {
        List<String> list = Arrays.asList(input);
        int index = list.indexOf(divider);
        String desc = "";
        String time = "";
        for (int i = 1; i < index; i++) {
            desc += input[i] + " ";
        }
        for (int i = index + 1; i < input.length; i++) {
            time += input[i] + " ";
        }
        List<String> temp = new ArrayList<>();
        temp.add(desc.trim());
        temp.add(time.trim());
        return temp;
    }


    public static void main(String[] args) {
        List<Task> list = new ArrayList<>();
        greetings();
        Scanner userInput = new Scanner(System.in);

        //Waits for userInput
        while (true) {
            String input = userInput.nextLine();

            //Processed input
            String[] words = input.split(" ");
            String desc = input;
            String time = "";

            //Switch variables
            Keywords key = Keywords.todo;
            int index = 0;

            //Looks for keywords
            //Simple keywords
            if (input.equals("bye")) {
                key = Keywords.bye;
            } else if (input.equals("list")) {
                key = Keywords.list;
            //Complicated Keywords
            } else if (words.length > 1) {
                String temp = words[0];
                if (temp.equals("done")) {
                    if (checkForInt(words[1])) {
                        index = parseInt(words[1]) - 1;
                        key = Keywords.done;
                    }
                } else if (temp.equals("todo")) {
                    desc = input.replaceFirst("todo ", "");
                } else if (temp.equals("deadline")) {
                    key = Keywords.deadline;
                    List<String> postFilter = processDesc("/by", words);
                    desc = postFilter.get(0);
                    time = postFilter.get(1);

                } else if (temp.equals("event")) {
                    key = Keywords.event;
                    List<String> postFilter = processDesc("/at", words);
                    desc = postFilter.get(0);
                    time = postFilter.get(1);
                }
            }

            //Performs the Appropriate Action
            switch (key) {

                case list:
                    printList(list);
                    break;
                case todo:
                    list.add(new ToDo(desc));
                    printTask(list);
                    break;
                case deadline:
                    list.add(new Deadline(desc, time));
                    printTask(list);
                    break;
                case event:
                    list.add(new Event(desc, time));
                    printTask(list);
                    break;
                case done:
                    list.get(index).markAsDone();
                    markTask(list.get(index));
                    break;
                case bye:
                    close();
                    System.exit(0);
                    break;
            }
        }
    }
}
