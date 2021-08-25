package processor;

import java.util.List;

import models.Command;

/**
 * Interface for the Processor object which processes command.
 */
public interface IProcessor {
    /**
     * Process command based on the type.
     *
     * @param command Type of command.
     * @param arguments Other arguments for the command.
     */
    void processCommand(Command command, List<String> arguments);

    /**
     * Process command with type BYE.
     */
    void processBye();

    /**
     * Process command with type LIST.
     */
    void processList();

    /**
     * Process command with type DONE.
     *
     * @param index Index that specify which Task is done.
     */
    void processDone(String index);

    /**
     * Process command with type DELETE.
     *
     * @param index Index that specify which tASK
     */
    void processDelete(String index);

    void processFind(String keyword);

    /**
     * Process command with type DEFAULT.
     *
     * @param arguments List of arguments related to the command.
     */
    void processDefault(List<String> arguments);
}