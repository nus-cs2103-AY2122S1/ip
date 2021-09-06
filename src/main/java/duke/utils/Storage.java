package duke.utils;

import duke.tasks.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {

    private String userDir = System.getProperty("user.dir");
    private String dataFilePath = userDir + "/" + "data.txt";

    private static final String DELIMITER = ";";

    public TaskList loadTaskList() throws FileNotFoundException {

        File f = new File(dataFilePath);
        ArrayList<Task> taskList = new ArrayList<Task>();

        if(!f.exists()){
            return new TaskList(new ArrayList<Task>());
        }

        try{
            Scanner dataReader = new Scanner(f);

            while(dataReader.hasNextLine()){
                // convert saved info to Task
                String currLine = dataReader.nextLine();
                Task currTask = convertStringToTask(currLine);
                taskList.add(currTask);
            }
            dataReader.close();

        } catch (FileNotFoundException e){
            System.out.println("File unable to load");
        }

        return new TaskList(taskList);

    }

    public void saveTaskListToDisk(TaskList taskList){

        File f = new File(dataFilePath);
        if(!f.exists()) {
            try{
                boolean created = f.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file");
            }
        }

        try{
            FileWriter writeTasks = new FileWriter(dataFilePath);
            String allTasksString = "";

            for(int i = 0; i < taskList.numberOfTasks(); i++){
                Task currTask = taskList.getTask(i);
                String taskString = convertTaskToString(currTask);
               allTasksString += taskString + "\n";

            }

            writeTasks.write(allTasksString);
            writeTasks.flush();
            writeTasks.close();


        } catch (IOException e){
            System.out.println("Error creating file");
        }

    }

    public String convertTaskToString(Task task){

        // Example storage in file:
        // T | 1 | read book
        // D | 0 | return book | June 6th
        // E | 0 | project meeting | Aug 6th 2-4pm

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

    public Task convertStringToTask(String line){

        // Example storage in file:
        // T | 1 | read book
        // D | 0 | return book | June 6th
        // E | 0 | project meeting | Aug 6th 2-4pm

        String[] lineSplit = line.split(DELIMITER);
        //System.out.println(lineSplit.length);
        Task.TaskType taskType = lineSplit[0].equals("T") ? Task.TaskType.TODO :
                lineSplit[0].equals("D") ? Task.TaskType.DEADLINE : Task.TaskType.EVENT;

        boolean taskDone = lineSplit[1].equals("0") ? false : true;

        Task currTask;

        switch (taskType){
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

    public String convertSavedStringDateToDukeDate(String savedDate){

        return "";

    }

}
