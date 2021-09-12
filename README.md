# DukePro

> Made with a project template for a greenfield Java project. It's named after the Java mascot _Duke_. 

### What can DukePro do?

DukePro is your companion to free your mind of having to remember things you need to do! It's:
- Text-Based
- Easy to learn and use
- **_VERY_** efficient and handy!


### How to set up DukePro?
Follow these steps to get your own DukePro:
1. Download it from [here](https://github.com/janjanchen/ip). 
2. Run it. 
3. Add your tasks. 
4. ~~Manage~~ **Let _DukePro_ manage** your tasks for you! :wink:


**Features**

- [X] Manages Todo Lists
- [X] Manages Deadlines
- [X] Manages Events
- [X] Save, Delete, Filters and many more!
- [ ] More features to be added

## Code SneakPeaks
This is the code in DukePro that can be found in its `Task` class:
```java
/**
 * A Parent class for the different types of input to the Task List.
 */
public class Task {
   protected String description;
   protected boolean isDone;

   /**
    * A public constructor to create a task.
    * @param description The description of the task.
    */
   public Task(String description) {
      this.description = description;
      this.isDone = false;
   }

   /**
    * Returns the string representation of the task's status.
    * @return the string representation of the task's status.
    */
   public String getStatusIcon() {
      return (isDone ? "[X] " : "[ ] "); // mark done task with X
   }

   /**
    * Marks the task as completed.
    */
   public void markAsDone() {
      this.isDone = true;
   }

   /**
    * Returns the string representation of the task.
    * @return the string representation of the task.
    */
   @Override
   public String toString() {
      return this.getStatusIcon() + this.description;
   }

   /**
    * Returns the string representation of the task
    * to be saved into the file.
    * @return the string representation of the task
    * to be saved into the file.
    */
   public String toStore() {
      return this.getStatusIcon() + this.description;
   }
}
```
