package Duke.Storage;

import java.io.IOException;
import java.util.List;

public interface Storage<T> {
    List<T> load() throws IOException;

    void save(List<T> list) throws IOException;
}
