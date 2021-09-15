package agent.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import agent.data.TaskStorageStub;
import agent.exceptions.InvalidTaskDataException;
import agent.exceptions.TaskFileIoException;
import agent.tasks.Deadline;
import agent.tasks.TaskList;

class ReminderCommandTest {
    @Test
    void deadlineWeekFilterAndSortingWorks_userInputReminderForWeekCommand_messageIncludesWeekDeadlines()
            throws TaskFileIoException, InvalidTaskDataException {
        Deadline expectedDeadlineA = new Deadline("a", false, LocalDate.now().plusDays(6));
        Deadline expectedDeadlineB = new Deadline("b", false, LocalDate.now().plusDays(3));
        Deadline expectedDeadlineC = new Deadline("c", false, LocalDate.now().plusDays(0));
        Deadline excludedDeadlineD = new Deadline("d", true, LocalDate.now().plusDays(0));
        Deadline excludedDeadlineE = new Deadline("e", false, LocalDate.now().plusDays(20));

        TaskList taskList = new TaskList(new TaskStorageStub());
        taskList.addTask(expectedDeadlineA);
        taskList.addTask(expectedDeadlineB);
        taskList.addTask(expectedDeadlineC);
        taskList.addTask(excludedDeadlineD);
        taskList.addTask(excludedDeadlineE);
        String agentResponse = new ReminderCommand("week").execute(taskList);

        assertEquals("Here's the list of incomplete tasks sorted by deadline (Due within a week from today "
                + "or earlier): \n"
                + expectedDeadlineC.toString() + "\n"
                + expectedDeadlineB.toString() + "\n"
                + expectedDeadlineA.toString(), agentResponse);
    }

    @Test
    void deadlineAllFilterAndSortingWorks_userInputReminderAllCommand_messageIncludesAllDeadlines()
            throws TaskFileIoException, InvalidTaskDataException {
        Deadline expectedDeadlineA = new Deadline("a", false, LocalDate.now().plusDays(20));
        Deadline expectedDeadlineB = new Deadline("b", false, LocalDate.now().plusDays(3));
        Deadline expectedDeadlineC = new Deadline("c", false, LocalDate.now().plusDays(1));
        Deadline expectedDeadlineD = new Deadline("d", true, LocalDate.now().plusDays(0));

        TaskList taskList = new TaskList(new TaskStorageStub());
        taskList.addTask(expectedDeadlineA);
        taskList.addTask(expectedDeadlineB);
        taskList.addTask(expectedDeadlineC);
        taskList.addTask(expectedDeadlineD);
        String agentResponse = new ReminderCommand("gibberish text").execute(taskList);

        assertEquals("Here's the list of incomplete tasks sorted by deadline (All): \n"
                + expectedDeadlineC.toString() + "\n"
                + expectedDeadlineB.toString() + "\n"
                + expectedDeadlineA.toString(), agentResponse);
    }

    @Test
    void deadlineTodayFilterAndSortingWorks_userInputReminderTodayCommand_messageIncludesTodayDeadlines()
            throws TaskFileIoException, InvalidTaskDataException {
        Deadline expectedDeadlineA = new Deadline("a", false, LocalDate.now().plusDays(20));
        Deadline expectedDeadlineB = new Deadline("b", false, LocalDate.now().plusDays(3));
        Deadline expectedDeadlineC = new Deadline("c", false, LocalDate.now().plusDays(1));
        Deadline expectedDeadlineD = new Deadline("d", false, LocalDate.now().plusDays(0));
        Deadline expectedDeadlineE = new Deadline("e", true, LocalDate.now().plusDays(0));

        TaskList taskList = new TaskList(new TaskStorageStub());
        taskList.addTask(expectedDeadlineA);
        taskList.addTask(expectedDeadlineB);
        taskList.addTask(expectedDeadlineC);
        taskList.addTask(expectedDeadlineD);
        taskList.addTask(expectedDeadlineE);
        String agentResponse = new ReminderCommand("today").execute(taskList);

        assertEquals("Here's the list of incomplete tasks sorted by deadline (Due today or earlier): \n"
                + expectedDeadlineD.toString(), agentResponse);
    }
}
