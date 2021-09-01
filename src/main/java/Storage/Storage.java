package Storage;

import java.io.*;
import java.util.Arrays;
import Task.TaskList;
import Task.Task;
import Task.TaskTypes;
import Task.DeadlineException;
import Task.TodoException;
import Task.EventsException;

public class Storage {
    private final Parser parser= new Parser();
    /**
     * Function obtain data from previous file records. Creates the file if it's not present.
     * @param lst is the target TaskList
     */
    public void getDataInputList(TaskList lst) {
        try {
            File f = new File("src/main/Duke/Duke.txt");
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

class TodoTasks extends Task{
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

class DeadlineTask extends Task{
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

class EventsTask extends Task{
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






