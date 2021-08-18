package commands;

import tasks.TaskList;
import utils.Util;

import java.util.Arrays;
import java.util.List;

public abstract class Command {
    protected List<String> userInputList;
    protected TaskList newTaskList;
    protected String newLog = "";

    public Command(String userInput) {
        this.userInputList = Arrays.asList(userInput.split(" "));
    }

    public abstract void updateLogAndTaskList(TaskList oldTaskList);

    public TaskList getTaskList() {
        return this.newTaskList;
    }

    public String getLog() {
        return this.newLog;
    }

    public enum ValidCommand {
        DONE,
        DELETE,
        LIST,
        DEADLINE,
        TODO,
        EVENT,
        INVALID,
        BYE
    }

    public static Command of(String userInput) {
        List<String> userInputList = Arrays.asList(userInput.split(" "));
        String userCommandString = userInputList.get(0);

        ValidCommand command;

        try {
            if (Util.isLowerCase(userCommandString)) {
                command = ValidCommand.valueOf(userCommandString.toUpperCase());
            } else {
                command = ValidCommand.INVALID;
            }
        } catch (IllegalArgumentException e) {
            command = ValidCommand.INVALID;
        }

        if (command == ValidCommand.DONE) {
            return new DoneCommand(userInput);
        } else if (command == ValidCommand.DELETE) {
            return new DeleteCommand(userInput);
        } else if (command == ValidCommand.LIST) {
            return new ListCommand(userInput);
        } else if (command == ValidCommand.DEADLINE) {
            return new DeadlineCommand(userInput);
        } else if (command == ValidCommand.TODO) {
            return new TodoCommand(userInput);
        } else if (command == ValidCommand.EVENT) {
            return new EventCommand(userInput);
        } else if (command == ValidCommand.INVALID) {
            return new InvalidCommand();
        } else if (command == ValidCommand.BYE){
            System.out.println("Bye. Hope to see you again soon!");
            System.exit(0);
        }
        return null;
    }
}