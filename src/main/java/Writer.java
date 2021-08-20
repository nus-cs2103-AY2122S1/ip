import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Writer {

  private Writer() {
    System.out.println("Don't try to initialize this utility class! :(");
  }

  public static String writeToFile(List<Task> taskList) throws IOException {
    FileWriter myWriter = new FileWriter("FergusChatBot.txt");

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
