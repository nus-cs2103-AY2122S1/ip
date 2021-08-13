import commands.*;
import components.TaskList;
import utils.InputParser;
import components.ToDo;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String greetingMessage = "Hello! I'm Tiger :)\nWhat can I do for you?";
        TaskList taskList = new TaskList();
        System.out.println(greetingMessage);

        boolean userExit = false;
        AppState applicationState = new AppState(userExit, taskList);
        while (!applicationState.isExited()) {
            String userInput = scanner.nextLine();
            InputParser inputParser = new InputParser(userInput);
            String command = inputParser.getCommand();
            String otherArguments = inputParser.getOtherArguments();
            switch (command) {
                case "bye":
                    ByeCommand byeCommand = new ByeCommand(applicationState);
                    applicationState = byeCommand.run();
                    break;
                case "list":
                    ListCommand listCommand = new ListCommand(applicationState);
                    applicationState = listCommand.run();
                    break;
                case "done":
                    if (otherArguments.equals("")) {
                        System.out.println("Please re-enter the command, with the index of the task to be marked as " +
                                "done.");
                    } else {
                        int index = Integer.parseInt(otherArguments.replaceAll(" ", ""));
                        MarkDoneCommand markDoneCommand = new MarkDoneCommand(applicationState, index - 1);
                        applicationState = markDoneCommand.run();
                    }
                    break;
                case "todo":
                    if (otherArguments.equals("")) {
                        System.out.println("Please re-enter the command, with the todo you want to track.");
                    } else {
                        ToDoCommand toDoCommand = new ToDoCommand(applicationState, otherArguments);
                        applicationState = toDoCommand.run();
                    }
                    break;
                default:
                    System.out.println("Please enter a valid command.");
            }
        }
        if (userExit) {
            String goodbyeMessage = "Bye. Hope to see you again soon!";
            System.out.println(goodbyeMessage);
        }
    }
}
