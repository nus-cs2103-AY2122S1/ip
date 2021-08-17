import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        Task[] listOfItems = new Task[100];
        int index = 0;
        while (true) {
            String input = sc.nextLine().toLowerCase();
            boolean isLastCharDigit =  Character.isDigit(input.charAt(input.length() - 1));
            if (input.equals("list")) {
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    System.out.println("\t" + (i + 1) + ". [" + listOfItems[i].getStatusIcon() + "] " + listOfItems[i].getDescription());
                }
            } else if (input.equals("bye")) {
                System.out.println(" \t Bye. Hope to see you again soon!");
                break;
            } else if (input.contains("done") && isLastCharDigit) {
                char itemIndex = input.charAt(input.length() - 1);
                int i = Integer.parseInt(String.valueOf(itemIndex)) - 1;
                if (i < index) {
                    listOfItems[i].markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n\t" +
                            "[" + listOfItems[i].getStatusIcon() + "] " + listOfItems[i].getDescription());
                } else {
                    System.out.println("\tNo task found or invalid input!");
                }

            } else if (input.contains("done") && !isLastCharDigit) {
                System.out.println("\tinvalid input!");
            }
            else {
                listOfItems[index] = new Task(input);
                index++;
                System.out.println("\tadded: " + input);
            }
        }
    }
}
