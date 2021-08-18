public abstract class DukeException extends Exception{
    public DukeException(String task) {
        super(task);
    }

    public abstract void printError();
}
