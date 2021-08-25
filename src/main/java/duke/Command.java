package duke;

import java.util.Scanner;

public class Command {

    Ui ui;
    Parser parser;
    Storage storage;
    TaskList taskList;

    Command(String filePath) {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage(filePath);
        if (this.storage.readFile() != null) {
            this.taskList = new TaskList(this.storage.readFile());
        } else {
            this.taskList = new TaskList();
        }
    }

    public void startDuke() {
        this.ui.startMessage(this.taskList.getTaskList());
        Scanner scanner = new Scanner(System.in);
        boolean hasNextCommand = true;
        while (hasNextCommand) {
            String echo = scanner.nextLine();
            String action = this.parser.parseCommand(echo);
            hasNextCommand = runCommand(action,echo);
        }
    }

    public boolean runCommand(String typeOfCommand, String description) {
        try {
            if (typeOfCommand.equals("bye")) {
                this.ui.endMessage();
                this.storage.writeToFile(this.taskList.getTaskList());
                return false;
            } else if (typeOfCommand.equals("list")) {
                this.ui.iterateTaskList(this.taskList.getTaskList());
                return true;
            } else if (typeOfCommand.equals("done")) {
                String task = this.parser.parseDoneCommand(description);
                this.taskList.markAsDone(task);
                return true;
            } else if (typeOfCommand.equals("delete")) {
                String task = this.parser.parseDeleteCommand(description);
                this.taskList.deleteTask(task);
                return true;
            } else if (typeOfCommand.equals("addTask")) {
                Task task = this.parser.parseAddTask(description);
                this.taskList.addTask(task);
                return true;
            } else if (typeOfCommand.equals("empty")) {
                System.out.println("Please enter a new task or action.");
                return true;
            } else {
                System.out.println("Sorry I don't understand this command :(");
                return true;
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return true;
        }
    }
}
