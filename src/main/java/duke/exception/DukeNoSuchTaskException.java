package duke.exception;

public class DukeNoSuchTaskException extends DukeException {
    public DukeNoSuchTaskException() {
        super("No such task :< (Check the index input!!!)");
    }
}
