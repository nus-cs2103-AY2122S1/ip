import org.junit.Test;

import Duke.InputNotValidError;
import Duke.Parser;

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
}