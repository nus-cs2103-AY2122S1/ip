import java.util.*;

public class Duke {
    public static void main(String[] args) {
        displayLogo();
        greet();
        addToList();
    }

    public static void displayLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void greet() {
        String greeting = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(greeting);
    }

    public static void echo() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(str);
        }
    }

    public static void addToList() {
        Scanner sc = new Scanner(System.in);
        List<String> strList = new ArrayList<String>();
        while(true) {
            String str = sc.nextLine();

            if (str.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (str.equals("list")) {
                int count = 1;
                for (String listItem : strList) {
                    System.out.println(count + ". " + listItem);
                    count++;
                }
            }
            else {
                strList.add(str);
                System.out.println("added: " + str);
            }
        }
    }
}
