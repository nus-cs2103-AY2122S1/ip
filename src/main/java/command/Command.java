package command;

import alice.exceptions.AliceException;
import command.exceptions.EmptyDescriptionException;
import command.exceptions.EmptyIndexException;
import command.exceptions.EmptyTaggerException;
import command.exceptions.InvalidArgumentException;
import command.exceptions.InvalidIndexException;
import command.exceptions.InvalidTimeFormatException;
import dialog.exceptions.DialogException;
import dialog.TaskDialog;

import parser.Parser;

import storage.Storage;

import task.Deadline;
import task.Event;
import task.Todo;


import ui.ChatPage;
import ui.Ui;

import java.io.IOException;

/**
 * Command class responsible for executing different command
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.02
 * @since 0.01
 */
public class Command {
    /**
     * Different type of Command available
     */
    public enum CommandType {
        TODO, DEADLINE, EVENT, LIST, DATE, FIND, DONE, DELETE, COMMANDS, BYE
    }

    private String fullCommand;
    private CommandType commandType;
    private boolean isExit;

    /**
     * Default constructor for Command.
     * Create a Command object from given full Command
     *
     * @param fullCommand command with its arguments and tagger
     * @throws AliceException invalid argument for command
     */
    public Command(String fullCommand) {
        this.fullCommand = fullCommand;
        commandType = Parser.stringToCommand(fullCommand.split(" ")[0]);
        isExit = commandType == CommandType.BYE;
    }

    /**
     * Return whether the command given is of type Bye.
     * The program is expected to exit if it runs on command line console.
     *
     * @return if the command is bye or not
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Execute the command according to its type
     *
     * @param taskDialog taskDialog for the command to interact with
     * @param storage    the storage for the command to interact with
     * @throws DialogException dialog cannot have the same id while the app is running
     * @throws IOException     if there is any error dealing with the system IO
     */
    public void execute(TaskDialog taskDialog, Storage storage) {
        switch (commandType) {
        case LIST:
            executeList(taskDialog);
            break;
        case DATE:
            executeDate(taskDialog);
            break;
        case FIND:
            executeFind(taskDialog);
            break;
        case TODO:
            executeTodo(taskDialog);
            break;
        case DEADLINE:
            executeDeadline(taskDialog);
            break;
        case EVENT:
            executeEvent(taskDialog);
            break;
        case DONE:
            executeDone(taskDialog);
            break;
        case DELETE:
            executeDelete(taskDialog);
            break;
        case COMMANDS:
            executeCommands(taskDialog);
            break;
        case BYE:
            executeBye(taskDialog, storage);
        default:
            // UNREACHABLE: already checked via StringToCommand
            break;
        }
    }

    private void executeList(TaskDialog taskDialog) {
        ChatPage chatPage = taskDialog.getChatPage();
        chatPage.showCurrentList();
    }

    private void executeDate(TaskDialog taskDialog) {
        ChatPage chatPage = taskDialog.getChatPage();
        try {
            if (fullCommand.split(" ").length == 1) {
                throw new EmptyDescriptionException("The date of deadline cannot be empty.");
            }
            String dlString = fullCommand.substring(("date ").length());
            chatPage.printAlicely(taskDialog.getFromDeadline(dlString).toString());
        } catch (EmptyDescriptionException | EmptyTaggerException | InvalidTimeFormatException e) {
            chatPage.printError(e);
        }
    }

    private void executeFind(TaskDialog taskDialog) {
        ChatPage chatPage = taskDialog.getChatPage();
        try {
            if (fullCommand.split(" ").length == 1) {
                throw new EmptyDescriptionException("The keyword of find cannot be empty. Try using command " +
                        "'list' if you want to see the full list");
            }
            String kwString = fullCommand.substring(("find ").length());

            chatPage.printAlicely(taskDialog.getFromKeyword(kwString).toString());
        } catch (EmptyDescriptionException | EmptyTaggerException | InvalidTimeFormatException e) {
            chatPage.printError(e);
        }
    }

    private void executeTodo(TaskDialog taskDialog) {
        ChatPage chatPage = taskDialog.getChatPage();
        try {
            if (fullCommand.split(" ").length == 1) {
                throw new EmptyDescriptionException("The description of a todo cannot be empty.");
            }
            taskDialog.addTask(new Todo(fullCommand.substring(("todo ").length())));
        } catch (DialogException | EmptyDescriptionException e) {
            chatPage.printError(e);
        }
    }

