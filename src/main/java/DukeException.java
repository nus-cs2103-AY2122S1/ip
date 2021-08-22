public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
class NoDescriptionException extends DukeException {
    public NoDescriptionException(String string){
        super("OOPS! The description of " + string
                + " cannot be empty.");
    }
}

class NoDescriptionAndTimeException extends DukeException {
    public NoDescriptionAndTimeException(String string){
        super("OOPS! The description & time of " + string +" cannot\nbe empty.");
    }
}

class NoTimeException extends DukeException {
    public NoTimeException(String string){
        super("OOPS! The time of " + string +" cannot be empty.");
    }
}

class NoCommandException extends DukeException {
    public NoCommandException() {
        super("OOPS! I'm sorry, but I don't know what that means :(");
    }
}

class InvalidTaskDeletionException extends DukeException {
    public InvalidTaskDeletionException() {
        super("OOPS! You are trying to delete a non-existent task!");
    }
}

class InvalidTaskDoneException extends DukeException {
    public InvalidTaskDoneException() {
        super("OOPS! You are seting a non-existent task as done!");
    }
}

class InvalidDateTimeException extends DukeException {
    public InvalidDateTimeException() {
        super("Please enter a date of the format yyyy-mm-dd!");
    }
}

