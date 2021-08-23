package duke;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    @Test
    public void storageTest() {
        try {
            Scanner testScanner = new Scanner(Paths.get("java","duke","add-markdone-command"));
            Storage storage = new Storage();
            Parser parser = new Parser(new TaskList(storage));
            parser.parse(testScanner);

            BufferedReader expectedReader = new BufferedReader(new FileReader(String.valueOf(Path.of("java","duke","expected-storage"))));
            BufferedReader actualReader2 = new BufferedReader(new FileReader(String.valueOf(Path.of("data","record"))));
            String expected = expectedReader.readLine();
            String actual = actualReader2.readLine();

            while (expected != null && actual != null) {
                assertEquals(expected,actual);
                expected = expectedReader.readLine();
                actual = actualReader2.readLine();
            }

            if(expected != null || actual != null) {
                fail();
            }

        }  catch (IOException e) {
            System.out.println("Test file does not exist");
        }
    }
}