package duke.main;

import duke.main.Date;
import duke.main.DukeException;
import duke.main.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Represents testing the TaskList class.
 */
public class TaskListTest {

    /**
     * Represents a stub for TaskList class.
     */
    public class StubTaskArray {
        TaskList taskList;

        /**
         * Class constructor.
         */
        public StubTaskArray() {
            Task[] taskArray = new Task[]{new Event("event attend concert at 03/09/2021"),
                    new Deadline("deadline submit report by 30/08/2021"),
                    new Event("event attend workshop at 03/09/2021"),
                    new Deadline("deadline sign up for course by 03/09/2021")};
            ArrayList<Task> taskArrayList = new ArrayList<Task>();
            for (Task t : taskArray) {
                taskArrayList.add(t);
            }
            taskList = new TaskList(taskArrayList);
        }

        /**
         * Retrieves the taskList.
         * 
         * @return taskList.
         */
        public TaskList getTaskListTest() {
            return this.taskList;
        }
    }

    /**
     * Represents a stub for the date class.
     */
    public class DateStub {
        
        Date date;

        /** 
         * class constructor.
         */
        public DateStub() {
            date = new Date("03/09/2021");    
        }

        /**
         * gets date object.
         * 
         * @return date of this dateStub.
         */
        public Date getDate() {
            return date;
        }
    }

    /**
     * Tests the findTasksMatchingDate method.
     */
    @Test
    public void findTasksMatchingDate_03SEP_ThreeTASKS() {
        Date stubDate = new DateStub().getDate();
        StubTaskArray stubTaskArray = new StubTaskArray();
        ArrayList<Task> matchingTasks = stubTaskArray.getTaskListTest().findTasksMatchingDate(stubDate);
        assertEquals(matchingTasks.size(), 3);
    }

    /**
     * Tests the markDone method.
     */
    @Test
    public void markDone_markSecondTaskDone_submitReportBy30Sep_void () {
        StubTaskArray stubTaskArray = new StubTaskArray();
        Task taskMarkedDone = stubTaskArray.getTaskListTest().markDone(1);
        assertEquals(taskMarkedDone.toString(), "[D][X] submit report (by: Aug 30)");
    }

    public static void main(String[] args) {
        
    }
}
