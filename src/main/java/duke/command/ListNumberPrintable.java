package duke.command;

import duke.tasklist.TaskList;

/**
 * This interface allows a contract to be created to return the string of a given TaskList.
 */
public interface ListNumberPrintable {

    String printListNumber(TaskList taskList);

}
