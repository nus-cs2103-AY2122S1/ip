# User Guide

## Features

------------------------------------------------------  

## ADD

* ### **Adds a task**

   - ### DEADLINE

        * #### `deadline XXXX by dd/mm/yyyy (#tttt)` 
    
        Adds an deadline task with description `XXXX` by date `dd/mm/yyyy` and _(tag - optional)_ `tttt`

        **Example:**
        
        `deadline submit lab report by 01/10/2021`
   
        **Expected outcome:**
        
        deadline `submit lab report` on `01/10` added

        ```
        Got it. I've added this task:
        ~~[D][𐤕]SUBMIT LAB REPORT (AT OCT 01)~~
        Now you have 3 tasks remaining.
        ```

   - ### EVENT

        * #### `event XXXX at dd/mm/yyyy (#tttt)` 

        Adds an event with description `XXXX` at date `dd/mm/yyyy` and _(tag - optional)_ `tttt`

        **Example of usage:**

        `event attend seminar at 12/12/2021 (#stonks)`

        **Expected outcome:**

        event `attend seminar` on `12/12` with tag `stonks` added

        ```
        Got it. I've added this task:
        ~~[E][𐤕]ATTEND SEMINAR (AT DEC 12) #STONKS~~
        Now you have 2 tasks remaining.
        ```
     
   - ### TODO
       
      * #### `todo XXXX (#tttt)`
      
      Adds a todo task with description `XXXX` and _(tag - optional)_ `tttt`

      **Example of usage:**

      `todo cook lunch (#yummy)`

      **Expected outcome:**

      todo task `cook lunch` with tag `yummy` added

       ```
       Got it. I've added this task:
       ~~[T][𐤕]COOK LUNCH #YUMMY~~ 
       Now you have 1 task remaining.
       ```
       
------------------------------------------------------  

## LIST

* **Lists all the task currently tracked by Duke**

#### `list`

#### Expected outcome:

list of tasks displayed

```
Here are the tasks in your list:
1.[E][√] attend seminar (at Jan 09) #stonks
2.[D][√] submit proposal (by Jan 10)
3.[T][√] go vacation #YAY
4.[T][𐤕] fix bicycle #healthy lifestyle
5.[T][√] cook lunch #yummy
6.[T][𐤕] learn how to fold origami #artsy
```

------------------------------------------------------  

## DELETE

* **Deletes a task**

#### `delete n` 

Delete task indexed `n` on the list

#### Example of usage:

`delete 3`

#### Expected outcome:

Third task  in the list - todo `go vacation` - deleted

Update number of tasks left

```
Alrightty. I've removed this task:
~~[T][√] go vacation #YAY~~
Now you have 5 tasks remaining.
```

------------------------------------------------------  

## FIND

* **Find all tasks with the _search word_**

#### `find wwww` 

find all tasks containing `wwww`

#### Example of usage:

`find fix`

#### Expected outcome:

A list of tasks containing `fix`

```
Here are the matching tasks in your list:
1.[T][𐤕] fix bicycle #healthy lifestyle
```

------------------------------------------------------  

## FILTER

* **Find all tasks matching the given _date_**

#### `filter dd/mm` 

find all tasks that falls on`dd/mm`

#### Example of usage:

`filter 10/01`

#### Expected outcome:

A list of tasks on `10/01` displayed

```
On Jan 10, you have:
1.[D][√] submit proposal (by Jan 10)
```

------------------------------------------------------  

## EXIT

* **Ends current chat session, save current tasks to storage file**

#### `bye` 

**Expected outcome:**

Farewell message displayed

```
@@ Till we meet again, my friend @@
```

____________________________________________________________

## Acknowledgements

1. Solution below adapted from past student (CS2103T-W10)

    @@author BILLXYR
    
   ```java
   dialog.setStyle("-fx-background-color: #001935; -fx-text-fill: #e6fbff; -fx-label-padding:5;"+ 
   "-fx-border-radius: 5; -fx-background-radius: 5;");
   ```
2. Solution below adapted from https://stackoverflow.com/questions/12341672/make-portion-of-a-text-bold-in-a-javafx-label-or-text

   ```javafx
   -fx-font-weight:bold;
   ```

3. Solution below adapted from https://stackoverflow.com/questions/53194987/how-do-i-insert-an-emoji-in-a-java-string

    ```java
    private static byte[] sadEmojiByteCode = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x9E};
    private static byte[] angryEmojiByteCode = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0xA0};
    private static String sadEmoji = new String(sadEmojiByteCode, Charset.forName("UTF-8"));
    private static String angryEmoji = new String(angryEmojiByteCode, Charset.forName("UTF-8"));
    byte[] tickEmojiByteCode = new byte[]{(byte) 0xE2, (byte) 0x9C, (byte) 0x94};
    String tickEmoji = new String(tickEmojiByteCode, Charset.forName("UTF-8"));
    byte[] crossEmojiByteCode = new byte[]{(byte) 0xE2, (byte) 0x9C, (byte) 0x96};
    String crossEmoji = new String(crossEmojiByteCode, Charset.forName("UTF-8"));
    ```

4. Solution below is adapted from current student

    @@author AllardQuek
    
    ```java
    File fileDirectory = new File(filePath);
                if (!fileDirectory.exists()) {
                    fileDirectory.mkdir();
                }
                file = new File(filePath + "/" + fileName);
                System.out.println(file.exists());
                if (!file.exists()) {
                    file.createNewFile();
                }
    ```
