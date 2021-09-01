package duke;

public interface Command {
    void execute(TaskList taskList, Ui ui) throws DukeException;
    boolean isExit();
}



