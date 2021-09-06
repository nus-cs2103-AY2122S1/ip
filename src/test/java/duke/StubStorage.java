package duke;

import java.time.LocalDate;
import java.util.ArrayList;

public class StubStorage extends Storage {
    private ArrayList<Task> userInputRecords;

    public StubStorage() {
        this.userInputRecords = new ArrayList<>();
    }

    public void setExpectedResults() {
        ToDo todoTestcase = new ToDo("sleep");
        Deadline deadlineTestcase = new Deadline("assignment ", LocalDate.of(2021, 8, 9));
        Event eventTestcase = new Event("meeting ", LocalDate.of(2021, 8, 1));
        todoTestcase.setDone(true);
        deadlineTestcase.setDone(true);
        eventTestcase.setDone(true);
        userInputRecords.add(todoTestcase);
        userInputRecords.add(deadlineTestcase);
        userInputRecords.add(eventTestcase);
    }

    @Override
    public ArrayList<Task> getUserInputRecords() {
        return this.userInputRecords;
    }
}
