package bot.assembly.function;

import java.util.List;

import bot.assembly.error.EmptyTaskListException;
import bot.assembly.memory.BotDynamicMemoryUnit;
import bot.assembly.memory.BotStaticMemoryUnit;
import bot.assembly.task.Task;

/**
 * A class that handles generation of responses to the user
 */
public class BotTaskStatusGeneratorUnit {

    private BotStaticMemoryUnit botStaticMemoryUnit = new BotStaticMemoryUnit();
    private BotDynamicMemoryUnit botDynamicMemoryUnit = BotDynamicMemoryUnit.getInstance();
    private List<Task> taskTracker = botDynamicMemoryUnit.getTaskTacker();

    /**
     * Constructor
     */
    public BotTaskStatusGeneratorUnit() {}

    /**
     * A method that generates a feedback message to the user when a task is added
     */
    public String generateAddTaskFeedback() {

        String output = String.format(
                "%s\n%s\n",
                botStaticMemoryUnit.MESSAGE_ADD_TASK_NOTICE,
                taskTracker.get(taskTracker.size() - 1).toString()
        );

        output += String.format(
                botStaticMemoryUnit.MESSAGE_ADD_TASK_SUMMARY,
                taskTracker.size()
        );

        return output;
    }

    /**
     * A method that generates a summary report of all task in task list to the user
     * @return task reports
     */
    public String generateTaskTrackerReport() {

        // throw empty list exception if task list is empty
        if (taskTracker.size() == 0) {
            return botStaticMemoryUnit.MESSAGE_EMPTY_TASKLIST;
        }

        StringBuilder formattedTask = new StringBuilder();
        formattedTask.append(botStaticMemoryUnit.MESSAGE_TASK_REPORT + "\n");

        taskTracker.stream()
                .forEach(x -> formattedTask.append((taskTracker.indexOf(x) + 1) + ". " + x.toString() + "\n"));

        formattedTask.append("(end)");

        return formattedTask.toString();
    }
}
