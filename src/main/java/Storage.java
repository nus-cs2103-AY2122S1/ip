import java.io.*;
import java.util.ArrayList;

public final class Storage {
  private final static String filePath = "../data/duke.txt";

  public void checkFiles() throws DukeException{
    File data = new File("../data");
    File saves = new File(filePath);
    try {
      if (!data.exists() || !data.isDirectory()) {
        boolean dir = data.mkdir();
      }
      if (!saves.exists()) {
        boolean save = saves.createNewFile();
      }
    } catch (IOException e) {
      throw new DukeException("Unexpected issue encountered");
    }
  }

  public void resetFile(ArrayList<Task> currTasks) {
    File file = new File(filePath);
    if (!file.delete()) {
      Ui.showInput("something went wrong");
    }
    try {
      boolean other = file.createNewFile();
      FileWriter writer = new FileWriter(filePath, true);
      for (Task t : currTasks) {
        writer.write(t.getSaveFormat() + "\n");
        writer.flush();
      }
      writer.close();
    } catch (IOException e) {
      Ui.showInput("Unable to write, something went wrong");
    }
  }

  public ArrayList<Task> loadSaves() {
    ArrayList<Task> tasksLoaded = new ArrayList<>();
    try {
      BufferedReader reader = new BufferedReader(new FileReader(filePath));
      String s = reader.readLine();
      while (s != null) {
        String[] words = s.split("\\|");
        if (words[0].equals("T")) {
          ToDoTask t = new ToDoTask(words[1]);
          if (words[words.length - 1].equals("1")) {
            t.markAsDone();
          }
          tasksLoaded.add(t);
        }
        if (words[0].equals("D")) {
          DeadLineTask d = new DeadLineTask(words[1], words[2]);
          if (words[words.length - 1].equals("1")) {
            d.markAsDone();
          }
          tasksLoaded.add(d);
        }
        if (words[0].equals("E")) {
          EventTask e = new EventTask(words[1], words[2]);
          if (words[words.length - 1].equals("1")) {
            e.markAsDone();
          }
          tasksLoaded.add(e);
        }
        s = reader.readLine();
      }
      reader.close();
    } catch (FileNotFoundException e) {
      Ui.showInput(e.getMessage());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return tasksLoaded;
  }
}
