import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }

    public void addTaskToFile(Task task){
        try {
            FileWriter fileWriter = new FileWriter(filePath, true);
            fileWriter.write(task.getIcon() + "&&" + task.getStatus() + "&&"+ task.getDescription()
                    + "&&" + task.getTaskTime() + "\n");
            fileWriter.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static Task convertTaskStringToTask(String taskString) throws DukeException{
        Task task = new Task();
        String[] newTask = taskString.split("&&");
        String taskType = newTask[0];
        String status = newTask[1];
        String taskDescription = newTask[2];
        String taskTime = newTask.length > 3 ? newTask[3] : "";
        switch (taskType){
            case "T" :
                task = new ToDo(taskDescription);
                break;
            case "D" :
                task = new Deadline(taskDescription,taskTime);
                break;
            case "E" :
                task = new Event(taskDescription,taskTime);
                break;
            default:
                throw new DukeException("can't understand this icon");
        }
        if(status.equals("1")){
            task.done();
        }
        return task;
    }

//    public void listAllTasks(){
//        File dukeFile = new File(filePath);
//        try {
//            Scanner scan = new Scanner(dukeFile);
//            int i = 0;
//            while(scan.hasNext()){
//                String taskString = scan.nextLine();
//                Task task = convertTaskStringToTask(taskString);
//                System.out.println((i+1) + "." + task.toString());
//                i++;
//            }
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        } catch (DukeException dukeException){
//            System.out.println(dukeException.getMessage());
//        }
//    }

    public TaskList convertFileToTaskList(){
        TaskList taskList = new TaskList(new ArrayList<Task>());
        File dukeFile = new File(filePath);
        try {
            Scanner scan = new Scanner(dukeFile);
            while(scan.hasNext()){
                String taskString = scan.nextLine();
                Task task = convertTaskStringToTask(taskString);
                taskList.add(task);
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        } catch (DukeException dukeException){
            System.out.println(dukeException.getMessage());
        }
        return taskList;
    }

    public void convertTaskListToFile(TaskList taskList){
        try {
            FileWriter clearFile = new FileWriter(filePath);
            clearFile.write("");
            clearFile.close();
            for(int i = 0; i < taskList.size(); i++){
                addTaskToFile(taskList.get(i));
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
//
//    public void markTaskAsDone(int index){
//        ArrayList<Task> taskArray = convertFileToArray();
//        taskArray.get(index).done();
//        System.out.println(taskArray.get(index).getStatus());
//        convertTaskListToFile(taskArray);
//        System.out.println("Nice! I've marked this task as done:\n"
//                + taskArray.get(index).toString());
//    }

//    public void deleteTask(int index) {
//        ArrayList<Task> taskArray = convertFileToArray();
//        Task removedTask = taskArray.remove(index);
//        convertTaskArrayToFile(taskArray);
//        System.out.println("Got it. I've removed this task:\n"
//                + removedTask.toString()
//                + "\nNow you have "
//                + taskArray.size() + " tasks in the list.");
//    }
}