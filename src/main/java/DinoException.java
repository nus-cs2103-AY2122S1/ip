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

class TaskNotCategorizedException extends DinoException {
    public TaskNotCategorizedException() {
        super("😐 Please specify the type of your task!");
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

/*
class TaskListFullException extends DinoException {
    public TaskListFullException() {
        super("😨 Hey you already have 100 tasks! Can't add more XD");
    }
}
 */





