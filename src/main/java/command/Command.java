package command;

import alice.exceptions.AliceException;
import command.exceptions.EmptyDescriptionException;
import command.exceptions.EmptyIndexException;
import command.exceptions.EmptyTaggerException;
import command.exceptions.InvalidArgumentException;
import command.exceptions.InvalidIndexException;
import command.exceptions.InvalidTimeFormatException;
import dialog.TaskDialog;
import dialog.exceptions.DialogException;
import model.task.Deadline;
import model.task.Event;
import model.task.Todo;
import model.vocab.DuplicateVocabException;
import model.vocab.VocabList;
import parser.Parser;
import storage.Storage;
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
        TODO, DEADLINE, EVENT, LIST, DATE, FIND, DONE, DELETE, LEARN, UNLEARN, COMMANDS, BYE
    }

    private final String fullCommand;
    private final CommandType commandType;

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
    }

    /**
     * Execute the command according to its type
     *
     * @param ui      the ui in which the command object is going to use to print output
     * @param storage the storage for the command to save progress
     */
    public void execute(Ui ui, Storage storage) {
        switch (commandType) {
        case LIST:
            executeList(ui);
            break;
        case DATE:
            executeDate(ui);
            break;
        case FIND:
            executeFind(ui);
            break;
        case TODO:
            executeTodo(ui);
            break;
        case DEADLINE:
            executeDeadline(ui);
            break;
        case EVENT:
            executeEvent(ui);
            break;
        case DONE:
            executeDone(ui);
            break;
        case DELETE:
            executeDelete(ui);
            break;
        case LEARN:
            executeLearn(ui);
            break;
        case UNLEARN:
            executeUnlearn(ui);
            break;
        case COMMANDS:
            executeCommands(ui);
            break;
        case BYE:
            executeBye(ui, storage);
        default:
            // UNREACHABLE: already checked via StringToCommand
            break;
        }
    }

    private void executeList(Ui ui) {
        ui.getChatPage().showCurrentList();
    }

    private void executeDate(Ui ui) {
        ChatPage chatPage = ui.getChatPage();
        try {
            if (fullCommand.split(" ").length == 1) {
                throw new EmptyDescriptionException("The date of deadline cannot be empty.");
            }
            String dlString = fullCommand.substring(("date ").length());
            chatPage.printWithAlice(ui.getTaskDialog().getFromDeadline(dlString).toString());
        } catch (EmptyDescriptionException | EmptyTaggerException | InvalidTimeFormatException e) {
            chatPage.printError(e);
        }
    }

    private void executeFind(Ui ui) {
        ChatPage chatPage = ui.getChatPage();
        try {
            if (fullCommand.split(" ").length == 1) {
                throw new EmptyDescriptionException("The keyword of find cannot be empty. Try using command " +
                        "'list' if you want to see the full list");
            }
            String kwString = fullCommand.substring(("find ").length());

            chatPage.printWithAlice(ui.getTaskDialog().getFromKeyword(kwString).toString());
        } catch (EmptyDescriptionException | EmptyTaggerException | InvalidTimeFormatException e) {
            chatPage.printError(e);
        }
    }

    private void executeTodo(Ui ui) {
        ChatPage chatPage = ui.getChatPage();
        try {
            if (fullCommand.split(" ").length == 1) {
                throw new EmptyDescriptionException("The description of a todo cannot be empty.");
            }
            chatPage.printWithAlice(ui.getTaskDialog().addTask(new Todo(fullCommand.substring(("todo ").length()))));
        } catch (DialogException | EmptyDescriptionException e) {
            chatPage.printError(e);
        }
    }

    private void executeDeadline(Ui ui) {
        ChatPage chatPage = ui.getChatPage();
        try {
            if (fullCommand.split(" ").length == 1) {
                throw new EmptyDescriptionException("The description of a deadline cannot be empty.");
            } else if (!fullCommand.contains("/by")) {
                throw new EmptyTaggerException("No /by tagger found.");
            }
            String dDescription = fullCommand.substring(("deadline ").length(), fullCommand.indexOf("/"));
            String by = fullCommand.substring(fullCommand.indexOf("/by ") + "/by ".length());
            chatPage.printWithAlice(ui.getTaskDialog().addTask(new Deadline(dDescription, by)));
        } catch (DialogException | EmptyDescriptionException
                | EmptyTaggerException | InvalidTimeFormatException e) {
            chatPage.printError(e);
        }
    }

    private void executeEvent(Ui ui) {
        ChatPage chatPage = ui.getChatPage();
        try {
            if (fullCommand.split(" ").length == 1) {
                throw new EmptyDescriptionException("The description of an event cannot be empty.");
            } else if (!fullCommand.contains("/at")) {
                throw new EmptyTaggerException("No /at tagger found.");
            }
            String eDescription = fullCommand.substring(("event ").length(), fullCommand.indexOf("/"));
            String at = fullCommand.substring(fullCommand.indexOf("/at ") + "/at ".length());
            chatPage.printWithAlice(ui.getTaskDialog().addTask(new Event(eDescription, at)));
        } catch (DialogException | EmptyDescriptionException
                | EmptyTaggerException | InvalidTimeFormatException e) {
            chatPage.printError(e);
        }
    }

    private boolean isValidListIndexFormat(String fullCommand, TaskDialog taskDialog) {
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
        return true;
    }

    private void executeDone(Ui ui) {
        ChatPage chatPage = ui.getChatPage();
        TaskDialog taskDialog = ui.getTaskDialog();
        try {
            if (isValidListIndexFormat(fullCommand, taskDialog)) {
                chatPage.printWithAlice(taskDialog.markTaskAsDone(Integer.parseInt(
                        fullCommand.substring(("done ").length())) - 1));
            }
        } catch (EmptyIndexException | InvalidArgumentException | DialogException | InvalidIndexException e) {
            chatPage.printError(e);
        }
    }

    private void executeDelete(Ui ui) {
        ChatPage chatPage = ui.getChatPage();
        TaskDialog taskDialog = ui.getTaskDialog();
        try {
            if (isValidListIndexFormat(fullCommand, taskDialog)) {
                chatPage.printWithAlice(taskDialog.deleteTaskByIndex(Integer.parseInt(
                        fullCommand.substring(("delete ").length())) - 1));
            }
        } catch (EmptyIndexException | InvalidArgumentException | DialogException | InvalidIndexException e) {
            chatPage.printError(e);
        }
    }

    private void executeLearn(Ui ui) {
        ChatPage chatPage = ui.getChatPage();
        try {
            if (fullCommand.split(" ").length == 1) {
                throw new EmptyDescriptionException("The phrase for me to learn cannot be empty");
            }
            String phraseString = fullCommand.substring(("learn ").length());
            VocabList vocabList = chatPage.getAlice().getVocabList();
            if (VocabList.isDefaultPhrase(phraseString)) {
                throw new DuplicateVocabException(phraseString + " is Alice's untouchable phrase!!, please choose "
                        + "other phrase for Alice to learn");
            } else if (vocabList.containsPhrase(phraseString)) {
                throw new DuplicateVocabException(phraseString + " is what Alice already know!!, please choose "
                        + "other phrase for Alice to learn");
            }

            chatPage.printWithAlice(Ui.getAskForFeedbackText(phraseString));
            chatPage.getAlice().setPhraseToLearn(phraseString);
            chatPage.setMode(ChatPage.Mode.LEARN);
        } catch (EmptyDescriptionException | DialogException | DuplicateVocabException  e) {
            chatPage.printError(e);
        }
    }

    private void executeUnlearn(Ui ui) {
        ChatPage chatPage = ui.getChatPage();
        try {
            if (fullCommand.split(" ").length == 1) {
                throw new EmptyDescriptionException("The phrase for me to unlearn cannot be empty");
            }
            String phraseString = fullCommand.substring(("unlearn ").length());
            VocabList vocabList = chatPage.getAlice().getVocabList();
            if (VocabList.isDefaultPhrase(phraseString)) {
                throw new DuplicateVocabException(phraseString + " is Alice's untouchable phrase!!, please choose "
                        + "other phrase for Alice to unlearn");
            } else if (!vocabList.containsPhrase(phraseString)) {
                throw new DuplicateVocabException(phraseString + " is what Alice doesn't know!!, please choose "
                        + "other phrase for Alice to unlearn");
            }

            vocabList.removePhrase(phraseString);
            chatPage.printWithAlice(Ui.getUnlearnText(phraseString));
        } catch (EmptyDescriptionException | DialogException | DuplicateVocabException  e) {
            chatPage.printError(e);
        }
    }

    private void executeCommands(Ui ui) {
        ChatPage chatPage = ui.getChatPage();
        try {
            chatPage.printWithAlice(Ui.getCommandListText());
        } catch (DialogException e) {
            chatPage.printError(e);
        }
    }

    private void executeBye(Ui ui, Storage storage) {
        ChatPage chatPage = ui.getChatPage();
        try {
            storage.save(ui.getTaskDialog().getTaskList());
            chatPage.exit();
        } catch (IOException e) {
            chatPage.printError(e);
        }
    }


}
