import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Parser {
    private TaskList taskList;
    private Storage storage;
    private Scanner sc = new Scanner(System.in);
    private boolean terminated;
    private static String[] SUPPORTED_COMMANDS = {"bye", "list", "done", "deadline", "event","todo", "delete"};

    private static void lineSpacing() {
        System.out.println("____________________________________________________________");
    }

    public Parser(Storage storage, TaskList taskList) {
        this.storage = storage;
        this.taskList = taskList;
        this.terminated = false;
    }

    private boolean isCommandSupported(String userInput) {
        boolean isSupported = false;
        for(int i = 0; i < SUPPORTED_COMMANDS.length; i++ ) {
            isSupported = isSupported || userInput.startsWith(SUPPORTED_COMMANDS[i]);
        }
        return isSupported;
    }

    public String parseCommand(String userInput) {
        try {
            if (!isCommandSupported(userInput)) {
                throw new DukeException("unknown command");
            }

            if (userInput.equals("bye")) {
                lineSpacing();
                System.out.println("Bye. Hope to see you again soon!");
                lineSpacing();
                this.terminated = true;
            }

            if (userInput.equals("list")) {
                return printList();
            }

            if (userInput.startsWith("done")) {
                return doneTask(userInput);
            }

            if (userInput.startsWith("deadline")) {
                return addDeadline(userInput);
            }

            if (userInput.startsWith("event")) {
              return  addEvent(userInput);
            }

            if (userInput.startsWith(("todo"))) {
               return addTodo(userInput);
            }

            if (userInput.startsWith("delete")) {
                return deleteTask(userInput);
            }

            return "";
        } catch (DukeException e) {
            return e.toString();
        }
    }


    public boolean isTerminated() {
        return this.terminated;
    }

    private String displayAddedTask(Task currentTask) {
        return String.format("Got it. I've added this task: \n%s\nNow you have %d tasks in the list.",
                currentTask, taskList.size());
    }

    private String printList() {
        StringBuilder outputList = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            outputList.append(String.format("%d.%s\n", i + 1, currentTask));
        }
        return outputList.toString();
    }

    private String doneTask(String userInput) {
        String[] inputArray = userInput.split(" ");
        Task completedTask = taskList.get(Integer.parseInt(inputArray[1]) - 1);
        completedTask.markAsDone();
        storage.saveData(taskList);
        return String.format("Nice! I've marked this task as done:\n%s", completedTask);
    }

    private String addDeadline(String userInput) {
        List<String> inputArray = Arrays.asList(userInput.split(" /by "));
        String by = inputArray.get(1);
        ArrayList<String> descriptionArray = new ArrayList<String>(Arrays.asList(inputArray.get(0).split(" ")));
        descriptionArray.remove(0);
        String description = String.join(" ", descriptionArray);
        Deadline newDeadline = new Deadline(description, by);
        taskList.addTask(newDeadline);
        storage.saveData(taskList);
        return displayAddedTask(newDeadline);

    }

    private String addEvent(String userInput) {
        List<String> inputArray = Arrays.asList(userInput.split(" /at "));
        String timeFrame = inputArray.get(1);
        ArrayList<String> descriptionArray = new ArrayList<String>(Arrays.asList(inputArray.get(0).split(" ")));
        descriptionArray.remove(0);
        String description = String.join(" ", descriptionArray);
        Event newEvent = new Event(description, timeFrame);
        taskList.addTask(newEvent);
        storage.saveData(taskList);
        return displayAddedTask(newEvent);

    }

    private String addTodo(String userInput) throws DukeException {
        List<String>  inputArray = Arrays.asList(userInput.split(" "));
        if (inputArray.size() <= 1) {
            throw new DukeException("todo");
        }
        ArrayList<String> descriptionArray =  new ArrayList<String>(inputArray);
        descriptionArray.remove(0);
        String description = String.join(" ",descriptionArray);
        Todo newTodo = new Todo(description);
        taskList.addTask(newTodo);
        storage.saveData(taskList);
        return displayAddedTask(newTodo);
    }

    private String deleteTask(String userInput) {
        String[] inputArray = userInput.split(" ");
        Task removedTask = taskList.deleteTask(Integer.parseInt(inputArray[1]) - 1);
        storage.saveData(taskList);
        return String.format("Noted. I've removed this task: \n%s\nNow you have %d tasks in the list.",
                removedTask, taskList.size());
    }
}
