package Duke.Command;

import Duke.Excpetions.DukeException;
import Duke.Task.TaskList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Parser {
    String Message;

    public Parser(String Message) {
        this.Message = Message;
    }

    private static boolean ValidDate(int day, int month, int year, int hour, int minute) {
        if (((year%4 == 0 && year%100 != 0) || (year % 400 == 0)) && month == 2) {
            if (day > 29 || day <= 0) {
                return false;
            }
        } else if (month == 2){
            if (day > 28 || day <= 0) {
                return false;
            }
        }

        if (month <= 0 || month > 12) {
            return false;
        }

        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            if (day > 31 || day <= 0){
                return false;
            }
        } else if (month != 2){
            if (day > 30 || day <= 0) {
                return false;
            }
        }

        if (hour > 24 || hour < 0) {
            return false;
        }

        if (minute > 60 || minute < 0) {
            return false;
        }

        return true;
    }

    /*Will only include the Time Format of: DD/MM/YY Time
     */
    public LocalDateTime ParseTime(String time) {
        LocalDateTime parsedTime = null;
        int day = 0;
        int month = 0;
        int year = 0;
        int hour = 0;
        int minute = 0;

        if (time.contains("/") && time.indexOf("/", 3) != -1 && time.contains(" ") && !time.contains("-")) {
            int endIndex1 = time.indexOf("/");
            int endIndex2 = time.lastIndexOf(" ");
            day = Integer.parseInt(time.substring(0, endIndex1));

            Integer dayInteger = day;
            int endIndex3 = time.indexOf("/", dayInteger.toString().length() + 1);
            month = Integer.parseInt(time.substring(endIndex1 + 1, endIndex3));
            year = Integer.parseInt(time.substring(endIndex3 + 1,  endIndex2));

            hour = Integer.parseInt(time.substring(endIndex2 + 1).substring(0, 2));
            minute = Integer.parseInt(time.substring(endIndex2 + 1).substring(2));
        } else if (time.contains("-")) {
            try {
                parsedTime = LocalDate.parse(time).atTime(0,0);
                return parsedTime;
            } catch (DateTimeParseException e){
                return null;
            }
        } else {
            return null;
        }

        //Some Other cases;
        if (!ValidDate(day,month,year,hour,minute)){
            return null;
        } else {
            parsedTime = LocalDate.of(year,month,day).atTime(hour, minute);
        }


        return parsedTime;
    }

    public String getSaveTask() {
        String task;
        char taskType = Message.charAt(0);
        if (taskType == 'D' || taskType == 'E') {
            task = Message.substring(8 ,Message.indexOf("|", 8) - 1);
        } else {
            task = Message.substring(8);
        }

        return task;
    }

    public String getSaveTime() {
        String time;

        char taskType = Message.charAt(0);
        if (taskType == 'D' || taskType == 'E') {
            time = Message.substring(Message.lastIndexOf("|") + 2);
        } else {
            time = "";
        }
        return time;
    }

    public String getOperationType() throws DukeException {
        String OperationType;
        if (Message.contains(" ")) {
            OperationType = Message.substring(0, Message.indexOf(" "));
        } else {
            OperationType = Message;
        }

        //If the task type does not belong to the three types, throw an error.
        TaskList.OperationType[] operationTypes = TaskList.OperationType.values();
        for (TaskList.OperationType o : operationTypes) {
            if (Message.startsWith(o.toString())) {
                return OperationType;
            }
        }
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

    }

    public String getTask() throws DukeException{
        String task = "";

        if (Message.startsWith("deadline") || Message.startsWith("event") || Message.startsWith("todo")) {
            //Get Task description and time if it has it.
            if (Message.contains("/")) {
                task = Message.substring(Message.indexOf(" ") + 1, Message.indexOf("/") - 1);
            } else {
                if (!Message.contains(" ")) {
                    throw new DukeException("☹ OOPS!!! The description of a " + Message + " cannot be empty.");
                } else {
                    task = Message.substring(Message.indexOf(" ") + 1);
                }
            }
        }

        return task;
    }

    public String getTime() throws DukeException{
        String time = "";

        //throw exceptions for deadline or events' format.
        if (Message.startsWith("todo") || Message.startsWith("deadline") || Message.startsWith("event")) {
            if (Message.contains("/")) {
                if (Message.startsWith("deadline")) {
                    if (Message.contains("/by")) {
                        time = Message.substring(Message.indexOf("/by") + 4);
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but the format of deadline is wrong :-(");
                    }
                } else if (Message.startsWith("event")) {
                    if (Message.contains("/at")) {
                        time = Message.substring(Message.indexOf("/at") + 4);
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but the format of event is wrong :-(");
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but the format of todo is wrong :-(");
                }
            }
        } else if (Message.startsWith("tell")) {
            if (!Message.contains(" ")) {
                throw new DukeException("☹ OOPS!!! I'm sorry, but the format of tell is wrong :-(");
            } else {
                time = Message.substring(Message.indexOf(" ") + 1);
            }
        }

        //Time for deadlines or event cannot be empty.
        if ((Message.startsWith("event") || Message.startsWith("deadline") || Message.startsWith("tell")) && time.equals("")) {
            throw new DukeException("☹ OOPS!!! The time of a " + Message.substring(0, Message.indexOf(" ")) +" cannot be empty.");
        }

        return time;
    }

    public Integer getIndex(){
        int index = (Message.contains(" ") && (Message.startsWith("done") || Message.startsWith("delete")))
                ? Integer.parseInt(Message.substring(Message.indexOf(" ") + 1)) - 1
                :-1;

        return index;
    }
}
