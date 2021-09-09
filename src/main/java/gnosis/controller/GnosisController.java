package gnosis.controller;

import java.io.File;

import gnosis.model.Command;
import gnosis.place.PlaceCommandManager;
import gnosis.place.PlaceStorageManager;
import gnosis.task.TaskCommandManager;
import gnosis.task.TaskStorageManager;
import gnosis.ui.GnosisUI;
import gnosis.util.GnosisConstants;
import gnosis.util.GnosisException;

/**
 * Represents Logic flow of interaction of user input and, to specified
 * managers to perform from user command.
 *
 * @author Pawandeep Singh
 * */
public class GnosisController {

    /** Task Manager to handle task commands */
    private TaskCommandManager taskCommandManager;

    /** Place Manager to handle place commands */
    private PlaceCommandManager placeCommandManager;

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
        this.taskCommandManager = new TaskCommandManager(TaskStorageManager.loadGnosisTasks());
        //TODO: UPDATE GNOSIS PLACE STORAGE
        this.placeCommandManager = new PlaceCommandManager(PlaceStorageManager.loadGnosisPlaces());
    }

    /**
     * Executes specified command from input.
     *
     * @param command Command specified by user.
     * @param commandInput input to perform from command.
     * @throws GnosisException If command not found.
     */
    public void executeCommand(String command, String commandInput) throws GnosisException {
        Command gc;
        String identifier;
        try {
            gc = Command.valueOf(command.toUpperCase().trim());
            identifier = Command.getCommandIdentifier(gc);
        } catch (IllegalArgumentException e) {
            throw new GnosisException(GnosisConstants.COMMAND_NOT_FOUND_MESSAGE);
        }

        // parse commands
        if (identifier.equalsIgnoreCase(GnosisConstants.SYSTEM_EXIT_IDENTIFER)) {
            view.displayByeMessage();
        } else if (identifier.equalsIgnoreCase(GnosisConstants.TASK_COMMAND_IDENTIFIER)) {
            this.executeTaskCommand(gc, commandInput);
        } else if (identifier.equalsIgnoreCase(GnosisConstants.PLACE_COMMAND_IDENTIFIER)) {
            this.executePlaceCommand(gc, commandInput);
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
        gc.setTaskActionHandler(this.view, taskCommandManager, gc, taskInput);

        // For every command executed, update tasks to file
        TaskStorageManager.writeTasksToFile(taskCommandManager.getTasks());
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
        gc.setPlaceActionHandler(this.view, this.placeCommandManager, gc, input);

        PlaceStorageManager.writePlacesToFile(placeCommandManager.getPlaces());
    }

    /**
     * loads Gnosis greeting message to UI.
     *
     */
    public void loadGreetingMessage() {
        view.displayGreetMessage(TaskStorageManager.isDataFileAvail(), PlaceStorageManager.isDataFileAvail());
    }

    public void exportToCsv(File pathToExport) {
        TaskStorageManager.copyTaskToPath(pathToExport);
    }
}
