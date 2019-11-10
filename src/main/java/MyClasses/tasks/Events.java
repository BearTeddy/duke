/**
 * Child Class of TaskList class define the type of Event tasklist
 * Event TaskList
 */

package MyClasses.tasks;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import MyClasses.ui.Utility;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

public class Events extends TaskList {
    private Date eventDeadLine = null;
    private String Type = "Event";

    //Constructor when the task is specified without time
    public Events(String Task) {
        super(Task);
    }

    //Constructor when the task is specified with time
    public Events(String Task, String at) {
        super(Task);
        try{
            this.eventDeadLine = DateUtils.parseDateStrictly(at, Utility.possibleDateFormats);
        }catch (ParseException e){
            this.eventDeadLine=  null;
        }
    }

    //Constructor when the task is specified with status and time
    public Events(String Task, String at, boolean Status) {
        super(Task, Status);
        try{
            this.eventDeadLine = DateUtils.parseDateStrictly(at, Utility.possibleDateFormats);
        }catch (ParseException e){
            this.eventDeadLine=  null;
        }
    }


    //Constructor when the task is specified with status but without time
    public Events(String Task, Boolean Status) {
        super(Task, Status);
    }

    @Override
    public void ListTask(int i) {
        String temp = this.taskStatus ? "\u2713" : "\u2613";
        String time;
        if (this.eventDeadLine!= null){
            time = DateFormatUtils.format(this.eventDeadLine,"dd-MMM-yyyy hh:mm a");
        }else{
            time ="not specified";
        }
        System.out.println(i + 1 + ".[" + Type.charAt(0) + "]" + "[" + temp + "] " + this.taskList + " (at: " + time + ")");
    }

    @Override
    public String Save() {
        if(this.eventDeadLine != null){
            return Type.trim() + " | " + this.taskStatus + " | " + this.taskList.trim() + " | " + Utility.DatetoString(this.eventDeadLine);
        }else{
            return Type.trim() + " | " + this.taskStatus + " | " + this.taskList.trim() + " | not specified";
        }
    }

    //To determine if the parameter task is the correct deadline in this event class object
    @Override
    public boolean FindTask(String searchCriteria,String type){
        if(type.contains("E")){
            String field = searchCriteria.substring(searchCriteria.indexOf("<") + 1, searchCriteria.indexOf(">"));
            if(searchCriteria.contains("on") ){
                Date date = null;
                try {
                    String datestr = searchCriteria.substring(searchCriteria.indexOf("(") + 1, searchCriteria.indexOf(")"));
                    date = DateUtils.parseDateStrictly(datestr,Utility.possibleDateFormats);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(this.eventDeadLine!=null){
                    return this.taskList.contains(field) && DateUtils.isSameDay(this.eventDeadLine, date);
                } else return false;
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
