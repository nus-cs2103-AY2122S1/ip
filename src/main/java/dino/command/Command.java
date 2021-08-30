package dino.command;

import dino.exception.*;
import dino.task.TaskList;
import dino.util.Storage;

/**
 * Contains a set of instructions that can be understood and executed by the ChatBot
 */
public abstract class Command {

    public enum CMDTYPE {TODO, DEADLINE, EVENT, DONE, DELETE, LIST, FIND}

    abstract public void execute(Storage storage, TaskList taskList) throws InvalidFormatException, TimeNotSpecifiedException, EmptyTaskDescriptionException, InvalidIndexException, TaskAlreadyDoneException, InvalidInputException, IndexNotSpecifiedException, EmptyListException, TaskNotFoundException;

}

