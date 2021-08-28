package tiger.app;

import java.util.Scanner;

import tiger.actions.StorageLoadAction;
import tiger.command.Command;
import tiger.components.TaskList;
import tiger.constants.Flag;


/**
 * {@code Ui} is responsible for handling interactions with users. This includes
 * responding and listening to user commands, storing and loading user tasks, and executing user
 * tasks.
 */

public class Ui {

    private AppState applicationState;
    private final Scanner scanner;

    /**
     * Constructor of the {@code Ui} class.
     */

    public Ui() {
        this.scanner = new Scanner(System.in);
        this.applicationState = new AppState(false, new TaskList(), Flag.STORAGE_NOT_YET_INIT);
    }

    /**
     * Attempts to initialise the {@code Ui} class until successful. Actions that should be done when the Ui is
     * starting up, such as loading saved tasks, should go here.
     */

    public void start() {
        StorageLoadAction storageLoadAction;
        storageLoadAction = new StorageLoadAction(this.applicationState);
        this.applicationState = storageLoadAction.run();
        System.out.println(this.applicationState.getResponse());
        while (this.applicationState.checkFlag().equals(Flag.STORAGE_FAILED)) {
            String userInput = scanner.nextLine();
            storageLoadAction = (StorageLoadAction) Command.getActionFromCommand(userInput, this.applicationState);
            this.applicationState = storageLoadAction.run();
            System.out.println(this.applicationState.getResponse());
        }
    }

    /**
     * Makes the {@code Ui} class listen to user inputs and respond to user commands until the user quits.
     * Ask saves the {@code TaskList} for every command.
     */

    public void runUntilStopped() {
        while (!this.applicationState.isExited()) {
            String userInput = scanner.nextLine();
            this.applicationState = new Pipeline(userInput, this.applicationState).run();
            System.out.println(this.applicationState.getResponse());
        }
    }

    /**
     * Exits the {@code Ui}. Handles the tasks that is supposed to be done upon exit.
     */

    public void exit() {
    }
}
