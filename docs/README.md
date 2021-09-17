# User Guide

Duke is a **desktop app for managing your todo list, optimised for use via a Command Line Interface** 
while still have the benefits of a Graphical User Interface (GUI). 
If you can type fast, Duke can manage your todo list faster than traditional GUI apps.

##Quick start
1. Ensure you have Java 11 or above installed in your computer 
2. Download the latest Duke.jar from [here](LINK TO JAVAFILE)
3. Copy the file to the folder you want to use as the *home folder* for Duke
4. Double-click the file to start the file. The GUI similiar to below should appear in a few seconds.
   (INSERT PHOTO HERE)
   
5. Type the command in the command box and press Enter to execute it. Eg. typing **help** and pressing enter
will display the help messasges.
   
6. Refer to [Features](#features) below for details of each command.

## Features

Notes about the command format: 
- Words in UPPER_CASE are the parameters to be supplied by the user.
  e.g. in find TASK_NAME, TASK_NAME is a parameter which can be used as find homework.

- Items in square brackets are optional.
e.g help [COMMAND] can be used as help add or as help.

- Items with …​ after them can be used multiple times including zero times.
e.g. [TAG_NAME]…​ can be used as   (i.e. 0 times), tag pain extreme_pain | 1 2 3.
  
### Viewing help:```help```
Shows help message on how to use Duke, or how to use a specific command.

Format: help [COMMAND]

Example: help add

### Add different tasks

Add different types of tasks to your todo list.

Task | Features | Format | Example
------------ | ------------- | ------------- | -------------
ToDo | Contains description of task | ```todo TASK_DESCRIPTION``` | ```todo get a life```
Deadline | Contains description and deadline of task | ```deadline TASK_DESCRIPTION /by ddMMyyyy hhmm``` | ```deadline homework /by 1/1/2021 0900```
Event | Contains description and start time of task | ```event TASK_DESCRIPTION /at ddMMyyyy hhmm``` | ```event AOT season 4 part 2 ep 1 /at 1/1/2030 0000```

### Listing all tasks: ```list```
Shows all the tasks on the todo list. 

Format: ```list```

### Mark a task as done: ```done```

Marks an existing task as done at an index.

Format: ```done TASK_INDEX```
Marks the task at ```TASK_INDEX``` as done. 

Example: ```done 1```

### Delete a task: ```delete```

Deletes an existing task at an index.

Format: ```delete TASK_INDEX```

Example: ```delete 1```

### Locating a task: ```find```
Finds tasks whose name, tag or date contains any of the given keywords. 

Format: ```find KEYWORD [MORE_KEYWORDS]```

Example: ```find birthday, friend```

### Tagging a task: ```tag```
Tag tasks at specified indexes with keywords. 

Format: ```tag TASK_INDEX1 ... | TAG_KEYWORD1 ...```

Example: ```tag 1 2 | pain hell ```

### Exiting: ```Exit```
Exits Duke.

Format:```bye```

### Saving the data
Duke data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

Editing the data file
AddressBook data are saved as a JSON file [JAR file location]/taskList.json. Advanced users are welcome to update data directly by editing that data file.

### Command Summary
Action | Format | Example
------------- | ------------- | -------------
Add todo | ```todo TASK_DESCRIPTION``` | ```todo get a life```
Add deadline | ```deadline TASK_DESCRIPTION /by ddMMyyyy hhmm``` | ```deadline homework /by 1/1/2021 0900```
Add event | ```event TASK_DESCRIPTION /at ddMMyyyy hhmm``` | ```event AOT season 4 part 2 ep 1 /at 1/1/2030 0000```
List current tasks |```list```|```list```
Mark a task as done | ```done TASK_INDEX``` | ```done 1```
Delete task | ```delete TASK_INDEX``` |```delete 1```
Find a task | ```find KEYWORD [MORE_KEYWORDS]```| ```find birthday```
Tag a task | <code>tag TASK_INDEX1 ... &#124; TAG_KEYWORD1 ...</code>| <code>tag 1 2 &#124; pain hell </code>
Exit Duke | ```bye``` | ```bye``` 


