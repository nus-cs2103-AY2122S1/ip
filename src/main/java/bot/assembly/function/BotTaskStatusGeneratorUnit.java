package bot.assembly.function;

import bot.assembly.memory.BotStaticMemoryUnit;
import bot.assembly.memory.BotDynamicMemoryUnit;
import bot.assembly.error.EmptyTaskListException;

import bot.assembly.task.Task;

import java.util.List;

/**
 * A class that handles generation of responses to the user
 */
public class BotTaskStatusGeneratorUnit {

    private BotPrinter botPrinter = new BotPrinter();
    private BotStaticMemoryUnit botStaticMemoryUnit = new BotStaticMemoryUnit();
    private BotDynamicMemoryUnit botDynamicMemoryUnit = BotDynamicMemoryUnit.getInstance();
    private List<Task> taskTracker = botDynamicMemoryUnit.taskTracker;

    /**
     * Constructor
     */
    public BotTaskStatusGeneratorUnit() {}

    /**
     * A method that generates a feedback message to the user when a task is added
     */
    public void generateAddTaskFeedback() {

        String output = String.format(
                "%s\n\t\t%s\n\t",
                botStaticMemoryUnit.MESSAGE_ADD_TASK_NOTICE,
                taskTracker.get(taskTracker.size()-1).toString()
        );

        output+= String.format(
                botStaticMemoryUnit.MESSAGE_ADD_TASK_SUMMARY,
                taskTracker.size()
        );

        botPrinter.print(output);
    }

    /**
     * A method that generates a summary report of all task in task list to the user
     * @throws EmptyTaskListException if the task list is empty
     */
    public void generateTaskTrackerReport() throws EmptyTaskListException {

        // throw empty list exception if task list is empty
        if (taskTracker.size() == 0) {
            throw new EmptyTaskListException(botStaticMemoryUnit.ERROR_MESSAGE_EMPTY_TASKLIST);
        }

        StringBuilder formattedTask = new StringBuilder();
        formattedTask.append(botStaticMemoryUnit.MESSAGE_TASK_REPORT + "\n\t");
        taskTracker.stream().
                forEach(
                        x -> formattedTask.append(
                                (taskTracker.indexOf(x) + 1)
                                        + ". "
                                        + x.toString()
                                        + "\n\t"
                        )
                );

        formattedTask.append("(end)");

        botPrinter.print(formattedTask.toString());
    }


}
