package duke.util;

import duke.exception.DukeException;
import duke.exception.LoadingException;
import duke.exception.SavingException;
import duke.exception.StorageException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the storage in the Duke program to store tasks created by users.
 */
public class Storage {
  private File file;

  /**
   * Constructs a Storage for the Duke program.
   *
   * @param filePath File path of the storage.
   * @throws DukeException If file path does not exist.
   */
  public Storage(String filePath) throws DukeException {
    // ensure parent directories exist
    String[] filePathComponents = filePath.split("/");
    String filePathTemp = filePathComponents[0];
    for (int i = 0; i < filePathComponents.length - 1; i++) {
      File dir = new File(filePathTemp);
      if (!dir.exists()) {
        dir.mkdir();
      }
      filePathTemp += filePathComponents[i + 1];
    }

    // ensure file exists
    this.file = new File(filePath);
    try {
      this.file.createNewFile();
    } catch (IOException e) {
      throw new StorageException();
    }
  }

  /**
   * Loads saved tasks from the storage.
   *
   * @return Tasks saved in the storage.
   * @throws DukeException If saved tasks cannot be retrieved from storage.
   */
  public ArrayList<Task> load() throws DukeException {
    // initialize task list
    ArrayList<Task> taskList = new ArrayList<>();

    // instantiate scanner obj
    Scanner scanner;
    try {
      scanner = new Scanner(this.file);
    } catch (FileNotFoundException e) {
      throw new LoadingException();
    }

    while (scanner.hasNext()) {
      // extract task components
      String taskRaw = scanner.nextLine();
      String[] taskComponents = taskRaw.split(" \\| ");
      String taskType = taskComponents[0];
      String taskStatus = taskComponents[1];
      String taskDescription = taskComponents[2];

      // create task
      Task task;
      switch (taskType) {
        case "T":
          task = new Todo(taskDescription);
          break;
        case "D":
          String taskBy = taskComponents[3];
          task = new Deadline(taskDescription, LocalDate.parse(taskBy));
          break;
        default:
          String taskAt = taskComponents[3];
          task = new Event(taskDescription, taskAt);
          break;
      }

      // mark task as done if applicable
      if (taskStatus.equals("1")) {
        task.markAsDone();
      }

      // add task to taskList
      taskList.add(task);
    }

    // return taskList
    return taskList;
  }

  /**
   * Save tasks to storage.
   *
   * @param taskList Tasks to be saved to storage.
   * @throws DukeException If tasks cannot be saved to storage.
   */
  public void save(ArrayList<Task> taskList) throws DukeException {
    try {
      // instantiate file writer obj
      FileWriter fw = new FileWriter(this.file);

      // write to file
      taskList.forEach(task -> {
        try {
          fw.write(task.toStringData() + "\n");
        } catch (IOException e) {
          System.out.println(e);
        }
      });

      // close file writer obj
      fw.close();
    } catch (IOException e) {
      throw new SavingException();
    }
  }
}
