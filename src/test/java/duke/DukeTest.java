package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class DukeTest {
    /**
     * Runs Duke with the specified input in the input text file.
     */
    @Test
    public void runTest() {
        File f = new File("./data/duke.txt");
        if (f.exists()) {
            f.delete();
        }

        f = new File("./data/input.txt");
        try {
            Scanner sc = new Scanner(f);
            StringBuilder input = new StringBuilder();
            while (sc.hasNext()) {
                input.append(sc.nextLine() + "\n");
            }
            System.setIn(new ByteArrayInputStream(input.toString().getBytes()));
            new Scanner(System.in);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checks the output of running Duke to see if the data saved is the same.
     */
    @Test
    public void checkOutput() {
        try {
            Scanner sc = new Scanner(new File("./data/duke.txt"));
            Scanner sco = new Scanner(new File("./data/output.txt"));
            while (sc.hasNext()) {
                assertEquals(sc.nextLine(), sco.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
