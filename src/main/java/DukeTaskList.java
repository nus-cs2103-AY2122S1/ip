import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DukeTaskList {
    public List<Task> taskList;

    public DukeTaskList() {
        taskList = new ArrayList<>(100);
    }

    public void loadDataToList(String type, String state, String body) {
        Task currTask = null;
        String[] bodySplit = body.split("\\|", 4);

        switch (type) {
            case "T":
                currTask = new ToDos(body);
                break;
            case "D":
                currTask = new Deadlines(bodySplit[0],
                    LocalDate.parse(bodySplit[1]),
                    LocalTime.parse(bodySplit[2]));
                break;
            case "E":
                currTask = new Events(bodySplit[0],
                    LocalDate.parse(bodySplit[1]),
                    LocalTime.parse(bodySplit[2]),
                    LocalTime.parse(bodySplit[3]));
                break;
        }

        assert currTask != null;
        if (state.equals("1")) {
            currTask.markAsDone();
        }
        taskList.add(currTask);
    }

    public String sendListToFile() {
        StringBuilder StrBuilder = new StringBuilder();
        for (Task task : taskList) {
            StrBuilder.append(task.toDataFileString()).append("\n");
        }
        return StrBuilder.toString();
    }

    public List<Task> sendListForPrint() {
        return taskList;
    }

    public void doneTask(int doneTaskNo) throws DukeException {
        if (doneTaskNo < 1 || doneTaskNo > taskList.size()) {
            // Task No entered out of range
            throw new DukeException("Task number entered out of range!\n");
        }
        Task doneTask = taskList.get(doneTaskNo - 1);
        doneTask.markAsDone();
        Ui.printDoneTask( doneTask.toString());
    }

    public void deleteTask(int deleteTaskNo) throws DukeException {
        if (deleteTaskNo < 1 || deleteTaskNo > taskList.size()) {
            // Task No entered out of range
            throw new DukeException("Task number entered out of range!\n");
        }
        Task deleteTask = taskList.get(deleteTaskNo - 1);
        taskList.remove(deleteTaskNo - 1);
        Ui.printDeleteTask(deleteTask.toString(), taskList.size());
    }


    public void addToDo(String toDoText){
        Task currTask = new ToDos(toDoText); // description trimmed of trailing white space behind
        taskList.add(currTask);
        Ui.printAddTask(currTask.toString(), taskList.size());
    }

    // Input format for deadline: /by yyyy-mm-dd hh:mm (ISO_LOCAL_DATE, space, ISO_LOCAL_TIME)
    public void addDeadline(String DdlText, LocalDate DdlDate, LocalTime DdlTime){
        Task currTask = new Deadlines(DdlText, DdlDate, DdlTime);
        taskList.add(currTask);
        Ui.printAddTask(currTask.toString(), taskList.size());
    }

    // Input format for event: /at yyyy-mm-dd hh:mm-hh:mm (ISO_LOCAL_DATE, space, ISO_LOCAL_TIME, dash, ISO_LOCAL_TIME)
    public void addEvent(String eventText, LocalDate eventDate, LocalTime eventStartTime, LocalTime eventEndTime){
        Task currTask = new Events(eventText, eventDate, eventStartTime, eventEndTime);
        taskList.add(currTask);
        Ui.printAddTask(currTask.toString(), taskList.size());
    }

}
