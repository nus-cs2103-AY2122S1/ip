package util.parser;

import util.tasks.DukeException;
import util.tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import util.commands.*;
import util.ui.*;
import util.tasks.*;
import util.storage.*;

public class Parser {


    private static final String TASK_NO_DESCRIPTOR_ERROR = "☹ OOPS!!! The description of a %s cannot be empty.";
    //probably have to provide some more helpful documentation
    //maybe error messages should be handled by the ui.
    private static final String TASK_NOT_UNDERSTOOD_ERROR = "☹ OOPS!!! I do no know what to do";
    private static final String DELETE = "delete";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    //the command for listing all the tasks under a certain date.
    private static final String DLIST = "dlist";
    private static final String LIST = "list";

    //is it just me or does the parser
    //have to contain all the objects to send the information to
    private final Ui ui;
    private final TaskList tasklist;
    private final DateTaskTable dateTaskList;



    public Parser(Ui ui, TaskList tasklist) {
        this.ui = ui;
        this.tasklist = tasklist;
        this.dateTaskList = new DateTaskTable();
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
                case LIST:
                    cmds.add(() -> ui.list(this.tasklist));
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    throw new DukeException(String.format(TASK_NO_DESCRIPTOR_ERROR, cmd));
                default:
                    throw new DukeException(TASK_NOT_UNDERSTOOD_ERROR);
            }
        } else {

            String description = twoInputs[1];
            switch (cmd) {
                case DLIST:
                    //to implement such a filter in tasklist
                    cmds.add(() -> ui.list(dateTaskList.get(LocalDate.parse(description))));
                case DELETE:
                    cmds.add(new DelCommand(Integer.parseInt(description), this.tasklist));
                    break;
                case TODO:
                    Task t = ToDos.of(description);
                    cmds.add(new AddCommand(tasklist, t));
                    break;
                case EVENT:
                    Events e = Events.of(description);
                    cmds.add(new AddCommand(tasklist, e));
                    cmds.add(() -> dateTaskList.add(e));
                    break;
                case DEADLINE:
                    Deadlines d = Deadlines.of(description);
                    cmds.add(new AddCommand(tasklist, d));
                    cmds.add(() -> dateTaskList.add(d));
                    break;
                default:
                    throw new DukeException(TASK_NOT_UNDERSTOOD_ERROR);

            }

        }
        return cmds;
    }

}
