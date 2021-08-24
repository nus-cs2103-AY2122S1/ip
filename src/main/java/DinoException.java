public class DinoException extends Exception {
    public DinoException(String message) {
        super(message);
    }
}

class EmptyTaskDescriptionException extends DinoException {
    public EmptyTaskDescriptionException() {
        super("😶 The description of a task cannot be empty.");
    }
}

class InvalidInputException extends DinoException {
    public InvalidInputException() {
        super("🤔 Hmm... sorry I really don't understand what you mean.");
    }
}

class EmptyListException extends DinoException {
    public EmptyListException() {
        super("😐 You don't have any task in your list~");
    }
}

class InvalidIndexException extends DinoException {
    public InvalidIndexException() {
        super("😮 Please enter a valid task index!");
    }
}

class IndexNotSpecifiedException extends DinoException {
    public IndexNotSpecifiedException() {
        super("😕 Please specify the index of the task!");
    }
}

class TimeNotSpecifiedException extends DinoException {
    public TimeNotSpecifiedException(String type) {
        super("😛 Please specify the time of your " + type + "!");
    }
}

class TaskAlreadyDoneException extends DinoException {
    public TaskAlreadyDoneException() {
        super("🤨 Hey you have already done this task!");
    }
}

class InvalidFormatException extends DinoException {
    public InvalidFormatException(String action, String format) {
        super("😕 Please "  + action + " is entered in the format: " + format);
    }
}










