import java.util.Scanner;
public class Duke {

    private static Task[] userInput;
    private static boolean isBye;
    private static Scanner scanner;

    public Duke()
    {
        scanner = new Scanner(System.in);
        userInput = new Task[100];
        for(int i = 0; i < 100; i++)
        {
            userInput[i] = null;
        }
        isBye = false;
    }

    public static void getInput(int counter) {
        while (!isBye && scanner.hasNext()) {
            String input = scanner.nextLine();
            String[] splitInput = input.split(" ");
            String[] splitTask = input.split("/");

            if (input.equals("bye") || input == "bye") {
                System.out.println("  ---------------------------------------------");
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("  ---------------------------------------------");
                isBye = true;
                scanner.close();
            } else if (input.equals("list") || input == "list") {
                System.out.println("  ---------------------------------------------\n" +
                        "    Here are the tasks in your list:");
                int point = 0;
                while (userInput[point] != null) {
                    Task temp = userInput[point];
                    System.out.println("        " + (point + 1) + ". " + temp.toString());
                    point++;
                }
                System.out.println("    Now you have " + point + " tasks in the list.\n" +
                       "  ---------------------------------------------");
            } else if(input.contains("done") && splitInput.length == 2) {
                Task temp = userInput[Integer.valueOf(splitInput[1]) - 1];
                temp.markAsDone();
                System.out.println("  ---------------------------------------------\n" +
                        "    Nice! I've marked this task as done:\n      " + temp.toString());
                System.out.println("  ---------------------------------------------");
            } else {
                System.out.println("  ---------------------------------------------");
                System.out.println("    Got it. I've added this task:");
                if(splitTask.length == 1) {
                    String[] todoInput = input.split("todo ");
                    Todo todo = new Todo(todoInput[1]);
                    userInput[counter] = todo;
                } else if(splitTask.length == 2) {
                    if(input.contains("at")) {
                        Event event = new Event(splitTask[0], splitTask[1].split("at")[1]);
                        userInput[counter] = event;
                    } else if(input.contains("by")) {
                        Deadline deadline = new Deadline(splitTask[0], splitTask[1].split("by")[1]);
                        userInput[counter] = deadline;
                    }
                }
                System.out.println("      " + userInput[counter].toString());
                counter++;
                System.out.println("    Now you have " + counter + (counter == 1 ?
                        " task in the list" : " tasks in the list."));
                System.out.println("  ---------------------------------------------");
            }

        }
    }

    public static void main(String[] args) {

        System.out.println("  ---------------------------------------------");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("  ---------------------------------------------");


        int counter = 0;
        Duke duke = new Duke();
        getInput(counter);

    }
}
