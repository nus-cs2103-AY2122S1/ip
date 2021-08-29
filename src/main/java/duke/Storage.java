package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.status.Status;
import duke.status.typeTask;
import java.util.HashMap;
import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;

public class Storage {
    private static final String TODO_IDENTIFIER = "T";
    private static final String EVENT_IDENTIFIER = "E";
    private static final String DEADLINE_IDENTIFIER = "D";

    private static final String TODO_FULL_IDEN = typeTask.TODO.getTask();
    private static final String EVENT_FULL_IDEN = typeTask.EVENT.getTask();
    private static final String DEADLINE_FULL_IDEN = typeTask.DEADLINE.getTask();

    private static final String INDICATOR_COMPLETE = "1";
    private static final String INDICATOR_EVENT = "/at";
    private static final String INDICATOR_DEADLINE = "/by";
    // private static final String INDICATOR_INCOMPLETE = "0";

    private final File FileSource;

    public Storage(String filePath) {
        this.FileSource = new File(filePath);
    }

    protected ArrayList<Task> load() throws FileNotFoundException, DukeException {
        HashMap<String, Boolean> stringTasks = new HashMap<>();
        Scanner scanner = new Scanner(FileSource);
        while (scanner.hasNext()) {
            this.storeDiskStorageInputs(scanner.nextLine(), stringTasks);
        }
        scanner.close();
        return this.changeStorageToTasks(stringTasks);
    }

    private ArrayList<Task> changeStorageToTasks(HashMap<String, Boolean> stringTasks) throws DukeException {
        ArrayList<Task> finalOutputTasks = new ArrayList<>();
        for (Map.Entry<String, Boolean> entry : stringTasks.entrySet()) {
            String fullInstructions = entry.getKey();
            String[] instructionsArray = fullInstructions.split(" ");
            String crucialInstructions = String.join(" ", Arrays.copyOfRange(instructionsArray, 1, instructionsArray.length));
            String identity = instructionsArray[0];
            if (identity.equals(TODO_FULL_IDEN)) {
                Task ToDoTask;
                if (entry.getValue()) {
                    ToDoTask = new ToDo(crucialInstructions).updateStatus(Status.COMPLETED.getStatus());
                } else {
                    ToDoTask = new ToDo(crucialInstructions);
                }
                finalOutputTasks.add(ToDoTask);
            } else if (identity.equals(EVENT_FULL_IDEN)) {
                Task EventTask;
                if (entry.getValue()) {
                    EventTask = new Event(crucialInstructions).updateStatus(Status.COMPLETED.getStatus());
                } else {
                    EventTask = new Event(crucialInstructions);
                }
                finalOutputTasks.add(EventTask);
            } else if (identity.equals(DEADLINE_FULL_IDEN)) {
                Task DeadlineTask;
                if (entry.getValue()) {
                    DeadlineTask = new Deadline(crucialInstructions).updateStatus(Status.COMPLETED.getStatus());
                } else {
                    DeadlineTask = new Deadline(crucialInstructions);
                }
                finalOutputTasks.add(DeadlineTask);
            } else {
                String errorMsg = "No such task!";
                throw new DukeException(errorMsg);
            }
        }
        return finalOutputTasks;
    }

