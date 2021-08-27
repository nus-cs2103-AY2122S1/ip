package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DukeTest {
    //@@author dfa
    //Reused from https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
    // with minor modifications
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
    //@@author dfa

    @Test
    public void testMain() {
        final InputStream original = System.in;

        try {
            // Get expected output
            String expectedOutput = Files.readString(
                    Path.of("../../text-ui-test/EXPECTED.TXT"),
                    StandardCharsets.UTF_8
            );

            // Feed in input to Duke
            File file = new File("../../text-ui-test/input.txt");
            final FileInputStream fips = new FileInputStream(file);
            System.setIn(fips);
            DukeStub.main(null);
            System.setIn(original);

            assertEquals(expectedOutput, outContent.toString());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
