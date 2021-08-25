package dino.command;

import dino.util.Storage;
import dino.task.TaskList;
import dino.exception.*;

/**
 * Contains a set of instructions that can be understood and executed by the ChatBot
 */
abstract public class Command {

    public enum CMDTYPE {TODO, DEADLINE, EVENT, DONE, DELETE, LIST, FIND};

    abstract public void execute(Storage storage, TaskList taskList) throws InvalidFormatException, TimeNotSpecifiedException, EmptyTaskDescriptionException, InvalidIndexException, TaskAlreadyDoneException, InvalidInputException, IndexNotSpecifiedException, EmptyListException, TaskNotFoundException;

}

