package Duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }


//    public void createNew() throws IOException {
//
//        File f = new File(this.filePath);
//        if (!f.exists()) {
//            System.out.println("Creating new file... Please try again");
//            f.createNewFile();
//        }
//    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> arr = new ArrayList<>();

        File f = new File(this.filePath); // create a File for the given file path
        if (!f.exists()) {
            System.out.println("Creating new file...");
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("Creating new file...1");
            }

        }
        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        while (s.hasNext()) {
            String readLine = s.nextLine();
            if (readLine.contains("[T]")) {
                Todo task = new Todo(readLine.substring(7));
                if (readLine.contains("[X]")) {
                    task.markAsDone();
                }
                arr.add(task);
            }
            else if (readLine.contains("[D]")) {
                String[] spl = readLine.split("--");
                int length = spl[1].length();

                LocalDate date = LocalDate.parse(spl[1].substring(5, length - 1));
                Deadline task = new Deadline(spl[0].substring(7), date);
                if (readLine.contains("[X]")) {
                    task.markAsDone();
                }
                arr.add(task);
            }
            else {
                String[] spl = readLine.split("--");
                int length = spl[1].length();
                Event task = new Event(spl[0].substring(7), spl[1].substring(5, length - 1));
                if (readLine.contains("[X]")) {
                    task.markAsDone();
                }
                arr.add(task);
            }
        }
        return arr;
    }

    public void writeToFile(ArrayList<Task> arr) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for(Task str: arr) {
            fw.write(str.toStore() + System.lineSeparator());
        }
        fw.close();
    }

    public String getPath() {
        return this.filePath;
    }


}
