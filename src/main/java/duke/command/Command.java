package duke.command;

/**
 * Enums for each command type. INDEXCOMMANDs are "done" and "delete"
 * as they refer to tasks by their index. ADDCOMMANDs are "todo", "deadline" and
 * "event" as they add new tasks to the tasklist
 */
public enum Command {
     EXIT,
     LIST,
     INDEXCOMMAND,
     ADDCOMMAND,
     UNKNOWN,
     FIND,
     UPDATE,
}
