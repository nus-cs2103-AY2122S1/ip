package retriever;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import retriever.task.Deadline;
import retriever.task.Event;
import retriever.task.Task;
import retriever.task.TaskDateAndTime;
import retriever.task.Todo;

public class UiTest {
    @Test
    public void printTaskList_anEmptyArrayList_success() {
        Ui uiTest = new Ui();
        uiTest.printTaskList(new ArrayList<Task>());
        String retrieverResponse = uiTest.getRetrieverResponse();

        assertEquals("My Memory Is Empty, Please Feed Items!", retrieverResponse);
    }

    @Test
    public void printTaskList_anArrayListWithItems_success() {
        Ui uiTest = new Ui();
        ArrayList<Task> sampleTasks = new ArrayList<Task>();
        sampleTasks.add(new Todo("Testing"));
        sampleTasks.add(new Deadline("Testing", new TaskDateAndTime("12/09/2021")));
        uiTest.printTaskList(sampleTasks);
        String retrieverResponse = uiTest.getRetrieverResponse();
        String expectedOutput = "-> Your Tasks, My Master:\n" + "1. [T][ ] Testing\n"
                + "2. [D][ ] Testing (by: Sep 12 2021)\n";
        assertEquals(expectedOutput, retrieverResponse);
    }

    @Test
    public void printTasksScheduledForTheDay_anEmptyArrayList_success() {
        Ui uiTest = new Ui();
        uiTest.printTasksScheduledForTheDay(new ArrayList<Task>());
        String retrieverResponse = uiTest.getRetrieverResponse();

        assertEquals("Master, You Can Play With Me, No Scheduled Tasks For The Day, Woooof!", retrieverResponse);
    }

    @Test
    public void printTasksScheduledForTheDay_anArrayListWithItems_success() {
        Ui uiTest = new Ui();
        ArrayList<Task> sampleTasks = new ArrayList<Task>();
        sampleTasks.add(new Todo("Testing"));
        sampleTasks.add(new Deadline("Testing", new TaskDateAndTime("12/09/2021")));
        uiTest.printTasksScheduledForTheDay(sampleTasks);
        String retrieverResponse = uiTest.getRetrieverResponse();
        String expectedOutput = "Master, You are Busy For The Day...\n" + "1. [T][ ] Testing\n"
                + "2. [D][ ] Testing (by: Sep 12 2021)\n";
        assertEquals(expectedOutput, retrieverResponse);
    }

    @Test
    public void printTasksFoundByKeyword_anEmptyArrayList_success() {
        Ui uiTest = new Ui();
        uiTest.printTasksFoundByKeyword(new ArrayList<Task>());
        String retrieverResponse = uiTest.getRetrieverResponse();

        assertEquals("Sorry Master, I Couldn't Smell And Find What You Asked For!"
                + "\n (Task With the Given Keyword Does Not Exist)\n", retrieverResponse);
    }

    @Test
    public void printTasksFoundByKeyword_anArrayListWithItems_success() {
        Ui uiTest = new Ui();
        ArrayList<Task> sampleTasks = new ArrayList<Task>();
        sampleTasks.add(new Todo("Testing"));
        sampleTasks.add(new Deadline("Testing", new TaskDateAndTime("12/09/2021")));
        uiTest.printTasksFoundByKeyword(sampleTasks);
        String retrieverResponse = uiTest.getRetrieverResponse();
        String expectedOutput = "Woof! Look What I Found: \n" + "1. [T][ ] Testing\n"
                + "2. [D][ ] Testing (by: Sep 12 2021)\n" + "You Owe Me " + sampleTasks.size() + " Treat(s), Master!";
        assertEquals(expectedOutput, retrieverResponse);
    }

    @Test
    public void printMessage_aMessage_success() {
        Ui uiTest = new Ui();
        uiTest.printMessage("Test Message");
        String retrieverResponse = uiTest.getRetrieverResponse();

        assertEquals("Test Message", retrieverResponse);
    }

    @Test
    public void printErrorMessage_aMessage_success() {
        Ui uiTest = new Ui();
        uiTest.printErrorMessage("Test Error Message");
        String retrieverResponse = uiTest.getRetrieverResponse();

        assertEquals("Test Error Message", retrieverResponse);
    }

    @Test
    public void printTaskAdded_aTodoTask_success() {
        Ui uiTest = new Ui();
        Todo todoSampleTask = new Todo("Testing");
        uiTest.printTaskAdded(todoSampleTask, 1);
        String retrieverResponse = uiTest.getRetrieverResponse();
        String expectedOutput = "Where's My Treat? I Added:\n"
                + "[T][ ] Testing" + "\nYou Owe Me 1 Treat(s), Master!";
        assertEquals(expectedOutput, retrieverResponse);
    }

