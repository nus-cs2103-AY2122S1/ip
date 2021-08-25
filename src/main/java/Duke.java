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
    private TaskList taskList;
    private boolean isActive;
    private Ui ui;

    public Duke(){
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList();
    }

    private void exit(){
        ui.farewellMsg();
        isActive = false;
    }

    private void addTask(Task task){
        taskList.add(task);
        ui.addTaskMsg(taskList.size(), task);
        storage.save(taskList.getList());
    }

    private void deleteTask(int taskNum){
        Task task = taskList.delete(taskNum);
        ui.deleteTaskMsg(taskList.size(), task);
        storage.save(taskList.getList());
    }

    private void markTaskAsDone(int taskNumber){
        ui.markDoneMsg(taskList.mark(taskNumber));
        storage.save(taskList.getList());
    }

    private void start(){
        isActive = true;
        taskList.loadFromList(storage.load());

        ui.greet();

        Scanner sc = new Scanner(System.in);

        while (isActive){
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
        ui.listTasks(taskList.getList());
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
        Duke duke = new Duke();
        duke.start();
    }
}
