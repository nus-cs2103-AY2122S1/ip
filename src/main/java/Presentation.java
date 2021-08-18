import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Presentation {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public String scan() {
        String command = new String();
        try {
            command = reader.readLine();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return command;
    }
}
