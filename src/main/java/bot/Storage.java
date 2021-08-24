package bot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tasks.Task;

public class Storage {

  public static final String savefilePath = "./data/save.txt";

  private static void write(List<String> texts) {
    try {
      FileWriter writer = new FileWriter(savefilePath);
      for (String s: texts) {
        writer.write(s + System.lineSeparator());
      }
      writer.close();
    } catch(IOException e) { }
  }

  private static List<String> read() {
    try {
      List<String> lines = new ArrayList<>();
      BufferedReader reader = new BufferedReader(new FileReader(savefilePath));
      String line = reader.readLine();
      while (line != null) {
        lines.add(line);
        line = reader.readLine();
      }
      reader.close();
      return lines;
    } catch (IOException e) {
      return new ArrayList<>();
    }
  }

  public static void save(Bot bot) {
    Storage.write(bot.taskList.getTaskStringList());
  }

  public static void load(Bot bot) {
    List<Task> tasks = new ArrayList<>();
    List<String> taskStrings = Storage.read();
    for (String taskString: taskStrings) {
      if (!taskString.equals("")) {
        tasks.add(Task.deserialize(taskString));
      }
    }
    bot.taskList.set(tasks);
  }
  
}
