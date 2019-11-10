/**
 * <h1>Utility Class</h1>
 * <p>
 * Class contains several utilities functions for the Main Process
 * </p>
 * <b>Methods</b>
 * <ul>
 *     <li>WelcomeMesssag</li>
 *     <li>ReadText</li>
 *     <li>ReadCommand</li>
 *     <li>isNumeric</li>
 * </ul>
 *
 * @author BearTeddy
 */

package MyClasses.ui;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Utility {

    public static String[] possibleDateFormats = {
            "dd-MM-yyyy",
            "dd/MM/yyyy",
            "yyyy-MM-dd",
            "yyyy/MM/dd",
            "dd-MMM-yyyy hh:mm a",
            "yyyy-MM-dd HH:mm",
            "yyyy/MM/dd HH:mm",
            "yyyy-MM-dd HH:mm:ss",
            "yyyy/MM/dd HH:mm:ss",
            "dd/MM/yyyy HH:mm",
            "dd/MM/yyyy HH:mm:ss",
            "dd-MM-yyyy HH:mm:ss",
            "dd-MM-yyyy HH:mm",
            "dd-MM-yyyy'T'HH:mm:ss",
            "dd-MM-yyyy'T'HH:mm",
            "yyyy-MM-dd'T'HH:mm",
            "yyyy-MM-dd'T'HH:mm:ss",
            "yyyy-MM-dd hh:mm a",
            "yyyy/MM/dd hh:mm a",
            "yyyy-MM-dd hh:mm:ss a",
            "yyyy/MM/dd hh:mm:ss a",
            "dd/MM/yyyy hh:mm a",
            "dd/MM/yyyy hh:mm:ss a",
            "dd-MM-yyyy hh:mm:ss a",
            "dd-MM-yyyy hh:mm a",
            "dd-MM-yyyy'T'hh:mm:ss a",
            "dd-MM-yyyy'T'hh:mm a",
            "yyyy-MM-dd'T'hh:mm a",
            "yyyy-MM-dd'T'hh:mm:ss a"
    } ;

    public static String ProcessTime(String TimeinString){
        try{
            Date date = DateUtils.parseDateStrictly(TimeinString,possibleDateFormats);
            return DateFormatUtils.format(date,"dd-MMM-yyyy hh:mm a");
        }catch (ParseException e){
            return "null" ;
        }
    }

    public static String DatetoString(Date date){
        return DateFormatUtils.format(date, "dd-MMM-yyyy hh:mm a");
    }

    public static void TimeFormats(){
        System.out.println("Below are the possible Time formats you can use. \n Priority format is by descending order");
        for (String s: possibleDateFormats){
            System.out.println(s);
        }
        Utility.PrintHL();
    }

    //To run at the start of the program for welcoming the user
    public static void WelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        PrintHL();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        PrintHL();
    }

    //To print the constant Line of HoriLine
    public static void PrintHL() {
        final String HoriLine = "--------------------------------------------------\n";
        System.out.println(HoriLine);
    }

    //To read the Console input text;
    public static String ReadText() {
        Scanner sc = new Scanner(System.in);
        String data = sc.nextLine();
        PrintHL();
        return data;
    }

    //To split the console input text into each command to process
    public static ArrayList<String> ReadCommand(String command) {
        ArrayList<String> commands = new java.util.ArrayList<String>(Arrays.asList(command.split(" ", 3)));
        return commands;
    }

    //Check if String numeric
    public static boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }
}
