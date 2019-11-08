/**
 * Child Class of TaskList class define the type of Event tasklist
 * Event TaskList
 */

package MyClasses.tasks;

public class Events extends TaskList {
    private String eventDeadLine = "not specified";
    private String Type = "Event";

    //Constructor when the task is specified without time
    public Events(String Task) {
        super(Task);
    }

    //Constructor when the task is specified with time
    public Events(String Task, String at) {
        super(Task);
        this.eventDeadLine = at;
    }

    //Constructor when the task is specified with status and time
    public Events(String Task, String at, boolean Status) {
        super(Task, Status);
        this.eventDeadLine = at;
    }


    //Constructor when the task is specified with status but without time
    public Events(String Task, Boolean Status) {
        super(Task, Status);
    }

    @Override
    public void ListTask(int i) {
        String temp = this.taskStatus ? "\u2713" : "\u2613";
        System.out.println(i + 1 + ".[" + Type.charAt(0) + "]" + "[" + temp + "] " + this.taskList + " (at: " + eventDeadLine + ")");
    }

    @Override
    public String Save() {
        return Type.trim() + " | " + this.taskStatus + " | " + this.taskList.trim() + " | " + eventDeadLine.trim();
    }
}
