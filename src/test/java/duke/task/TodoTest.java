package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
     @Test
     public void toString_descriptionOfTodo1_todoStringReturned() {
          assertEquals("[T][ ] Return book",
                  new Todo("Return book").toString());
     }

     @Test
     public void toString_descriptionOfTodo2_todoStringReturned() {
          assertEquals("[T][ ] Buy milk",
                  new Todo("Buy milk").toString());
     }
}
