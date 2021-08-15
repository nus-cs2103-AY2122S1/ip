import java.util.Scanner;

public class Duke {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Task[] taskArray = new Task[100];
        int listIndex = 0;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo + "\nWhat can I do for you today?");

        while(true) {
            String input = scan.next();

            if (input.equals("list")) {
                for (int i = 0; i < 100; i++) {
                    if (taskArray[i] == null) {
                        break;
                    }
                    int listNumber = i + 1;
                    System.out.println(listNumber + ". " + taskArray[i].toString());
                }
                continue;
            }

            if (input.equals("done")) {
                int index = scan.nextInt();
                int arrayIndex = index - 1;
                if (index > listIndex || index < 1) {
                    System.out.println("Sorry, that task does not exist!");
                    continue;
                }
                taskArray[arrayIndex].setCompleted();
                System.out.println("Ok, very nice. I have set the following task as completed.\n" + taskArray[arrayIndex].toString());
                continue;
            }

            if (input.equals("bye")) {
                break;
            }

            System.out.println("Added: " + input);
            taskArray[listIndex] = new Task(input);
            listIndex += 1;
        }

        scan.close();
        System.out.println("See you again next time!");
    }
}
