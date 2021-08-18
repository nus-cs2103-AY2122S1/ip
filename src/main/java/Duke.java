import java.util.Scanner;

public class Duke {
    static Task[] botList = new Task[100];
    static int numItems = 0;

    static String introMsg = "Hello! I'm Biscuit.\n"
            + "What do you want me to do?\n";
    static String byeMsg = "Bye. Hope to see you again soon!";
    static String listMsg = "Here are the tasks in your list:\n";
    static String doneMsg = "Nice! I've marked this task as done: \n";

    static void introduce() {
        System.out.println(introMsg);
    }

    static void reply() {
        Scanner sc = new Scanner(System.in);
        outerLoop:
        while (true) {
            String userInput = sc.nextLine();
            String[] tokens = userInput.split("\\s+", 2);
            String command = tokens[0];

            switch (command) {
                case "list":
                    System.out.println(listMsg);
                    for (int i = 0; i < numItems; i++) {
                        System.out.println((i + 1) + "." + botList[i]);
                    }
                    break;
                case "done":
                    int param = Integer.parseInt(tokens[1].strip()) - 1;
                    System.out.println(doneMsg);
                    botList[param].markAsDone();
                    System.out.println(botList[param]);
                    break;
                case "bye":
                    System.out.println(byeMsg);
                    break outerLoop;
                default: // Adds task
                    botList[numItems++] = new Task(userInput);
                    System.out.println("added: " + userInput);
            }
        }
    }

    static void run() {
        introduce();
        reply();
    }

    public static void main(String[] args) {
        run();
    }
}
