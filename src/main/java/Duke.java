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
        ArrayList<Task> myList = new ArrayList<>();
        boolean tryMore = true;

        while (tryMore) {
            String data = scan.nextLine();


            switch (data.toLowerCase()) {
                case "bye":
                    tryMore = false;
                    break;
                case "list":
                    for (int i = 1; i <= myList.size(); i++) {
                        System.out.println(i + ". " + myList.get(i - 1).toString());
                    }
                    break;
                case "done .*":
                    System.out.println("Here");
                    break;
                default:
                    printMsg(data);
                    myList.add(new Task(data));
            }


//            } else if ((data.length() >= 2) && data.substring(0, 3).equals("done")) {
//                myList.get(data.charAt(5)).isDone = true;
//                System.out.println(" Nice! I've marked this task as done: \n"
//                        + myList.get(data.charAt(5)).toString());

        }
        printMsg("Bye. Hope to see you again soon!");
    }

    public static void printMsg(String msg) {
        System.out.println("-----------------------\n" +
                msg +
                "\n-----------------------");
    }


}
