package tiger.app;

import tiger.exceptions.storage.TigerStorageException;
import tiger.exceptions.storage.TigerStorageLoadException;
import tiger.storage.Storage;

import java.util.Scanner;

/**
 * {@code Ui} is responsible for handling interactions with users. This includes
 * responding and listening to user commands, storing user tasks, and loading user
 * tasks.
 */

public class Ui {

    private AppState applicationState;
    private final Scanner scanner;

    /**
     * Constructor of the {@code Ui} class. It creates storage file if not
     * present.
     *
     * @param partialLoad Whether partial loading should be done.
     * @throws TigerStorageException if the loaded file is corrupt.
     */

    private Ui(boolean partialLoad) throws TigerStorageException {
        Storage.makeFileIfNotPresent();
        if (!partialLoad) {
            this.applicationState = new AppState(false, Storage.load());
        } else {
            this.applicationState = new AppState(false, Storage.partialLoad());
        }
        System.out.println(String.format("Hello, I am Tiger, your personal assistant. I have fetched %d tasks from my" +
                " memory.", this.applicationState.numTasks()));
        this.scanner = new Scanner(System.in);
    }

    /**
     * Attempts to initalise the {@code Ui} class until successful. If the storage file is
     * corrupted, asks the user if the storage should be wiped or if a partial load should be done.
     *
     * @return a new {@code Ui} class that is properly initalised.
     */

    public static Ui start() {
        try {
            return new Ui(false);
        } catch (TigerStorageLoadException e) {
            boolean loaded = false;
            System.out.println(e); // prompt user to do partial loading
            while (!loaded) {
                Scanner scanner = new Scanner(System.in);
                String userInput = scanner.nextLine();
                switch (userInput) {
                case "Y":
                    loaded = true;
                    return new Ui(true);
                case "N":
                    loaded = true;
                    Storage.wipeStorage();
                    return new Ui(false);
                default:
                    System.out.println("Please enter only Y or N.");
                    break;
                }
            }
        }
        // shouldn't reach here
        return new Ui(true);
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
