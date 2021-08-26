public interface Command {
    void execute(TaskList tasks, Ui ui) throws MalformedCommandException;

    boolean isExit();
}

