package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Storage {

    private String filePath;
    static TaskList tasklist;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.tasklist = new TaskList();
    }
    /**
     * Gets called when user inputs bye.Saves tasks to hard disk in
     * DukeData.txt file
     * @param tasks TaskList containing tasks
     */
     void saveTasks(TaskList tasks){
        try{
            File data_file = new File("Data/DukeData.txt");
            FileWriter writer = new FileWriter("Data/DukeData.txt");//Overwriting entire file
            for(int i = 0;i < tasks.size();i++){
                Task task = tasks.get(i);
                writer.write(task.toString());
                writer.write("\n");
            }
            writer.close();
            if (data_file.createNewFile()) {
                System.out.println("File created: " + data_file.getName());
            }
//            else {
//                System.out.println("File already exists.");
//            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * Called when chatbot starts to load up TaskList
     * @param filePath FilePath where data is stored
     */
     static void readFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String currLine = s.nextLine();
//            System.out.println(currLine);
            char taskType = currLine.charAt(1);
//            System.out.println(taskType);
            switch (taskType){
                case 'T':
                    tasklist.addTask(new ToDo(currLine.substring(7)));
                    System.out.println(new ToDo(currLine.substring(7)));
                    break;
                case 'D':
                    int l = currLine.indexOf("(");
//                    int m = currLine.indexOf("by ");
                    int n = currLine.indexOf(")");
//                    System.out.println(currLine.substring(7,l));
//                    System.out.println(currLine.substring(l+1,n));
                    tasklist.addTask(new Deadline(currLine.substring(7,l),currLine.substring(l+1,n)));
                    System.out.println(new Deadline(currLine.substring(7,l),currLine.substring(l+1,n)));
                    break;
                case 'E':
                    int i = currLine.indexOf("(");
//                    int j = currLine.indexOf("at ");
                    int k = currLine.indexOf(")");
//                    System.out.println(currLine.substring(7,i));
//                    System.out.println(currLine.substring(j,k));
                    tasklist.addTask(new Event(currLine.substring(7,i),currLine.substring(i+1,k)));
                    System.out.println(new Event(currLine.substring(7,i),currLine.substring(i+1,k)));
                    break;
            }
        }
    }
}