    private void executeDeadline(TaskDialog taskDialog) {
        ChatPage chatPage = taskDialog.getChatPage();
        try {
            if (fullCommand.split(" ").length == 1) {
                throw new EmptyDescriptionException("The description of a deadline cannot be empty.");
            } else if (!fullCommand.contains("/by")) {
                throw new EmptyTaggerException("No /by tagger found.");
            }
            String dDescription = fullCommand.substring(("deadline ").length(), fullCommand.indexOf("/"));
            String by = fullCommand.substring(fullCommand.indexOf("/by ") + "/by ".length());
            taskDialog.addTask(new Deadline(dDescription, by));
        } catch (DialogException | EmptyDescriptionException
                | EmptyTaggerException | InvalidTimeFormatException e) {
            chatPage.printError(e);
        }
    }

    private void executeEvent(TaskDialog taskDialog) {
        ChatPage chatPage = taskDialog.getChatPage();
        try {
            if (fullCommand.split(" ").length == 1) {
                throw new EmptyDescriptionException("The description of an event cannot be empty.");
            } else if (!fullCommand.contains("/at")) {
                throw new EmptyTaggerException("No /at tagger found.");
            }
            String eDescription = fullCommand.substring(("event ").length(), fullCommand.indexOf("/"));
            String at = fullCommand.substring(fullCommand.indexOf("/at ") + "/at ".length());
            taskDialog.addTask(new Event(eDescription, at));
        } catch (DialogException | EmptyDescriptionException
                | EmptyTaggerException | InvalidTimeFormatException e) {
            chatPage.printError(e);
        }
    }

    private void executeDone(TaskDialog taskDialog) {
        ChatPage chatPage = taskDialog.getChatPage();
        try {
            if (fullCommand.split(" ").length == 1) {
                throw new EmptyIndexException("The index of done cannot be empty.");
            } else if (Integer.parseInt(fullCommand.split(" ")[1]) <= 0 ||
                    Integer.parseInt(fullCommand.split(" ")[1]) > taskDialog.getTaskList().length()) {
                if (taskDialog.getTaskList().length() == 0) {
                    throw new InvalidIndexException("Looks like your list is currently empty.");
                } else {
                    throw new InvalidIndexException("Your list index can only be from 1 to "
                            + taskDialog.getTaskList().length() + ".");
                }
            } else if (fullCommand.split(" ").length > 2) {
                throw new InvalidArgumentException("The number of arguments seems to exceed for command done.");
            }
            taskDialog.markTaskAsDone(Integer.parseInt(fullCommand.substring(("done ").length())) - 1);
        } catch (EmptyIndexException | InvalidArgumentException | DialogException | InvalidIndexException e) {
            chatPage.printError(e);
        }
    }

    private void executeDelete(TaskDialog taskDialog) {
        ChatPage chatPage = taskDialog.getChatPage();
        try {
            if (fullCommand.split(" ").length == 1) {
                throw new EmptyIndexException("The index of delete cannot be empty.");
            } else if (Integer.parseInt(fullCommand.split(" ")[1]) <= 0
                    || Integer.parseInt(fullCommand.split(" ")[1]) > taskDialog.getTaskList().length()) {
                if (taskDialog.getTaskList().length() == 0) {
                    throw new InvalidIndexException("Looks like your list is currently empty.");
                } else {
                    throw new InvalidIndexException("Your list index can only be from 1 to "
                            + taskDialog.getTaskList().length() + ".");
                }
            } else if (fullCommand.split(" ").length > 2) {
                throw new InvalidArgumentException("The number of arguments seems to exceed for command delete.");
            }
            taskDialog.deleteTaskByIndex(Integer.parseInt(fullCommand.substring(("delete ").length())) - 1);
        } catch (EmptyIndexException | InvalidArgumentException | DialogException | InvalidIndexException e) {
            chatPage.printError(e);
        }
    }

    private void executeCommands(TaskDialog taskDialog) {
        ChatPage chatPage = taskDialog.getChatPage();
        try {
            chatPage.printAlicely(Ui.getCommandListText());
        } catch (DialogException e) {
            chatPage.printError(e);
        }
    }

    private void executeBye(TaskDialog taskDialog, Storage storage) {
        ChatPage chatPage = taskDialog.getChatPage();
        try {
            storage.save(taskDialog.getTaskList());
            chatPage.exit();
        } catch (IOException e) {
            chatPage.printError(e);
        }
    }


}
