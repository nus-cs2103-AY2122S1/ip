import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

  private String filepath;

  public Storage(String filePath) {
    this.filepath = filePath;
  }

  private static final String READ_SUCCESS = "A saved file has been found! It will now be loaded :)";
  private static final String READ_FAILURE = "No saved file has been found :(";

  public List<String> load() {
    List<String> taskArrayAsString = new ArrayList<>();
    try {
      File myObj = new File(this.filepath);
      Scanner myStorage = new Scanner(myObj);
      Printer.print(READ_SUCCESS);
      while (myStorage.hasNextLine()) {
        String data = myStorage.nextLine();
        taskArrayAsString.add(data);
      }
      myStorage.close();
    } catch (FileNotFoundException e) {
      Printer.print(READ_FAILURE);
    }
    return taskArrayAsString;
  }

  public String writeToFile(List<Task> taskList) throws IOException {
    FileWriter myWriter = new FileWriter(this.filepath);

    String output = "";
    for (Task task : taskList) {
      output += task.toWriteString();
      output += "\n";
    }

    myWriter.write(output);
    myWriter.close();

    return output;
  }
}
