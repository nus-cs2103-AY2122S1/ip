import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that loads and saves a user's file.
 */
public class UserDataManager {
    /** File containing the user's saved tasks */
    private File userData;

    public UserDataManager() {
        try {
            userData = new File("./src/main/data/USERDATA.TXT");
            userData.createNewFile();
        } catch (IOException e) {
            System.out.println("File not created");
        }
    }

    /**
     * Returns an ArrayList containing the user's saved tasks.
     * If user has no saved tasks, returns empty ArrayList.
     *
     * @return an ArrayList of tasks
     * @throws DukeException If file is not found
     */
    public ArrayList getUserDataFromFile() throws DukeException {
        ArrayList<Task> userDataList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(userData);
            while (sc.hasNextLine()) {
                String[] newTaskArray = sc.nextLine().split(" ");
                String taskType = newTaskArray[0];
                boolean isDone = newTaskArray[1].equals("1");
                Task newTask = taskType.equals("D") ? new Deadline(newTaskArray[2], newTaskArray[3])
                        : taskType.equals("E") ? new Event(newTaskArray[2], newTaskArray[3])
                        : new ToDo(newTaskArray[2]);
                if (isDone) {
                    newTask.markAsDone();
                }
                userDataList.add(newTask);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Save file is not found");
        }
        return userDataList;
    }

    /**
     * Updates the USERDATA.TXT to reflect changes that the user made.
     *
     * @param listOfUserTasks Updated ArrayList
     */
    public void updateFile(ArrayList<Task> listOfUserTasks) {
        try {
            FileWriter newFile = new FileWriter("./src/main/data/USERDATA.TXT");
            for (int i = 0; i < listOfUserTasks.size(); i++) {
                newFile.write(listOfUserTasks.get(i).toData());
            }
            newFile.close();
            if (listOfUserTasks.size() == 1) {
                System.out.println("Your task has been updated");
            } else {
                System.out.println("Your tasks has been updated");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
