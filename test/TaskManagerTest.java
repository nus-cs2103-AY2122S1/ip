import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {

    @Test
    void testDateFormat1() {
        TaskManager tm = new TaskManager();
        String date = tm.formatDate("2019-10-15");
        assertEquals("Oct 15 2019", date);
    }

//    @Test
//    void testDateFormat2() {
//        TaskManager tm = new TaskManager();
//        String date = tm.formatDate("2/12/2019 1800");
//        assertEquals("Oct 15 2019", date);
//    }
}