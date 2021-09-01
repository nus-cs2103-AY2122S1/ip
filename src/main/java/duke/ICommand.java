package duke;

/**
 * This interface is used by all commands and ensures that commands implement the execute() method.
 */
public interface ICommand {

    void execute(TaskManager tm, ResponseManager responseManager, Storage storage);

    String getReply();

}
