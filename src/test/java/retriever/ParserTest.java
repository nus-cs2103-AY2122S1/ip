package retriever;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import retriever.exception.IllegalCommandException;
import retriever.exception.RetrieverException;
import retriever.task.TaskListStub;

public class ParserTest {
    private Ui uiTest = new Ui();
    private StorageStub storageStub = new StorageStub("tasksListStub.txt");
    private TaskListStub taskListStub = new TaskListStub(storageStub);

    @Test
    public void parseUserInput_aStringWithViewKeyword_success() {
        Parser parserTest = new Parser(taskListStub);
        String retrieverResponse;
        try {
            parserTest.parseUserInput("view 21/09/2021");
            retrieverResponse = uiTest.getRetrieverResponse();
            assertEquals("Master, You Can Play With Me, No Scheduled Tasks For The Day, Woooof!", retrieverResponse);
        } catch (RetrieverException e) {
            fail();
        }
    }

    @Test
    public void parseUserInput_aStringWithFindKeyword_success() {
        Parser parserTest = new Parser(taskListStub);
        String retrieverResponse;
        try {
            parserTest.parseUserInput("find String");
            retrieverResponse = uiTest.getRetrieverResponse();
            assertEquals("Sorry Master, I Couldn't Smell And Find What You Asked For!\n "
                    + "(Task With the Given Keyword Does Not Exist)\n", retrieverResponse);
        } catch (RetrieverException e) {
            fail();
        }
    }

    @Test
    public void parseUserInput_aStringWithListKeyword_success() {
        Parser parserTest = new Parser(taskListStub);
        String retrieverResponse;
        try {
            parserTest.parseUserInput("list");
            retrieverResponse = uiTest.getRetrieverResponse();
            assertEquals("My Memory Is Empty, Please Feed Items!", retrieverResponse);
        } catch (RetrieverException e) {
            fail();
        }
    }

    @Test
    public void parseUserInput_aStringWithDeleteKeyword_success() {
        Parser parserTest = new Parser(taskListStub);
        String retrieverResponse;
        try {
            parserTest.parseUserInput("delete 1");
            retrieverResponse = uiTest.getRetrieverResponse();
            String expectedOutput = "Woof! Whose a Bad Boy?\n"
                    + "Task Deleted!\n"
                    + "[T][ ] Testing"
                    + "\nYou Owe Me 0 Treat(s), Master!";
            assertEquals(expectedOutput, retrieverResponse);
        } catch (RetrieverException e) {
            fail();
        }
    }

    @Test
    public void parseUserInput_aStringWithDoneKeyword_success() {
        Parser parserTest = new Parser(taskListStub);
        String retrieverResponse;
        try {
            parserTest.parseUserInput("done 1");
            retrieverResponse = uiTest.getRetrieverResponse();
            String expectedOutput = "Woof! Whose a Good Boy?\n"
                    + "Task Done!\n"
                    + "[T][X] Testing";
            assertEquals(expectedOutput, retrieverResponse);
        } catch (RetrieverException e) {
            fail();
        }
    }

    @Test
    public void parseUserInput_aStringWithDeadlineKeyword_success() {
        Parser parserTest = new Parser(taskListStub);
        String retrieverResponse;
        try {
            parserTest.parseUserInput("deadline Testing /by 12/09/2021");
            retrieverResponse = uiTest.getRetrieverResponse();
            String expectedOutput = "Where's My Treat? I Added:\n"
                    + "[D][ ] Testing (by: Sep 12 2021)"
                    + "\nYou Owe Me 1 Treat(s), Master!";
            assertEquals(expectedOutput, retrieverResponse);
        } catch (RetrieverException e) {
            fail();
        }
    }

    @Test
    public void parseUserInput_aStringWithEventKeyword_success() {
        Parser parserTest = new Parser(taskListStub);
        String retrieverResponse;
        try {
            parserTest.parseUserInput("event Testing /at 12/09/2021");
            retrieverResponse = uiTest.getRetrieverResponse();
            String expectedOutput = "Where's My Treat? I Added:\n"
                    + "[E][ ] Testing (at: Sep 12 2021)"
                    + "\nYou Owe Me 1 Treat(s), Master!";
            assertEquals(expectedOutput, retrieverResponse);
        } catch (RetrieverException e) {
            fail();
        }
    }

    @Test
    public void parseUserInput_aStringWithTodoKeyword_success() {
        Parser parserTest = new Parser(taskListStub);
        String retrieverResponse;
        try {
            parserTest.parseUserInput("todo Testing");
            retrieverResponse = uiTest.getRetrieverResponse();
            String expectedOutput = "Where's My Treat? I Added:\n"
                    + "[T][ ] Testing"
                    + "\nYou Owe Me 1 Treat(s), Master!";
            assertEquals(expectedOutput, retrieverResponse);
        } catch (RetrieverException e) {
            fail();
        }
    }

    @Test
    public void parseUserInput_aStringWithHelpKeyword_success() {
        Parser parserTest = new Parser(taskListStub);
        String retrieverResponse;
        try {
            parserTest.parseUserInput("help");
            retrieverResponse = uiTest.getRetrieverResponse();

            Ui uiActual = new Ui();
            uiActual.printHelpSection();
            String expectedOutput = uiActual.getRetrieverResponse();
            assertEquals(expectedOutput, retrieverResponse);
        } catch (RetrieverException e) {
            fail();
        }
    }

    @Test
    public void parseUserInput_aStringWithByeKeyword_success() {
        Parser parserTest = new Parser(taskListStub);
        String retrieverResponse;
        try {
            parserTest.parseUserInput("bye");
            assertTrue(parserTest.isSessionDone());
        } catch (RetrieverException e) {
            fail();
        }
    }

    @Test
    public void parseUserInput_aStringWithNonsenseKeyword_success() {
        Parser parserTest = new Parser(taskListStub);
        try {
            parserTest.parseUserInput("Nonsense");
            fail();
        } catch (IllegalCommandException e) {
            assertTrue(true);
        } catch (RetrieverException e) {
            fail();
        }
    }
}
