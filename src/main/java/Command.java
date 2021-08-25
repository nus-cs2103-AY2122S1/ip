public interface Command {
    void execute(TaskList tasks, Storage storage);

    boolean isExit();
}
