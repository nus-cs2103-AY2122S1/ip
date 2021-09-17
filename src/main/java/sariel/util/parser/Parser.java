package sariel.util.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.function.Predicate;

import sariel.util.commands.AddCommand;
import sariel.util.commands.CommandList;
import sariel.util.commands.DelCommand;
import sariel.util.commands.DoneCommand;
import sariel.util.commands.ExitCommand;
import sariel.util.commons.Messages;
import sariel.util.tasks.DateTaskTable;
import sariel.util.tasks.DatedTask;
import sariel.util.tasks.Deadline;
import sariel.util.tasks.DukeException;
import sariel.util.tasks.Event;
import sariel.util.tasks.Task;
import sariel.util.tasks.TaskList;
import sariel.util.tasks.ToDo;
import sariel.util.ui.Ui;



/**
 * The class representing the parser that interprets the input.
 *
 */

public class Parser {


    private static final String DONE = "done";
    private static final String DELETE = "delete";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    //the command for listing all the tasks under a certain date.
    private static final String DLIST = "dlist";
    private static final String LIST = "list";
    private static final String BYE = "bye";
    private static final String FIND = "find";
    private static final String REMIND = "remind";
    //is it just me or does the parser
    //have to contain all the objects to send the information to
    private final Ui ui;
    private final TaskList taskList;
    private final DateTaskTable dateTaskList;

    /**
     * Constructor for the parser class.
     *
     * @param ui The ui that displays the information.
     * @param taskList The tasklist that contains the tasks.
     * @param dateTaskList The datetasktable that contains the datetasks as well.
     */
    public Parser(Ui ui, TaskList taskList, DateTaskTable dateTaskList) {
        this.ui = ui;
        this.taskList = taskList;
        this.dateTaskList = dateTaskList;
    }






    /**
     * To understand the input.
     *
     * @param input The input String
     * @throws DukeException Exception due to wrong input.
     */
    public CommandList inputsParser(String input) throws DukeException, DateTimeParseException {
        String[] twoInputs = input.split(" ", 2);
        String cmd = twoInputs[0];
        CommandList commands = new CommandList();
        if (twoInputs.length == 1) {
            noArgumentParser(cmd, commands);
        } else {
            String description = twoInputs[1];
            oneArgumentParser(cmd, commands, description);
        }
        return commands;
    }

    private void noArgumentParser(String cmd, CommandList commands) throws DukeException {
        switch(cmd) {
        case BYE:
            commands.add(new ExitCommand());
            break;
        case REMIND:
            find(commands, task -> {
                if (task.isDated()) {
                    return task.getDate().equals(LocalDate.now());
                }
                return false;
            });
            break;
        case LIST:
            listCommandFromArray(commands, this.taskList);
            break;
        case DELETE:
            //fallthrough
        case DLIST:
            //fallthrough
        case DONE:
            //fallthrough
        case TODO:
            //fallthrough
        case DEADLINE:
            //fallthrough
        case EVENT:
            throw new DukeException(String.format(Messages.TASK_NO_DESCRIPTOR_ERROR, cmd));
        default:
            throw new DukeException(Messages.TASK_NOT_UNDERSTOOD_ERROR);
        }
    }

    private void oneArgumentParser(String cmd, CommandList commands, String description) throws DukeException {
        switch (cmd) {
        case FIND:
            find(commands, t -> t.containsPattern(description));
            break;
        case DONE:
            markAsDone(commands, description);
            break;
        case DLIST:
            dlist(commands, description);
            break;
        case DELETE:
            delete(commands, description);
            break;
        case TODO:
            todo(commands, description);
            break;
        case EVENT:
            Event e = Event.of(description);
            addDatedTask(commands, e);
            break;
        case DEADLINE:
            Deadline d = Deadline.of(description);
            addDatedTask(commands, d);
            break;
        default:
            throw new DukeException(Messages.TASK_NOT_UNDERSTOOD_ERROR);
        }
    }

    private void dlist(CommandList commands, String description) {
        ArrayList<DatedTask> ls = dateTaskList.get(dateParse(description.trim()));
        listCommandFromArray(commands, ls);
    }

    private void delete(CommandList commands, String description) {
        commands.add(new DelCommand(Integer.parseInt(description), this.taskList, this.dateTaskList));
    }

    private void todo(CommandList commands, String description) {
        Task t = ToDo.of(description);
        commands.add(new AddCommand(taskList, t));
    }

    private void find(CommandList commands, Predicate<Task> predicate) {
        commands.add(() -> ui.list(taskList.filter(predicate)));
    }

    private void markAsDone(CommandList commands, String description) throws DukeException {
        int i = Integer.parseInt(description) - 1;
        if (i > taskList.size() || i < 0) {
            throw new DukeException(Messages.INVALID_DONE_INPUT);
        }
        Task b = taskList.get(i);
        commands.add(new DoneCommand(b, this.ui));
    }

    private void listCommandFromArray(CommandList cmds, ArrayList<? extends Task> ls) {
        cmds.add(() -> {
            if (ls != null) {
                return ui.list(ls);
            }
            return "";
        });
    }

    private void addDatedTask(CommandList cmds, DatedTask task) {
        cmds.add(new AddCommand(taskList, task));
        cmds.add(() -> {
            dateTaskList.add(task);
            return "";
        });
    }





    /**
     * Method to parse the string into a date.
     * Goal: To be able to parse as many possible formats
     * as possible. (TBC)
     *
     * @param s The string to parse
     * @return The LocalDate object representing the input date.
     */
    public static LocalDate dateParse(String s) {
        return LocalDate.parse(s.trim());
    }
}
