package duke;




import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UITest {
    private static PrintStream ORIGINAL_OUT = System.out;
    private static ByteArrayOutputStream NEW_STREAM = new ByteArrayOutputStream();
    private static PrintStream NEW_OUT = new PrintStream(NEW_STREAM);

    @BeforeEach
    void setUp() {
        System.setOut(NEW_OUT);
    }

    @AfterEach
    void tearDown() {
        System.setOut(ORIGINAL_OUT);
    }

    @Test
    void displayLogo() {
        String logo =
                "  _____          _   _    ___   ___   ___   ___  \n" +
                " |  __ \\   /\\   | \\ | |  / _ \\ / _ \\ / _ \\ / _ \\ \n" +
                " | |  | | /  \\  |  \\| | | (_) | | | | | | | | | |\n" +
                " | |  | |/ /\\ \\ | . ` |  \\__, | | | | | | | | | |\n" +
                " | |__| / ____ \\| |\\  |    / /| |_| | |_| | |_| |\n" +
                " |_____/_/    \\_\\_| \\_|   /_/  \\___/ \\___/ \\___/ \n\n";


        new UI().displayLogo();

        NEW_OUT.flush();
        assertEquals(NEW_STREAM.toString().replace("\r",""),logo);
    }

//    @Test
//    void displayWelcome() {
//    }
//
//    @Test
//    void display() {
//    }
//
//    @Test
//    void testDisplay() {
//    }
//
//    @Test
//    void showLoadingError() {
//    }
//
//    @Test
//    void displayException() {
//    }
}