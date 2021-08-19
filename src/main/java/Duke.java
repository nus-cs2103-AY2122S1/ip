/**
 * Contains skeleton of Duke chatbot for IP of CS2103 2021
 *
 * @author: Ren Weilin
 * @version: 1.0 (19 August 2021)
 */

import java.util.ArrayList;
import  java.util.Scanner;

public class Duke {

    private ArrayList<String> commandList;

    private Duke() {
        commandList = new ArrayList<>(100);
    }

    public static void main(String[] args) {
        Duke instance = new Duke();

        //Greet
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
                for (int i = 1; i < instance.commandList.size() + 1; i++) {
                    System.out.printf("  %d. %s%n", i, instance.commandList.get(i - 1));
                }
                printBreakLine();
            } else {
                instance.commandList.add(command);
                printBreakLine();
                System.out.println("  added:" + command);
                printBreakLine();
            }
        }
    }


    private static void printBreakLine() {
        for (int i = 0; i < 12; i++) {
            System.out.print("==");
        }
        System.out.println();
    }
}
