import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Duke {

    public static void main(String[] args) {

            } else if (userInput.startsWith("todo")) {
                try {
                    handleToDo(userInput, false, true);
                } catch (EmptyTaskDescriptionException e) {
                    System.out.println(e.getMessage());
                }
            } else if (userInput.startsWith("deadline")) {
                try {
                    handleDeadline(userInput, false, true);
                } catch (EmptyTaskDescriptionException e) {
                    System.out.println(e.getMessage());
                }
            } else if (userInput.startsWith("event")) {
                try {
                    handleEvent(userInput, false, true);
                } catch (EmptyTaskDescriptionException e) {
                    System.out.println(e.getMessage());
                }
                // All other cases means input error
                UnknownInputException e = new UnknownInputException();
                try {
                    throw e;
                } catch (UnknownInputException e1) {
                    System.out.println(e1.getMessage());
                }
            }
        }
    }
}
