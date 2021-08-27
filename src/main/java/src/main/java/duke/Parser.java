package src.main.java.duke;

import java.util.Scanner;

/**
 * Represents the driver of the chat bot and contains all operations to respond
 * to the user
 */

public class Parser {

    private UI ui;

    Parser(UI ui) {
        this.ui = ui;
    }

    /**
     * driver of the chat bot that decides the response
     *
     * @throws DukeException
     */
    void parse() throws DukeException {
        Scanner sc = new Scanner(System.in);
        String input = "";

        //loop to check if next input is available
        while (sc.hasNext()) {
            input = sc.nextLine();
            String[] split = input.split(" ", 2);

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                //exit command for when entered exit code
                System.exit(1);
            } else if (input.equals("list")) {
                ui.printList();
            } else if (split[0].equals("done")) {
                if (split.length < 2 || split[1].isEmpty()) {
                    throw new NoNumberException("☹ OOPS!!! No task number was given in the input");
                }
                ui.markAsDone(Integer.parseInt(split[1]));
            } else if (split[0].equals("delete")) {
                if (split.length < 2 || split[1].isEmpty()) {
                    throw new NoNumberException("☹ OOPS!!! No task number was given in the input");
                }
                ui.deleteTask(Integer.parseInt(split[1]));
            } else if (split[0].equals("todo")) {
                if (split.length < 2) {
                    throw new NoDescriptionException("☹ OOPS!!! The description of a " + split[0] + " cannot be empty.");
                }
                ui.createTodo(split[1]);
            } else if (split[0].equals("event")) {
                String time = split[1].split(" /at ", 2)[1];
                String task = split[1].split(" /at ", 2)[0];
                ui.createEvent(task, time);
            } else if (split[0].equals("deadline")) {
                String time = split[1].split(" /by ", 2)[1];
                String task = split[1].split(" /by ", 2)[0];
                ui.createDeadline(task, time);
            } else {
                throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
