/**
 * Child Class of TaskList class define the type of Deadline tasklist
 * Deadline TaskList
 */

package MyClasses.tasks;

public class DeadLines extends TaskList {
    private String deadLine = "not specified";
    private String Type = "Deadline";

    //Constructor when the task is specified without time
    public DeadLines(String Task) {
        super(Task);
    }

    //Constructor when the task is specified with time
    public DeadLines(String Task, String by) {
        super(Task);
        this.deadLine = by;
    }

    //Constructor when the task is specified with time and status
    public DeadLines(String Task, String by, boolean Status) {
        super(Task, Status);
        this.deadLine = by;
    }

    //Constructor when the task is specified without time but with status
    public DeadLines(String Task, boolean Status) {
        super(Task, Status);
    }


    @Override
    public void ListTask(int i) {
        String temp = this.taskStatus ? "\u2713" : "\u2613";
        System.out.println(i + 1 + ".[" + Type.charAt(0) + "]" + "[" + temp + "] " + this.taskList + " (by: " + deadLine + ")");
    }

    @Override
    public String Save() {
        return Type.trim() + " | " + this.taskStatus + " | " + this.taskList.trim() + " | " + deadLine.trim();
    }
}
