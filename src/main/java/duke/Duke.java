package duke;

import duke.exception.DukeFileSystemException;
import duke.exception.InvalidDukeCommandException;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class Duke {
    // Constant declarations
    private final Ui ui;
    private final DukeParser parser;
    private Optional<Storage> storage;
    private TaskList taskList;

    private void todoHandler(String args) throws InvalidDukeCommandException {
        if (args.equals("")) {
            throw new InvalidDukeCommandException("The description of a todo cannot be empty.");
        }
        taskList.appendTask(new Todo(args));
        String returnMessage = String.format("Got it. I've added this task:\n%s\nNow you have %d task%s in the list.",
                args, taskList.size(), taskList.size() > 1 ? "s" : "");
        ui.dukePrint(returnMessage);
    }

    private void eventHandler(String args) throws InvalidDukeCommandException {
        if (args.equals("")) {
            throw new InvalidDukeCommandException("The description of an event cannot be empty.");
        }
        String taskDescription = args.split(" /at ")[0];
        String eventTime;
        try {
            eventTime = args.split(" /at ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidDukeCommandException(
                    "Incorrect description format. Description should follow this pattern: *description* /at *time*");
        }
        taskList.appendTask(new Event(taskDescription, eventTime));
        String returnMessage = String.format("Got it. I've added this task:\n%s\nNow you have %d task%s in the list.",
                taskDescription, taskList.size(), taskList.size() > 1 ? "s" : "");
        ui.dukePrint(returnMessage);
    }

    private void deadlineHandler(String args) throws InvalidDukeCommandException {
        if (args.equals("")) {
            throw new InvalidDukeCommandException("The description of a deadline cannot be empty.");
        }
        String taskDescription = args.split(" /by ")[0];
        String finishDate;
        try {
            finishDate = args.split(" /by ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidDukeCommandException(
                    "Incorrect description format. Description should follow this pattern: *description* /by *time*");
        }
        taskList.appendTask(new Deadline(taskDescription, finishDate));
        String returnMessage = String.format("Got it. I've added this task:\n%s\nNow you have %d task%s in the list.",
                taskDescription, taskList.size(), taskList.size() > 1 ? "s" : "");
        ui.dukePrint(returnMessage);
    }

    private void listHandler() {
        ui.dukePrint(taskList.toString());
    }

    private void doneHandler(String args) throws InvalidDukeCommandException {
        if (args.equals("")) {
            throw new InvalidDukeCommandException(
                    "The done command expects an integer argument indicating the index of a task.");
        }
        Task task;
        try {
            task = taskList.getTask(Integer.parseInt(args));
        } catch (NumberFormatException e) {
            throw new InvalidDukeCommandException("Invalid argument for done command. Argument should be an integer.");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDukeCommandException("Invalid integer. Integer should match the index of a task."
                    + " Run list to look at the list of tasks and their corresponding indices.");
        }
        String acknowledgementMessage = "Nice! I've marked this task as done:\n";
        task.markAsDone();
        ui.dukePrint(acknowledgementMessage + task);
    }

    private void deleteHandler(String args) throws InvalidDukeCommandException {
        if (args.equals("")) {
            throw new InvalidDukeCommandException(
                    "The delete command expects an integer argument indicating the index of a task.");
        }
        Task task;
        try {
            task = taskList.deleteTask(Integer.parseInt(args));
        } catch (NumberFormatException e) {
            throw new InvalidDukeCommandException(
                    "Invalid argument for delete command. Argument should be an integer.");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDukeCommandException("Invalid integer. Integer should match the index of a task."
                    + " Run list to look at the list of tasks and their corresponding indices.");
        }
        String returnMessage = String.format("Noted. I've removed this task:\n%s\nNow you have %d task%s in the list.",
                task, taskList.size(), taskList.size() > 1 ? "s" : "");
        ui.dukePrint(returnMessage);
    }

    private void byeHandler() {
        ui.dukePrint("Bye. Hope to see you again soon!");
    }

    private void defaultHandler() throws InvalidDukeCommandException {
        throw new InvalidDukeCommandException("Invalid command detected");
    }

    /**
     * Designates the job to the appropriate handler based on the duke command and args
     *
     * @param command     the Duke command read by the parser.
     * @param argsLiteral the arguments read by the parser.
     */
    private void handleInput(DukeCommand command, String argsLiteral) {
        try {
            switch (command) {
            case DONE:
                doneHandler(argsLiteral);
                break;
            case BYE:
                byeHandler();
                break;
            case LIST:
                listHandler();
                break;
            case TODO:
                todoHandler(argsLiteral);
                break;
            case EVENT:
                eventHandler(argsLiteral);
                break;
            case DEADLINE:
                deadlineHandler(argsLiteral);
                break;
            case DELETE:
                deleteHandler(argsLiteral);
                break;
            case INVALID:
                defaultHandler();
                // Fallthrough
            }
            if (storage.isPresent()) {
                storage.get().writeTasksToFile(this.taskList);
            }
        } catch (InvalidDukeCommandException e) {
            ui.dukePrint(e.getMessage());
        } catch (IOException e) {
            ui.dukeShowError("Error occurred while storing tasks in data file.");
        }
    }

    /**
     * Prompts users to input their commands to Duke
     */
    private void promptUserCommands() {
        String introduction = "Hello! I'm Duke\nWhat can I do for you?";
        ui.dukePrint(introduction);

        String userInput;
        Scanner reader = new Scanner(System.in);
        while (true) {
            userInput = reader.nextLine();
            DukeCommand command = parser.getCommandType(userInput);
            String argsLiteral = parser.getArgsLiteral(userInput);
            handleInput(command, argsLiteral);
            if (command == DukeCommand.BYE) {
                break;
            }
        }
        reader.close();
    }

    public Duke(String filePath) {
        this.ui = new Ui();
        this.parser = new DukeParser();
        try {
            this.storage = Optional.of(new Storage(filePath));
            this.taskList = this.storage.map(value -> new TaskList(value.load())).orElseGet(TaskList::new);
        } catch (IOException e) {
            ui.dukeShowError("The file path " + filePath + " is invalid. This session will not be stored.");
            this.storage = Optional.empty();
        } catch (DukeFileSystemException e) {
            ui.dukePrint(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    public void run() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        promptUserCommands();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
