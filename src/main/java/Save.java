import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Save {

    public static void addData(PrintWriter writer, Task task) {
        writer.println(task.toString());
        writer.flush();
    }


    public static void markAsDeleted(File file, Task task) {
        try {
            Scanner sc = new Scanner(file);
            StringBuffer buffer = new StringBuffer();

            while (sc.hasNext()) {
                buffer.append(sc.nextLine() + System.lineSeparator());
            }

            String fileContents = buffer.toString();
            sc.close();

            String oldLine = task.toString();
            String newLine = oldLine + " [deleted]";
            fileContents = fileContents.replace(oldLine, newLine);

            FileWriter wr = new FileWriter(file);
            wr.append(fileContents);
            wr.flush();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveAsCompleted(File file, Task task, String oldLine) {
        try {
            Scanner sc = new Scanner(file);
            StringBuffer buffer = new StringBuffer();

            while (sc.hasNext()) {
                buffer.append(sc.nextLine() + System.lineSeparator());
            }

            String fileContents = buffer.toString();
            sc.close();

            String newLine = task.toString();
            fileContents = fileContents.replace(oldLine, newLine);

            FileWriter wr = new FileWriter(file);
            wr.append(fileContents);
            wr.flush();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}

