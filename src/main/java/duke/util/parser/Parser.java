package duke.util.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.util.commands.AddCommand;
import duke.util.commands.CommandList;
import duke.util.commands.DelCommand;
import duke.util.commands.DoneCommand;
import duke.util.commands.ExitCommand;
import duke.util.commons.Messages;
import duke.util.tasks.DateTaskTable;
import duke.util.tasks.DatedTask;
import duke.util.tasks.Deadline;
import duke.util.tasks.DukeException;
import duke.util.tasks.Event;
import duke.util.tasks.Task;
import duke.util.tasks.TaskList;
import duke.util.tasks.ToDo;
import duke.util.ui.Ui;



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
        case LIST:
            listCommandFromArray(commands, this.taskList);
            break;
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
            find(commands, description);
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

    private void find(CommandList commands, String description) {
        commands.add(() -> ui.list(taskList.filter(t -> t.contains(description.trim()))));
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
