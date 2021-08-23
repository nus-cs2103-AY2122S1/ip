package duke;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    /**
     * Runs Duke with the specified input in the input text file.
     */
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

            Duke duke = new Duke("./data/duke.txt");
            duke.run();


        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    /**
     * Checks the output of running Duke to see if the data saved is the same.
     */
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
