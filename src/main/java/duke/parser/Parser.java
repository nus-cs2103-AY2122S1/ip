package duke.parser;

import duke.commands.Deadline;
import duke.commands.Event;
import duke.commands.Task;
import duke.commands.Todo;
import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Parser {
    private TaskList tasks;
    private Ui ui;

    public enum Keyword {
        TODO, LIST, DEADLINE, EVENT, DONE, DELETE, FIND
    }

    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public void parse(String cmd) throws DukeException {
        String[] tokens = cmd.split(" ");
        Keyword command = validifyCommand(tokens);
        int middle;
        int index;
        String description;
        String dateString;
        LocalDateTime at;
        LocalDateTime by;
        Task task;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        switch (command) {
            case LIST:
                this.tasks.iterList();
                break;
            case TODO:
                task = new Todo(cmd.substring(5));
                this.tasks.add(task);
                ui.addTaskMsg(task);
                break;
            case DEADLINE:
                middle = Arrays.asList(tokens).indexOf("/by");
                if (middle == -1) {
                    throw new DukeException("Missing deadline.");
                }
                description = String.join(" ", Arrays.copyOfRange(tokens, 1, middle));
                dateString = String.join(" ", Arrays.copyOfRange(tokens, middle + 1, tokens.length));
                try {
                    by = LocalDateTime.parse(dateString, format);
                    task = new Deadline(description, by);
                    this.tasks.add(task);
                    ui.addTaskMsg(task);
                } catch (DateTimeParseException e) {
                    System.out.println("Please enter a date in the following format: dd/MM/yyyy HHmm");
                }
                break;
            case EVENT:
                middle = Arrays.asList(tokens).indexOf("/at");
                if (middle == -1) {
                    throw new DukeException("Missing time of event.");
                }
                description = String.join(" ", Arrays.copyOfRange(tokens, 1, middle));
                dateString = String.join(" ", Arrays.copyOfRange(tokens, middle + 1, tokens.length));
                try {
                    at = LocalDateTime.parse(dateString, format);
                    task = new Event(description, at);
                    this.tasks.add(task);
                    ui.addTaskMsg(task);
                } catch (DateTimeParseException e) {
                    System.out.println("Please enter a date in the following format: dd/MM/yyyy HHmm");
                }
                break;
            case DONE:
                index = Integer.parseInt(tokens[1]) - 1;
                task = this.tasks.get(index);
                this.tasks.complete(index);
                ui.completeTaskMsg(task);
                break;
            case DELETE:
                index = Integer.parseInt(tokens[1]) - 1;
                task = this.tasks.get(index);
                this.tasks.delete(index);
                ui.deleteTaskMsg(task);
                ui.listCountMsg();
                break;
            case FIND:
                String keyword = cmd.substring(5);
                tasks.searchKeyword(keyword);
                break;
        }
    }

    private static Keyword validifyCommand (String[]cmd) throws DukeException {
        Keyword keyword;
        try {
            keyword = Keyword.valueOf(cmd[0]);
            if ((keyword.equals(Keyword.TODO) || keyword.equals(Keyword.DEADLINE) || keyword.equals(Keyword.EVENT))
                    && cmd.length < 2) {
                throw new DukeException(String.format("☹ OOPS!!! The description of a %s cannot be empty.", keyword));
            }
            if ((keyword.equals(Keyword.DONE) || keyword.equals(Keyword.DELETE) || keyword.equals(Keyword.FIND))
                    && (cmd.length < 2)) {
                throw new DukeException("☹ OOPS!!! Missing arguments.");
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return keyword;
    }
}
