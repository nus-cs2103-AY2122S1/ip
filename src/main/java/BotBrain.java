import java.util.List;
import java.util.Scanner;

public class BotBrain {

    private BotMemory botMemory = new BotMemory();
    private BotPrinter botPrinter = new BotPrinter();
    private List<Task> taskTracker = botMemory.taskTracker;

    BotBrain() {

    }

    private void addTask(String input){
        String[] inputToken = tokenize(input);
        String taskType = inputToken[0];

        if (taskType.equals("todo")){
            taskTracker.add(new ToDo(inputToken[1]));
        } else if (taskType.equals("deadline")){
            String[] deadlineTask = inputToken[1].split(" /by ", 2);
            taskTracker.add(new Deadline(deadlineTask[0].trim(), deadlineTask[1].trim()));
        } else if (taskType.equals("event")){
            String[] eventTask = inputToken[1].split(" /at ", 2);
            taskTracker.add(new Event(eventTask[0].trim(), eventTask[1].trim()));
        } else{
            botPrinter.print("Wrong input");
        }
        addTaskReport();
    }

    private void addTaskReport(){
        String output = String.format("%s\n\t\t%s\n\t", botMemory.MESSAGE_ADD_TASK_NOTICE, taskTracker.get(taskTracker.size()-1).toString())
                + String.format(botMemory.MESSAGE_ADD_TASK_SUMMARY, taskTracker.size());
        botPrinter.print(output);
    }

    private String[] tokenize(String input){
        String[] token = input.split(" ", 2);
        return token;
    }

    private String outputAllTask() {
        StringBuilder formattedTask = new StringBuilder();
        formattedTask.append(botMemory.MESSAGE_TASK_REPORT + "\n\t");
        for (int i = 0; i < taskTracker.size(); i++) {
            String eachLine = (i + 1) + ". " + taskTracker.get(i).toString() + "\n\t";
            formattedTask.append(eachLine);
        }
        formattedTask.append("(end)");
        return formattedTask.toString();
    }

    private void completeTask(String input){
        Integer index = Integer.parseInt(input.split(" ", 2)[1]);
        Task completedTask = taskTracker.get(index-1);
        completedTask.maskAsDone();
        String output = botMemory.MESSAGE_TASK_COMPLETE + (index) + ". " + completedTask.taskTitle + "\n\t" + botMemory.MESSAGE_CHEERING;
        botPrinter.print(output);
    }

    private void interact() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine().trim();
            if (input.equals("bye")) {
                botPrinter.print(botMemory.MESSAGE_GOODBYE);
                return;
            }else if (input.equals("list")){
                botPrinter.print(outputAllTask());
            }else if (input.startsWith("done")) {
                completeTask(input);
            }else {
                addTask(input);
            }

        }
    }

    void initiate() {
        System.out.println("\t" + botMemory.LOGO.replaceAll("\n", "\n\t"));
        botPrinter.print(botMemory.MESSAGE_GREETING);
        this.interact();
    }
}
