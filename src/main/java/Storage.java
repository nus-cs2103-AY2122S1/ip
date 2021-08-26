import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

import commands.Command;
import commands.NonExecutableCommand;

public class Storage {
    private static final String TODO_IDENTIFIER = "T";
    private static final String EVENT_IDENTIFIER = "E";
    private static final String DEADLINE_IDENTIFIER = "D";
    private static final String INDICATOR_COMPLETE = "1";
    private static final String INDICATOR_EVENT = "/at";
    private static final String INDICATOR_DEADLINE = "/by";
    // private static final String INDICATOR_INCOMPLETE = "0";

    private static final File FILE_SOURCE = new File("./data/duke.txt");

    public Storage() {
    }

    protected HashMap<String, Boolean> retrieveDiskStorageReadableFormat() throws FileNotFoundException {
        HashMap<String, Boolean> outputReadableStrings = new HashMap<>();
        try {
            Scanner scanner = new Scanner(FILE_SOURCE);
            while (scanner.hasNext()) {
                this.storeDiskStorageInputs(scanner.nextLine(), outputReadableStrings);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return outputReadableStrings;
    }

    protected void updateDiskStorage(ArrayList<Command> updatesList) throws IOException {
        FileWriter fw = new FileWriter(FILE_SOURCE);
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < updatesList.size(); i++) {
            NonExecutableCommand com = (NonExecutableCommand) updatesList.get(i);
            String fullInstr = com.getOriginalFormatForStorage();
            String[] instr = fullInstr.split(" ");
            String indicator = instr[0];
            if (i == updatesList.size() - 1) {
                this.addToStringBuilder(sb, indicator, instr, fullInstr, "");
            } else {
                this.addToStringBuilder(sb, indicator, instr, fullInstr, System.lineSeparator());
            }
        }
        try {
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error occured while updating file storage");
        }
    }

    private void addToStringBuilder(StringBuilder sb, String indicator, String[] instr, String fullInstr, String extra) {
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
