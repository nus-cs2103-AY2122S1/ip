package ui;

import dialog.Dialog;
import dialog.DialogException;
import dialog.TaskDialog;
import storage.Storage;

import task.TaskList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Ui {

    public static Scanner sc = new Scanner(System.in);

    private String input;
    private TaskDialog taskDialog;

    public Ui() {
        this.input = "";
    }

    public void importTaskList(TaskList taskList) {
        this.taskDialog = (TaskDialog) TaskDialog.generate("taskList: " + taskList.hashCode(), taskList);
    }

    public TaskDialog getTaskDialog() {
        return this.taskDialog;
    }

    public static void printError(Exception e) throws DialogException {
        if (Dialog.have(e.toString())) {
            System.out.println(Dialog.getDialog(e.toString()));
        } else {
            Dialog errorMessage = Dialog.generate(e.toString());
            errorMessage.add("☹ OOPS!!! " + e.getMessage());
            System.out.println(errorMessage);
        }
    }

    public static void printSelectSaveFile() throws DialogException, IOException {
        String dialogId = "selectSaveFile";
        if (Dialog.containsId(dialogId)) {
            System.out.println(Dialog.get(dialogId));
        } else {
            Dialog selectSaveFile = Dialog.generate("selectSaveFile");
            selectSaveFile.add("Select your save file:\n");
            if (!java.nio.file.Files.exists(Paths.get(Storage.DIRECTORY_PATH))) {
                Files.createDirectory(Paths.get(Storage.DIRECTORY_PATH));
            }
            ArrayList<File> files = new ArrayList<>(Arrays.asList(Storage.getFilesFromDirectory(Storage.DIRECTORY_PATH)));

            int count = 1;
            for (int i = 0; i < files.size(); i++) {
                if (files.get(i).isFile() && !files.get(i).isHidden()) {
                    String fullFileName = files.get(i).getName();
                    selectSaveFile.add(count + ". " + fullFileName.substring(0, fullFileName.indexOf(".")));
                    count++;
                }
            }
            selectSaveFile.add("(input <new_file_name> to create new save file)");
            System.out.println(selectSaveFile);
        }
    }

    public static void showWelcome() throws DialogException {
        Dialog greeting = Dialog.generate("greeting");

        String logo =
                "     ___       __       __    ______  _______\n" +
                        "        /   \\     |  |     |  |  /      ||   ____|\n" +
                        "       /  ^  \\    |  |     |  | |  ,----'|  |__\n" +
                        "      /  /_\\  \\   |  |     |  | |  |     |   __|\n" +
                        "     /  _____  \\  |  `----.|  | |  `----.|  |____\n" +
                        "    /__/     \\__\\ |_______||__|  \\______||_______|\n";

        greeting.add(logo);
        greeting.add("Hello! I'm Alice, your personal assistant");
        greeting.add("What can I do for you?");
        System.out.println(greeting);
    }

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

    public static void showCommandList() throws DialogException {
        String dialogId ="commands";
        if (Dialog.containsId(dialogId)) {
            System.out.println(Dialog.get(dialogId));
        } else {
            Dialog commandsList = Dialog.generate(dialogId);
            commandsList.add("This is the following commands, I can perform:\n");
            commandsList.add("1. 'todo <task description>' - add a todo task to the list");
            commandsList.add("2. 'deadline <task description> /by <by when>' - add a deadline task with specific deadline");
            commandsList.add("3. 'event <task description> /at <at when>' - add an event task with specific time");
            commandsList.add("4. 'date <yyyy-mm-dd>' - list all todos and all the deadlines and events before specified time");
            commandsList.add("5. 'list' - show the current task list");
            commandsList.add("6. 'done <task index>' - mark that task as done");
            commandsList.add("7. 'delete <task index>' - delete that task from the list");
            commandsList.add("8. 'commands' - show this current command window");
            commandsList.add("9. 'bye' - end session and save your task list");
            System.out.println(commandsList);
        }
    }

    public void showCurrentList() {
        System.out.println(taskDialog);
    }

    public static void showGoodBye() throws DialogException {
        Dialog bye = Dialog.generate("bye");
        bye.add("Bye. Hope to see you again soon!");
        System.out.println(bye);
    }

    public static String fastRead() {
        System.out.print("> ");
        return sc.nextLine();
    }

    public String readCommand() {
        System.out.print("> ");
        input = sc.nextLine();
        return input;
    }
}
