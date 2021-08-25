import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final Ui ui;
    private final Scanner sc = new Scanner(System.in);
    private ArrayList<Task> taskList = new ArrayList<>();
    private final Storage storage;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
    }


    public Command parseUserInput(String input) throws DukeException {
        String[] strings = input.split(" ");
        String operation = strings[0];
        switch (operation) {
        case "bye":
        case "list":
            return new Command(operation);
        case "done":
            if (strings.length == 1) {
                throw new DukeException("You need to indicate which task number should be marked as done.");
            }
            try {
                int doneTaskNum = Integer.parseInt(strings[1]);
                return new Command(strings[0], doneTaskNum);
            } catch (NumberFormatException e) {
                throw new DukeException("The task to be marked as done should be indicated its list index.");
            }
        case "todo":
            if (strings.length == 1) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            return new Command(strings[0], input.substring(5));
        case "deadline":
            if (strings.length == 1) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            String[] descriptionAndBy = input.substring(9).split("/by ");
            if (descriptionAndBy.length == 1) {
                throw new DukeException("The done-by date of a deadline cannot be empty.");
            }
            return new Command(strings[0], descriptionAndBy[0], descriptionAndBy[1]);
        case "event":
            if (strings.length == 1) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            String[] descriptionAndAt = input.substring(6).split("/at ");
            if (descriptionAndAt.length == 1) {
                throw new DukeException("The timing of an event cannot be empty.");
            }
            return new Command(strings[0], descriptionAndAt[0], descriptionAndAt[1]);
        case "delete":
            if (strings.length == 1) {
                throw new DukeException("You need to indicate which task number should be deleted.");
            }
            try {
                int delTaskNum = Integer.parseInt(strings[1]);
                return new Command(strings[0], delTaskNum);
            } catch (NumberFormatException e) {
                throw new DukeException("The task to be deleted should be indicated its list index.");
            }
        default:
            throw new DukeException("Sorry, I do not understand this command.");
        }
    }

    public void run() {
        try {
            this.taskList = storage.readSave(taskList);
        } catch (EOFException e) {
            ui.showNewSave();
        } catch (IOException | ClassNotFoundException e) {
            ui.showReadSaveError();
        }
        ui.showIntro();
        outer:
        while (true) {
            String userEntry = sc.nextLine();
            Command command;
            try {
                command = parseUserInput(userEntry);
            } catch (DukeException e) {
                ui.print(e.toString());
                continue;
            }
            Task task;
            switch (command.getOperation()) {
            case "bye":
                ui.showOutro();
                try {
                    storage.writeSave(this.taskList);
                } catch (IOException e) {
                    ui.showWriteSaveError();
                }
                break outer;
            case "list":
                ui.showTasks(taskList);
                break;
            case "done":
                task = taskList.get(command.getIndex() - 1);
                task.setDone(true);
                ui.showDone(task);
                break;
            case "todo":
                task = new ToDo(command.getDescription());
                taskList.add(task);
                ui.showAdded(task, taskList.size());
                break;
            case "deadline":
                task = new Deadline(command.getDescription(), command.getTime());
                taskList.add(task);
                ui.showAdded(task, taskList.size());
                break;
            case "event":
                task = new Event(command.getDescription(), command.getTime());
                taskList.add(task);
                ui.showAdded(task, taskList.size());
                break;
            case "delete":
                task = taskList.get(command.getIndex() - 1);
                taskList.remove(command.getIndex() - 1);
                ui.showDeleted(task, taskList.size());
                break;
            }
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke("duke.txt").run();
    }
}
