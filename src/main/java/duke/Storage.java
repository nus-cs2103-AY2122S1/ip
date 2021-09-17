package duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Class that implements methods to store Entries.
 */
public class Storage {

    private final String PROJECT_ROOT = System.getProperty("user.dir");
    private final Path DATA_DIRECTORY_PATH = Paths.get(PROJECT_ROOT, "data");

    private Path dataPath;
    private File dukeData;

    /**
     * Constructor for Storage.
     */
    Storage() {
        this.dataPath = DATA_DIRECTORY_PATH.resolve("duke.txt");
        this.dukeData = new File(dataPath.toString());
    }

    /**
     * Saves all Entries from entries into memory.
     *
     * @param entries EntryList of all entries to be saved.
     * @throws DukeException Error thrown if data file is corrupted/missing.
     */
    public void saveEntries(EntryList entries) throws DukeException {
        Path dataPath = DATA_DIRECTORY_PATH.resolve("duke.txt");
        File dukeData = new File(dataPath.toString());
        try {
            FileWriter fw = new FileWriter(dukeData);
            BufferedWriter dukeWriter = new BufferedWriter(fw);
            for (Entry entry : entries) {
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

    /**
     * Returns EntryList with data from memory.
     *
     * @return EntryList containing all data from memory.
     * @throws DukeException Error thrown if file is missing/corrupted.
     */
    public EntryList readData() throws DukeException {
        EntryList entries = new EntryList();
        try {
            return addEntriesFromFile(entries);
        } catch (FileNotFoundException e) {
            try {
                if (!(dukeData.createNewFile())) {
                    throw new DukeException("Uh-Oh! Your Data can't be stored!");
                } else {
                    return new EntryList();
                }
            } catch (IOException err) {
                throw new DukeException("Uh-Oh! Your Data can't be stored!");
            }
        }
    }

    private EntryList addEntriesFromFile(EntryList entries) throws DukeException, FileNotFoundException {
        Scanner fileScanner = new Scanner(dukeData).useDelimiter("[,\n]");
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
                nextEntry = new Deadline(entryData, fileScanner.next()); //fileScanner.next() will contain timing
                break;
            case "E":
                nextEntry = new Event(entryData, fileScanner.next()); //fileScanner.next() will contain timing
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
    }
}
