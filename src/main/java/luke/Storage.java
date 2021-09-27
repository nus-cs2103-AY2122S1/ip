package luke;

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
    private File lukeData;

    /**
     * Constructor for Storage.
     */
    Storage() {
        this.dataPath = DATA_DIRECTORY_PATH.resolve("luke.txt");
        this.lukeData = new File(dataPath.toString());
    }

    /**
     * Saves all Entries from entries into memory.
     *
     * @param entries EntryList of all entries to be saved.
     * @throws LukeException Error thrown if data file is corrupted/missing.
     */
    public void saveEntries(EntryList entries) throws LukeException {
        Path dataPath = DATA_DIRECTORY_PATH.resolve("luke.txt");
        File lukeData = new File(dataPath.toString());
        try {
            FileWriter fw = new FileWriter(lukeData);
            BufferedWriter lukeWriter = new BufferedWriter(fw);
            for (Entry entry : entries){
                String nextEntry = entry.saveString();
                lukeWriter.write(nextEntry);
                lukeWriter.write("\n");
            }
            lukeWriter.close();
            fw.close();
        } catch (IOException e) {
            throw new LukeException("Luke's data file is corrupted/missing! Can't be saved");
        }
    }

    /**
     * Returns EntryList with data from memory.
     *
     * @return EntryList containing all data from memory.
     * @throws LukeException Error thrown if file is missing/corrupted.
     */
    public EntryList readData() throws LukeException {
        EntryList entries = new EntryList();
        try {
            return addEntriesFromFile(entries);
        } catch (FileNotFoundException e) {
            try {
                if (!(lukeData.getParentFile().mkdir()) && !(lukeData.createNewFile())) {
                    throw new LukeException("Uh-Oh! Your data can't be stored (I can't create a directory) :/");
                } else {
                    return new EntryList();
                }
            } catch (IOException err) {
                throw new LukeException("Uh-Oh! Your data can't be stored (IO Error) :/");
            }
        }
    }

    private EntryList addEntriesFromFile(EntryList entries) throws LukeException, FileNotFoundException {
        Scanner fileScanner = new Scanner(lukeData).useDelimiter("[,\n]");
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