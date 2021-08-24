package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    //private static List<String> lines;
    public Storage(){

    }

    void checkExistence(){
        File file = new File("data/list.txt");
        if(!file.exists()){
            try {
                new File("data").mkdir();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void listFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        TaskList.lines = new ArrayList<String>();
        System.out.println("hi");
        while (s.hasNext()) {
            TaskList.lines.add(s.nextLine());

        }
        s.close();
    }

    void writeListToFile(String filePath) throws IOException {
        FileWriter clearer = new FileWriter(filePath);
        clearer.write(""); //clear the file
        clearer.close();
        FileWriter fw = new FileWriter(filePath, true);
        for(String line : TaskList.lines){
            fw.write(line + System.lineSeparator());
        }
        fw.close();
    }

}
