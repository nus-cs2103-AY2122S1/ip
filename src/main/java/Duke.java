import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<String> itemList = new ArrayList<String>();

    public static void greet() {
        System.out.println("Hello! I'm Duke" + '\n' + "What can I do for you?");
    }

    public static void start() {

        String exit = "bye";
        String input;
        String lineBreak = "========================================================================";
        String items = "list";

        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        System.out.println(lineBreak);

        if (input.equals(exit)) {
            itemList.clear();
            System.out.println("Bye. Hope to see you again soon!");
        } else if (input.equals(items)) {
            for (int i = 0; i < itemList.size(); i++) {
                System.out.println(i + 1 + "." + " " + itemList.get(i));
            }
        } else {
            itemList.add(input);
            System.out.println("added:" + " " + input);
        }
        System.out.println(lineBreak);
        start();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        start();
    }
}
