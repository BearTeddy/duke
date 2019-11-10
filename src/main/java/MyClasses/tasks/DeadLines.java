/**
 * Child Class of TaskList class define the type of Deadline tasklist
 * Deadline TaskList
 */

package MyClasses.tasks;
import MyClasses.ui.Utility;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;


public class DeadLines extends TaskList {
    private Date deadLine = null;
    private String Type = "Deadline";

    //Constructor when the task is specified without time
    public DeadLines(String Task) {
        super(Task);
    }

    //Constructor when the task is specified with time
    public DeadLines(String Task, String by) {
        super(Task);
        try{
            this.deadLine = DateUtils.parseDateStrictly(by.trim(),Utility.possibleDateFormats);
        }catch (ParseException e){
           this.deadLine=  null;
        }
    }

    //Constructor when the task is specified with time and status
    public DeadLines(String Task, String by, boolean Status) {
        super(Task, Status);
        try{
            this.deadLine = DateUtils.parseDateStrictly(by,Utility.possibleDateFormats);
        }catch (ParseException e){
            this.deadLine=  null;
        }
    }

    //Constructor when the task is specified without time but with status
    public DeadLines(String Task, boolean Status) {
        super(Task, Status);
    }


    @Override
    public void ListTask(int i) {
        String temp = this.taskStatus ? "\u2713" : "\u2613";
        String time;
        if (deadLine!= null){
            time = DateFormatUtils.format(this.deadLine,"dd-MMM-yyyy hh:mm a");
        }else{
            time ="not specified";
        }
        System.out.println(i + 1 + ".[" + Type.charAt(0) + "]" + "[" + temp + "] " + this.taskList + " (by: " + time + ")");
    }

    @Override
    public String Save() {
        if(this.deadLine != null){
            return Type.trim() + " | " + this.taskStatus + " | " + this.taskList.trim() + " | " + Utility.DatetoString(this.deadLine);
        }else{
            return Type.trim() + " | " + this.taskStatus + " | " + this.taskList.trim() + " | not specified";
        }
    }

    @Override
    public boolean FindTask(String searchCriteria, String type){
        if(type.contains("D")){
            String field = searchCriteria.substring(searchCriteria.indexOf("<") + 1, searchCriteria.indexOf(">"));
            if(searchCriteria.contains("on")){
                Date date = null;
                try {
                    date = DateUtils.parseDateStrictly(searchCriteria.substring(searchCriteria.indexOf("(") + 1, searchCriteria.indexOf(")")),Utility.possibleDateFormats);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return this.taskList.contains(field) && DateUtils.isSameDay(this.deadLine, date);
            }
            else{
                return this.taskList.contains(field);
            }
        }
        else{
            return false;
        }
    }

}