    public void updateStorageList(ArrayList<Task> storageTaskList) {
        try {
            FileWriter fw = new FileWriter(FileSource);
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < storageTaskList.size(); i++) {
                Task task = storageTaskList.get(i);
                String fullInstr = task.getOriginalFormatForStorage();
                String[] instr = fullInstr.split(" ");
                String indicator = instr[0];
                if (i == storageTaskList.size() - 1) {
                    this.addToStringBuilder(sb, indicator, instr, fullInstr, "");
                } else {
                    this.addToStringBuilder(sb, indicator, instr, fullInstr, System.lineSeparator());
                }
            }
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error occured while updating file storage");
        }
    }

    private void addToStringBuilder(StringBuilder sb, String indicator, String[] instr, String fullInstr,
            String extra) {
        if (indicator.contains(TODO_IDENTIFIER)) {
            String details = String.join(" ", Arrays.copyOfRange(instr, 1, instr.length));
            if (indicator.contains("X")) {
                details = TODO_IDENTIFIER + " | 1 | " + details;
            } else {
                details = TODO_IDENTIFIER + " | 0 | " + details;
            }
            sb.append(details + extra);
        } else if (indicator.contains(DEADLINE_IDENTIFIER)) {
            String details = this.getImptContentsOfDescription(fullInstr);
            if (indicator.contains("X")) {
                details = DEADLINE_IDENTIFIER + " | 1 | " + details;
            } else {
                details = DEADLINE_IDENTIFIER + " | 0 | " + details;
            }
            sb.append(details + extra);
        } else {
            String details = this.getImptContentsOfDescription(fullInstr);
            if (indicator.contains("X")) {
                details = EVENT_IDENTIFIER + " | 1 | " + details;
            } else {
                details = EVENT_IDENTIFIER + " | 0 | " + details;
            }
            sb.append(details + extra);
        }

    }

    private String getImptContentsOfDescription(String input) {
        StringBuilder sb = new StringBuilder("");
        String[] stringParts = input.split(" ");
        for (int i = 0; i < stringParts.length; i++) {
            String str = stringParts[i];
            if (str.matches("[/|(a-zA-Z0-9)|-]+") && i != stringParts.length - 1) {
                sb.append(str + " ");
            } else if (i == stringParts.length - 1) {
                sb.append(str.substring(0, str.length() - 1));
            }
        }
        return sb.toString();
    }

    private void storeDiskStorageInputs(String input, HashMap<String, Boolean> stringStorage) {
        String[] formatted = input.trim().split("[|]+");
        String[] trimFormatted = this.formatStringToProperInput(formatted).split(" ");
        boolean isDone = this.isTaskCompleted(trimFormatted);
        String[] finalFormatted = this.removeDigitIndicator(trimFormatted);
        String formattedIden = finalFormatted[0];
        String[] finalToOutuput = this.updateToCommandConverterReadableFormat(formattedIden, finalFormatted);
        String outputStorageInput = String.join(" ", finalToOutuput);
        stringStorage.put(outputStorageInput, isDone);
    }

    private String[] updateToCommandConverterReadableFormat(String identifier, String[] finalFormatted) {
        if (identifier.equals(TODO_IDENTIFIER)) {
            finalFormatted[0] = "todo";
            return finalFormatted;
        } else if (identifier.equals(EVENT_IDENTIFIER)) {
            finalFormatted[0] = "event";
            return this.addIndicator(finalFormatted, 3, INDICATOR_EVENT);
        } else {
            finalFormatted[0] = "deadline";
            return this.addIndicator(finalFormatted, 3, INDICATOR_DEADLINE);
        }
    }

    private boolean isTaskCompleted(String[] input) {
        return (input[1].equals(INDICATOR_COMPLETE)) ? true : false;
    }

    private String[] addIndicator(String[] input, int index, String indicator) {
        String[] output = new String[input.length + 1];
        for (int i = 0; i < output.length; i++) {
            if (i == index) {
                output[i] = indicator;
                continue;
            } else if (i > index) {
                output[i] = input[i - 1];
                continue;
            }
            output[i] = input[i];
        }
        return output;
    }

    private String[] removeDigitIndicator(String[] input) {
        String[] output = new String[input.length - 1];
        for (int i = 0; i < input.length; i++) {
            if (i == 1) {
                continue;
            } else if (i > 1) {
                output[i - 1] = input[i];
                continue;
            }
            output[i] = input[i];
        }
        return output;
    }

    private String formatStringToProperInput(String[] input) {
        for (int i = 0; i < input.length; i++) {
            input[i] = input[i].trim();
        }
        return String.join(" ", input);
    }
}
