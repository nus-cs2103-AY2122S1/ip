package bloom.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import bloom.constant.Message;

/**
 * Manages database.
 */
public class Storage {

    /**
     * Saves the list of tasks into the database file.
    */
    public void saveToDb() {
        try {
            Path path = Paths.get("src/main/java/bloom/data/bloomDB.txt");

            if (!Files.exists(path)) {
                Files.createFile(path);
            }

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < TaskList.size(); ++i) {
                stringBuilder.append(TaskList.get(i).toDb());
            }
            String tasks = stringBuilder.toString();

            byte[] bytes = tasks.getBytes(tasks);
            Files.write(path, bytes);
        } catch (IOException e) {
            System.out.println(Message.EXCEPTION_IO.getMessage());
        }
    }
}
