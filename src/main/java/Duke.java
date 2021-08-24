import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws IOException {



// NEED TO HANDLE AUTO CREATE FILE, do not rewrite the next time we start
        String dukeFile = "text-ui-test/duke.txt";
        File f = new File(dukeFile);

        if(!f.exists()){
            System.out.println("Creating new file... Please try again");
            f.createNewFile();
        } else {
            System.out.println("Hello! I'm Duke\n" +
                    "To add a Todo, type -> todo <Description> \n" +
                    "To add a Deadline, type -> deadline <Description> /by <deadline>\n" +
                    "To add an Event, type -> event <Description> /at <details>\n" +
                    "To mark as done, type -> done <task list index>\n" +
                    "To see all of your tasks, type -> list\n" +
                    "To end session, type -> bye\n" +
                    "What can I do for you today?");
            Recieve recieve = new Recieve();
        recieve.run(dukeFile);
        }
//        String home = System.getProperty("user.home");
//        java.nio.file.Path path = java.nio.file.Paths.get(home, "Documents", "UNI", "Y1S2", "CS2103T", "ip");




    }

}
