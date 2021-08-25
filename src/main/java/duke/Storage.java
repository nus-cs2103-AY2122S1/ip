package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    File taskList;

    public Storage(File taskList) {
        this.taskList = taskList;
    }

    public void write(TaskList list) {
        try {
            FileWriter writer = new FileWriter(taskList);

            for (int i = 0; i < list.size(); i++) {
                String temp = list.get(i).toString();
                writer.write(temp);
                writer.write("\n");
            }

            System.out.println("List saved!");
            writer.close();

        } catch (IOException | DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void read(TaskList list) {
        try {
            Scanner scanner = new Scanner(taskList);

            while (scanner.hasNextLine()) {
                String temp = scanner.nextLine();
                list.read(Parser.read(temp));
            }

            System.out.println("List loaded successfully!");
            scanner.close();

        } catch (IOException | DukeException e) {
            System.out.println(e.getMessage());
        }

    }
}
