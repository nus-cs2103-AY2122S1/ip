import java.util.List;

public class BotTaskStatusGeneratorUnit {

    BotPrinter botPrinter = new BotPrinter();
    BotStaticMemoryUnit botStaticMemoryUnit = new BotStaticMemoryUnit();
    BotDynamicMemoryUnit botDynamicMemoryUnit = BotDynamicMemoryUnit.getInstance();
    List<Task> taskTracker = botDynamicMemoryUnit.taskTracker;

    public BotTaskStatusGeneratorUnit() {}

    /**
     *  A method to feedback to user of the new task added
     */
    public void generateTaskFeedback(){

        String output = String.format(
                "%s\n\t\t%s\n\t",
                botStaticMemoryUnit.MESSAGE_ADD_TASK_NOTICE,
                taskTracker.get(taskTracker.size()-1).toString());

        output+= String.format(
                botStaticMemoryUnit.MESSAGE_ADD_TASK_SUMMARY,
                taskTracker.size());

        botPrinter.print(output);
    }

    /**
     * A method to format all tasks from the list into a single string for printing
     * @return (String) formatted list of tasks
     * @throws EmptyTaskListException for empty list
     */
    public void reportTaskTracker() throws EmptyTaskListException {

        // throw empty list exception if task list is empty
        if (taskTracker.size() == 0) {
            throw new EmptyTaskListException(botStaticMemoryUnit.ERROR_MESSAGE_EMPTY_TASKLIST);
        }

        StringBuilder formattedTask = new StringBuilder();
        formattedTask.append(botStaticMemoryUnit.MESSAGE_TASK_REPORT + "\n\t");
        taskTracker.stream().
                forEach(x -> formattedTask.append((taskTracker.indexOf(x) + 1) + ". " + x.toString() + "\n\t"));
        formattedTask.append("(end)");

        botPrinter.print(formattedTask.toString());
    }
}
