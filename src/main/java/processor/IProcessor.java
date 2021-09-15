package processor;

import java.util.List;

import models.Command;

/**
 * Interface for the Processor object which processes command.
 */
public interface IProcessor {
    /**
     * Processes command based on the type.
     *
     * @param command Type of command.
     * @param arguments Other arguments for the command.
     * @return Response from the chatbot.
     */
    String processCommand(Command command, List<String> arguments);

    /**
     * Processes command with type BYE.
     *
     * @return Response from the chatbot.
     */
    String processBye();

    /**
     * Processes command with type LIST.
     *
     * @return Response from the chatbot.
     */
    String processList();

    /**
     * Processes command with type DONE.
     *
     * @param index Index that specify which Task is done.
     * @return Response from the chatbot.
     */
    String processDone(String index);

    /**
     * Processes command with type DELETE.
     *
     * @param index Index that specify which Task.
     * @return Response from the chatbot.
     */
    String processDelete(String index);

    String processFind(String ... keywords);

    String processUndo();

    /**
     * Processes command with type DEFAULT.
     *
     * @param arguments List of arguments related to the command.
     * @return Response from the chatbot.
     */
    String processDefault(List<String> arguments);
}
