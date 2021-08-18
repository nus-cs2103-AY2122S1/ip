import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello, I'm Duke!\nWhat can I do for you?");

        //initialise scanner
        Scanner sc = new Scanner(System.in);
        boolean finished = false;

        //array to hold tasks
        Task[] listOfTasks = new Task[100];
        int arrayCounter = 0;

        //string array to hold keywords
        String[] commandWords = {"bye", "list", "done"};

        while(!finished) {
            String userResponse = sc.nextLine();

            //bye
            if (userResponse.contains(commandWords[0])) {
                System.out.println("Bye. Hope to see you again soon!");
                finished = true;
                break;
            //list
            } else if (userResponse.contains(commandWords[1])) {
                for (int i = 0; i < listOfTasks.length; i++) {
                    if (listOfTasks[i] != null) {
                        System.out.println((i + 1) + "." + listOfTasks[i].toString());
                    }
                }
            //done
            } else if (userResponse.contains(commandWords[2])) {
                char indexDone = userResponse.charAt(5);
                if (Character.isDigit(indexDone)) {
                    int numberIndex = Character.getNumericValue(indexDone) - 1;
                    listOfTasks[numberIndex].markAsDone();
                    System.out.println("Nice, I've marked this task as done!\n" + listOfTasks[numberIndex].toString());
                } else {
                    System.out.println("Invalid task number!");
                }
            } else {
                System.out.println("added: " + userResponse);
                listOfTasks[arrayCounter] = new Task(userResponse);
                arrayCounter++;
            }
        }

    }

}
