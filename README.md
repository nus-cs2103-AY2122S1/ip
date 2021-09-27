# Duke.Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Duke.Duke.java` file, right-click it, and choose `Run Duke.Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```

### Acknowledgements 

1. Solution below adapted from past student (CS2103T-W10) 

//@@author BILLXYR
```
   dialog.setStyle("-fx-background-color: #001935; -fx-text-fill: 
   #e6fbff; -fx-label-padding:5;"+ " -fx-border-radius: 5; -fx-background-radius: 5;");
   ```
2. Solution below adapted from https://stackoverflow.com/questions/12341672/make-portion-of-a-text-bold-in-a-javafx-label-or-text

    `-fx-font-weight:bold;`
   
3. Solution below adapted from https://stackoverflow.com/questions/53194987/how-do-i-insert-an-emoji-in-a-java-string 
```
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

//@@authorAllardQuek
```
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