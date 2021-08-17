import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    //List of important keywords
    enum Keywords {
        list,
        bye,
        add,
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
    static void printList(List lst) {
        for(int i = 0; i < lst.size(); i++) {
            System.out.println((i+1) + ": " + lst.get(i));
        }
        printDivider();
    }


    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        greetings();
        Scanner userInput = new Scanner(System.in);

        //Waits for userInput
        while (true) {
            String input = userInput.nextLine();
            Keywords key = Keywords.add;

            //Looks for keywords
            if (input.equals("bye")) {
                key = Keywords.bye;
            } else if (input.equals("list")) {
                key = Keywords.list;
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
                    list.add(input);
                    printDivider();
                    System.out.println("added: " + input);
                    printDivider();
                    break;
            }
        }
    }
}
