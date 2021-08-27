package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {

    private final String PROJECT_ROOT = System.getProperty("user.dir");
    private final Path DATA_DIRECTORY_PATH = Paths.get(PROJECT_ROOT,"data");

    Storage() {}

    public void saveEntries(EntryList entries) throws DukeException {
        Path dataPath = DATA_DIRECTORY_PATH.resolve("duke.txt");
        File dukeData = new File(dataPath.toString());
        try {
            FileWriter fw = new FileWriter(dukeData);
            BufferedWriter dukeWriter = new BufferedWriter(fw);
            for (Entry entry : entries){
                String nextEntry = entry.saveString();
                dukeWriter.write(nextEntry);
                dukeWriter.write("\n");
            }
            dukeWriter.close();
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Duke's data file is corrupted/missing! Can't be saved");
        }
    }

    public EntryList readData() throws DukeException {
        Path dataPath = DATA_DIRECTORY_PATH.resolve("duke.txt");
        File dukeData = new File(dataPath.toString());
        EntryList entries = new EntryList();
        try {
            Scanner fileScanner = new Scanner(dukeData);
            fileScanner.useDelimiter("[,\n]");
            while (fileScanner.hasNext()) {
                String entryType = fileScanner.next();
                boolean isDone = Integer.parseInt(fileScanner.next()) == 1;
                String entryData = fileScanner.next();
                Entry nextEntry = new Todo("");
                boolean hasNextEntry = true;
                switch (entryType) {
                case "T":
                    nextEntry = new Todo(entryData);
                    break;
                case "D":
                    String deadlineTiming = fileScanner.next();
                    nextEntry = new Deadline(entryData, deadlineTiming);
                    break;
                case "E":
                    String eventTiming = fileScanner.next();
                    nextEntry = new Event(entryData, eventTiming);
                    break;
                default:
                    //Corrupted Entry Case
                    hasNextEntry = false;
                    break;
                }
                if (hasNextEntry) {
                    if (isDone) {
                        nextEntry.setDone();
                    }
                    entries.addEntry(nextEntry);
                }
            }
            return entries;
        } catch (FileNotFoundException e) {
            try {
                if (!(dukeData.createNewFile())) {
                    throw new DukeException("Uh-Oh! Your Data can't be stored!");
                } else {
                    return new EntryList();
                }
            } catch (IOException err) {
                throw new DukeException(err.getMessage());
            }
        }
    }
}
