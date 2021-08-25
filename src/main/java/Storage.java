import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class Storage {
    private final File cache;

    public Storage(String filePath) {
        cache = new File(filePath);
    }

    public TaskList load() throws DukeException {
        TaskList tasks = new TaskList();
        if (cache.exists()) {
            try {
                Scanner sc = new Scanner(cache);
                boolean exit = false;
                Parser parser = new Parser(tasks); 
        
                while(!exit) {
                    try {
                        if (sc.hasNextLine()) {
                            String nextInput = sc.nextLine();
                            String[] resultMsg = parser.parseCommand(nextInput);
                            if (Ui.isByeMsg(resultMsg)) {
                                exit = true;
                            }
                        } else {
                            exit = true;
                        }
                    } catch(DukeException e) {
                        // Ignore previous invalid statements, continue adding tasks.
                    }
                }

                sc.close();
            } catch (FileNotFoundException e) {
                throw new DukeException("Could not load from cache.");
            }
        } else {
            File dir = cache.getParentFile();

            if (!dir.exists()) {
                if (!dir.mkdir()) {
                    throw new DukeException("Failed to crete required directory.");
                }
            }

            try {
                if (!cache.createNewFile()) {
                    throw new DukeException("Failed to crete required cache.");
                }
            } catch (IOException e) {
                throw new DukeException("Failed to crete required cache.");
            }
        }

        return tasks;
    }

    public void cache(String command) throws DukeException {
        try {
            FileWriter fw = new FileWriter(cache, true);
            fw.write(command + "\n");
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Failed to write to cache.");
        }
    }
}
