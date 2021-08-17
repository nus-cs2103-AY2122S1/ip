import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke. \n" + "What can I do for you?");



        Scanner scan = new Scanner(System.in);
        ArrayList<String> myList = new ArrayList<>();

        while (true) {
            String data = scan.nextLine();
            if (data.equals("bye")) {
                break;
            } else if (data.equals("list")) {
                System.out.println("-----------------------");
                for (int i = 1; i <= myList.size(); i++) {
                    System.out.println(i + ". " + myList.get(i - 1));
                }
                System.out.println("-----------------------");
            } else {
                printMsg(data);
                myList.add(data);
            }


        }

        printMsg("Bye. Hope to see you again soon!");

    }

    public static void printMsg(String msg) {
        System.out.println("-----------------------\n" +
                msg +
                "\n-----------------------");
    }


}
