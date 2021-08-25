package duke.util;

import duke.exception.DukeException;
import duke.util.List;

import java.util.Scanner;

public class Parser {
    private Scanner sc;
    private List list;

    public Parser(List list) {
        this.sc = new Scanner(System.in);
        this.list = list;
    }

    // extract int from done and delete prompts
    public static int extractInt(String input) throws DukeException {
        String[] parts = input.split(" ");//split along the whitespace to get the integer
        if (parts.length <= 1) {// checking for incomplete prompts
            throw new DukeException();
        }
        String numStr = parts[1];
        return Integer.valueOf(numStr);
    }
    public void execute() {
        String input = sc.nextLine(); // scan the line for the user's input

        boolean shouldContinue = true;
        while (shouldContinue) {//create loop for the chat
            try {
                if (input.startsWith("done ")) {
                    int numInt = extractInt(input);
                    list.setIndexDone(numInt);
                    input = sc.nextLine();
                    continue;
                } else if (input.startsWith("delete ")) {
                    int numInt = extractInt(input);
                    list.deleteTask(numInt);
                    input = sc.nextLine();
                    continue;
                } else if (input.startsWith("todo ")) {
                    String task = input.replaceFirst("todo ", "");//remove to-do from input
                    if (task.isBlank()) {//check for incomplete input
                        throw new DukeException();
                    }
                    list.addToDo(task);
                    input = sc.nextLine();
                    continue;
                } else if (input.startsWith("deadline ")) {
                    String task = input.replaceFirst("deadline ", "");//remove deadline from input
                    if (task.isBlank()) {//checking incomplete input
                        throw new DukeException();
                    }
                    String[] parts = task.split("/by ");//split string along keyword /by
                    list.addDeadline(parts[0], parts[1]);
                    input = sc.nextLine();
                    continue;
                } else if (input.startsWith("event ")) {
                    String task = input.replaceFirst("event ", "");//remove event from input
                    if (task.isBlank()) {//checking incomplete input
                        throw new DukeException();
                    }
                    String[] parts = task.split("/at");//split string along keyword /at
                    list.addEvent(parts[0], parts[1]);
                    input = sc.nextLine();
                    continue;
                }

                switch (input) {
                    case "bye":
                    case "Bye":
                    case "BYE":
                        System.out.println("Bye. Hope to see you again soon!");
                        shouldContinue = false;
                        break;
                    case "list":
                        list.show();
                        input = sc.nextLine();
                        break;
                    default:
                        throw new DukeException();
                }
            } catch (DukeException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                input = sc.nextLine();
            }
        }
        sc.close();
    }
}
