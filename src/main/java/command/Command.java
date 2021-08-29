package command;

import alice.AliceException;
import dialog.DialogException;
import dialog.TaskDialog;

import parser.Parser;

import storage.Storage;

import task.Deadline;
import task.Event;
import task.Todo;


import ui.Ui;

import java.io.IOException;

public class Command {
    public enum CommandType {
        TODO, DEADLINE, EVENT, LIST, DATE, FIND, DONE, DELETE, COMMANDS, BYE
    }

    private String fullCommand;
    private CommandType commandType;
    private boolean isExit;

    public Command(String fullCommand) throws AliceException {
        this.fullCommand = fullCommand;
        commandType = Parser.stringToCommand(fullCommand.split(" ")[0]);
        isExit = commandType == CommandType.BYE;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public void execute(TaskDialog taskDialog, Storage storage) throws DialogException, IOException {

            switch (commandType) {
                case LIST:
                    System.out.println(taskDialog);
                    break;
                case DATE:
                    try {
                        if (fullCommand.split(" ").length == 1) {
                            throw new EmptyDescriptionException("The date of deadline cannot be empty.");
                        }
                        String dlString = fullCommand.substring(("date ").length());
                        System.out.println(taskDialog.by(dlString));
                    } catch (EmptyDescriptionException | EmptyTaggerException | InvalidTimeFormatException e) {
                        Ui.printError(e);
                    }
                    break;
                case FIND:
                    try {
                        if (fullCommand.split(" ").length == 1) {
                            throw new EmptyDescriptionException("The keyword of find cannot be empty. Try using command " +
                                    "'list' if you want to see the full list");
                        }
                        String kwString = fullCommand.substring(("find ").length());
                        System.out.println(taskDialog.with(kwString));
                    } catch (EmptyDescriptionException | EmptyTaggerException | InvalidTimeFormatException e) {
                        Ui.printError(e);
                    }
                    break;
                case TODO: {
                    try {
                        if (fullCommand.split(" ").length == 1) {
                            throw new EmptyDescriptionException("The description of a todo cannot be empty.");
                        }
                        taskDialog.addTask(new Todo(fullCommand.substring(("todo ").length())));
                    } catch (DialogException | EmptyDescriptionException e) {
                        Ui.printError(e);
                    }
                    break;
                }
                case DEADLINE: {
                    try {
                        if (fullCommand.split(" ").length == 1) {
                            throw new EmptyDescriptionException("The description of a deadline cannot be empty.");
                        } else if (!fullCommand.contains("/by")) {
                            throw new EmptyTaggerException("No /by tagger found.");
                        }
                        String dDescription = fullCommand.substring(("deadline ").length(), fullCommand.indexOf("/"));
                        String by = fullCommand.substring(fullCommand.indexOf("/by ") + "/by ".length());
                        taskDialog.addTask(new Deadline(dDescription, by));
                    } catch (DialogException | EmptyDescriptionException | EmptyTaggerException | InvalidTimeFormatException e) {
                        Ui.printError(e);
                    }
                    break;
                }
                case EVENT: {
                    try {
                        if (fullCommand.split(" ").length == 1) {
                            throw new EmptyDescriptionException("The description of an event cannot be empty.");
                        } else if (!fullCommand.contains("/at")) {
                            throw new EmptyTaggerException("No /at tagger found.");
                        }
                        String eDescription = fullCommand.substring(("event ").length(), fullCommand.indexOf("/"));
                        String at = fullCommand.substring(fullCommand.indexOf("/at ") + "/at ".length());
                        taskDialog.addTask(new Event(eDescription, at));
                    } catch (DialogException | EmptyDescriptionException | EmptyTaggerException | InvalidTimeFormatException e) {
                        Ui.printError(e);
                    }
                    break;
                }
                case DONE: {
                    try {
                        if (fullCommand.split(" ").length == 1) {
                            throw new EmptyIndexException("The index of done cannot be empty.");
                        } else if (Integer.parseInt(fullCommand.split(" ")[1]) <= 0 || Integer.parseInt(fullCommand.split(" ")[1]) > taskDialog.getTaskList().length()) {
                            if (taskDialog.getTaskList().length() == 0) {
                                throw new InvalidIndexException("Looks like your list is currently empty.");
                            } else {
                                throw new InvalidIndexException("Your list index can only be from 1 to " + taskDialog.getTaskList().length() + ".");
                            }
                        } else if (fullCommand.split(" ").length > 2) {
                            throw new InvalidArgumentException("The number of arguments seems to exceed for command done.");
                        }
                        taskDialog.markTaskAsDone(Integer.parseInt(fullCommand.substring(("done ").length())) - 1);
                    } catch (EmptyIndexException | InvalidArgumentException | InvalidIndexException e) {
                        Ui.printError(e);
                    }
                    break;
                }
                case DELETE: {
                    try {
                        if (fullCommand.split(" ").length == 1) {
                            throw new EmptyIndexException("The index of delete cannot be empty.");
                        } else if (Integer.parseInt(fullCommand.split(" ")[1]) <= 0 || Integer.parseInt(fullCommand.split(" ")[1]) > taskDialog.getTaskList().length()) {
                            if (taskDialog.getTaskList().length() == 0) {
                                throw new InvalidIndexException("Looks like your list is currently empty.");
                            } else {
                                throw new InvalidIndexException("Your list index can only be from 1 to " + taskDialog.getTaskList().length() + ".");
                            }
                        } else if (fullCommand.split(" ").length > 2) {
                            throw new InvalidArgumentException("The number of arguments seems to exceed for command delete.");
                        }
                        taskDialog.deleteTaskByIndex(Integer.parseInt(fullCommand.substring(("delete ").length())) - 1);
                    } catch (EmptyIndexException | InvalidArgumentException | InvalidIndexException e) {
                        Ui.printError(e);
                    }
                    break;
                }
                case COMMANDS:
                    Ui.showCommandList();
                    break;
                case BYE:
                    storage.save(taskDialog.getTaskList());
                    Ui.showGoodBye();
                default:
                    // UNREACHABLE: already checked via StringToCommand
                    break;
            }
    }
}
