import tiger.actions.AppState;
import tiger.actions.ByeAction;
import tiger.actions.DeadLineAction;
import tiger.actions.DeleteAction;
import tiger.actions.EventAction;
import tiger.actions.InvalidAction;
import tiger.actions.ListAction;
import tiger.actions.MarkDoneAction;
import tiger.actions.ToDoAction;

import tiger.components.TaskList;

import tiger.exceptions.TigerEmptyStringException;
import tiger.exceptions.TigerInvalidArgumentException;

import tiger.exceptions.TigerStorageInitException;
import tiger.exceptions.TigerStorageLoadException;
import tiger.parser.CommandParser;
import tiger.parser.DeadLineCommand;
import tiger.parser.DeleteCommand;
import tiger.parser.DoneCommand;
import tiger.parser.EventCommand;
import tiger.parser.ToDoCommand;
import tiger.storage.Storage;

import java.util.Scanner;

public class Tiger {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String greetingMessage = "Hello! I'm Tiger :)\nWhat can I do for you?";
        TaskList taskList = new TaskList();
        System.out.println(greetingMessage);

        AppState applicationState = new AppState(false, taskList);
        while (!applicationState.isExited()) {
            String userInput = scanner.nextLine();
            CommandParser command = new CommandParser(userInput);
            switch (command.getCommand()) {
            case "bye":
                ByeAction byeAction = new ByeAction(applicationState);
                applicationState = byeAction.run();
                break;
            case "list":
                ListAction listAction = new ListAction(applicationState);
                applicationState = listAction.run();
                break;
            case "done":
                try {
                    DoneCommand doneCommand = new DoneCommand(userInput);
                    MarkDoneAction markDoneAction = new MarkDoneAction(applicationState, doneCommand.index - 1);
                    applicationState = markDoneAction.run();
                } catch (TigerEmptyStringException e) {
                    System.out.println(e);
                } catch (TigerInvalidArgumentException e) {
                    System.out.println(e);
                }
                break;
            case "delete":
                try {
                    DeleteCommand deleteCommand = new DeleteCommand(userInput);
                    DeleteAction deleteAction = new DeleteAction(applicationState, deleteCommand.index - 1);
                    applicationState = deleteAction.run();
                } catch (TigerEmptyStringException e) {
                    System.out.println(e.toString());
                } catch (TigerInvalidArgumentException e) {
                    System.out.println(e.toString());
                }
                break;
            case "todo":
                try {
                    ToDoCommand toDoCommand = new ToDoCommand(userInput);
                    ToDoAction toDoAction = new ToDoAction(applicationState, toDoCommand.todo);
                    applicationState = toDoAction.run();
                } catch (TigerEmptyStringException e) {
                    System.out.println(e);
                }
                break;
            case "deadline":
                try {
                    DeadLineCommand deadLineCommand = new DeadLineCommand(userInput);
                    DeadLineAction deadLineAction = new DeadLineAction(applicationState, deadLineCommand.todo, deadLineCommand.dateLine);
                    applicationState = deadLineAction.run();
                } catch (TigerEmptyStringException e) {
                    System.out.println(e);
                }
                break;
            case "event":
                try {
                    EventCommand eventCommand = new EventCommand(userInput);
                    EventAction eventAction = new EventAction(applicationState, eventCommand.todo,
                            eventCommand.eventAt);
                    applicationState = eventAction.run();
                } catch (TigerEmptyStringException e) {
                    System.out.println(e);
                }
                break;
            case "save":
                try {
                    new Storage().save(taskList);
                } catch (TigerStorageInitException e) {
                    System.out.println(e);
                }
                break;
            case "load":
                try {
                    taskList = new Storage().load();
                    applicationState = new AppState(applicationState.isExited(), taskList);
                } catch (TigerStorageLoadException e) {
                    System.out.println(e);
                }
                break;
            default:
                InvalidAction invalidAction = new InvalidAction(applicationState);
                applicationState = invalidAction.run();
                break;
            }
        }
        if (applicationState.isExited()) {
            String goodbyeMessage = "Bye. Hope to see you again soon!";
            System.out.println(goodbyeMessage);
        }
    }
}
