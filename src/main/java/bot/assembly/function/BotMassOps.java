package bot.assembly.function;

import java.util.List;

import bot.assembly.task.Task;

/**
 * A class that is responsible for massOps command operations
 */
public class BotMassOps {

    /**
     * Constructor
     */
    public BotMassOps() {
    }

    /**
     * A method that delete all items in the tasklist.
     * @param taskList
     */
    public void deleteAll(List<Task> taskList) {
        taskList.clear();
    }

    /**
     * A method that marks everything as done in the tasklist.
     * @param taskList
     */
    public void doneAll(List<Task> taskList) {
        taskList.stream().forEach(x -> x.markAsDone());
    }
}
