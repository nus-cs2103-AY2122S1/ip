import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private enum CommandTypes {
        EXIT, LIST, DONE, TODO, DEADLINE, EVENT, UNKNOWN, DELETE
    }

    private final String INDENTATION = "    ";
    private Storage storage;
    private ArrayList<Task> taskList;
    private boolean isActive;
    private Ui ui;

    private void greet(){
        String msg = "Hello! I'm Duke";
        ui.printMessageWithFormat(msg);
    }



    private void exit(){
        String msg = "Bye. Hope to see you again soon!";
        ui.printMessageWithFormat(msg);
        this.isActive = false;
    }

    private void addTask(Task task){
        this.taskList.add(task);
        String msg = "Got it. I've added this task:\n" + INDENTATION + "  " + task.checkStatus();
        msg += String.format("\n%sNow you have %d tasks in the list.", INDENTATION, taskList.size());
        ui.printMessageWithFormat(msg);
        storage.save(taskList);
    }

    private void deleteTask(int taskNum){
        Task task = taskList.remove(taskNum-1);
        String msg = "Noted. I've removed this task:\n" + INDENTATION + "  " + task.checkStatus();
        msg += String.format("\n%sNow you have %d tasks in the list.", INDENTATION, taskList.size());
        ui.printMessageWithFormat(msg);
        storage.save(taskList);
    }

    private void markTaskAsDone(int taskNumber){
        taskList.get(taskNumber-1).markDone();
        String msg = "Nice! I've marked this task as done:\n   ";
        msg += INDENTATION + taskList.get(taskNumber-1).checkStatus();
        ui.printMessageWithFormat(msg);
        storage.save(taskList);
    }

    private void start(){
        this.ui = new Ui();
        greet();

        this.isActive = true;
        this.storage = new Storage();
        this.taskList = storage.load();


        Scanner sc = new Scanner(System.in);

        while (this.isActive){
            String command = sc.nextLine();
            try {
                processCommand(command);
            } catch (DukeException e){
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }

    private void listTasks(){
        String msg = "Here are the tasks in your list:";
        for (int i = 1; i <= taskList.size(); i++){
            msg += String.format("\n%s%d. %s", INDENTATION, i, taskList.get(i-1).checkStatus());
        }
        ui.printMessageWithFormat(msg);
    }

    private void processCommand(String command) throws DukeException{
        switch (getCommandType(command)){
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
            try {
                LocalDateTime at = LocalDateTime.parse(command.substring(command.indexOf("at")+3),
                        DateTimeFormatter.ofPattern("yyyy-M-d H:m"));
                Task event = new Event(eventDescription, false, at);
                addTask(event);
            } catch (DateTimeParseException e){
                System.out.println("Please enter Date and Time in YYYY-MM-DD HH:MM.");
            }
            break;

        case DEADLINE:
            String deadlineDescription = command.substring(command.indexOf(" ")+1, command.indexOf("/by")-1);
            try {
                LocalDateTime by = LocalDateTime.parse(command.substring(command.indexOf("by")+3),
                        DateTimeFormatter.ofPattern("yyyy-M-d H:m"));
                Task deadline = new Deadline(deadlineDescription, false, by);
                addTask(deadline);
            } catch (DateTimeParseException e){
                System.out.println("Please enter Date and Time in YYYY-MM-DD HH:MM.");
            }
            break;

        case TODO:
            int spaceIndex = command.indexOf(" ");
            String toDoDescription = command.substring(spaceIndex+1);
            if (toDoDescription.isBlank() || spaceIndex == -1){
                throw new NoToDoDescriptionException();
            };
            Task toDo = new ToDo(toDoDescription, false);
            addTask(toDo);
            break;

        case DELETE:
            int taskNum = Integer.parseInt(command.substring(command.indexOf(" ")+1));
            deleteTask(taskNum);
            break;

        case UNKNOWN: default:
            throw new UnknownCommandException();
        }
    }

    private CommandTypes getCommandType(String command){
        String commandType = command.split(" ")[0];
        switch (commandType){
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

        case "delete":
            return CommandTypes.DELETE;

        default:
            return CommandTypes.UNKNOWN;
        }
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
