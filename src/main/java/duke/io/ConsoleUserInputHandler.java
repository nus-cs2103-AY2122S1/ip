package duke.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Handles reading from input source.
 *
 * @author kevin9foong
 */
public class ConsoleUserInputHandler implements UserInputHandler {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Reads a line of input from the console.
     *
     * @return String which contains user input from the console.
     * @throws IOException thrown when failure to read from console occurs.
     */
    public String handleInput() throws IOException {
        return br.readLine();
    }
}
