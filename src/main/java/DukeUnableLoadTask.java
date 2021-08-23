public class DukeUnableLoadTask extends DukeException {
    public DukeUnableLoadTask() {
        super("Duke was unable to load your file", new Throwable());
    }
}
