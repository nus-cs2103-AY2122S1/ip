public class InvalidKeywordException extends DukeException{

    public InvalidKeywordException() {

        super("Sorry, I don't know what that means. " +
                "\nPlease use the keywords 'todo', 'deadline' or 'event' to create a new task, " +
                "\n'list' to view all the existing tasks or 'bye' to exit.");
    }
}
