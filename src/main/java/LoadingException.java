public class LoadingException extends DukeException{
    public LoadingException() {
        super("There is no file of given name initially, but it is created.");
    }
}
