import java.time.Period;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Duke {

    private final String LOGO =
                    "░░░░░░░░░░░░░░░░▄▄▄███████▄▄░░░░░░░░░░░░\n" +
                    "░░░░░░░░░░░░░░▄███████████████▄░░░░░░░░░\n" +
                    "░░░░░░░░░░░░░█▀▀▀▄░░░░█████▀▀███▄░░░░░░░\n" +
                    "░░░░░░░░░░░░█░░▄░░█▄▄█░░░░▀▄▄█████░░░░░░\n" +
                    "░░░░░░░░░▄▀▀▀▄▄▀▀▀▀▀▀▄░░▀░░█▀▀▀░▀██░░░░░\n" +
                    "░░░░░░░▄▀░░░░░█░░░░░░▀▄▄▄▄█░░░░░░░▀▄░░░░\n" +
                    "░░░░░░▄▀░░░░░▄█▀▄▄▄▄░░░░░▄░░░░░░░░░█░░░░\n" +
                    "░░░░░░█░░░░░▄█▀▄▄▄▄▄▄▄▄▄▀▀░░░░░░░░░▀▄░░░\n" +
                    "░░░░░█░░░░░░▀█▄░░░░░░░░░░░░░░░░░░░░░█░░░\n" +
                    "░░░░░█░░░░░░░░▀▄░░░░░░░░░░░░░░░░░░░░█░░░\n" +
                    "░░░░░█░░░░░░░░▄█░░░░░░░░░░░░░░░░░░░░█░░░\n" +
                    "░░░░░█░░░░░░▄▀░░░░░░░░▀▄░░░░░░░░░░░░█░░░\n" +
                    "░░░░░█░░░░░░▀▄░░░▄░░░░░█░░░░░░░░░░░░█░░░\n" +
                    "░░░░░█░░░░░░░░▀▀▀▀▀█▄▄▀░░░░░░░░░░░░░█░░░\n" +
                    "░░░░░█░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▄██░░\n" +
                    "░░░░░█▄░░░░░░░░░░░░░░░░░░░░░░░░░░▄▀▀▄▀▄▄\n" +
                    "░░░░▄█▀▄░░░░░░░░░░░░░░░░░░░░░░▄▄▀░░▄▀░░░\n" +
                    "░▄░▀░░█░▀▄▄░░░░░░░░░░░░░░░▄▄▄▀░░░▄▀░░░░░\n" +
                    "▀░░░░░░▀▄░░▀▄░░░░░░░░▄▄▄▀▀░░░░▄▀▀░░░░░░░";
    private final String HORIZONTAL_LINE =
                    "\t  .--.      .-'.      .--.      .--.      .--.      .--.      .`-.      .--.\n" +
                    "\t:::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\\n" +
                    "\t'      `--'      `.-'      `--'      `--'      `--'      `-.'      `--'      `";
    private final String MESSAGE_GREETING = "Hallo! My name's Peter!\n\tHow may I be of service to you?";
    private final String MESSAGE_GOODBYE = "Good day! I'm gonna find Lois if you're not using me!";
    private final String MESSAGE_TASK_REPORT = "Here are all your tasks! No Procrastination!";
    private final String MESSAGE_TASK_COMPLETE = "Task Completed: ";
    private final String MESSAGE_CHEERING = "Great job! You're the best! Keep up the good work! Oho! Oho! Ohooooooo!";
    private final String MESSAGE_ADD_TASK_NOTICE = "GOSH! You have one more task.";
    private final String MESSAGE_ADD_TASK_SUMMARY = "Now you have %s task in the list";
    private Task[] taskTracker = new Task[100];
    private int taskNo = 0;

    private void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    private void printNewLine(String input) {
        System.out.println("\t" + input);
    }

    private void response(String input) {
        printHorizontalLine();
        printNewLine(input);
        printHorizontalLine();
    }

    private void addTask(String input){
        //taskTracker[taskNo++] = new Task(input);
        String[] inputToken = tokenize(input);
        String taskType = inputToken[0];

        if (taskType.equals("todo")){
            taskTracker[taskNo++] = new ToDo(inputToken[1]);
        } else if (taskType.equals("deadline")){
            String[] deadlineTask = inputToken[1].split(" /by ", 2);
            taskTracker[taskNo++] = new Deadline(deadlineTask[0].trim(), deadlineTask[1].trim());
        } else if (taskType.equals("event")){
            String[] eventTask = inputToken[1].split(" /at ", 2);
            taskTracker[taskNo++] = new Event(eventTask[0].trim(), eventTask[1].trim());
        } else{
            response("Wrong input");
        }
        addTaskReport();
    }

    private void addTaskReport(){
        String output = String.format("%s\n\t\t%s\n\t", MESSAGE_ADD_TASK_NOTICE, taskTracker[taskNo-1].toString())
                + String.format(MESSAGE_ADD_TASK_SUMMARY, taskNo);
        response(output);
    }

    private String[] tokenize(String input){
        String[] token = input.split(" ", 2);
        return token;
    }

    private String printAllTask() {
        StringBuilder formattedTask = new StringBuilder();
        formattedTask.append(MESSAGE_TASK_REPORT + "\n\t");
        System.out.println("clear");
        for (int i = 0; i < taskTracker.length; i++) {
            if (taskTracker[i] != null) {
                String eachLine = (i + 1) + ". " + taskTracker[i].toString() + "\n\t";
                formattedTask.append(eachLine);
            }
        }
        formattedTask.append("(end)");
        return formattedTask.toString();
    }

    private void completeTask(String input){
        Integer index = Integer.parseInt(input.split(" ", 2)[1]);
        Task completedTask = taskTracker[index - 1];
        completedTask.maskAsDone();
        String output = MESSAGE_TASK_COMPLETE + (index) + ". " + completedTask.taskTitle + "\n\t" + MESSAGE_CHEERING;
        response(output);
    }

    private void interact() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine().trim();
            if (input.equals("bye")) {
                response(MESSAGE_GOODBYE);
                return;
            }else if (input.equals("list")){
                response(printAllTask());
            }else if (input.startsWith("done")) {
                completeTask(input);
            }else {
                addTask(input);
            }

        }
    }

    void initiate() {
        System.out.println("\t" + LOGO.replaceAll("\n", "\n\t"));
        response(MESSAGE_GREETING);
        this.interact();
    }

    public static void main(String[] args) {
        new Duke().initiate();
    }
}