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

class InvalidTaskDeletion extends DukeException {
    public InvalidTaskDeletion() {
        super("OOPS! You are trying to delete a non-existent task!");
    }
}

