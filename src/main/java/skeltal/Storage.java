package skeltal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.util.Pair;
import skeltal.task.Task;
import skeltal.task.TaskList;
import skeltal.task.expense.Expense;
import skeltal.task.expense.ExpenseList;
import skeltal.task.types.Deadline;
import skeltal.task.types.Event;
import skeltal.task.types.ToDo;

/**
 * A class to handle the loading and storage of the list of tasks.
 */
public class Storage {
    final public static String SKELTAL_PATH = "src/main/Data/skeltal.txt";
    final public static String EXPENSE_PATH = "src/main/Data/expense.txt";

    public static SkeltalFunction<String, Task> wrappedStringToTask = new SkeltalFunction<String, Task>() {
        @Override
        public Task apply(String data) throws SkeltalException {
            return stringToTask(data);
        }
    };

    public static SkeltalFunction<String, Expense> wrappedStringToExpense = new SkeltalFunction<String, Expense>() {
        @Override
        public Expense apply(String data) throws SkeltalException {
            return stringToExpense(data);
        }
    };
    /**
     * Loads the txt file of the list of tasks from the specified path.
     *
     * @param <T>        The input type T of the SkeltalFunction.
     * @param path       The path of the file to load from.
     * @param loadFormat The wrapped function to use to convert a String
     *                   to a type T object.
     * @return A Pair<ArrayList<T>, String> of Tasks representative of the
     * ArrayList and Skeltal's response to the user.
     */
    public static <T> Pair<ArrayList<T>, String> loadFile(String path, SkeltalFunction<String, T> loadFormat) {
        ArrayList<T> arrayList = new ArrayList<>();
        String reply = "";
        try {
            File taskFile = new File(path);
            Scanner sc = new Scanner(taskFile);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                T task = loadFormat.apply(data);
                arrayList.add(task);
            }
        } catch (FileNotFoundException e) {
            reply = "No existing tasks can be found! Creating a new list for you :)";
            File taskFile = new File(path);
            try {
                taskFile.mkdir();
                taskFile.createNewFile();
            } catch (IOException ioException) {
                reply = "List could not be created :(";
            }
        } catch (SkeltalException e) {
            reply = e.getMessage();
        } finally {
            reply = "Saved tasks have been loaded into the skeltal system!";
            return new Pair<>(arrayList, reply);
        }
    }

    /**
     * Writes the current list of tasks to the txt file at the specified filepath.
     *
     * @param path      The filepath to write to.
     * @param classType A string to decide on which function to use.
     * @throws SkeltalException If the file does not exist.
     */
    public static String write(String path, String classType) throws SkeltalException {
        try {
            String reply = "";
            FileWriter fw = new FileWriter(path, false);
            if (classType.equals("task")) {
                fw.write(TaskList.tasksToFileFormat());
            } else if (classType.equals("expense")) {
                fw.write(ExpenseList.expensesToFileFormat());
            }
            reply += "wrote to skeltal.txt";
            fw.close();
            return reply;
        } catch (Exception e) {
            throw new SkeltalException(e.getMessage());
        }
    }

    private static void setDone(String done, Task task) {
        int doneInt = Integer.parseInt(done);
        if (doneInt == 1) {
            task.setComplete();
        }
    }

    private static Task stringToTask(String data) throws SkeltalException {
        String[] dataArr = data.split(" \\| ");
        String taskType = dataArr[0];
        String done = dataArr[1];
        String description = taskType.equals("T") ? dataArr[2]
                : dataArr[2] + "/" + dataArr[3];

        Task task = new Task("");

        switch (taskType) {
        case "T":
            ToDo todo = ToDo.of(description);
            setDone(done, todo);
            task = todo;
            break;
        case "E":
            Event event = Event.of(description);
            setDone(done, event);
            task = event;
            break;
        case "D":
            Deadline dead = Deadline.of(description);
            setDone(done, dead);
            task = dead;
            break;
        default:
            //Do nothing
        }
        return task;
    }

    private static Expense stringToExpense(String data) throws SkeltalException {
        String[] dataArr = data.split(" \\| ");
        String formattedData = dataArr[1] + " $" + dataArr[0];
        return Expense.of(formattedData);
    }



}
