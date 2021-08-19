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

class NoSuchTaskException extends DinoException {
    public NoSuchTaskException() {
        super("😮 Wait... you don't even have this task in your list yet!");
    }
}

class TaskIndexNotSpecifiedException extends DinoException {
    public TaskIndexNotSpecifiedException() {
        super("😕 Please specify the index of the task!");
    }
}


class TaskListFullException extends DinoException {
    public TaskListFullException() {
        super("😨 Hey you already have 100 tasks! Can't add more XD");
    }
}





