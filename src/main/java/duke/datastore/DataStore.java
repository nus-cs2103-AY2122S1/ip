package duke.datastore;

/**
 * This class represents the DataStore which serves as the primary
 * working memory for all actions performed on the chatbot.
 *
 * @author Rishabh Anand
 * @version CS2103 AY21/22 Semester 1
 */

import java.util.ArrayList;
import duke.parser.Parser;
import duke.tasks.Task;

public class DataStore {
    private ArrayList<Task> dataStore;
    
    public DataStore() {
        dataStore = new ArrayList<Task>();
    }

    /**
     * Ingets and stores the data from the flat file in memory.
     *
     * @param store the data store containing the per-line task records.
     * @param parser the Parser object that parses the commands from the file.
     */
    public void ingestExternalSource(ArrayList<String> store, Parser parser) {
        dataStore = parser.parseFromFile(store);
    }

    // returns the size of the datastore
    public int length() {
        return dataStore.size();
    }

    public Task get(int index) {
        return dataStore.get(index);
    }

    public void add(Task task) {
        dataStore.add(task);
    }

    public void remove(int i) {
        dataStore.remove(i);
    }
}
