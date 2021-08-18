package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class handles reading from input source.
 *
 * @author kevin9foong
 */
public class InputHandler {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public String readInput() throws IOException {
        return br.readLine();
    }
}
