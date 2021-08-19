import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String GREETING = "Hello! I'm Duck \n"
                + "*quack*  >(.)__\n"
                + "          (___/ \n"
                + "What can I do for you?";
        String BYE = "Bye. Hope to see you again soon!\n"
                + "   __(.)>   *quack*\n"
                + "~~ \\___)\n";

        String[] tasks = new String[100];
        boolean[] completionStatus = new boolean[100];
        int index = 0;
        boolean active = true;

        Scanner scanner = new Scanner(System.in);
        System.out.println(GREETING);
        String newUserInput = null;
        
        while (active) {
            newUserInput = scanner.nextLine();
            String firstWord = newUserInput.contains(" ")
                    ? newUserInput.split(" ")[0]
                    : newUserInput;
            switch (firstWord) {
                case "bye":
                    active = false;
                    System.out.println(BYE);
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < index; i++) {
                        System.out.println(i + 1 + ". ["
                                + (completionStatus[i] ? "X] " : " ] ")
                                + tasks[i]);
                    }
                    break;
                case "done":
                    int taskNo = Integer.parseInt(newUserInput.split(" ")[1]);
                    if (taskNo < 1 || taskNo > index) {
                        System.out.println("Error: No such task exists");
                    } else {
                        completionStatus[taskNo - 1] = true;
                        System.out.println("Nice! I've marked this task as done: \n"
                                + "[X] " + tasks[taskNo - 1]);

                    }
                    break;
                default:
                    tasks[index] = newUserInput;
                    completionStatus[index] = false;
                    index++;
                    System.out.println("added: " + newUserInput);
            }
        }
        scanner.close();
    }


}



