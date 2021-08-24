import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;
    public Storage(String filepath) {
        String[] filepathArr = filepath.split("/");
        try {
            File dir = new File("./"+filepathArr[0]);
            if (!dir.exists()) {
                dir.mkdir();
            }
            file = new File("./"+ filepath);
            if (!file.exists()) {
                file.createNewFile();
            }

        } catch (IOException e) {
            e.printStackTrace();
            //throw new DukeException("Write file error");
        }
    }

    public void save(TaskList list) {
        try {
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < list.getSize(); i++) {
                int num = i + 1;
                writer.write(list.get(i).toString() + "\n");
            }
            writer.close();
            System.out.println("    File saved to /data/duke.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> load() throws DukeException {
        ArrayList<String> stringList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                stringList.add(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found");
        }
        return stringList;
    }

}
