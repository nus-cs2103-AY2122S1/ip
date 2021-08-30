import javax.xml.stream.FactoryConfigurationError;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    private String line = "__________________________________\n";
    // global varibale to keep track of all the task
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Task inner class.
     * Able to keep track of task type, done and date.
     */
    private class Task {
        private String done;
        private String[] dataOfAction;
        private String finalAction = "";
        private TaskTypes type;
        private boolean isDone = false;
        /**
         * constructor for Task.
         *
         * @param action
         */
        public Task(String action, String done, TaskTypes type) {
            this.done = done;
            this.type = type;
            dataOfAction = action.split("/")[0].split(" ");
            for (int i = 1; i < dataOfAction.length; i++) {
                finalAction += dataOfAction[i] + " ";
            }
            if (action.split("/").length > 1) {
                String day = setDate(action.split("/")[1].split(" "));
                finalAction += day;
            }
        }

        /**
         * Create a tail made up of the deadline.
         * @param data
         * @return String
         */
        private String setDate(String[] data) {
            String output = " (" + data[0] + ": ";
            for (int i = 1 ;i < data.length; i++) {
                output += " " + data[i];
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
         * @return String
         */
        public String toString() {
            return done + finalAction;
        }

        /////////////////getters////////////////////////

        /**
         * get Task Type
         * @return
         */
        public TaskTypes getType() {
            return type;
        }

        /**
         * get isDone
         * @return
         */
        public boolean getIsDone() {
            return isDone;
        }
    }

    private enum TaskTypes {
        Todo,
        Deadline,
        Events
    }

    private class TodoTasks extends Task {
        /**
         * constructor for Task.
         *
         * @param action
         */
        public TodoTasks(String action) throws TodoException{
            super(action, "[T][] ", TaskTypes.Todo);
            if (action.split(" ").length < 2){
                throw new TodoException("OOPS!!! The description of a todo cannot be empty.");
            }
        }
    }

    private class DeadlineTask extends Task {
        public TaskTypes type = TaskTypes.Deadline;
        /**
         * constructor for Task.
         *
         * @param action
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

    private class EventsTask extends Task {
        /**
         * constructor for Task.
         *
         * @param action
         */
        public EventsTask(String action) throws EventsException{
            super(action, "[E][] ", TaskTypes.Events);
            if (action.split("/").length < 2){
                throw new EventsException("incorrect format for Events task");
            } else if (action.split("/")[1].split(" ").length < 3) {
                throw new EventsException("incorrect date format for Events task");
            }
        }
    }
    ////////////exceptions////////////////////////////////////////////
    private class TodoException extends Exception {
        public TodoException(String message) {
            super(message);
        }
    }

    private class EventsException extends Exception {
        public EventsException(String message) {
            super(message);
        }
    }

    private class DeadlineException extends Exception {
        public DeadlineException(String message) {
            super(message);
        }
    }
    ///////////////////////////////////////////////////////////////////
    /**
     * Function able to read the input the judge which Task to use.
     * @param action
     * @return
     * @throws Exception
     */
    private Task createTask(String action) throws Exception{
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
    /**
     * Generate the initiate message.
     */
    private void greet() {
            System.out.println(line);
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println(logo);
            System.out.println("Hello! I'm Duke\n");
            System.out.println("What can I do for you?");
            System.out.println(line);
        }

    /**
     * generate the end message.
     */
    private void bye() {
        String line = "__________________________________";
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
        saveListInFile("src/main/java/Duke.txt");
    }
    /**
     * The original method to simply generate an echo message.
     * Now only meant to decide if the user has decided to end
     * the program.
     * @return boolean
     */
    private boolean echo() {
        Scanner user = new Scanner(System.in);
        String input = user.nextLine();
        if (input.equals("bye")) {
            bye();
            return false;
        } else {
            return choiceOfAction(input);
        }
    }

    /**
     * function adds a new Task into the taskList if valid.
     * @param newTask
     */
    private void addTask(Task newTask) {
        taskList.add(newTask);
        System.out.println(line);
        System.out.println("Got it. I've added this task: ");
        System.out.println("    " + newTask);
        System.out.format("Now you have %d tasks in the list.\n", taskList.size());
    }

    /**
     * function removes the task located at the index of the task list.
     * @param newInput
     */
    private void deleteTask(String[] newInput ) throws Exception{

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
     * To decide base on the input what is the next action.
     * The decision on whether to show list, set existing task to
     * done, or to create and record new task.
     * @param input
     * @return boolean
     */
    private boolean choiceOfAction(String input) {
        if (input.equals("list")) {
            showList();
            return true;
        } else {
            String[] newInput = input.split(" ");
            String instruction = newInput[0];
            if (instruction.equals("done")) {
                int index = Integer.parseInt(newInput[1]);
                if (index <= taskList.size() && index > 0) {
                    taskList.get(index - 1).done();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println("    " + this);
                } else {
                    System.out.println("invalid index");
                }
            } else if (instruction.equals("delete")) {
                try {
                    deleteTask(newInput);
                }catch (Exception e) {
                    System.out.println(e);
                }
            }else {
                try {
                    Task newTask = createTask(input);
                    addTask(newTask);
                } catch (TodoException tx) {
                    System.out.println(tx);
                }catch (DeadlineException dx) {
                    System.out.println(dx);
                }catch (EventsException ex) {
                    System.out.println(ex);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            System.out.println(line);
            return true;
        }
    }
    /**
     * create a message to show all the tasks and their states.
     */
    private void showList() {
        int count = 0;
        for (Task action : taskList ) {
            count++;
            System.out.format("%d. " + action + "\n", count);
        }
        System.out.println(line);
    }

    /**
     * Function obtain data from previous file records. Creates the file if it's not present.
     */
    private void getDataInputList() {
        try {
            File f = new File("src/main/java/Duke.txt");
            if(!f.exists()){
                f.createNewFile();
            }
            FileReader reader = new FileReader(new File("src/main/java/Duke.txt"));
            BufferedReader br = new BufferedReader(reader);  //creates a buffering character input stream
            String line;
            Task curTask;
            boolean isCurTaskDone = false;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(" ");
                int lengthLimit = data.length;
                String taskData = "";

                if (data[data.length - 1].equals("done")){
                    isCurTaskDone = true;
                    lengthLimit -= 1;
                }

                try {
                    for (int i = 0; i < lengthLimit; i++) {
                        taskData += data[i] + " ";
                    }
                    curTask = createTask(taskData);
                    if (isCurTaskDone) {
                        curTask.done();
                    }
                    taskList.add(curTask);
                } catch (Exception e) {
                }
            }
        }catch (FileNotFoundException f) {
        }catch(IOException io){
        }
    }

    /**
     * Save the current Task in the target file.
     * @param filename
     */
    private void saveListInFile(String filename) {
        try {
            //clear the current data file
            PrintWriter pw = new PrintWriter(filename);
            pw.close();
            //write into the file
            File tempFile = new File(filename);
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            for (Task task : taskList) {
                writer.write(formatTaskIntoCommands(task));
            }
            writer.close();
        }catch (IOException io) {
        }
    }

    private String formatTaskIntoCommands(Task task) {
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
                output += " " + str;
            }
        }
        if (task.getIsDone()) {
            output += " done";
        }
        return output + "\n";
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.greet();
        boolean end = true;
        bot.getDataInputList();
        while (end) {
            end = bot.echo();
        }
    }
}
