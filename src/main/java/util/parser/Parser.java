package util.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import util.commands.AddCommand;
import util.commands.CommandList;
import util.commands.DelCommand;
import util.commands.DoneCommand;
import util.commands.ExitCommand;
import util.commons.Messages;
import util.tasks.DateTaskTable;
import util.tasks.DatedTask;
import util.tasks.Deadline;
import util.tasks.DukeException;
import util.tasks.Event;
import util.tasks.Task;
import util.tasks.TaskList;
import util.tasks.ToDo;
import util.ui.Ui;



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
        CommandList cmds = new CommandList();

        if (twoInputs.length == 1) {
            //when there is only one input
            switch(cmd) {
            case BYE:
                cmds.add(new ExitCommand());
                break;
            case LIST:
                cmds.add(() -> ui.list(this.taskList));
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
        } else {

            String description = twoInputs[1];
            switch (cmd) {

            case FIND:
                cmds.add(() -> ui.list(taskList.filter(t -> t.contains(description.trim()))));
                break;
            case DONE:
                int i = Integer.parseInt(description) - 1;
                if (i > taskList.size() || i < 0) {
                    throw new DukeException(Messages.INVALID_DONE_INPUT);
                }
                Task b = taskList.get(i);
                cmds.add(new DoneCommand(b, this.ui));
                break;
                //dlist does not work -- suspect is coz of the hashmap
            case DLIST:
                //to implement such a filter in tasklist
                ArrayList<DatedTask> ls = dateTaskList.get(dateParse(description.trim()));
                cmds.add(() -> {
                    if (ls != null) {
                        ui.list(ls);
                    }
                    return "";
                });
                break;
            case DELETE:
                cmds.add(new DelCommand(Integer.parseInt(description), this.taskList, this.dateTaskList));
                break;
            case TODO:
                Task t = ToDo.of(description);
                cmds.add(new AddCommand(taskList, t));
                break;
            case EVENT:
                Event e = Event.of(description);
                cmds.add(new AddCommand(taskList, e));
                cmds.add(() -> {
                    dateTaskList.add(e);
                    return "";
                });
                break;
            case DEADLINE:
                Deadline d = Deadline.of(description);
                cmds.add(new AddCommand(taskList, d));
                cmds.add(() -> {
                    dateTaskList.add(d);
                    return "";
                });
                break;
            default:
                throw new DukeException(Messages.TASK_NOT_UNDERSTOOD_ERROR);

            }

        }
        return cmds;
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
