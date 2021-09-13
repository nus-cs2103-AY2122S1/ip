package ashy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import ashy.exceptions.AshyException;
import ashy.exceptions.EmptyKeywordException;
import ashy.exceptions.EmptyTaskDescriptionException;
import ashy.exceptions.EmptyTaskNumberException;
import ashy.exceptions.InvalidTaskException;
import ashy.exceptions.MultipleKeywordException;
import ashy.exceptions.NoTimeException;
import ashy.exceptions.TaskDoneAlreadyException;
import ashy.exceptions.UnknownInputException;
import ashy.task.Deadline;
import ashy.task.Event;
import ashy.task.Task;
import ashy.task.ToDo;
import ashy.util.AshyCommands;
import ashy.util.Parser;
import ashy.util.Storage;
import ashy.util.TaskList;
import ashy.util.Ui;

public class Ashy {
    private ArrayList<Task> commands = new ArrayList<>();
    private int doneTasks = 0;
    private Ui ui = new Ui();
    private Storage storage = new Storage();
    private Parser parser = new Parser();
    private TaskList taskList = new TaskList();
    // ...

    /**
     * Executes the commands entered
     * @param command command to be executed
     * @param input description of the command
     * @param isDone checks if the task is completed
     */
    public String execute(AshyCommands command, String input, int isDone) {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d-MM-yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("d-MM-yyyy HHmm");
        String response = "";
        try {
            boolean taskToAdd = false;
            int taskIndex;
            switch (command) {
            case TODO:
                if (input.equals("")) {
                    throw new EmptyTaskDescriptionException();
                }
                commands.add(new ToDo(input));
                if (isDone == 1) {
                    doneTasks++;
                    commands.get(commands.size() - 1).markAsDone();
                }
                storage.saveCommands(commands);
                taskToAdd = true;
                break;
            case DEADLINE:
                if (input.equals("")) {
                    throw new EmptyTaskDescriptionException();
                }
                String[] deadline = input.split("/by");
                if (deadline.length < 2) {
                    throw new NoTimeException();
                }
                LocalDate date = LocalDate.parse(deadline[1].trim(), formatter1);
                commands.add(new Deadline(deadline[0], date));
                if (isDone == 1) {
                    doneTasks++;
                    commands.get(commands.size() - 1).markAsDone();
                }
                storage.saveCommands(commands);
                taskToAdd = true;
                break;
            case EVENT:
                if (input.equals("")) {
                    throw new EmptyTaskDescriptionException();
                } else {
                    String[] event = input.split("/at");
                    if (event.length < 2) {
                        throw new NoTimeException();
                    }
                    LocalDateTime time = LocalDateTime.parse(event[1].trim(), formatter2);
                    commands.add(new Event(event[0], time));
                    if (isDone == 1) {
                        doneTasks++;
                        commands.get(commands.size() - 1).markAsDone();
                    }
                    storage.saveCommands(commands);
                    taskToAdd = true;
                    storage.saveCommands(commands);
                }
                break;
            case FIND:
                if (input.equals("")) {
                    throw new EmptyKeywordException();
                }
                String[] keyword = input.trim().split(" ");
                if (keyword.length == 1) {
                    response = taskList.find(commands, keyword[0]);
                } else if (keyword.length == 2) {
                    response = taskList.find(commands, keyword[0], keyword[1]);
                } else {
                    throw new MultipleKeywordException();
                }
                break;
            case LIST:
                response = taskList.list(commands);
                break;
            case DONE:
                if (input.equals("")) {
                    throw new EmptyTaskNumberException();
                }
                taskIndex = Integer.parseInt(input.trim());
                response = done(taskIndex - 1);
                break;
            case DELETE:
                if (input.equals("")) {
                    throw new EmptyTaskNumberException();
                }
                taskIndex = Integer.parseInt(input.trim());
                response = taskList.remove(taskIndex - 1, commands);
                break;
            case UPDATE:
                if (input.equals("")) {
                    throw new EmptyTaskNumberException();
                }
                String [] update = input.split(":", 2);
                int taskNumber = Integer.parseInt(update[0].trim());
                if (update.length < 2) {
                    throw new EmptyTaskDescriptionException();
                }
                String description = update[1].trim();
                response = taskList.updateDescription(commands, description, taskNumber - 1);
                break;
            case HELP:
                response = ui.helpOutput();
                break;
            default:
                throw new UnknownInputException();
            }
            if (taskToAdd) {
                response = "I have added this task: \n" + commands.get(commands.size() - 1)
                        + "\n" + "You now have " + commands.size() + " tasks in your list";
            }
        } catch (AshyException e) {
            response = e.getMessage();
        }
        return response;
    }

    String done(int listNumber) {
        String response;
        try {
            if (listNumber >= commands.size()) {
                throw new InvalidTaskException();
            }
            if (commands.get(listNumber).getIsDone()) {
                throw new TaskDoneAlreadyException();
            }
            commands.get(listNumber).markAsDone();
            doneTasks++;
            storage.saveCommands(commands);
            response = ui.doneOutput(commands.get(listNumber), (commands.size() - doneTasks));
        } catch (AshyException e) {
            response = e.getMessage();
        }
        storage.saveCommands(commands);
        return response;
    }

    /**
     * Execute the program
     */
    String run(String input) {
        String response;
        ui.greetingMessage();
        String []parse = input.split(" ", 2);
        String command = parse[0].trim();
        String description = "";
        if (parse.length >= 2) {
            description = parse[1].trim();
        }
        try {
            AshyCommands dukeCommand = parser.parseCommand(command);
            response = execute(dukeCommand, description, 0);
        } catch (IllegalArgumentException e) {
            response = "I'm sorry, I don't know what that means! ☹\nUse the help command to learn my commands!";
        }
        return response;
    }

    public static void main(String[] args) {
        //new Duke().run();
    }
}
