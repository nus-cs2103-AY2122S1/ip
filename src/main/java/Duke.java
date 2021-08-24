import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    public static void writeTaskToFile(Task task){
        try {
            FileWriter fileWriter = new FileWriter("data/duke.txt", true);
            fileWriter.write(task.getIcon() + "&&" + task.getStatus() + "&&"+ task.getDescription()
                    + "&&" + task.getTaskTime() + "\n");
            fileWriter.close();
        } catch (IOException e){
            System.out.println("can't find this file");
        }
    }

    public static Task convertStringToTask(String taskString) throws DukeException{
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

    public static void list(){
        File dukeFile = new File("data/duke.txt");
        try {
            System.out.println("Here are the tasks in your list:");
            Scanner scan = new Scanner(dukeFile);
            int i = 0;
            while(scan.hasNext()){
                String taskString = scan.nextLine();
                Task task = convertStringToTask(taskString);
                System.out.println((i+1) + "." + task.toString());
                i++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (DukeException dukeException){
            System.out.println(dukeException.getMessage());
        }
    }



    public static ArrayList<Task> convertFileToArray(){
        ArrayList<Task> taskArray = new ArrayList<>();
        File dukeFile = new File("data/duke.txt");
        try {
            Scanner scan = new Scanner(dukeFile);
            while(scan.hasNext()){
                String taskString = scan.nextLine();
                Task task = convertStringToTask(taskString);
                taskArray.add(task);
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        } catch (DukeException dukeException){
            System.out.println(dukeException.getMessage());
        }
        return taskArray;
    }

    public static void convertTaskArrayToFile(ArrayList<Task> taskArray){
        try {
            FileWriter clearFile = new FileWriter("data/duke.txt");
            clearFile.write("");
            clearFile.close();
            for(int i = 0; i < taskArray.size(); i++){
                System.out.println(taskArray.get(i).getStatus());
                writeTaskToFile(taskArray.get(i));
            }
        } catch (IOException e){
            System.out.println("can't find this file");
        }
    }

    public static void markTaskAsDone(int index){
        ArrayList<Task> taskArray = convertFileToArray();
        taskArray.get(index).done();
        System.out.println(taskArray.get(index).getStatus());
        convertTaskArrayToFile(taskArray);
        System.out.println("Nice! I've marked this task as done:\n"
                + taskArray.get(index).toString());
    }

    public static void deleteTask(int index) {
        ArrayList<Task> taskArray = convertFileToArray();
        Task removedTask = taskArray.remove(index);
        convertTaskArrayToFile(taskArray);
        System.out.println("Got it. I've removed this task:\n"
                + removedTask.toString()
                + "\nNow you have "
                + taskArray.size() + " tasks in the list.");
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = new String();

        System.out.println("Hello! I'm duke! What can I do for you?");
        input = sc.nextLine();
        while(!input.equals("bye")){
            try {
                String words[] = input.split(" ");
                if (input.equals("list")) {
//                    System.out.println("Here are the tasks in your list:");
//                    for (int i = 0; i < store.size(); i++) {
//                        System.out.println((i + 1) + "." + store.get(i).toString());
//                    }
                    list();
                } else if (words[0].equals("done")) {
                    int textNumber = Integer.parseInt(words[1]);
                    markTaskAsDone(textNumber - 1);
//                    store.get(textNumber - 1).done();
//                    System.out.println("Nice! I've marked this task as done:\n"
//                            + store.get(textNumber - 1).toString());
                } else if(words[0].equals("delete")){
                    int deleteIndex = Integer.parseInt(words[1]);
                    deleteTask(deleteIndex - 1);
//                    Task removedTask = store.get(deleteIndex);
//                    store.remove(deleteIndex - 1);
//                    System.out.println("Got it. I've removed this task:\n"
//                            + removedTask.toString()
//                            + "\nNow you have "
//                            + store.size() + " tasks in the list.");
                } else {
                    ArrayList<Task> taskArray = convertFileToArray();
                    Task task = new Task();
                    if (words[0].equals("todo")) {
                        if(words.length <= 1){
                            throw new DukeException("The description of a todo " +
                                    "cannot be empty.");
                        }
                        task = new ToDo(input.substring(5));
                    } else if (words[0].equals("deadline")) {
                        if(words.length <= 1 || words[1].equals("/by")){
                            throw new DukeException("The description of deadline task " +
                                    "cannot be empty.");
                        }
                        if(input.indexOf("/by") < 0){
                            throw new DukeException("Please enter '/by' followed by a task deadline.");
                        }
                        String content = input.substring(9, input.indexOf("/by") - 1);
                        if(input.indexOf("/by") + 4 >= input.length()){
                            throw new DukeException("Please enter a deadline.");
                        }
                        String by = input.substring(input.indexOf("/by") + 4);
                        task = new Deadline(content, by);
                    } else if (words[0].equals("event")) {
                        if(words.length <= 1 || words[1].equals("/at")){
                            throw new DukeException("The description of event " +
                                    "cannot be empty.");
                        }
                        if(input.indexOf("/at") < 0){
                            throw new DukeException("Please enter '/at' followed by an event time.");
                        }
                        String content = input.substring(6, input.indexOf("/at") - 1);
                        if(input.indexOf("/at") + 4 >= input.length()){
                            throw new DukeException("Please provide the event time.");
                        }
                        String at = input.substring(input.indexOf("/at") + 4);
                        task = new Event(content, at);
                    } else {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                    taskArray.add(task);
                    writeTaskToFile(task);

                    System.out.println("Got it. I've added this task:\n"
                            + task.toString()
                            + "\nNow you have "
                            + taskArray.size() + " tasks in the list.");
                }
            }catch(DukeException e){
                System.out.println("☹ OOPS!!!" + e.getMessage());
            }
            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
