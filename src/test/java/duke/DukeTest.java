import duke.Duke;
import duke.DukeStub;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    // referenced from https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
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

    @Test
    public void testMain() {
        final InputStream original = System.in;

        try {
            // Get expected output
            String expectedOutput = Files.readString(Path.of("../../text-ui-test/EXPECTED.TXT"), StandardCharsets.UTF_8);

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
