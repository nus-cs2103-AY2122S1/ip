package duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String load() {
        String str = "";

        File dukeData = new File(filePath);

        try {
            if (!dukeData.exists()) {
                dukeData.getParentFile().mkdirs();
                dukeData.createNewFile();
            } else {
                Scanner sc = new Scanner(dukeData);
                while (sc.hasNext()) {
                    str += sc.nextLine() + "\n";
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return str;
    }

    public void write(TaskList list) {
        String savedData = list.checkOut();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath)));
            writer.write(savedData);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
