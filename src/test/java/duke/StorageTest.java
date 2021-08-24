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
    public void autoSave_autoLoadTest() {
        try {
            Scanner testScanner = new Scanner(Paths.get("java","duke","autosave-autoload-command"));
            Storage storage = new Storage();
            Parser parser = new Parser(new TaskList(storage));
            parser.parse(testScanner);

            BufferedReader expectedReader = new BufferedReader(new FileReader(String.valueOf(Path.of("java","duke","expected-storage"))));
            BufferedReader actualReader = new BufferedReader(new FileReader(String.valueOf(Path.of("data","record"))));
            String expected = expectedReader.readLine();
            String actual = actualReader.readLine();

            while (expected != null && actual != null) {
                assertEquals(expected,actual);
                expected = expectedReader.readLine();
                actual = actualReader.readLine();
            }

            if(expected != null || actual != null) {
                fail();
            }

        }  catch (IOException e) {
            System.out.println("Test file does not exist");
        }
    }

    @Test
    public void save_loadTest() {
        try {
            Scanner testScanner = new Scanner(Paths.get("java","duke","save-load-command"));
            Storage storage = new Storage();
            Parser parser = new Parser(new TaskList(storage));
            parser.parse(testScanner);

            BufferedReader expectedReader = new BufferedReader(new FileReader(String.valueOf(Path.of("java","duke","expected-storage"))));
            BufferedReader actualReader = new BufferedReader(new FileReader(String.valueOf(Path.of("data","record"))));
            String expected = expectedReader.readLine();
            String actual = actualReader.readLine();

            while (expected != null && actual != null) {
                assertEquals(expected,actual);
                expected = expectedReader.readLine();
                actual = actualReader.readLine();
            }

            if(expected != null || actual != null) {
                fail();
            }

        }  catch (IOException e) {
            System.out.println("Test file does not exist");
        }
    }
}