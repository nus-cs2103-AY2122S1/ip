/**
 * Contains skeleton of Duke chatbot for IP of CS2103 2021
 *
 * @author: Ren Weilin
 * @version: 19 August 2021
 */

import java.util.ArrayList;
import  java.util.Scanner;
import  java.time.LocalDate;

public class Duke {

    private final ArrayList<Task> commandList;

    private Duke() {
        commandList = new ArrayList<>(100);
    }

    public static void main(String[] args) {
        Duke instance = new Duke();
        instance.begin();
    }

    public void begin() throws DukeException{
        //TODO
        //Complete better exception handling

        //Greet
        welcomeGreet();

        //Echo
        Scanner scanner = new Scanner(System.in);
        while (true) {
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
                System.out.println("  Here are the tasks in your list:");
                for (int i = 1; i < commandList.size() + 1; i++) {
                    System.out.printf("  %d. %s%n", i, commandList.get(i - 1)).toString();
                }
                printBreakLine();

            } else if(command.contains("done")) {
                int index = Integer.parseInt(command.split(" ")[1]);
                printBreakLine();
                commandList.get(index - 1).markAsDone();
                printBreakLine();

            } else if(command.contains("delete")) {
                int index = Integer.parseInt(command.split(" ")[1]);
                printBreakLine();
                System.out.println("  Noted. I've removed this task:");
                System.out.println("  " + commandList.get(index - 1).toString());
                commandList.remove(index - 1);
                System.out.printf("  Now you have %d tasks in the list.%n", commandList.size());
                printBreakLine();

            } else if(command.contains("todo")){
                String description = command.split("/by ")[0].substring(5);
                if (description.isBlank()) {
                    printBreakLine();
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                Task newTask = new ToDo(description);
                commandList.add(newTask);
                addTaskMsg(newTask);

            } else if(command.contains("deadline")) {

                String description = command.split("/by ")[0].substring(9);
                String by = command.split("/by ")[1];
                LocalDate deadline  = LocalDate.parse(by);
                if (description.isBlank()) {
                    printBreakLine();
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                }

                Task newTask = new Deadline(description, deadline);
                commandList.add(newTask);
                addTaskMsg(newTask);

            } else if(command.contains("event")) {
                String description = command.split("/at ")[0].substring(6);
                String by = command.split("/at ")[1];
                LocalDate eventDate  = LocalDate.parse(by);
                if (description.isBlank()) {
                    printBreakLine();
                    throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                }

                Task newTask = new Event(description, eventDate);
                commandList.add(newTask);
                addTaskMsg(newTask);

            } else {
                printBreakLine();
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    private void printBreakLine() {
        for (int i = 0; i < 12; i++) {
            System.out.print("==");
        }
        System.out.println();
    }

    private void addTaskMsg(Task newTask) {
        printBreakLine();
        System.out.println("  Got it. I've added this task: ");
        System.out.println("  " + newTask.toString());
        System.out.printf("  Now you have %d tasks in the list.%n", commandList.size());
        printBreakLine();
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
