import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Task> taskList = new ArrayList<>();

    private static void lineSpacing() {
        System.out.println("____________________________________________________________");
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                lineSpacing();
                System.out.println("Bye. Hope to see you again soon!");
                lineSpacing();
                break;
            }

           if (userInput.equals("list")) {
                lineSpacing();
                for (int i = 0; i < taskList.size(); i++) {
                    Task currentTask = taskList.get(i);
                    System.out.println(String.format("%d.[%s] %s", i + 1, currentTask.getStatusIcon(), currentTask));
                }
                lineSpacing();
                continue;
            }

           //takes first 4 characters of userInput, if userInput has less than 4 characters it will just
            // take the whole userInput
           if (userInput.substring(0, Math.min(userInput.length(), 4)).equals("done")) {
                    String[] inputArray = userInput.split(" ");
                    Task completedTask = taskList.get(Integer.parseInt(inputArray[1]) - 1);
                    completedTask.markAsDone();
                    lineSpacing();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(String.format("[%s] %s", completedTask.getStatusIcon(), completedTask));
                    lineSpacing();
                    continue;
           }

           taskList.add(new Task(userInput));
           lineSpacing();
           System.out.println(String.format("added : %s", userInput));
           lineSpacing();


        }
    }
}
