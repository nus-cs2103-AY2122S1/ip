# User Guide

## Features 

### Feature - Store history

Able to *store* previously inputs and *load* them upon next opening

### Feature - AddTask

Able to *add* new task 

### Feature - DeleteTask

Able to *delete* tasks that have been done

### Feature-prevent duplicate

Able to *recognise* and *remove duplicated task* to prevent unnecessary task present in the note.



## Usage

### `Keyword` - Todo *input action param*

####Expected outcome:

Programs adds the *input action* to the a list object. Its
recorded and available for subsequent action.

####Description of the outcome.

Message shows that the task has been record.
Format [Type][isdone] *input action*
Then shows how many existing task in the list

```
####expected output

Got it, I've added this task:
	[T][] *input action*
Now you have *1* tasks in the list.
```


