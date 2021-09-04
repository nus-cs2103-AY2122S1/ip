package duke;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTaskTest {

    @Test
    public void byWhenDate_enterLongDate_storesProperly() {
        DeadlineTask dlTask = null;
        try {
            dlTask = new DeadlineTask("Homework ", "12 March 2020");
        } catch (DukeExceptionBase e) {
            System.out.println(e);
        }

        String byWhenStr = dlTask.getTaskExtraInfo();

        assertEquals("12 March 2020", byWhenStr);
    }

    @Test
    public void byWhenDate_enterLongDate2_storesProperly() {
        DeadlineTask dlTask = null;
        try {
            dlTask = new DeadlineTask("Homework ", "12 January 2020");
        } catch (DukeExceptionBase e) {
            System.out.println(e);
        }

        String byWhenStr = dlTask.getTaskExtraInfo();

        assertEquals("12 January 2020", byWhenStr);
    }

    @Test
    public void byWhenDate_enterLongDateSingleDigitDate_storesProperly() {
        DeadlineTask dlTask = null;
        try {
            dlTask = new DeadlineTask("Homework ", "5 May 1993");
        } catch (DukeExceptionBase e) {
            System.out.println(e);
        }

        String byWhenStr = dlTask.getTaskExtraInfo();

        assertEquals("05 May 1993", byWhenStr);
    }

    @Test
    public void byWhenDate_enterSlashDate1_storesProperly() {
        DeadlineTask dlTask = null;
        try {
            dlTask = new DeadlineTask("Homework ", "5/5/1993");
        } catch (DukeExceptionBase e) {
            System.out.println(e);
        }

        String byWhenStr = dlTask.getTaskExtraInfo();

        assertEquals("05 May 1993", byWhenStr);
    }

    @Test
    public void byWhenDate_enterSlashDate2_storesProperly() {
        DeadlineTask dlTask = null;
        try {
            dlTask = new DeadlineTask("Homework ", "15/1/2019");
        } catch (DukeExceptionBase e) {
            System.out.println(e);
        }

        String byWhenStr = dlTask.getTaskExtraInfo();

        assertEquals("15 January 2019", byWhenStr);
    }

    @Test
    public void testListStringHeader() {
        DeadlineTask dlTask = null;
        try {
            dlTask = new DeadlineTask("Homework ", "5/1/1956");
        } catch (DukeExceptionBase e) {
            System.out.println(e);
        }
        String headerStr = dlTask.getTaskTypeStringHeader();

        assertEquals("[D]", headerStr);
    }

}
