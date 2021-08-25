package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Description:
 * Duke the ChatBot allows users to add 3 different types of tasks, mark them as done, and delete tasks.
 * The commands for usage are as follows:
 * 1. "todo <name>" where name is what the user would like the todo to be called.
 * 2. "event <name> /at <date>" where name is what the user would like the event to be called and date is when the event is happening.
 * 3. "deadline <name> /by <date>" where name is what the user would like the deadline to be called and date is when the deadline is set.
 * 4. "list" to view current tasks added to the tasks list.
 * 5. "done <number>" where number is the task with the corresponding number in the list which the user would like to mark as completed.
 * 6. "delete <number>" where number is the task with the corresponding number in the list which the user would like to remove.
 * 7. "bye" to leave the ChatBot.
 * Disclaimer: any other commands will not be recognised and user will be prompted to enter a valid command.
 *
 * @author Leong Hong Fai
 */

public class Duke {
    private static final String fileAddress = "data/duke.txt";

    private static Storage storage;
    private static TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            //ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public void run() {
        commands();
    }


    /**
     * Adds a ToDo to the list of tasks.
     *
     * @param command entered by the user.
     * @throws DukeException upon invalid command format.
     */
    public void addToDo(String command, boolean printOutput) throws DukeException {
        if (command.length() < 6 || command == null) {
            throw new DukeException("invalidToDo");
        } else {
            String name = command.substring(5);
            Task task = new ToDo(name);
            tasks.add(task);
            if (printOutput) {
                try {
                    storage.appendToFile(fileAddress, "T - 0 - " + name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ui.printTaskAdded(task, tasks.size());
            }
        }

    }

    /**
     * Adds a deadline to the list of tasks.
     *
     * @param command entered by the user.
     * @throws DukeException upon invalid command format.
     */
    public void addDeadline(String command, boolean printOutput) throws DukeException {
        String[] words = command.split(" ");
        if (words.length <= 3 ) {
            throw new DukeException("invalidDeadline");
        } else if (!command.contains("/by")) {
            throw new DukeException("invalidDeadline");
        } else {
            try {
                LocalDate.parse(words[3]);
                int position = command.indexOf("/by");
                String name = command.substring(9, position);
                String date = command.substring(position + 4);
                Task task  = new Deadline(name, date);
                tasks.add(task);
                if (printOutput) {
                    storage.appendToFile(fileAddress, "D - 0 - " + name + "- " + date);
                    ui.printTaskAdded(task, tasks.size());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DateTimeParseException e) {
                System.out.println("Enter valid date format!");
            }
        }
    }

    /**
     * Adds event to the list of tasks.
     *
     * @param command entered by the user.
     * @throws DukeException upon invalid command format.
     */
    public void addEvent(String command, boolean printOutput) throws DukeException {
        String[] words = command.split(" ");
        if (words.length <= 3 ) {
            throw new DukeException("invalidEvent");
        } else if (!command.contains("/at")) {
            throw new DukeException("invalidEvent");
        } else {
            try {
                LocalDate.parse(words[3]);
                int position = command.indexOf("/at");
                String name = command.substring(6, position);
                String date = command.substring(position + 4);
                Task task  = new Event(name, date);
                tasks.add(task);
                if (printOutput) {
                    storage.appendToFile(fileAddress, "E - 0 - " + name + "- " + date);
                    ui.printTaskAdded(task, tasks.size());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DateTimeParseException e) {
                System.out.println("Enter valid date format!");
            }
        }
    }

    /**
     * Marks a specific task in the list as completed.
     *
     * @param command entered by the user.
     * @throws DukeException upon incorrect command format.
     */
    public void markCompleted(String command, boolean printOutput) throws DukeException {
        boolean numeric;
        String restOfCommand ="";
        try {
            restOfCommand = command.substring(5);
            int temp = Integer.parseInt(restOfCommand);
            numeric = true;
        } catch (NumberFormatException err) {
            numeric = false;
        } catch (StringIndexOutOfBoundsException err) {
            throw new DukeException("invalidNumberFormat");
        }
        if (numeric) {
            int taskNum = Integer.parseInt(restOfCommand) - 1;
            if (taskNum < tasks.size()) {
                Task currTask = tasks.get(taskNum);
                currTask.setCompleted();
                if (printOutput) {
                    storage.editFileContentsForCompletion(taskNum + 1);
                    ui.printTaskCompleted(currTask);
                }
            } else {
                throw new DukeException("invalidTaskNumber");
            }
        } else {
            throw new DukeException("invalidNumberFormat");
        }
    }

    /**
     * Deletes a specified task from the list of tasks.
     *
     * @param command entered by the user.
     * @throws DukeException upon incorrect command format.
     */
    private void deleteTask(String command) throws DukeException {
        String restOfCommand = command.substring(7);
        boolean numeric;
        try {
            int temp = Integer.parseInt(restOfCommand);
            numeric = true;
        } catch (NumberFormatException err) {
            numeric = false;
        }
        if (numeric) {
            int taskNum = Integer.parseInt(restOfCommand) - 1;
            if (taskNum < tasks.size()) {
                storage.editFileContentsForDeletion(taskNum + 1);
                Task currTask = tasks.get(taskNum);
                tasks.remove(taskNum);
                ui.printDeleteTask(currTask, tasks.size());
            } else {
                throw new DukeException("invalidTaskNumber");
            }
        } else {
            throw new DukeException("invalidNumberFormatDelete");
        }
    }

    private void commands() throws DukeException {
        ui.start();
        String command;
        while (!(command = ui.readCommand()).equals("bye")) {
            String parsed = Parser.process(command);
            try {
                switch (parsed) {
                    case ("list"):
                        tasks.printList(command);
                        break;
                    case ("todo"):
                        addToDo(command, true);
                        break;
                    case ("deadline"):
                        addDeadline(command, true);
                        break;
                    case ("event"):
                        addEvent(command, true);
                        break;
                    case ("done"):
                        markCompleted(command, true);
                        break;
                    case ("delete"):
                        deleteTask(command);
                        break;
                    default:
                        throw new DukeException("invalidCommand");
                }
            } catch (DukeException err) {
                ui.printError(err);
            }
        }
        ui.end();
        }
    }





