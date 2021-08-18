import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private enum CommandTypes {
        EXIT, LIST, DONE, TODO, DEADLINE, EVENT
    }

    private final String INDENTATION = "    ";
    private final ArrayList<Task> taskList;
    private boolean isActive;

    private Duke(){
        this.taskList = new ArrayList<>();
        this.isActive = true;
    }

    private void greet(){
        String msg = "Hello! I'm Duke\n";
        msg += INDENTATION + "What can I do for you?";
        printMessageWithFormat(msg);
    }

    private void printMessageWithFormat(String msg){
        String HORIZONTAL_LINE = "____________________________________________________________";
        System.out.println(INDENTATION + HORIZONTAL_LINE);
        System.out.println(INDENTATION + msg);
        System.out.println(INDENTATION + HORIZONTAL_LINE);
    }

    private void exit(){
        String msg = "Bye. Hope to see you again soon!";
        printMessageWithFormat(msg);
        this.isActive = false;
    }

    private void addTask(Task task){
        this.taskList.add(task);
        String msg = "Got it. I've added this task:\n" + INDENTATION + "  " + task.checkStatus();
        msg += String.format("\n%sNow you have %d tasks in the list.", INDENTATION, taskList.size());
        printMessageWithFormat(msg);
    }

    private void markTaskAsDone(int taskNumber){
        taskList.get(taskNumber-1).markDone();
        String msg = "Nice! I've marked this task as done:\n   ";
        msg += INDENTATION + taskList.get(taskNumber-1).checkStatus();
        printMessageWithFormat(msg);
    }

    private void start(){
        greet();
        processCommand();
    }

    private void listTasks(){
        String msg = "Here are the tasks in your list:";
        for (int i = 1; i <= taskList.size(); i++){
            msg += String.format("\n%s%d. %s", INDENTATION, i, taskList.get(i-1).checkStatus());
        }
        printMessageWithFormat(msg);
    }

    private void processCommand(){
        Scanner sc = new Scanner(System.in);

        while (this.isActive){
            String command = sc.nextLine();
            switch(getCommandType(command)){
                case EXIT:
                    exit();
                    break;

                case LIST:
                    listTasks();
                    break;

                case DONE:
                    int taskNumber = Integer.parseInt(command.split(" ")[1]);
                    markTaskAsDone(taskNumber);
                    break;

                case EVENT:
                    String eventDescription = command.substring(command.indexOf(" ")+1, command.indexOf("/at")-1);
                    Task event = new Event(eventDescription, command.substring(command.indexOf("at")+3));
                    addTask(event);
                    break;

                case DEADLINE:
                    String deadlineDescription = command.substring(command.indexOf(" ")+1, command.indexOf("/by")-1);
                    Task deadline = new Deadline(deadlineDescription, command.substring(command.indexOf("by")+3));
                    addTask(deadline);
                    break;

                case TODO:
                    String toDoDescription = command.substring(command.indexOf(" ")+1);
                    Task toDo = new ToDo(toDoDescription);
                    addTask(toDo);
                    break;

            }
        }
        sc.close();
    }

    private CommandTypes getCommandType(String command){
        String commandType = command.split(" ")[0];
        switch(commandType){
            case "list":
                return CommandTypes.LIST;

            case "bye":
                return CommandTypes.EXIT;

            case "done":
                return CommandTypes.DONE;

            case "todo":
                return CommandTypes.TODO;

            case "deadline":
                return CommandTypes.DEADLINE;

            case "event":
                return CommandTypes.EVENT;
        }
        return null;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.start();
    }
}
