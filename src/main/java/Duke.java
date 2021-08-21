import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Duke class contains the main method that takes
 * in inputs.
 */
public class Duke {

    protected static String pathAddress = "../data/duke.txt";
    protected static File dataFile;

    /**
     * Creates a duke.txt file if it does't exist
     */
    public static void createFile() {
        File testFile = new File(pathAddress);
        try {
            testFile.createNewFile();
            dataFile = testFile;
        } catch (IOException e) {
            System.out.println("error creating file");
        }
    }

    /**
     * Main method for the Duke class.
     * @params the inputs from the user.
     */
    public static void main(String[] args) {
        Response response = new Response();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        System.out.println("These are the current tasks I have:");
        createFile();

        try {

            Scanner textFile = new Scanner(dataFile);
            while (textFile.hasNext()) {
                System.out.println(textFile.nextLine());
            }

            Scanner inputs = new Scanner(System.in);
            while (inputs.hasNext()) {
                System.out.println(response.output(inputs.nextLine()));
            }

            textFile.close();
            inputs.close();
        } catch (FileNotFoundException e) {
            System.out.println("File wasnt found!");
        }
    }
}
