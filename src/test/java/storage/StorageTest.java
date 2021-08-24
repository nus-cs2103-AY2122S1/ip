package storage;

import duke.storage.Storage;
import duke.task.*;
import org.junit.jupiter.api.Test;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void loadTest() {
        String[] strings = {"T | 0 | return book",
                "E | 1 | carnival  | 2/12/2021 1430",
                "D | 0 | assignment 0  | 29/8/2021 2359"};
        Storage storage = new Storage("test.txt");
        try {
            FileWriter fw = new FileWriter("test.txt");
            for (String str : strings) {
                fw.write(str + '\n');
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        ArrayList<Task> list = storage.load();
        assertEquals("[T][ ] return book", list.get(0).toString());
        assertEquals("[E][X] carnival (at: 2 Dec 2021 02:30 PM)", list.get(1).toString());
        assertEquals("[D][ ] assignment 0 (by: 29 Aug 2021 11:59 PM)", list.get(2).toString());
    }
}
