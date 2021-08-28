package duke.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import org.junit.jupiter.api.Test;

class StorageTest {

  @Test
  void loadFile() throws FileNotFoundException {
    Storage s = new Storage("data/test.txt");
    PrintWriter writer = new PrintWriter("data/test.txt");
    writer.close();
    assertEquals(s.loadFile().size(), 0);
  }
}