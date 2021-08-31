import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.time.format.DateTimeFormatter;

public class Duke {
    private final Ui userInterface = new Ui();

    public static void main(String[] args) {
        Duke user = new Duke();
        boolean end = true;
        user.userInterface.greet();
        user.userInterface.getDataInputList();
        while (end) {
            end = user.userInterface.echo();
            System.out.println("__________________________________");
        }
    }
}
enum TaskTypes {
    Todo,
    Deadline,
    Events
}

//deals with interactions with the user
class Ui {
    private final Storage store = new Storage();
    private TaskList taskList = new TaskList();
    /**
     * Generate the initiate message.
     */
    public void greet() {
        System.out.println("__________________________________");
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?");
        System.out.println("__________________________________");
    }

    /**
     * generate the end message.
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        store.saveListInFile("Duke.txt", taskList);
    }
    /**
     * The original method to simply generate an echo message.
     * Now only meant to decide if the user has decided to end
     * the program.
     * @return boolean
     */
    public boolean echo() {
        Scanner user = new Scanner(System.in);
        String input = user.nextLine();
        return choiceOfAction(input);
    }

    /**
     * To decide base on the input what is the next action.
     * The decision on whether to show list, set existing task to
     * done, or to create and record new task.
     * @param input by the user
     * @return boolean
     */
    public boolean choiceOfAction(String input) {
        if (input.equals("bye")) {
            bye();
            return false;
        } else if (input.equals("list")) {
            taskList.showList();
            return true;
        } else {
            String[] newInput = input.split(" ");
            String instruction = newInput[0];
            if (instruction.equals("done")) {
                if (newInput.length < 2) {
                    System.out.println("Please input the index of the Task to be done!");
                } else {
                    int index = Integer.parseInt(newInput[1]);
                    if (index <= taskList.getTaskList().size() && index > 0) {
                        taskList.getTaskList().get(index - 1).done();
                        System.out.println("Nice! I've marked this task as done: ");
                        System.out.println("    " + this.taskList.getTaskList().get(index - 1));
                    } else {
                        System.out.println("invalid index");
                    }
                }
            } else if (instruction.equals("delete")) {
                try {
                    taskList.deleteTask(newInput);
                }catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }else {
                try {
                    Task newTask = store.createTask(input);
                    taskList.addTask(newTask);
                } catch (TodoException tx) {
                    System.out.println(tx.getLocalizedMessage());
                } catch (DeadlineException dx) {
                    System.out.println(dx.getLocalizedMessage());
                } catch (EventsException ex) {
                    System.out.println(ex.getLocalizedMessage());
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
            return true;
        }
    }

    /**
     * Encapsulation up the class structure.
     */
    public void getDataInputList() {
        store.getDataInputList(this.taskList);
    }
}

//deals with loading tasks from the file and saving tasks in the file.
class Storage {
    private final Parser parser= new Parser();
    /**
     * Function obtain data from previous file records. Creates the file if it's not present.
     * @param lst is the target TaskList
     */
    public void getDataInputList(TaskList lst) {
        try {
            File f = new File("Duke.txt");
            if(!f.exists()){
                f.createNewFile();
            }
            FileReader reader = new FileReader(f);
            BufferedReader br = new BufferedReader(reader);  //creates a buffering character input stream
            String line;
            Task curTask;

            while ((line = br.readLine()) != null) {
                boolean isCurTaskDone = false;
                String[] data = line.split(" ");
                int lengthLimit = data.length;
                String taskData = "";

                if (data[data.length - 1].equals("done")){
                    isCurTaskDone = true;
                    lengthLimit -= 1;
                }
                try {
                    for (int i = 0; i < lengthLimit; i++) {
                        taskData = taskData.concat(data[i] + " ");
                    }
                    curTask = parser.createTask(taskData);
                    if (isCurTaskDone) {
                        curTask.done();
                    }
                    lst.getTaskList().add(curTask);
                } catch (Exception e) {
                }
            }
        }catch (FileNotFoundException f) {
        }catch(IOException io){
            System.out.println(io.getLocalizedMessage());
        }
    }

    /**
     * Save the current Task in the target file.
     * @param filename of the file for saving of data from tasklist.
     */
    public void saveListInFile(String filename, TaskList lst) {
        try {
            //clear the current data file
            PrintWriter pw = new PrintWriter(filename);
            pw.close();
            //write into the file
            File tempFile = new File(filename);
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            for (Task task : lst.getTaskList()) {
                writer.write(parser.formatTaskIntoCommands(task));
            }
            writer.close();
        }catch (IOException io) {
        }
    }

    /**
     * Create the correct Task using the information in the input String
     * @param input contains data regarding the action and date of the Task.
     * @return the correct Task.
     * @throws Exception if the input is invalid.
     */
    public Task createTask(String input) throws Exception{
        return parser.createTask(input);
    }
}

// deals with making sense of the user command
class Parser {

    /**
     * Function reformat Task in TaskList back to valid format input commands.
     * @param task to be reformatted and store in file.
     * @return String command.
     */
    public String formatTaskIntoCommands(Task task) {
        String[] taskInfo = task.toString().split(" ");
        String output;
        String separator = "";
        String keyword = "";
        taskInfo = Arrays.copyOfRange(taskInfo, 1, taskInfo.length);
        if (task.getType().equals(TaskTypes.Todo)) {
            output = "Todo";
        } else if (task.getType().equals(TaskTypes.Deadline)) {
            output = "Deadline";
            separator = "(by:";
            keyword = "by";
        } else {
            output = "Events";
            separator = "(at:";
            keyword = "at";
        }
        for (String str : taskInfo) {
            if (!str.equals(")") && !str.equals("")) {
                if (str.equals(separator)) {
                    str = "/" + keyword;
                }
                output = output.concat(" " + str);
            }
        }
        if (task.getIsDone()) {
            output += " done";
        }
        return output + "\n";
    }

    /**
     * Function able to read the input the judge which Task to use.
     * @param action contains information for what task to be created.
     * @return the task created.
     * @throws Exception if format is invalid.
     */
    public Task createTask(String action) throws Exception{
        // separate using the / as its the point of the date
        String[] dataOfAction = action.split("/")[0].split(" ");
        String type = dataOfAction[0];
        //decide the correct header
        if (type.equals("Todo")) {
            return new TodoTasks(action);
        } else {
            if (type.equals("Deadline")) {
                return new DeadlineTask(action);
            } else if  (type.equals("Events")) {
                return new EventsTask(action);
            } else {
                throw new Exception("Error type");
            }
        }
    }
}

class Task {
    private String done;
    private String[] dataOfAction;
    private String finalAction = "";
    private final TaskTypes type;
    private boolean isDone = false;
    private LocalDate localDate;

    /**
     * Constructor for the Task.
     * @param action is what to be done.
     * @param done is the tick box before the task.
     * @param type is what type of task to be created.
     */
    public Task(String action, String done, TaskTypes type) {
        this.done = done;
        this.type = type;
        dataOfAction = action.split("/")[0].split(" ");
        for (int i = 1; i < dataOfAction.length; i++) {
            finalAction = finalAction.concat(dataOfAction[i] + " ");
        }
        if (action.split("/").length > 1) {
            String day = setDate(action);
            finalAction = finalAction.concat(day);
        }
    }

    /**
     * Create a tail made up of the deadline.
     * @param inputs String containing the date with some other information.
     * @return String containing only the date with information correctly organised.
     */
    private String setDate(String inputs) {
        String[] data = inputs.split(" ");
        int pos = 0;
        String keywords = "";
        if (type.equals(TaskTypes.Deadline)) {
            keywords = "by";
        } else if (type.equals(TaskTypes.Events)) {
            keywords = "at";
        }
        for (String str : data) {
            if (str.equals("/" + keywords)) {
                break;
            }
            pos += 1;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String date = data[pos + 1];
        this.localDate = LocalDate.parse(date, formatter);

        String output = " (" + keywords + ": ";
        for (int i = pos + 1 ;i < data.length; i++) {
            output = output.concat(" " + data[i]);
        }
        return output + " )";
    }
    /**
     * modify the task message if done.
     */
    public void done() {
        done = done.substring(0, 3) + "[X] ";
        this.isDone = true;
    }
    /**
     * Override the original toString method to generate the correct value.
     * @return String representation of the Task, which is presentable at the terminal.
     */
    public String toString() {
        return done + finalAction;
    }

    /////////////////getters////////////////////////

    /**
     * get Task Type
     * @return Task Type from the enum
     */
    public TaskTypes getType() {
        return type;
    }

    /**
     * get isDone of this Task.
     * @return boolean value of isDone.
     */
    public boolean getIsDone() {
        return isDone;
    }
}


class TodoTasks extends Task {
    /**
     * constructor for Task.
     *
     * @param action contains all the information to build a TodoTask.
     * @throws TodoException if the String action is invalid.
     */
    public TodoTasks(String action) throws TodoException {
        super(action, "[T][] ", TaskTypes.Todo);
        if (action.split(" ").length < 2){
            throw new TodoException("OOPS!!! The description of a todo cannot be empty.");
        }
    }
}

class DeadlineTask extends Task {
    /**
     * constructor for Task.
     *
     * @param action containinf all the data needed for the creation of Deadline Task.
     * @throws DeadlineException if the String action is invalid.
     */
    public DeadlineTask(String action) throws DeadlineException {
        super(action, "[D][] ", TaskTypes.Deadline);
        if (action.split("/").length < 2) {
            throw new DeadlineException("incorrect format for deadline task");
        } else if (action.split("/")[1].split(" ").length < 2) {
            throw new DeadlineException("incorrect date format for Deadline task");
        }
    }
}

class EventsTask extends Task {
    /**
     * constructor for Task.
     *
     * @param action contains all the data need for the creation of the EventTasks.
     * @throws EventsException if the action is invalid.
     */
    public EventsTask(String action) throws EventsException {
        super(action, "[E][] ", TaskTypes.Events);
        if (action.split("/").length < 2){
            throw new EventsException("incorrect format for Events task");
        }
    }
}
////////////exceptions////////////////////////////////////////////
class TodoException extends Exception {
    public TodoException(String message) {
        super(message);
    }
}

class EventsException extends Exception {
    public EventsException(String message) {
        super(message);
    }
}

class DeadlineException extends Exception {
    public DeadlineException(String message) {
        super(message);
    }
}
///////////////////////////////////////////////////////////////////

//contains the task list e.g., it has operations to add/delete tasks in the list
class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();
    /**
     * Getter to obtain the TaskList.
     * @return the TaskList.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * function adds a new Task into the taskList if valid.
     * @param newTask is the newly created Task to be added to the task list.
     */
    public void addTask(Task newTask) {
        taskList.add(newTask);
        System.out.println("Got it. I've added this task: ");
        System.out.println("    " + newTask);
        System.out.format("Now you have %d tasks in the list.\n", taskList.size());
    }

    /**
     * function removes the task located at the index of the task list.
     *
     * @param newInput contains information for the Task to be deleted.
     * @throws Exception if the index provided is not within valid range.
     */
    public void deleteTask(String[] newInput ) throws Exception{

        if (newInput.length < 2) {
            throw new Exception("invalid Syntax for delete instruction");
        } else {
            int index = Integer.parseInt(newInput[1]);
            if (index > 0 && index <= taskList.size()) {
                System.out.println("Noted. I've removed this task: ");
                System.out.println("    " + taskList.get(index - 1).toString());
                taskList.remove(index - 1);
                System.out.format("Now you have %d tasks in the list.\n", taskList.size());
            } else {
                throw new Exception("index not within valid range");
            }
        }
    }

    /**
     * create a message to show all the tasks and their states.
     */
    public void showList() {
        int count = 0;
        for (Task action : taskList ) {
            count++;
            System.out.format("%d. " + action + "\n", count);
        }
    }
}
