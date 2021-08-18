import java.util.List;
import java.util.Scanner;

public class BotBrain {

    private BotMemory botMemory = new BotMemory();
    private BotPrinter botPrinter = new BotPrinter();
    private List<Task> taskTracker = botMemory.taskTracker;

    BotBrain() {

    }

    private String[] tokenize(String input) {
        String[] token = input.split(" ", 2);
        return token;
    }

    private void classifyTask(String input) throws InvalidCommandException, InvalidCommandFormatException{
        String[] inputToken = tokenize(input);
        String taskType = inputToken[0];

        switch (taskType){
            case "todo":
                if (inputToken.length == 1) throw new InvalidCommandFormatException(botMemory.ERROR_MESSAGE_INVALID_COMMAND_FORMAT);
                taskTracker.add(new ToDo(inputToken[1]));
                return;

            case "deadline":
                String[] deadlineTask = inputToken[1].split(" /by ", 2);
                if (deadlineTask.length != 2) throw new InvalidCommandFormatException(botMemory.ERROR_MESSAGE_INVALID_COMMAND_FORMAT);
                taskTracker.add(new Deadline(deadlineTask[0].trim(), deadlineTask[1].trim()));
                return;

            case "event":
                String[] eventTask = inputToken[1].split(" /at ", 2);
                if (eventTask.length != 2) throw new InvalidCommandFormatException(botMemory.ERROR_MESSAGE_INVALID_COMMAND_FORMAT);
                taskTracker.add(new Event(eventTask[0].trim(), eventTask[1].trim()));
                return;

            default:
                throw new InvalidCommandException(botMemory.ERROR_MESSAGE_INVALID_COMMAND);
        }
    }

    private void generateTaskFeedback(){
        String output = String.format("%s\n\t\t%s\n\t", botMemory.MESSAGE_ADD_TASK_NOTICE, taskTracker.get(taskTracker.size()-1).toString())
                + String.format(botMemory.MESSAGE_ADD_TASK_SUMMARY, taskTracker.size());
        botPrinter.print(output);
    }

    private String formatTaskTracker() throws EmptyTaskListException {
        if (taskTracker.size() == 0) throw new EmptyTaskListException(botMemory.ERROR_MESSAGE_EMPTY_TASKLIST);
        StringBuilder formattedTask = new StringBuilder();
        formattedTask.append(botMemory.MESSAGE_TASK_REPORT + "\n\t");
        taskTracker.stream().forEach(x -> formattedTask.append((taskTracker.indexOf(x) + 1) + ". " + x.toString() + "\n\t"));
        formattedTask.append("(end)");
        return formattedTask.toString();
    }

    private void checkTaskListModificationCommand(String input) throws TaskOutOfRangeException, InvalidTaskIndexException {
        if (!isInteger(input.split(" ", 2)[1])) throw new InvalidTaskIndexException(botMemory.ERROR_MESSAGE_INVALID_TASK_INDEX);
        Integer index = Integer.parseInt(input.split(" ", 2)[1]);
        if (index - 1 > taskTracker.size() || taskTracker.size() == 0) throw new TaskOutOfRangeException(botMemory.ERROR_MESSAGE_TASK_OUT_OF_RANGE);

    }

    private void markTaskAsDone(String input) throws TaskOutOfRangeException, InvalidTaskIndexException {
        checkTaskListModificationCommand(input);
        Integer index = Integer.parseInt(input.split(" ", 2)[1]);
        Task completedTask = taskTracker.get(index-1);
        completedTask.maskAsDone();
        String output = String.format("%s\n\t\t%s%s\n\t%s",botMemory.MESSAGE_TASK_COMPLETE, (index) + ". ", completedTask, botMemory.MESSAGE_CHEERING);
        botPrinter.print(output);
    }

    private void deleteTaskFromList(String input) throws TaskOutOfRangeException, InvalidTaskIndexException {
        checkTaskListModificationCommand(input);
        Integer index = Integer.parseInt(input.split(" ", 2)[1]);
        Task removedTask = taskTracker.get(index-1);
        String output = String.format("%s\n\t\t%s%s\n\t", botMemory.MESSAGE_REMOVE_TASK, (index) + ". ", removedTask)
                + String.format(botMemory.MESSAGE_ADD_TASK_SUMMARY, taskTracker.size() - 1);
        taskTracker.remove(removedTask);
        botPrinter.print(output);
    }

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    private void interact() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String input = sc.nextLine().trim();
                if (input.equals("bye")) {
                    botPrinter.print(botMemory.MESSAGE_GOODBYE);
                    return;
                } else if (input.equals("list")) {
                    botPrinter.print(formatTaskTracker());
                } else if (input.startsWith("done")) {
                    markTaskAsDone(input);
                } else if (input.startsWith("delete")){
                    deleteTaskFromList(input);
                } else {
                    classifyTask(input);
                    generateTaskFeedback();
                }
            }
            catch (Exception error){
                botPrinter.print(botMemory.ERROR_MESSAGE_PROMPT + error.getMessage());
            }
        }
    }

    void initiate() {
        System.out.println("\t" + botMemory.LOGO.replaceAll("\n", "\n\t"));
        botPrinter.print(botMemory.MESSAGE_GREETING);
        this.interact();
    }
}
