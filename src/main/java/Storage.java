
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.ArrayList;


public class Storage {
    private File file;

    Storage (String filePath){
        File temp =  new File(filePath);
        try {
            temp.createNewFile();
            this.file = temp;
        } catch (Exception e) {
            System.out.println("Can't create file");
        }
    }

    ArrayList<Task> parseFile () {
        ArrayList<Task> history = new ArrayList<Task>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                Task temp = parseTask(currentLine);
                history.add(temp);
            }
        } catch (Exception e ) {
            System.out.println("File is not found");
        }
        return history;
    }

    Task parseTask(String task) {
        String[] temp = task.split(" \\| ");
        if (temp.length == 3) {
            Todo toReturn = new Todo(temp[2]);
            if (temp[1].equalsIgnoreCase("1")) {
                toReturn.markAsDone();
            }
            return toReturn;
        } else if (temp[0].equalsIgnoreCase("E")) {
            Event toReturn = new Event(temp[2],temp[3]);
            if (temp[1].equalsIgnoreCase("1")) {
                toReturn.markAsDone();
            }
            return toReturn;
        } else {
            Deadline toReturn = new Deadline(temp[2],temp[3]);
            if (temp[1].equalsIgnoreCase("1")) {
                toReturn.markAsDone();
            }
            return toReturn;
        }
    }

    void fileClear () {
        try {
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.close();
        } catch (Exception e ) {
            System.out.println("Can't clear file.");
        }
    }

    void writeToFile (String text) {
        try {
            System.out.println("text");
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(text);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Cant write to file");
        }
    }
}
