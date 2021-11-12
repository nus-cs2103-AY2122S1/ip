package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UI {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Used to scan and parse the commands entered by the user
     * @return the command as one string
     */
    public static String scan() {
        String command = new String();
        try {
            command = reader.readLine();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return command;
    }


    /**
     * Seperate inputs
     *
     * @param output is the string to be surrounded by the lines
     */
    public static void renderOutput(String output) {
        System.out.println("________________________________________________________");
        output.lines().map(x -> "     " + x).forEach(System.out::println);
        System.out.println("________________________________________________________");
    }
}
