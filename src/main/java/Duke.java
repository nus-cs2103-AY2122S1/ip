import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    private String storageFileName;

    public Duke(String storageFileName) {
        this.storageFileName = storageFileName;
    }

    public static class DukeException extends Exception {
        public DukeException(String errorMessage) {
            super(errorMessage);
        }
    }

    public void run() throws IOException, DukeException {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList storage = new TaskList();
        Files.createDirectories(Paths.get(this.storageFileName).getParent().getFileName());
        File file = new File(this.storageFileName);

        if (!file.createNewFile()) {
            try {
                FileInputStream fis = new FileInputStream(this.storageFileName);
                ObjectInputStream ois = new ObjectInputStream(fis);
                storage = (TaskList) ois.readObject();
                ois.close();
            } catch (IOException e) {
                System.out.println("IOException");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("classnotfound");
                e.printStackTrace();
            }
        } else {
            System.out.println("CAnt find the file");
            storage = new TaskList();
        }


        ui.init();
        boolean ended = false;

        while (!ended) {
            // Enter data using BufferReader
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

            // Reading data using readLine
            String input = ui.getNextCommand();
            ended = parser.parse(input, storage);
        }

        FileOutputStream fos = new FileOutputStream(this.storageFileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(storage);
        oos.close();
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        try {
            duke.run();
        } catch (IOException e) {

            System.out.println("Error found while parsing input, shutting down");
            e.printStackTrace();
        } catch (DukeException e) {

        }
    }
}
