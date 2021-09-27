package sora.util;

import sora.exception.SoraException;
import sora.task.TaskList;

/**
 * An interface which is similar to Function< TaskList, String >.
 * This is able to handle SoraException.
 *
 * @author Zhang Shi Chen
 */
public interface CheckedFunction {
    String execute(TaskList taskList) throws SoraException;
}
