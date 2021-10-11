package duke.storage;

import java.io.IOException;
import java.util.List;

/**
 * A store of a certain type of elements.
 *
 * @author cai
 * @param <T> The type of elements in this storage.
 */
public interface Storage<T> {
    /**
     * Loads and returns a list of elements in this store.
     *
     * @return The list of elements in this store.
     * @throws IOException If an IOException is thrown when loading the elements.
     */
    List<T> load() throws IOException;

    /**
     * Saves the specified list of elements in this store.
     *
     * @param list The list of elements to save.
     * @throws IOException If an IOException is thrown when saving the elements.
     */
    void save(List<T> list) throws IOException;
}
