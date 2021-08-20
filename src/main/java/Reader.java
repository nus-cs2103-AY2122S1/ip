import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {

  private Reader() {
    System.out.println("Don't try to initialize this utility class! :(");
  }

  private static final String READ_SUCCESS = "A saved file has been found! It will now be loaded :)";
  private static final String READ_FAILURE = "No saved file has been found :(";

  public static List<String> readTasksFromFile() {
    List<String> taskArrayAsString = new ArrayList<>();
    try {
      File myObj = new File("FergusChatBot.txt");
      Scanner myReader = new Scanner(myObj);
      Printer.print(READ_SUCCESS);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        taskArrayAsString.add(data);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      Printer.print(READ_FAILURE);
    }
    return taskArrayAsString;
  }
}
