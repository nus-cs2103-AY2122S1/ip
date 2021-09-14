package duke.utils;

import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.TodoTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that handles storage of data by writing to disk
 * and reading from disk
 */

public class Storage {



    private String userDir = System.getProperty("user.dir");
    private String dataFilePath = userDir + "/" + "data.txt";

    private static final String DELIMITER = ";";

    /**
     * Loads task list as a TaskList object from the data.txt file
     * in the directory, and creates a new file if it does not
     * already exist.
     *
     * @return TaskList containing tasks
     * @throws FileNotFoundException
     */
    public TaskList loadTaskList() throws FileNotFoundException {

        assert(dataFilePath != null);

        File f = new File(dataFilePath);
        ArrayList<Task> taskList = new ArrayList<Task>();
        assert(taskList != null);

        if (!f.exists()) {
            return new TaskList(new ArrayList<Task>());
        }

        try {
            Scanner dataReader = new Scanner(f);

            while (dataReader.hasNextLine()) {
                // convert saved info to Task
                String currLine = dataReader.nextLine();
                Task currTask = convertStringToTask(currLine);
                taskList.add(currTask);
            }
            dataReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("File unable to load");
        }

        return new TaskList(taskList);

    }

    /**
     * Saves the tasks data from the TaskList into the
     * data.txt file in the directory.
     *
     * @param taskList tasklist
     * @throws IOException
     */
    public void saveTaskListToDisk(TaskList taskList) throws IOException {
        assert (dataFilePath != null);
        File f = new File(dataFilePath);
        if (!f.exists()) {
            try {
                boolean created = f.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file");
            }
        }

        try {
            FileWriter writeTasks = new FileWriter(dataFilePath);
            String allTasksString = "";

            for (int i = 0; i < taskList.numberOfTasks(); i++) {
                Task currTask = taskList.getTask(i);
                String taskString = convertTaskToString(currTask);
               allTasksString += taskString + "\n";
                System.out.println("written" + taskString);

            }

            writeTasks.write(allTasksString);
            writeTasks.flush();
            writeTasks.close();
            System.out.println("I have saved your tasks.");



        } catch (IOException e) {
            System.out.println("Error creating file");
        }

    }

    /**
     * Converts a Task object to its String representation
     * in an acceptable format to be stored in the data.txt file.
     *
     * @param task
     * @return String representation of the task
     */
    public String convertTaskToString(Task task){
        // Example storage in file:
        // T;0;readbook
        // D;0;return book ;2/12/2019 1800
        assert (task != null);

        String taskType = task.getType() == Task.TaskType.TODO ? "T" :
                task.getType() == Task.TaskType.EVENT ? "E" : "D";

        String taskName = task.getName();

        String isDone = task.isDone() ? "1" : "0";

        String taskString = taskType + DELIMITER + isDone + DELIMITER + taskName;

        if (task.getType() == Task.TaskType.EVENT){
            taskString += DELIMITER;
            taskString += ((EventTask) task).getDate();
        }

        if(task.getType() == Task.TaskType.DEADLINE){
            taskString += DELIMITER;
            taskString += ((DeadlineTask) task).getDate();
        }

        return taskString;

    }

    /**
     * Converts a task from the String format that is stored in the
     * data.txt file into a Task Object.
     *
     * @param line String representation of a task
     * @return
     */
    public Task convertStringToTask(String line){

         // Example storage in file:
         // T;0;readbook
         // D;0;return book ;2/12/2019 1800


        String[] lineSplit = line.split(DELIMITER);
        Task.TaskType taskType = lineSplit[0].equals("T") ? Task.TaskType.TODO :
                lineSplit[0].equals("D") ? Task.TaskType.DEADLINE : Task.TaskType.EVENT;

        boolean taskDone = lineSplit[1].equals("0") ? false : true;

        Task currTask;

        switch (taskType) {
            case TODO:
                currTask = new TodoTask(lineSplit[2]);
                break;

            case DEADLINE:
                currTask = new DeadlineTask(lineSplit[2], lineSplit[3]);
                break;

            case EVENT:
                currTask = new EventTask(lineSplit[2], lineSplit[3]);
                break;

            default:
                throw new IllegalArgumentException("Corrupted file");
        }

        if(taskDone ){
            currTask.makeDone();
        }

        return currTask;

    }


}
