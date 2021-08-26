package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

class Duke {

    Scanner myScanner = new Scanner(System.in);


    private Parser parser;


    public Duke(String filePath) {
        parser = new Parser(myScanner);
    }

    static List<Task> toDo = new ArrayList<>();

    static int countTasks(){
        int count = 0;
        for(Task task:toDo){
            if(!task.isDone){
                count ++;
            }
        }
        return count;
    }

    static void saveTasks(List<Task> tasks){//Called on "bye"
        try{
            File data_file = new File("Data/DukeData.txt");
            FileWriter writer = new FileWriter("Data/DukeData.txt");//Overwriting entire file
            for(Task task: tasks){
                writer.write(task.toString());
                writer.write("\n");
            }
            writer.close();
            if (data_file.createNewFile()) {
                System.out.println("File created: " + data_file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

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
                    toDo.add(new ToDo(currLine.substring(7)));
                    System.out.println(new ToDo(currLine.substring(7)));
                    break;
                case 'D':
                    int l = currLine.indexOf("(");
//                    int m = currLine.indexOf("by ");
                    int n = currLine.indexOf(")");
//                    System.out.println(currLine.substring(7,l));
//                    System.out.println(currLine.substring(l+1,n));
                    toDo.add(new Deadline(currLine.substring(7,l),currLine.substring(l+1,n)));
                    System.out.println(new Deadline(currLine.substring(7,l),currLine.substring(l+1,n)));
                    break;
                case 'E':
                    int i = currLine.indexOf("(");
//                    int j = currLine.indexOf("at ");
                    int k = currLine.indexOf(")");
//                    System.out.println(currLine.substring(7,i));
//                    System.out.println(currLine.substring(j,k));
                    toDo.add(new Event(currLine.substring(7,i),currLine.substring(i+1,k)));
                    System.out.println(new Event(currLine.substring(7,i),currLine.substring(i+1,k)));
                    break;
            }
        }
    }

    public void run() {

        Ui.welcomeUser();
        while (myScanner.hasNext()) {
            try {
                String input = myScanner.nextLine();//Parse the line
                parser.parse(input);
                if(parser.getBreak()){
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        myScanner.close();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
