package duke;

import duke.exceptions.*;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.*;
import duke.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Duke {

    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    // Initialising the object
    public Duke(String filePath) throws IOException {
        ui = new Ui(); // has the self intro
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

    public void run() throws DukeExceptions, IOException {
        ui.showWelcome();
        String command; // this is the container for the full command received from the user
        String cmd; // this is the container for the first word of the command

        ///// This listens for the commands and interprets them
        // This part listens for user input and repeats until the command "bye" is identified
        Scanner sc = new Scanner(System.in);
        while (true) {
            command = sc.nextLine();
            Parser parser = new Parser(command);
            cmd = parser.getFirstWord();

            // 'bye' : Ends the program
            if (command.equals("bye") || cmd.equals("bye")) {
                ui.showBye();
                sc.close();
                break;


            // 'list' : Retrieves information from the hard drive and prints it
            } else if (cmd.equals("list")) {
                ui.showList(taskList);
                ui.showLine();


            // 'done [int]' : marks the corresponding number in the list as done
            } else if (cmd.equals("done")) {

                // Throws exception if there is error accessing the integer following "done"
                // Marks the task as done and prints statements as proof
                int ref = parser.getSecondInteger(taskList.size()) - 1;
                Task task = taskList.get(ref);

                if (task.getStatusIcon().equals("X")) {
                    System.out.println("You've already done this!");
                } else {
                    task.markAsDone();
                    storage.updateDone(ref, task.getStatusString());
                    ui.showCompletion(taskList.get(ref).toString());
                }
                ui.showLine();


                // 'delete [int]' : delete the corresponding number
            } else if (cmd.equals("delete")) {
                int ref = parser.getSecondInteger(taskList.size()) - 1;
                ui.showRemoval(taskList.get(ref).toString(), taskList.size() - 1);
                taskList.remove(ref);
                storage.removeTask(ref);
                ui.showLine();

            } else if (cmd.equals("find")) {
                String wordSearch = parser.getSecondWord();
                ui.showSearch(taskList.search(wordSearch));
                ui.showLine();


                // Else, an item has been added to the chat bot
                // Commands are either todo, deadline or event
            } else {

                switch (cmd) {
                case "todo" : {
                    String taskInfo = parser.getTodoInfo();
                    taskList.add(new Todo(taskInfo));
                    storage.addTask(taskList.getLastStatusString());
                    ui.showAddition(cmd, command);
                    ui.showLine();
                    break;

                }
                case "deadline" : {

                    String description = parser.getDeadlineInfo();
                    LocalDate date = parser.getDeadlineDate();

                    taskList.add(new Deadline(description, date));
                    storage.addTask(taskList.getLastStatusString());
                    ui.showAddition(cmd, command);
                    ui.showLine();
                    break;

                }
                case "event" : {

                    String description = parser.getEventInfo();
                    String eventDetails = parser.getEventLocation();
                    taskList.add(new Event(description, eventDetails));
                    storage.addTask(taskList.getLastStatusString());
                    ui.showAddition(cmd, command);
                    ui.showLine();
                    break;

                }
                default : throw new CommandDoesNotExist(command);
                }
            }
        }
    }

    public static void main(String[] args) throws DukeExceptions, IOException {
        new Duke("data/duke.txt").run();
    }
}




