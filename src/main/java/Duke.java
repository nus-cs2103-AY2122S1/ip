import java.util.Scanner;

public class Duke {
    public static boolean isInt(String str) { // check if a string is integer
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("*******************************************");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("*******************************************");

        Scanner myScanner= new Scanner(System.in); // open scanner
        boolean hasQuit = false;
        Task[] tasks = new Task[100];
        int numOfTasks = 0;

        while (!hasQuit && myScanner.hasNextLine()) {
            String userInput = myScanner.nextLine();
            if (userInput.equals("bye")) { // if user enters "bye"
                hasQuit = true;
                System.out.println("Bye. Hope to see you again soon!");
            } else if (userInput.equals("list")) { // if user enters "list"
                for (int i = 0; i < numOfTasks; i++) {
                    Task curr = tasks[i];
                    int taskNum = i + 1;
                    System.out.println(taskNum + "." + curr.toString());
                }
            } else if (userInput.length() > 5
                    && userInput.substring(0, 4).equals("done")
                    && isInt(userInput.substring(5))
            ) { // if user wants to mark a task as done
                int taskNum = Integer.parseInt(userInput.substring(5));
                if (taskNum > numOfTasks || taskNum < 1) {
                    System.out.println("There is no task number " + taskNum);
                } else {
                    Task curr = tasks[taskNum - 1];
                    curr.markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n  "
                            + curr.toString());
                }
            } else { // any other input from user
                tasks[numOfTasks] = new Task(userInput);
                numOfTasks += 1;
                System.out.println("added: " + userInput);
            }
            System.out.println("*******************************************");
        }
        myScanner.close(); // close scanner
    }
}

