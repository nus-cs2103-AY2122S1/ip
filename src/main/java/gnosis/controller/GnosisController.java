package gnosis.controller;

import gnosis.model.Command;
import gnosis.ui.GnosisUI;
import gnosis.util.GnosisConstants;
import gnosis.util.GnosisException;

import java.io.File;

/**
 * Represents Logic flow of interaction of user input and, to specified
 * managers to perform from user command.
 *
 * @author Pawandeep Singh
 * */
public class GnosisController {

    /** Task Controller to handle task commands */
    private TaskController taskController;

    /** Place Controller to handle place commands */
    private PlaceController placeController;


    /** UI view of Gnosis */
    private GnosisUI view;

    /**
     * GnosisController constructor to connect with GnosisView and,
     * to initialise TaskCommandManager with loaded task.
     *
     * @param view UI view to user.
     */
    public GnosisController(GnosisUI view) {
        this.view = view;
        this.taskController = new TaskController();
        this.placeController = new PlaceController();
    }

    /**
     * Executes specified command from input.
     *
     * @param strCommand Command specified by user.
     * @param commandInput input to perform from command.
     * @throws GnosisException If command not found.
     */
    public void executeUserCommand(String strCommand, String commandInput) throws GnosisException {
        Command command;
        String commandIdentifier;
        try {
            command = Command.valueOf(strCommand.toUpperCase().trim());
            commandIdentifier = Command.getCommandIdentifier(command);
        } catch (IllegalArgumentException e) {
            throw new GnosisException(GnosisConstants.COMMAND_NOT_FOUND_MESSAGE);
        }

        if (commandIdentifier.equalsIgnoreCase(GnosisConstants.SYSTEM_EXIT_IDENTIFER)) {
            view.displayByeMessage();
        } else if (commandIdentifier.equalsIgnoreCase(GnosisConstants.TASK_COMMAND_IDENTIFIER)) {
            this.executeTaskCommand(command, commandInput);
        } else if (commandIdentifier.equalsIgnoreCase(GnosisConstants.PLACE_COMMAND_IDENTIFIER)) {
            this.executePlaceCommand(command, commandInput);
        } else {
            throw new GnosisException(GnosisConstants.COMMAND_NOT_FOUND_MESSAGE);
        }
    }


    /**
     * Executes specified command from input.
     * Updates user tasks into file system.
     *
     * @param gc task command to perform
     * @param taskInput input to perform from task command.
     * @throws GnosisException If command not found.
     * @throws NumberFormatException if taskInput for DONE and DELETE command cannot be converted to integer.
     */
    public void executeTaskCommand(Command gc, String taskInput) throws GnosisException, NumberFormatException {
        this.taskController.executeCommand(gc, taskInput, this.view);
    }

    /**
     * Executes specified place command from input.
     * Updates user tasks into file system.
     *
     * @param gc task command to perform
     * @param input input to perform from place command.
     * @throws GnosisException If command not found.
     */
    public void executePlaceCommand(Command gc, String input ) throws GnosisException {
        this.placeController.executeCommand(gc, input, this.view);
    }

    /**
     * loads Gnosis greeting message to UI.
     *
     */
    public void loadGreetingMessage() {
        view.displayGreetMessage(taskController.isTaskLoaded(), placeController.isPlacesLoaded());
    }

    public boolean export(String fileName, File pathToExport) {
        //TODO: improve implementation
        if (fileName.equalsIgnoreCase("tasks")) {
            return taskController.exportFile(pathToExport);
        } else if (fileName.equalsIgnoreCase("places")) {
            return placeController.exportFile(pathToExport);
        }
        return false;
    }
}
