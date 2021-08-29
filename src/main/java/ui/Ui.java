package ui;

import dialog.Dialog;
import dialog.DialogException;
import dialog.TaskDialog;
import storage.Storage;

import task.TaskList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The Ui class dealing with user interacting with the application and its system.
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.01
 * @since 0.00
 */
public class Ui {
    /** Scanner of the Ui - getting the input from the user */
    public static Scanner sc = new Scanner(System.in);

    /** the latest input stored in the Ui */
    private String input;
    private TaskDialog taskDialog;

    /**
     * Constructor of Ui.
     * The Ui object should be specified of which TaskList it is supposed to import
     * with the method importTaskList(TaskList taskList) not through the constructor.
     */
    public Ui() {
        this.input = "";
        this.taskDialog = null;
    }

    /**
     * Load the taskList to the Ui TaskDialog.
     *
     * @param taskList the taskList to be imported
     */
    public void importTaskList(TaskList taskList) {
        this.taskDialog = (TaskDialog) TaskDialog.generate("taskList: " + taskList.hashCode(), taskList);
    }

    /**
     * Return the current taskDialog of the Ui object.
     * The TaskDialog equal to null if it has not loaded a TaskList yet.
     *
     * @return taskDialog of the current Ui to be pass around to other method or fields
     */
    public TaskDialog getTaskDialog() {
        return this.taskDialog;
    }

    /**
     * Static method for printing an error to the Ui.
     *
     * @param exception the exception with its message to be printed to the user
     * @throws DialogException the dialog cannot have the same id while the app is running
     */
    public static void printError(Exception exception) throws DialogException {
        if (Dialog.containsId(exception.toString())) {
            System.out.println(Dialog.get(exception.toString()));
        } else {
            Dialog errorMessage = Dialog.generate(exception.toString());
            errorMessage.add("☹ OOPS!!! " + exception.getMessage());
            System.out.println(errorMessage);
        }
    }

    /**
     * Static method for printing to the user the current save file names in the system.
     *
     * @throws DialogException the dialog cannot have the same id while the app is running
     * @throws IOException     if there is any error dealing with the system IO
     */
    public static void printSelectSaveFile() throws DialogException, IOException {
        String dialogId = "selectSaveFile";
        if (Dialog.containsId(dialogId)) {
            System.out.println(Dialog.get(dialogId));
        } else {
            Dialog selectSaveFile = Dialog.generate("selectSaveFile");
            selectSaveFile.add("Select your save file:\n");
            if (!Storage.haveSaveLocation()) {
                Storage.createSaveLocation();
            }
            ArrayList<File> files = new ArrayList<>(Arrays.asList(Storage.getFilesFromDirectory(
                    Storage.DIRECTORY_PATH + Storage.DATA_PATH)));

            int count = 1;
            for (File file : files) {
                if (file.isFile() && !file.isHidden()) {
                    String fullFileName = file.getName();
                    selectSaveFile.add(count + ". " + fullFileName.substring(0, fullFileName.indexOf(".")));
                    count++;
                }
            }
            selectSaveFile.add("(input <new_file_name> to create new save file)");
            System.out.println(selectSaveFile);
        }
    }

    /**
     * Static method for printing to the user a welcome message and alice self-introduction.
     *
     * @throws DialogException the dialog cannot have the same id while the app is running
     */
    public static void showWelcome() throws DialogException {
        Dialog greeting = Dialog.generate("greeting");

        String logo = "     ___       __       __    ______  _______\n"
                + "        /   \\     |  |     |  |  /      ||   ____|\n"
                + "       /  ^  \\    |  |     |  | |  ,----'|  |__\n"
                + "      /  /_\\  \\   |  |     |  | |  |     |   __|\n"
                + "     /  _____  \\  |  `----.|  | |  `----.|  |____\n"
                + "    /__/     \\__\\ |_______||__|  \\______||_______|\n";

        greeting.add(logo);
        greeting.add("Hello! I'm Alice, your personal assistant");
        greeting.add("What can I do for you?");
        System.out.println(greeting);
    }

    /**
     * Static method for printing to the user a confirmation message if the user really want to create a new file.
     *
     * @param fileName the filename to be printed along with the confirmation message
     * @throws DialogException the dialog cannot have the same id while the app is running
     */
    public static void showConfirmCreateNewFile(String fileName) throws DialogException {
        String dialogId = "confirmCreateNewFile" + fileName;
        if (Dialog.containsId(dialogId)) {
            System.out.println(Dialog.get(dialogId));
        } else {
            Dialog confirmCreate = Dialog.generate(dialogId);
            confirmCreate.add("Are you sure you want to create new file " + fileName + " [y/N]?");
            System.out.println(confirmCreate);
        }
    }

    /**
     * Static method for showing to the user the command in which alice can perform.
     *
     * @throws DialogException the dialog cannot have the same id while the app is running
     */
    public static void showCommandList() throws DialogException {
        String dialogId = "commands";
        if (Dialog.containsId(dialogId)) {
            System.out.println(Dialog.get(dialogId));
        } else {
            Dialog commandsList = Dialog.generate(dialogId);
            commandsList.add("This is the following commands, I can perform:\n");
            commandsList.add("1. 'todo <task description>' - add a todo task to the list");
            commandsList.add("2. 'deadline <task description> /by <by when>' "
                    + "- add a deadline task with specific deadline");
            commandsList.add("3. 'event <task description> /at <at when>' "
                    + "- add an event task with specific time");
            commandsList.add("4. 'date <yyyy-mm-dd>' "
                    + "- list all todos and all the deadlines and events before specified time");
            commandsList.add("5. 'list' - show the current task list");
            commandsList.add("6. 'done <task index>' - mark that task as done");
            commandsList.add("7. 'delete <task index>' - delete that task from the list");
            commandsList.add("8. 'commands' - show this current command window");
            commandsList.add("9. 'bye' - end session and save your task list");
            System.out.println(commandsList);
        }
    }

    /** Print to the user the taskList stored in the Ui in a taskDialog format */
    public void showCurrentList() {
        System.out.println(taskDialog);
    }

    /**
     * Static method for printing to the user a good bye message.
     *
     * @throws DialogException the dialog cannot have the same id while the app is running
     */
    public static void showGoodBye() throws DialogException {
        // a good bye will always be shown only once in this update
        Dialog bye = Dialog.generate("bye");
        bye.add("Bye. Hope to see you again soon!");
        System.out.println(bye);
    }

    /**
     * Static method for quickly asking the user to input something to the Ui without storing it into the input
     * of the Ui and return it right away.
     *
     * @return the String read by the Scanner until the next line
     */
    public static String fastRead() {
        System.out.print("> ");
        return sc.nextLine();
    }

    /**
     * Read the command input by the user until the next line while storing the input in the Ui for future reference.
     *
     * @return the input from the user
     */
    public String readCommand() {
        System.out.print("> ");
        input = sc.nextLine();
        return input;
    }

    /**
     * The getter for the input stored in the Ui.
     *
     * @return the input stored in the current ui object
     */
    public String getLatestInput() {
        return this.input;
    }
}
