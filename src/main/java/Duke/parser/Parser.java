package duke.parser;

import duke.exception.InvalidInputException;
import duke.exception.InvalidInstructionException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class Parser {

    private static Boolean isDate(String str) {
        try {
            if (str.length() != 10) {
                return false;
            }
            if (str.charAt(4) != str.charAt(7)) {
                return false;
            }
            int year = Integer.parseInt(str.substring(0, 4));
            int month = Integer.parseInt(str.substring(5, 7));
            if (month < 0 || month > 12) {
                return false;
            }
            int day = Integer.parseInt(str.substring(8, 10));
            if (day < 0 || day > 31) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static Boolean isTime(String str) {
        try {
            if (str.length() != 4) {
                return false;
            }
            int time = Integer.parseInt(str);
            if (time < 0 || time > 2359) {
                return false;
            } else if (time % 100 > 59){
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Parser() {}

    public HashMap<String, Object> parse(String input)
            throws InvalidInputException, InvalidInstructionException, IllegalStateException, NoSuchElementException {

        HashMap<String, Object> data = new HashMap<>();
        Scanner scanner = new Scanner(input);
        String cmd = scanner.next();
        data.put("cmd", cmd);
        if (Objects.equals(cmd, "bye") || Objects.equals(cmd, "list")) {
            return data;
        }

        input = input.substring(input.indexOf(cmd) + cmd.length()).trim();

        switch (cmd) {
        case "done":
            try {
                int doneIndex = Integer.parseInt(input);
                data.put("index", doneIndex);
                return data;
            } catch (NumberFormatException e) {
                throw new InvalidInputException("To complete a task: enter \"done (task number)\"");
            }
        case "delete":
            try {
                int deleteIndex = Integer.parseInt(input);
                data.put("index", deleteIndex);
                return data;
            } catch (NumberFormatException e) {
                throw new InvalidInputException("To delete a task: enter \"delete (task number)\"");
            }
        case "todo":
        case "deadline":
        case "event":
            if (input.length() == 0) {
                throw new InvalidInputException(cmd + " task needs a description.");
            }

            String center = null;
            switch (cmd) {
            case "todo":
                center = "";
                break;
            case "deadline":
                center = "/by";
                break;
            case "event":
                center = "/at";
                break;
            }

            int centerIndex = input.indexOf(center);
            if (centerIndex == -1) {
                throw new InvalidInputException("To create a " + cmd + " task, " + center + " is required.\"");
            }

            String details = null;
            String aftCenter = null;
            String date = null;
            int time1 = -1;
            int time2 = -1;

            if (!cmd.equals("todo")) {

                details = input.substring(0, centerIndex).trim();
                aftCenter = input.substring(centerIndex).trim();
                if (details.length() == 0 || aftCenter.length() <= center.length()) {
                    throw new InvalidInputException(cmd + " task must have details before and after "
                            + center + ".");
                }

                aftCenter = aftCenter.substring(3).trim();

                if (cmd.equals("deadline")) {
                    if (aftCenter.length() == 15) {
                        if (isDate(aftCenter.substring(0, 10))
                                && isTime(aftCenter.substring(11))) {
                            date = aftCenter.substring(0, 4) + '-'
                                    + aftCenter.substring(5, 7) + '-'
                                    + aftCenter.substring(8, 10);
                            time1 = Integer.parseInt(aftCenter.substring(11));
                            aftCenter = null;
                        }
                    }
                } else if (cmd.equals("event")) {
                    if (aftCenter.length() == 20) {
                        if (isDate(aftCenter.substring(0, 10))
                                && isTime(aftCenter.substring(11, 15))
                                && isTime(aftCenter.substring(16))) {
                            date = aftCenter.substring(0, 4) + '-'
                                    + aftCenter.substring(5, 7) + '-'
                                    + aftCenter.substring(8, 10);
                            time1 = Integer.parseInt(aftCenter.substring(11, 15));
                            time2 = Integer.parseInt(aftCenter.substring(16));
                            aftCenter = null;
                        }
                    }
                }
            }

            switch (cmd) {
            case "todo":
                data.put("details", input);
                return data;
            case "deadline":
                data.put("details", details);
                data.put("by", aftCenter);
                data.put("date", date);
                data.put("time", time1);
                return data;
            case "event":
                data.put("details", details);
                data.put("at", aftCenter);
                data.put("date", date);
                data.put("start", time1);
                data.put("end", time2);
                return data;
            }

            break;
        case "date":
            if (input.length() != 15) {
                throw new InvalidInputException(
                        "Enter the date and time after \"date\" in this format: YYYY-MM-DD (24hr time).");
            } else {
                if (isDate(input.substring(0, 10)) && isTime(input.substring(11))) {
                    LocalDate dateSearch = LocalDate.parse(input.substring(0, 4)
                            + '-' + input.substring(5, 7) + '-'
                            + input.substring(8, 10));
                    int time = Integer.parseInt(input.substring(11));
                    data.put("date", dateSearch);
                    data.put("time", time);
                    return data;
                } else {
                    throw new InvalidInputException(
                            "Date and time format is incorrect. Correct format: YYYY-MM-DD (24hr time).");
                }
            }
        default:
            throw new InvalidInstructionException(cmd);
        }

        return data;
    }
}
