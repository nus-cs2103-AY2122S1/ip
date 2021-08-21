package tiger.ui;

import tiger.actions.Action;
import tiger.actions.AppState;
import tiger.command.Command;
import tiger.exceptions.inputs.TigerInvalidInputException;
import tiger.exceptions.storage.TigerStorageException;
import tiger.exceptions.storage.TigerStorageLoadException;
import tiger.storage.Storage;

import java.util.Scanner;

/*
 * Ui is responsible for handling interactions with users. This includes
 * responding and listening to user commands, storing user tasks, and loading user
 * tasks.
 */

public class Ui {

    private AppState applicationState;
    private Scanner scanner;

    /*
     * Inits the Ui, loads the taskList from the saved file.
     */

    private Ui(boolean partialLoad) throws TigerStorageException {
        Storage.makeFileIfNotPresent();
        if (!partialLoad) {
            this.applicationState = new AppState(false, Storage.load());
        } else {
            this.applicationState = new AppState(false, Storage.partialLoad());
        }
        System.out.println(String.format("Hello, I am Tiger, your personal assistant. I have fetched %d tasks from my memory.", this.applicationState.numTasks()));
        this.scanner = new Scanner(System.in);
    }

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

    public void runUntilStopped() {
        while (!this.applicationState.isExited()) {
            try {
                String userInput = scanner.nextLine();
                Action action = Command.getActionFromCommand(userInput, applicationState);
                this.applicationState = action.run();
                // save the storage everytime the user runs an action in case the app inexplictly quits
                Storage.save(this.applicationState.taskList);
            } catch (TigerInvalidInputException e) {
                System.out.println(e);
            }
        }
    }

    public void exit() {
        if (this.applicationState.isExited()) {
            String goodbyeMessage = "Bye. Hope to see you again soon!";
            System.out.println(goodbyeMessage);
        }
    }
}
