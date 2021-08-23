import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage(File file) {
        this.file = file;
    }

    public ArrayList<Task> load() throws IOException {
        Scanner myReader = new Scanner(file);
        ArrayList<Task> result = new ArrayList<>();
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            result.add(Task.create(line.split("\\|")));
        }
        myReader.close();
        return result;
    }
}
