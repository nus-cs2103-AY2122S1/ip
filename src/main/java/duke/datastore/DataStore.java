package duke.datastore;

import java.util.ArrayList;
import duke.parser.Parser;
import duke.tasks.Task;

public class DataStore {
    private ArrayList<Task> dataStore;
    
    public DataStore() {
        dataStore = new ArrayList<Task>();
    }

    public void ingestExternalSource(ArrayList<String> store, Parser parser) {
        dataStore = parser.parseFromFile(store);
    }

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
