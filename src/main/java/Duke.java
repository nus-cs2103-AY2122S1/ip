import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Ui ui;
    private String filePath;
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Task> taskList;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.filePath = filePath;
        this.readSave();
    }

    public void writeSave() {
        try {
            FileOutputStream writeData = new FileOutputStream(filePath);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            writeStream.writeObject(taskList);
            writeStream.flush();
            writeStream.close();
        } catch (IOException e) {
            ui.showWriteSaveError();
        }
    }

    public void readSave() {
        try {
            File f = new File(filePath);
            if (!f.createNewFile()) { // save file exists
                FileInputStream readData = new FileInputStream(filePath);
                ObjectInputStream readStream = new ObjectInputStream(readData);
                @SuppressWarnings("unchecked")
                ArrayList<Task> readList = (ArrayList<Task>) readStream.readObject();
                this.taskList = readList;
                readStream.close();
            } else {
                this.taskList = new ArrayList<>();
            }
        } catch (EOFException e) {
            this.taskList = new ArrayList<Task>();
            writeSave();
        } catch (IOException | ClassNotFoundException e) {
            ui.showReadSaveError();
        }
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
        Command command;
        ui.showIntro();
        outer:
        while (true) {
            String userEntry = sc.nextLine();
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
                writeSave();
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
