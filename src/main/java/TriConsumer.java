@FunctionalInterface
public interface TriConsumer {
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, Exception;
}
