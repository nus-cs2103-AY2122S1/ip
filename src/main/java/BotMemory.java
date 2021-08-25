import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//  BotMemory imitates a bot's memory unit that stores the commonly used messages
//  and tracks the tasks added
public class BotMemory {

    private BotTemporalUnit botTemporalUnit = new BotTemporalUnit();

    final String LOGO =
                    "░░░░░░░░░░░░░░░░▄▄▄███████▄▄░░░░░░░░░░░░\n" +
                    "░░░░░░░░░░░░░░▄███████████████▄░░░░░░░░░\n" +
                    "░░░░░░░░░░░░░█▀▀▀▄░░░░█████▀▀███▄░░░░░░░\n" +
                    "░░░░░░░░░░░░█░░▄░░█▄▄█░░░░▀▄▄█████░░░░░░\n" +
                    "░░░░░░░░░▄▀▀▀▄▄▀▀▀▀▀▀▄░░▀░░█▀▀▀░▀██░░░░░\n" +
                    "░░░░░░░▄▀░░░░░█░░░░░░▀▄▄▄▄█░░░░░░░▀▄░░░░\n" +
                    "░░░░░░▄▀░░░░░▄█▀▄▄▄▄░░░░░▄░░░░░░░░░█░░░░\n" +
                    "░░░░░░█░░░░░▄█▀▄▄▄▄▄▄▄▄▄▀▀░░░░░░░░░▀▄░░░\n" +
                    "░░░░░█░░░░░░▀█▄░░░░░░░░░░░░░░░░░░░░░█░░░\n" +
                    "░░░░░█░░░░░░░░▀▄░░░░░░░░░░░░░░░░░░░░█░░░\n" +
                    "░░░░░█░░░░░░░░▄█░░░░░░░░░░░░░░░░░░░░█░░░\n" +
                    "░░░░░█░░░░░░▄▀░░░░░░░░▀▄░░░░░░░░░░░░█░░░\n" +
                    "░░░░░█░░░░░░▀▄░░░▄░░░░░█░░░░░░░░░░░░█░░░\n" +
                    "░░░░░█░░░░░░░░▀▀▀▀▀█▄▄▀░░░░░░░░░░░░░█░░░\n" +
                    "░░░░░█░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▄██░░\n" +
                    "░░░░░█▄░░░░░░░░░░░░░░░░░░░░░░░░░░▄▀▀▄▀▄▄\n" +
                    "░░░░▄█▀▄░░░░░░░░░░░░░░░░░░░░░░▄▄▀░░▄▀░░░\n" +
                    "░▄░▀░░█░▀▄▄░░░░░░░░░░░░░░░▄▄▄▀░░░▄▀░░░░░\n" +
                    "▀░░░░░░▀▄░░▀▄░░░░░░░░▄▄▄▀▀░░░░▄▀▀░░░░░░░";

    final String MESSAGE_GREETING = "Hallo! My name's Peter!\n\tHow may I be of service to you?";
    final String MESSAGE_GOODBYE = "Good day! I'm gonna find Lois if you're not using me!";
    final String MESSAGE_TASK_REPORT = "Here are all your tasks! No Procrastination!";
    final String MESSAGE_TASK_COMPLETE = "Task Completed:";
    final String MESSAGE_CHEERING = "Great job! You're the best! Keep up the good work! Oho! Oho! Ohooooooo!";
    final String MESSAGE_ADD_TASK_NOTICE = "GOSH! You have one more task.";
    final String MESSAGE_ADD_TASK_SUMMARY = "Now you have %s task in your list";
    final String MESSAGE_REMOVE_TASK = "YEAH! You have removed one task from your list!";
    final String ERROR_MESSAGE_PROMPT = "ERROR: ";
    final String ERROR_MESSAGE_INVALID_COMMAND = "OPS! I am not even sure whether I can accept this command!";
    final String ERROR_MESSAGE_INVALID_COMMAND_FORMAT = "OPS! Your command format is wrong! Enter in the right format please!";
    final String ERROR_MESSAGE_EMPTY_TASKLIST = "HEY! You have no task at hand! Get your life together!";
    final String ERROR_MESSAGE_TASK_OUT_OF_RANGE = "HEY! You don't have that many tasks!";
    final String ERROR_MESSAGE_INVALID_TASK_INDEX = "HOLD ON! The index you entered is not an Integer!";
    final String ERROR_MESSAGE_INVALID_DATA_FORMAT = "HOLD ON! The data: \n\t\t%s\n\t is in the wrong format! Stop feeding me shit!";
    final String ERROR_MESSAGE_INVALID_FILE = "WAIT! There are some errors with your file!";
    final String ERROR_MESSAGE_INVALID_DATETIME_FORMAT = "I CANNOT UNDER THE TIMING FORMAT! Please input in the following format \n\t\t{yyyy-mm-ddThh:mm:ss}";
    final String HARD_DISK_DATA_NAME = "data.txt";


    //  A list to track the tasks added
    List<Task> taskTracker = new ArrayList<Task>();

    /**
     * Constructor
     */
    public BotMemory(){}

    public String produceStringData(){
        StringBuilder outputData = new StringBuilder();
        taskTracker.stream().forEach(x -> outputData.append(generateEasyDataTaskFormat(x)));
        return outputData.toString();
    }

    public String generateEasyDataTaskFormat(Task task){

        String taskType = task.getTaskType();
        switch (taskType){
            case "T": {
                return String.format(
                        "%s | %s | %s\n",
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

    public void saveToHardDisk() throws IOException {
        FileWriter fw = new FileWriter(HARD_DISK_DATA_NAME, false);
        fw.write(produceStringData());
        fw.close();
    }

    public String[] tokenizeData(String input) {
        return input.split(" \\| ");
    }

    public Task decipherStringData(String stringData) throws InvalidDataFormatException {
        String[] stringDataToken = tokenizeData(stringData);
        String stringDataType = stringDataToken[0];
        try {
            switch (stringDataType){
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
                            String.format(ERROR_MESSAGE_INVALID_DATA_FORMAT, stringData));
            }
        } catch (InvalidDataFormatException e) {
            throw new InvalidDataFormatException(
                    String.format(ERROR_MESSAGE_INVALID_DATA_FORMAT, stringData));
        }
    }

    public void loadFromHardDisk() throws IOException {
        try {
            File dataFile = new File(HARD_DISK_DATA_NAME);

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
            throw new InvalidFileException(ERROR_MESSAGE_INVALID_FILE);
        }
    }
}
