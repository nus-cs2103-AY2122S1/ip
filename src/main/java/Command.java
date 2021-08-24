abstract class Command {

    enum CMDTYPE {TODO, DEADLINE, EVENT, DONE, DELETE, LIST};

    abstract void execute(Storage storage, TaskList taskList) throws InvalidFormatException, TimeNotSpecifiedException, EmptyTaskDescriptionException, InvalidIndexException, TaskAlreadyDoneException, InvalidInputException, IndexNotSpecifiedException;

}

