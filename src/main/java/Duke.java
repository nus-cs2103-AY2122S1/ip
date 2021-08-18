import java.util.Scanner;
public class Duke {

    private static Task[] userInput = new Task[100];
    private static boolean isBye = false;

    public static void setArray()
    {
        for(int i = 0; i < 100; i++)
        {
            userInput[i] = null;
        }
    }

    public static void getInput(Scanner scanner, int counter) {
        while (!isBye) {
            String input = scanner.nextLine();
            String[] splitInput = input.split(" ");

            if (input.equals("bye") || input == "bye") {
                System.out.println("  ---------------------------------------------");
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("  ---------------------------------------------");
                isBye = true;
                scanner.close();
            } else if (input.equals("list") || input == "list") {
                System.out.println("  ---------------------------------------------\n   " +
                        "Here are the tasks in your list:");
                int point = 0;
                while (userInput[point] != null) {
                    Task temp = userInput[point];
                    System.out.println("        " + (point + 1) + ".[" + temp.getStatusIcon()
                        + "] " + temp.toString());
                    point++;
                }
                System.out.println("  ---------------------------------------------");
            } else if(input.contains("done") && splitInput.length == 2) {
                Task temp = userInput[Integer.valueOf(splitInput[1]) - 1];
                temp.markAsDone();
                System.out.println("---------------------------------------------\n" +
                        "   Nice! I've marked this task as done:\n     [" +
                        temp.getStatusIcon() + "] " + temp.toString()
                            + "\n---------------------------------------------");
            } else {
                System.out.println("  ---------------------------------------------");
                System.out.println("    added: " + input);
                System.out.println("  ---------------------------------------------");
                Task addTask = new Task(input);
                userInput[counter] = addTask;
                counter++;
            }

        }
    }

    public static void main(String[] args) {
        System.out.println("  ---------------------------------------------");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("  ---------------------------------------------");

        Scanner scanner = new Scanner(System.in);
        int counter = 0;
        setArray();
        getInput(scanner, counter);

    }
}
