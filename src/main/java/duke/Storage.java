package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class to handle saving and loading tasks from a file
 */
public class Storage {
    File file;
    String filePath;

    /**
     *
     * @param filePath a String representing the directory of the file
     */
    Storage(String filePath){
        this.filePath = filePath;
        file = new File(filePath);
        if(!file.exists()){
            try{
                file = new File("data");
                file.mkdir();
                file = new File("data/duke.txt");
                file.createNewFile();
            }catch (IOException e){
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }

    /**
     * method to save tasks into a file
     * @param filePath a String representing the directory of the file
     * @param list list of tasks that will be written into the file
     */
    public void writeToFile(String filePath, TaskList list){
        try{
            String text = "";
            FileWriter fw = new FileWriter(filePath);
            for(int i=0;i<list.size();i++){
                Task t = list.get(i);
                text += textToAdd(t);
            }
            fw.write(text);
            fw.close();
        }catch (IOException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * method to load tasks from a file
     * @param filePath a String representing the directory of the file
     * @return return a TaskList object containing the list of tasks that was in the file
     * @throws FileNotFoundException
     */
    public ArrayList<Task> readFile(String filePath) throws FileNotFoundException{
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> list = new ArrayList<>();
        int count = 1;
        while (s.hasNext()) {
            String taskType = s.next();
            switch(taskType){
                case "T":
                    String[] todoArr = s.nextLine().trim().split("\\|");
                    Task todo = new Todo(todoArr[2].trim(),count);
                    if(Integer.parseInt(todoArr[1].trim()) == 1){
                        todo.markAsDone();
                    }
                    list.add(todo);
                    count++;
                    break;
                case "D":
                    String[] deadlineArr = s.nextLine().trim().split("\\|");
                    Task deadline = new Deadline(deadlineArr[2].trim(),deadlineArr[3].trim(),count);
                    if(Integer.parseInt(deadlineArr[1].trim()) == 1){
                        deadline.markAsDone();
                    }
                    list.add(deadline);
                    count++;
                    break;
                case "E":
                    String[] eventArr = s.nextLine().trim().split("\\|");
                    Task event = new Event(eventArr[2].trim(),eventArr[3].trim(),count);
                    if(Integer.parseInt(eventArr[1].trim()) == 1){
                        event.markAsDone();
                    }
                    list.add(event);
                    count++;
                    break;
            }
        }
        return list;
    }


    private static String textToAdd(Task t){
        String mes = "";
        if(t instanceof Todo){
            mes += "T" + statusIcon(t) + t.description +"\n";
        }
        if(t instanceof Deadline){
            Deadline d = (Deadline)t;
            mes += "D" + statusIcon(t) + t.description + " | " + d.getBy() + "\n";
        }
        if(t instanceof Event){
            Event e = (Event)t;
            mes += "E" + statusIcon(t) + t.description + " | " + e.getAt() + "\n";
        }
        return mes;
    }

    private static String statusIcon(Task t){
        if(t.getStatusIcon() == "X"){
            return " | 1 | ";
        }else{
            return " | 0 | ";
        }
    }

}
