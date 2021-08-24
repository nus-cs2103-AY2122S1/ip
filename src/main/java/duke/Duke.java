package duke;

import duke.exceptions.*;
import duke.tasks.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Duke {

    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;
    private final Parser parser;

    // Initialising the object
    public Duke(String filePath) throws IOException {
        ui = new Ui(); // has the self intro
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
        parser = new Parser();
    }

    public void run() throws DukeExceptions, IOException {
        ui.showWelcome();
        String command; // this is the container for the command received from the user


        ///// This listens for the commands and interprets them
        // This part listens for user input and repeats until the command "bye" is identified
        Scanner sc = new Scanner(System.in);
        while (true) {
            command = sc.nextLine();

            // 'bye' : Ends the program
            if (command.toLowerCase().equals("bye")) {
                ui.showBye();
                sc.close();
                break;


            // 'list' : Retrieves information from the hard drive and prints it
            } else if (command.toLowerCase().equals("list")) {
                ui.showList(taskList);
                ui.showLine();


            // 'done [int]' : marks the corresponding number in the list as done
            } else if (command.toLowerCase().split(" ")[0].equals("done")) {

                if (command.toLowerCase().split(" ").length == 1 || Integer.parseInt(command.split(" ")[1]) < 1
                        || Integer.parseInt(command.split(" ")[1]) > taskList.size()) {
                    sc.close();
                    throw new NotDoneRightException("1", String.valueOf(taskList.size()));
                }

                // Marks the task as done and prints statements as proof
                int ref = Integer.parseInt(command.split(" ")[1]) - 1;
                Task task = taskList.get(ref); //TODO???

                if (task.getStatusIcon().equals("X")) {
                    System.out.println("You've already done this!");
                    ui.showLine();
                } else {
                    task.markAsDone();
                    ui.showCompletion(taskList.get(ref).toString());
                    ui.showLine();

                    // Updates the duke.txt file
                    storage.updateDone(ref, task.getStatusString());
                }


                // 'delete [int]' : delete the corresponding number
            } else if (command.toLowerCase().split(" ")[0].equals("delete")) {

                if (command.toLowerCase().split(" ").length == 1 || Integer.parseInt(command.split(" ")[1]) < 1
                        || Integer.parseInt(command.split(" ")[1]) > taskList.size()) {
                    sc.close();
                    throw new DeletionException("1", String.valueOf(taskList.size()));
                }

                int ref = Integer.parseInt(command.split(" ")[1]) - 1;
                ui.showRemoval(taskList.get(ref).toString(), taskList.size() - 1);
                taskList.remove(ref);
                storage.removeTask(ref);
                ui.showLine();


                // Else, an item has been added to the chat bot
            } else {

                String taskType = command.toLowerCase().split(" ", 2)[0];

                switch (taskType) {
                case "todo" : {
                    String[] taskInfo = command.split(" ", 2);
                    if (taskInfo.length == 1) {
                        throw new EmptyDescriptionException("todo");
                    }

                    System.out.println("added: " + command);
                    ui.showLine();
                    taskList.add(new Todo(taskInfo[1]));
                    storage.addTask(taskList.getLastStatusString());
                    break;

                }
                case "deadline" : {
                    String[] taskInfo = command.split(" ", 2);
                    if (taskInfo.length == 1) {
                        throw new EmptyDescriptionException("todo");
                    }

                    String[] taskMoreInfo = taskInfo[1].split("/by", 2);
                    if (taskMoreInfo.length == 1) {
                        throw new EmptyDetailsException("deadline");
                    }
                    String description = taskMoreInfo[0];
                    String date = taskMoreInfo[1];

                    System.out.println("added: " + command);
                    ui.showLine();

                    taskList.add(new Deadline(description, LocalDate.parse(date.strip())));
                    storage.addTask(taskList.getLastStatusString());
                    break;

                }
                case "event" : {
                    String[] taskInfo = command.split(" ", 2);
                    if (taskInfo.length == 1) {
                        throw new EmptyDescriptionException("todo");
                    }

                    String[] additionalTaskInfo = taskInfo[1].split("/at", 2);
                    if (additionalTaskInfo.length == 1) {
                        throw new EmptyDetailsException("event");
                    }
                    String description = additionalTaskInfo[0];
                    String eventDetails = additionalTaskInfo[1];

                    System.out.println("added: " + command);
                    ui.showLine();
                    taskList.add(new Event(description, eventDetails));
                    storage.addTask(taskList.getLastStatusString());
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




