package IP.duke.main;

import IP.duke.task.Deadline;
import IP.duke.task.Event;
import IP.duke.task.Task;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Represents testing the TaskList class.
 */
public class TaskListTest {

    /**
     * Represents a stub for TaskList class.
     */
    public class StubTaskList {
        TaskList taskList;
        public StubTaskList() {
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
         * Retrieves the list of tasks.
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
         * Class constructor.
         */
        public DateStub() {
            date = new Date("03/09/2021");    
        }

        /**
         * Gets date object.
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
        StubTaskList stubTaskList = new StubTaskList();
        ArrayList<Task> matchingTasks = stubTaskList.getTaskListTest().findTasksMatchingDate(stubDate);
        assertEquals(matchingTasks.size(), 3);
    }

    /**
     * Tests the markDone method.
     */
    @Test
    public void markDone_markSecondTaskDone_submitReportBy30Sep_void () {
        StubTaskList stubTaskList = new StubTaskList();
        Task taskMarkedDone = stubTaskList.getTaskListTest().markDone(1);
        assertEquals(taskMarkedDone.toString(), "[D][X] submit report (by: Aug 30)");
    }

    public static void main(String[] args) {
        
    }
}
