/**
 * Contains skeleton of Duke chatbot for IP of CS2103 2021
 *
 * @author: Ren Weilin
 * @version: 19 August 2021
 */

import java.util.ArrayList;
import  java.util.Scanner;

public class Duke {

    private final ArrayList<Task> commandList;

    private Duke() {
        commandList = new ArrayList<>(100);
    }

    public static void main(String[] args) {
        Duke instance = new Duke();
        instance.begin();
    }

    public void begin() {
        //Greet
        welcomeGreet();

        //Echo
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println();
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                //Exit
                printBreakLine();
                System.out.println(" Bye. Hope to see you again soon!");
                printBreakLine();
                break;
            } else  if (command.equals("list")) {
                printBreakLine();
                for (int i = 1; i < commandList.size() + 1; i++) {
                    System.out.printf("  %d. %s%n", i, commandList.get(i - 1)).toString();
                }
                printBreakLine();
            } else if(command.contains("done")) {
                int index = Integer.parseInt(command.split(" ")[1]);
                printBreakLine();
                commandList.get(index - 1).markAsDone();
                printBreakLine();
            } else {
                commandList.add(new Task(command));
                printBreakLine();
                System.out.println("  added: " + command);
                printBreakLine();
            }
        }
    }

    private void printBreakLine() {
        for (int i = 0; i < 12; i++) {
            System.out.print("==");
        }
        System.out.println();
    }

    private void welcomeGreet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printBreakLine();
        System.out.println("  Hello! I'm Duke");
        System.out.println("  What can I do for you?");
        printBreakLine();
    }
}
