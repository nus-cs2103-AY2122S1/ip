import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class Storage {
    public File txt;
    public void loadFile() throws Exception {
        Path p = Paths.get("data");
        if (!Files.exists(p)) {
            Files.createDirectories(p);
        }
        File f = Paths.get("data", "duke.txt").toFile();
        f.createNewFile();
        txt = f;
    }
    public void readFromFile(TaskList t){
        try {
            BufferedReader br = new BufferedReader(new FileReader(txt));
            String curLine;
            while ((curLine = br.readLine()) != null) {
                String[] parts = curLine.split(" ", 3);
                String[] descriptionParts;
                char taskType = parts[0].charAt(1);
                boolean completionStatus = parts[1].charAt(1) == 'X';
                switch(taskType){
                case 'T':
                    t.addToList(new Todo(parts[2], completionStatus));
                    break;
                case 'D':
                    descriptionParts = parts[2].split(" \\(by: ");
                    Deadline d = new Deadline(descriptionParts[0], completionStatus, LocalDateTime.parse(descriptionParts[1].substring(0, descriptionParts[1].length()-1)));
                    t.addToList(d);
                    break;
                case 'E':
                    descriptionParts = parts[2].split(" \\(at: ");
                    Event e = new Event(descriptionParts[0], completionStatus, LocalDateTime.parse(descriptionParts[1].substring(0, descriptionParts[1].length()-1)));
                    t.addToList(e);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Read error: " + e.getMessage());
        }
    }
    public void writeToFile(TaskList t) throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(txt));
        String curLine;
        for(int i=0; i<t.size(); i++) {
            curLine = t.getTask(i).toString();
            bw.write(curLine + "\n");
        }
        bw.close();
    }

}
