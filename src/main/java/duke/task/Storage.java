package duke.task;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    private File f;
    private String txtPath;

    public Storage(String txtFile) throws IOException {
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "duke", "dir");
        txtPath = new StringBuilder().append(home).append("\\duke").
                append("\\dir").append("\\" + txtFile).toString();
        boolean directoryExists = Files.exists(path);

        if (!directoryExists) {
            Files.createDirectories(path);
        }

        f = new File(txtPath);

        if (!f.exists()) {
            f.createNewFile();
        }
    }

    public ArrayList<String> load() throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(txtPath));
        ArrayList<String> loadedTasks = new ArrayList<>();
        String s;
        while((s = reader.readLine()) != null) {
            loadedTasks.add(s);
        }
        reader.close();
        return loadedTasks;
    }

    public void save(TaskList tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(txtPath));
        for (int i = 1; i < tasks.size() + 1; i++) {
            writer.write(tasks.getStringDes(i) + "\n");
        }
        writer.close();
    }

}
