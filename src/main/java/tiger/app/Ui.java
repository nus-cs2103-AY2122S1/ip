package tiger.app;

import tiger.actions.StorageLoadAction;
import tiger.command.Command;
import tiger.components.TaskList;
import tiger.constants.Flag;


/**
 * {@code Ui} is responsible for handling interactions with users. This includes
 * responding to user commands, storing and loading user tasks, and executing user
 * tasks.
 */

public class Ui {

    private AppState applicationState;

    /**
     * Constructor of the {@code Ui} class.
     */

    public Ui() {
        this.applicationState = new AppState(new TaskList(), Flag.STORAGE_NOT_YET_INIT);
    }

    /**
     * Attempts to initialise the {@code Ui} class until successful. Actions that should be done when the Ui is
     * starting up, such as loading saved tasks, should go here.
     */

    public void start() {
        StorageLoadAction storageLoadAction;
        storageLoadAction = new StorageLoadAction(this.applicationState);
        this.applicationState = storageLoadAction.run();
    }

    /**
     * Simulates one iteration of interaction with the user, based on the given user input.
     *
     * @param userInput the given user input.
     */

    public void iterateOnce(String userInput) {
        if (this.applicationState.checkFlag().equals(Flag.STORAGE_FAILED)) {
            StorageLoadAction storageLoadAction = (StorageLoadAction) Command.getActionFromCommand(userInput,
                    this.applicationState);
            this.applicationState = storageLoadAction.run();
        } else if (this.applicationState.checkFlag().equals(Flag.DEFAULT)) {
            this.applicationState = new Pipeline(userInput, this.applicationState).run();
        }
    }

    /**
     * Checks if the user has exited the app.
     *
     * @return a boolean representing if the user has exited the app.
     */

    public boolean isExited() {
        return this.applicationState.checkFlag().equals(Flag.IS_EXITED);
    }

    /**
     * Gets the response from the bot.
     *
     * @return the response from the bot.
     */

    public String getResponse() {
        return this.applicationState.getResponse();
    }
}
