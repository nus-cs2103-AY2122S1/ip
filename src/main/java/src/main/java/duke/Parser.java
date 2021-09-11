package src.main.java.duke;

import javafx.scene.image.Image;

import java.util.Scanner;

/**
 * Represents the driver of the chat bot and contains all operations to respond
 * to the user
 */

public class Parser {

    private UI ui;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    Parser(UI ui) {
        this.ui = ui;
    }

    /**
     * driver of the chat bot that decides the response
     *
     * @throws DukeException
     */
    String parse(String text) throws DukeException {
        Scanner sc = new Scanner(System.in);
        String input = "";

        //loop to check if next input is available
        //while (sc.hasNext()) {
        input = text;
        assert input != null : "input cannot be null";
        String[] split = input.split(" ", 2);

        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            //exit command for when entered exit code
            return "Bye. Hope to see you again soon!";
        } else if (input.equals("list")) {
            return ui.printList();
        } else if (split[0].equals("done")) {
            if (split.length < 2 || split[1].isEmpty()) {
                throw new NoNumberException("☹ OOPS!!! No task number was given in the input");
            }
            return ui.markAsDone(Integer.parseInt(split[1]));
        } else if (split[0].equals("delete")) {
            if (split.length < 2 || split[1].isEmpty()) {
                throw new NoNumberException("☹ OOPS!!! No task number was given in the input");
            }
            return ui.deleteTask(Integer.parseInt(split[1]));
        } else if (split[0].equals("find")) {
            return ui.find(split[1]);
        } else if (split[0].equals("todo")) {
            System.out.println("does todo work");
            if (split.length < 2) {
                throw new NoDescriptionException("☹ OOPS!!! The description of a " + split[0] + " cannot be empty.");
            }
            return ui.createTodo(split[1]);
        } else if (split[0].equals("event")) {
            String time = split[1].split(" /at ", 2)[1];
            String task = split[1].split(" /at ", 2)[0];
            return ui.createEvent(task, time);
        } else if (split[0].equals("deadline")) {
            String time = split[1].split(" /by ", 2)[1];
            String task = split[1].split(" /by ", 2)[0];
            return ui.createDeadline(task, time);
        } else if (split[0].equals("update")) {
            String task = split[1].split(" /to ", 2)[0];
            String time = split[1].split(" /to ", 2)[1];
            return ui.update(task, time);
        }
        //}
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