    @Test
    public void printTaskAdded_aDeadlineTask_success() {
        Ui uiTest = new Ui();
        Deadline deadlineSampleTask = new Deadline("Testing", new TaskDateAndTime("12/09/2021"));
        uiTest.printTaskAdded(deadlineSampleTask, 1);
        String retrieverResponse = uiTest.getRetrieverResponse();
        String expectedOutput = "Where's My Treat? I Added:\n"
                + "[D][ ] Testing (by: Sep 12 2021)" + "\nYou Owe Me 1 Treat(s), Master!";
        assertEquals(expectedOutput, retrieverResponse);
    }

    @Test
    public void printTaskAdded_anEventTask_success() {
        Ui uiTest = new Ui();
        Event eventSampleTask = new Event("Testing", new TaskDateAndTime("12/09/2021"));
        uiTest.printTaskAdded(eventSampleTask, 1);
        String retrieverResponse = uiTest.getRetrieverResponse();
        String expectedOutput = "Where's My Treat? I Added:\n"
                + "[E][ ] Testing (at: Sep 12 2021)" + "\nYou Owe Me 1 Treat(s), Master!";
        assertEquals(expectedOutput, retrieverResponse);
    }

    @Test
    public void printTaskDeleted_aTodoTask_success() {
        Ui uiTest = new Ui();
        Todo todoSampleTask = new Todo("Testing");
        todoSampleTask.markAsDone();
        uiTest.printTaskDeleted(todoSampleTask, 1);
        String retrieverResponse = uiTest.getRetrieverResponse();
        String expectedOutput = "Woof! Whose a Bad Boy?\n"
                + "Task Deleted!\n"
                + "[T][X] Testing"
                + "\nYou Owe Me 1 Treat(s), Master!";
        assertEquals(expectedOutput, retrieverResponse);
    }

    @Test
    public void printTaskDeleted_aDeadlineTask_success() {
        Ui uiTest = new Ui();
        Deadline deadlineSampleTask = new Deadline("Testing", new TaskDateAndTime("12/09/2021"));
        deadlineSampleTask.markAsDone();
        uiTest.printTaskDeleted(deadlineSampleTask, 1);
        String retrieverResponse = uiTest.getRetrieverResponse();
        String expectedOutput = "Woof! Whose a Bad Boy?\n"
                + "Task Deleted!\n"
                + "[D][X] Testing (by: Sep 12 2021)"
                + "\nYou Owe Me 1 Treat(s), Master!";
        assertEquals(expectedOutput, retrieverResponse);
    }

    @Test
    public void printTaskDeleted_anEventTask_success() {
        Ui uiTest = new Ui();
        Event eventSampleTask = new Event("Testing", new TaskDateAndTime("12/09/2021"));
        eventSampleTask.markAsDone();
        uiTest.printTaskDeleted(eventSampleTask, 1);
        String retrieverResponse = uiTest.getRetrieverResponse();
        String expectedOutput = "Woof! Whose a Bad Boy?\n"
                + "Task Deleted!\n"
                + "[E][X] Testing (at: Sep 12 2021)"
                + "\nYou Owe Me 1 Treat(s), Master!";
        assertEquals(expectedOutput, retrieverResponse);
    }

    @Test
    public void printTaskMarkedAsDone_aTodoTask_success() {
        Ui uiTest = new Ui();
        Todo todoSampleTask = new Todo("Testing");
        todoSampleTask.markAsDone();
        uiTest.printTaskMarkedAsDone(todoSampleTask);
        String retrieverResponse = uiTest.getRetrieverResponse();
        String expectedOutput = "Woof! Whose a Good Boy?\n" + "Task Done!\n" + "[T][X] Testing";
        assertEquals(expectedOutput, retrieverResponse);
    }

    @Test
    public void printTaskMarkedAsDone_aDeadlineTask_success() {
        Ui uiTest = new Ui();
        Deadline deadlineSampleTask = new Deadline("Testing", new TaskDateAndTime("12/09/2021"));
        deadlineSampleTask.markAsDone();
        uiTest.printTaskMarkedAsDone(deadlineSampleTask);
        String retrieverResponse = uiTest.getRetrieverResponse();
        String expectedOutput = "Woof! Whose a Good Boy?\n" + "Task Done!\n"
                + "[D][X] Testing (by: Sep 12 2021)";
        assertEquals(expectedOutput, retrieverResponse);
    }

    @Test
    public void printTaskMarkedAsDone_anEventTask_success() {
        Ui uiTest = new Ui();
        Event eventSampleTask = new Event("Testing", new TaskDateAndTime("12/09/2021"));
        eventSampleTask.markAsDone();
        uiTest.printTaskMarkedAsDone(eventSampleTask);
        String retrieverResponse = uiTest.getRetrieverResponse();
        String expectedOutput = "Woof! Whose a Good Boy?\n" + "Task Done!\n"
                + "[E][X] Testing (at: Sep 12 2021)";
        assertEquals(expectedOutput, retrieverResponse);
    }
}
