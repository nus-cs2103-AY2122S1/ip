package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyTaskDescriptionException;
import duke.exceptions.EmptyTaskNumberException;
import duke.exceptions.InvalidTaskException;
import duke.exceptions.MultipleKeywordException;
import duke.exceptions.NoTimeException;
import duke.exceptions.TaskDoneAlreadyException;
import duke.exceptions.UnknownInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.util.DukeCommands;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class Duke {
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
    public String execute(DukeCommands command, String input, int isDone) {
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
                } else {
                    commands.add(new ToDo(input));
                    if (isDone == 1) {
                        doneTasks++;
                        commands.get(commands.size() - 1).markAsDone();
                    }
                    taskToAdd = true;
                }
                break;
            case DEADLINE:
                if (input.equals("")) {
                    throw new EmptyTaskDescriptionException();
                } else {
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
                    taskToAdd = true;
                }
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
                    taskToAdd = true;
                }
                break;
            case FIND:
                if (input.equals("")) {
                    throw new MultipleKeywordException();
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
                } else {
                    taskIndex = Integer.parseInt(input.trim());
                    response = done(taskIndex - 1);
                }
                break;
            case DELETE:
                if (input.equals("")) {
                    throw new EmptyTaskNumberException();
                } else {
                    taskIndex = Integer.parseInt(input.trim());
                    response = taskList.remove(taskIndex - 1, commands);
                }
                break;
            default:
                throw new UnknownInputException();
            }
            if (taskToAdd) {
                response = "I have added this task: \n" + commands.get(commands.size() - 1)
                        + "\n" + "You now have " + commands.size() + " tasks in your list";
            }
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }

    String done(int listNumber) {
        String response = "";
        try {
            if (listNumber < commands.size()) {
                if (!commands.get(listNumber).isDone) {
                    commands.get(listNumber).markAsDone();
                } else {
                    throw new TaskDoneAlreadyException();
                }
                doneTasks++;
                response = ui.doneOutput(commands.get(listNumber), (commands.size() - doneTasks));
            } else {
                throw new InvalidTaskException();
            }
        } catch (DukeException e) {
            response = e.getMessage();
        }
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
            DukeCommands dukeCommand = parser.parseCommand(command);
            response = execute(dukeCommand, description, 0);
        } catch (IllegalArgumentException e) {
            response = "I'm sorry, I don't know what that means! ☹";
        }
        return response;
    }

    public static void main(String[] args) {
        //new Duke().run();
    }
}
