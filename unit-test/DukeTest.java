import org.junit.Test;

import Duke.InputNotValidError;
import Duke.Parser;
import Duke.Ui;

import static org.junit.Assert.*;

public class DukeTest{

   @Test
   public void wrongCommandreturnaError(){
       // 二つの数字の平均を返すメソッドに対するテスト
       // 準備する
       Parser commandpraser = new Parser("nothing lol");
       boolean valid;
        try {
            valid = commandpraser.isValid();
        } catch (InputNotValidError e) {
            valid = false;
        }
        boolean expected = false;

        assertEquals(valid, expected);
   }

   @Test
   public void returnGreatSent(){

        Ui ui = new Ui();
        String greatsent = ui.great();
        String expected = 
        " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";

        assertEquals(greatsent, expected);
   }

}