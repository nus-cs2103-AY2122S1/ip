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
            String input = sc.nextLine();
            boolean isLastCharDigit =  Character.isDigit(input.charAt(input.length() - 1));
            if (input.equals("list")) {
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    System.out.println("\t" + (i + 1) + ". " + listOfItems[i].toString());
                }
            } else if (input.equals("bye")) {
                System.out.println(" \t Bye. Hope to see you again soon!");
                break;
            } else if (input.contains("done") && isLastCharDigit) {
                char itemIndex = input.charAt(input.length() - 1);
                int i = Integer.parseInt(String.valueOf(itemIndex)) - 1;
                if (i < index) {
                    listOfItems[i].markAsDone();
                    System.out.println("\tNice! I've marked this task as done:\n\t\t" +
                          listOfItems[i].toString());
                } else {
                    System.out.println("\tNo task found or invalid input!");
                }

            } else if (input.contains("done") && !isLastCharDigit) {
                System.out.println("\tinvalid input!");
            }
            else {
                if (input.contains("todo")) {
                    int firstIndexAfterDeadline = 5;
                    listOfItems[index] = new Todo(input.substring(firstIndexAfterDeadline));
                } else if (input.contains("deadline")) {
                    int firstIndexAfterDeadline = 9;
                    int i = input.indexOf('/');
                    String deadline = input.substring(i + 1, input.length() - 1+1);
                    listOfItems[index] = new Deadline(input.substring(firstIndexAfterDeadline, i), deadline);
                } else if (input.contains("event")) {
                    int firstIndexAfterDeadline = 6;
                    int i = input.indexOf('/');
                    String deadline = input.substring(i + 1, input.length() - 1+1);
                    listOfItems[index] = new Event(input.substring(firstIndexAfterDeadline, i), deadline);
                } else {
                    listOfItems[index] = new Task(input);
                }

                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t\t" + listOfItems[index].toString());
                index++;
                System.out.println("\tNow you have " + index + " tasks in the list.");

            }
        }
    }
}
