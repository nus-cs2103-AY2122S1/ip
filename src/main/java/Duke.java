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
        String[] commandWords = {"bye", "list", "done", "todo", "deadline", "event", "/by", "/at"};

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
            //to do
            } else if (userResponse.contains(commandWords[3])) {
                String desc = userResponse.substring(5);
                listOfTasks[arrayCounter] = new Todo(desc);
                arrayCounter++;

                System.out.println("Got it. I'll add this task:");
                System.out.println(listOfTasks[arrayCounter - 1].toString());
                System.out.println("Now you've got " + arrayCounter + " tasks in your list.");
            //deadline
            } else if (userResponse.contains(commandWords[4])) {
                if (userResponse.contains(commandWords[6])) {
                    int slashIndex = userResponse.indexOf("/by");
                    String desc = userResponse.substring(9,slashIndex - 1);
                    String time = userResponse.substring(slashIndex + 4);
                    listOfTasks[arrayCounter] = new Deadline(desc, time);
                    arrayCounter++;

                    System.out.println("Got it. I'll add this task:");
                    System.out.println(listOfTasks[arrayCounter - 1].toString());
                    System.out.println("Now you've got " + arrayCounter + " tasks in your list.");
                } else {
                    System.out.println("Invalid description!");
                }
            //event
            } else if (userResponse.contains(commandWords[5])) {
                if (userResponse.contains(commandWords[7])) {
                    int slashIndex = userResponse.indexOf("/at");
                    String desc = userResponse.substring(6,slashIndex - 1);
                    String time = userResponse.substring(slashIndex + 4);
                    listOfTasks[arrayCounter] = new Event(desc, time);
                    arrayCounter++;

                    System.out.println("Got it. I'll add this task:");
                    System.out.println(listOfTasks[arrayCounter - 1].toString());
                    System.out.println("Now you've got " + arrayCounter + " tasks in your list.");
                } else {
                    System.out.println("Invalid description!");
                }
            } else {
                System.out.println("added: " + userResponse);
                listOfTasks[arrayCounter] = new Task(userResponse);
                arrayCounter++;
            }
        }

    }

}
