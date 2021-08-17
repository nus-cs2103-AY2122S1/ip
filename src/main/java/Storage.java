import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;

public class Storage {
    BufferedWriter writer;
    BufferedReader reader;
    Path path;

    public Storage (String filename) {
        try {
            path = FileSystems.getDefault().getPath("data", filename);
            reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Temp. IO Exception occurred while initialising Storage.");
            e.printStackTrace();
        }
    }

    public void load(TaskList tasklist) {
        try {
            String data = reader.readLine();
            if (data != null) {
                System.out.println("Looks like there's a previous record! Loading the data in...");
            } else {
                return;
            }
            while(data != null) {
                tasklist.add(formatForLoad(data));
                data = reader.readLine();
            }
            System.out.println("Load complete!");
        } catch (IOException e) {
            System.out.println("error occurred in load");
            e.printStackTrace();
        }

    }


    public void save(TaskList tasklist) {
        System.out.println("Just a moment, i'm saving your list!");
        try {
            writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Temp. IO Exception occurred while initialising writer");
            e.printStackTrace();
        }
        for (int i = 0; i < tasklist.size(); i++) {
            try {
                writer.write(formatForSave(tasklist.get(i)));
                writer.newLine();
            } catch (IOException e) {
                System.out.println("Temp. IO Exception occurred while writing");
                e.printStackTrace();
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("Temp. IO Exception occurred while closing writer.");
            e.printStackTrace();
        }
    }

    //converts a Task into a string to be saved to a txt file.
    private String formatForSave(Task e) {
        String entry = "";
        entry = entry + e.getType() + "|";
        entry = entry + e.getFlag() + "|";
        entry = entry + e.getLabel();
        return entry;
    }

    //dual of formatForSave. Converts from saved string back to Task.
    private Task formatForLoad(String s) {
        String[] arr = s.split("[|]");
        Task t;
        if (arr[0].equals("T")) {
            t = new Todo(arr[2]);
        } else if (arr[0].equals("D")) {
            t = new Deadline(arr[2]);
        } else if (arr[0].equals("E")) {
            t = new Event(arr[2]);
        } else {
            System.out.println("INVALID DATABASE ENTRY");
            t = new Todo("system error");
        }

        if (Boolean.parseBoolean(arr[1])) {
            t.setFlag(true);
        }

        return t;
    }
}
