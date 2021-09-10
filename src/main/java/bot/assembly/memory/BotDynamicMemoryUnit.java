package bot.assembly.memory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bot.assembly.error.InvalidDataFormatException;
import bot.assembly.error.InvalidFileException;
import bot.assembly.function.BotTemporalUnit;
import bot.assembly.task.Deadline;
import bot.assembly.task.Event;
import bot.assembly.task.Task;
import bot.assembly.task.ToDo;

/**
 * A class that handles the task list
 */
public class BotDynamicMemoryUnit {

    private static BotDynamicMemoryUnit dynamicMemoryUnit = null;
    private List<Task> taskTracker = new ArrayList<Task>();
    private BotStaticMemoryUnit botStaticMemoryUnit = new BotStaticMemoryUnit();
    private BotTemporalUnit botTemporalUnit = new BotTemporalUnit();

    private final String HARD_DISK_DATA_NAME = "data.txt";
    private final String HARD_DISK_DATA_STORAGE_DIRECTORY = System.getProperty("user.home");

    /**
     * Constructor
     */
    public BotDynamicMemoryUnit() {}

    /**
     * A method to help parse the data from data.txt
     * @param input
     * @return String[] that contains the essential information of task
     */
    private String[] tokenizeData(String input) {
        return input.split(" \\| ");
    }

    /**
     * A method to get task tracker
     * @return List of tasks
     */
    public List<Task> getTaskTacker() {
        return this.taskTracker;
    }

    /**
     * A method that tokenizes the command input to 2 parts:
     * 1. Command Type
     * 2. Rest of the command
     * @param input command input
     * @return [Command Type, Rest of the command]
     */
    private String[] tokenize(String input) {

        String[] token = input.split(" ", 2);

        return token;
    }

    /**
     * A method that users generateEasyDataTaskFormat() method and convert the data
     * in task list into String
     * @return String single line data string
     */
    private String produceStringData() {
        StringBuilder outputData = new StringBuilder();

        taskTracker.stream().forEach(x -> outputData.append(generateEasyDataTaskFormat(x)));

        return outputData.toString();
    }

    /**
     A method that ensures that only 1 BotDynamicMemoryUnit exists (Singleton)
     */
    public static BotDynamicMemoryUnit getInstance() {

        // create new BotDynamicMemoryUnit if it doesn't exit
        if (dynamicMemoryUnit == null) {
            dynamicMemoryUnit = new BotDynamicMemoryUnit();
        }
        return dynamicMemoryUnit;
    }

    /**
     * A method that helps to format task in task list into the following format:
     * Task Type | isDone | Task Title | DateTime for Event & Deadline Task
     * @param task
     * @return String single line data string
     */
    private String generateEasyDataTaskFormat(Task task) {

        String taskType = task.getTaskType();

        switch (taskType) {
        case "T": {
            return String.format("%s | %s | %s\n",
                    task.getTaskType(),
                    task.getIsDone(),
                    task.getTaskTitle()
            );
        }

        default: {
            return String.format(
                    "%s | %s | %s | %s\n",
                    task.getTaskType(),
                    task.getIsDone(),
                    task.getTaskTitle(),
                    task.getDateTime()
            );
        }
        }
    }

    /**
     * A method that saves String data generated produceStringData() method
     * and save to the HARD_DISK_DATA_STORAGE_DIRECTORY as HARD_DISK_DATA_NAME
     * @throws IOException if there is IO issue
     */
    public void saveToHardDisk() throws IOException {

        FileWriter fw = new FileWriter(
                String.format(
                        "%s/%s",
                        HARD_DISK_DATA_STORAGE_DIRECTORY,
                        HARD_DISK_DATA_NAME
                ),
                false
        );

        fw.write(produceStringData());
        fw.close();
    }

    /**
     * A method that deciphers string data and create the respective task
     * @param stringData
     * @return Task
     * @throws InvalidDataFormatException if data string is in the wrong format
     */
    private Task decipherStringData(String stringData) throws InvalidDataFormatException {

        String[] stringDataToken = tokenizeData(stringData);
        String stringDataType = stringDataToken[0];

        try {
            switch (stringDataType) {
            case "T":
                return new ToDo(
                        stringDataToken[1].equals("true") ? true : false,
                        stringDataToken[2]);
            case "D":
                return new Deadline(
                        stringDataToken[1].equals("true") ? true : false,
                        stringDataToken[2],
                        botTemporalUnit.convertStringToTemporalData(stringDataToken[3]));
            case "E":
                return new Event(
                        stringDataToken[1].equals("true") ? true : false,
                        stringDataToken[2],
                        botTemporalUnit.convertStringToTemporalData(stringDataToken[3]));
            default:
                throw new InvalidDataFormatException(
                        String.format(botStaticMemoryUnit.ERROR_MESSAGE_INVALID_DATA_FORMAT, stringData));
            }
        } catch (InvalidDataFormatException e) {
            throw new InvalidDataFormatException(
                    String.format(botStaticMemoryUnit.ERROR_MESSAGE_INVALID_DATA_FORMAT, stringData));
        }
    }

    /**
     * A method to load the data.txt from the user's directory
     * @throws IOException if there is IO issue
     */
    public void loadFromHardDisk() throws IOException {
        try {
            File dataFile = new File(
                    String.format(
                            "%s/%s",
                            HARD_DISK_DATA_STORAGE_DIRECTORY,
                            HARD_DISK_DATA_NAME
                    )
            );

            // If data.txt cannot be found in the specified directory, then create a new data.txt in that directory
            if (dataFile.createNewFile()) {
                return;
            }

            Scanner myReader = new Scanner(dataFile);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                taskTracker.add(decipherStringData(data));
            }
            myReader.close();

        } catch (IOException e) {
            throw new InvalidFileException(botStaticMemoryUnit.ERROR_MESSAGE_INVALID_FILE);
        }
    }
}
