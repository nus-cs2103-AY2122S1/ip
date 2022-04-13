package dino.command;

import dino.exception.*;
import dino.task.TaskList;
import dino.util.Storage;

/**
 * Contains a set of instructions that can be understood and executed by the ChatBot
 */
public abstract class Command {

    abstract public String execute(Storage storage, TaskList taskList) throws InvalidFormatException, TimeNotSpecifiedException, EmptyTaskDescriptionException, InvalidIndexException, TaskAlreadyDoneException, InvalidInputException, IndexNotSpecifiedException, EmptyListException, TaskNotFoundException;

}

