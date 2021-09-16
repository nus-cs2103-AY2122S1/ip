package duke.util;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.exception.FileNotFoundException;
import duke.taskTypes.Task;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class StorageCsv implements Storage {

    private File csvFile;
    private final static String COMMA_DELIMITER = ",";
    private final static int NUMBER_CSV_COLUMN = 6;
    private final static String HEADER = "Task Type,Done,Description,Splitter,Date,Time";

    /**
     * Constructor for StorageCSV and sets the file that contains previous state
     *
     * @param filePath File location that contains text file containing previous state
     * @throws DukeException Thrown when file does not exist
     */
    public StorageCsv(String filePath) {
        File dir = new File(filePath);
        dir.mkdirs();
        File csvFile = new File(filePath + "/savedOutput.csv");
        if (!csvFile.exists()) {
            try{
                csvFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.csvFile = csvFile;
    }

    /**
     * Reads and returns a list of previous task
     *
     * @throws DukeException
     */
    public List<String> loadSaved() throws DukeException{
        List<String> pastCommand = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(csvFile);
            avoidReadingCsvHeaderLine(scanner);
            while (scanner.hasNextLine()) {
                List<String> fields = scanningIndividualRow(scanner);
                pastCommand.add(convertCsvRowToTaskDetails(fields));
            }
        } catch (IOException e) {
            throw new FileNotFoundException("Invalid FilePath");
        }
        return pastCommand;
    }

    /**
     * Avoid reading header of the csv file
     *
     * @param scanner
     */
    private void avoidReadingCsvHeaderLine(Scanner scanner) {
        if (scanner.hasNextLine()){
            scanner.nextLine();
        }
    }

    /**
     * Scans each individual row
     *
     * @param scanner
     * @return fields list that contains task details of each individual row
     */
    private List<String> scanningIndividualRow(Scanner scanner) {
        String line = scanner.nextLine();
        List<String> fields = new ArrayList<>();
        Scanner rowScanner = new Scanner(line);
        rowScanner.useDelimiter(COMMA_DELIMITER);
        while (rowScanner.hasNext()) {
            fields.add(rowScanner.next());
        }
        return fields;
    }

    /**
     * Converts csv row format to task details format
     *
     * @param rowDetails
     * @return
     */
    public String convertCsvRowToTaskDetails(List<String> rowDetails) {
        String row = "";
        for (String k : rowDetails) {
            row = row + " " + k;
        }
        return  row.trim();
    }

    /**
     * Saves newly added task into storage file
     * @param task Newly added task
     * @throws DukeException Thrown when file does not exist
     */
    public void saveAddedTask(Task task) throws DukeException {
        String msg = task.saveTaskCsv();
        try {
            FileWriter fileWriter = new FileWriter(csvFile, true);
            fileWriter.write(msg);
            fileWriter.write(System.lineSeparator());
            fileWriter.close();
        } catch (IOException e) {
            throw new FileNotFoundException("Invalid FilePath");
        }
    }

    /**
     * Updates and saves the state of the changed task
     *
     * @param taskList current state
     * @throws DukeException Thrown when file does not exist
     */
    public void saveUpdateTask(TaskList taskList) throws DukeException {
        String[] currentState = taskList.saveStateCsv();
        try {
            // Deletes past history
            FileWriter fileWriter = new FileWriter(csvFile,false);
            fileWriter.write(System.lineSeparator());

            // Updates the history with current state of taskList
            fileWriter = new FileWriter(csvFile, true);
            fileWriter.write(HEADER);
            fileWriter.write(System.lineSeparator());
            for( String msg : currentState){
                fileWriter.write(msg);
                fileWriter.write(System.lineSeparator());
            }

            fileWriter.close();
        } catch (IOException e) {
            throw new FileNotFoundException("Invalid FilePath");
        }
    }

}
