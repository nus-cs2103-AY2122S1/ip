package duke.general;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DoneCommand;
import duke.command.ListCommand;
import duke.command.DeleteCommand;
import duke.command.ByeCommand;
import duke.command.ErrorCommand;

import java.util.Scanner;

public class Parser {
    public Command parse(String fullCommand) {
        String[] inputSplit = fullCommand.split(" ", 2);
        Command c = null;
        try {
            switch(inputSplit[0]) {
            case "todo":
                c = new AddCommand(TaskType.TODO, inputSplit);
                break;
            case "deadline":
                c = new AddCommand(TaskType.DEADLINE, inputSplit);
                break;
            case "event":
                c = new AddCommand(TaskType.EVENT, inputSplit);
                break;
            case "list":
                c = new ListCommand();
                break;
            case "done":
                c = new DoneCommand(inputSplit);
                break;
            case "delete":
                c = new DeleteCommand(inputSplit);
                break;
            case "bye":
                c = new ByeCommand();
                break;
            default:
                c = new ErrorCommand();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
}