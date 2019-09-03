import java.util.*;

public class Duke {

    private static Scanner userInput = new Scanner(System.in);

    public Duke() throws DukeCmdException {
        String startDuke = "____________________________________________________________\n" +
                "   Hello! I'm Duke\n" +
                "   What can I do for you?\n" +
                "____________________________________________________________";

        System.out.println(startDuke);
        Task[] lines = new Task[100];
        int i = 0;

         while(true) {
             String cmd = userInput.nextLine();
             if(cmd.equals("bye")) {
                 String bye = "____________________________________________________________\n" +
                         "\t"+"Bye! See you next time!\n" +
                         "____________________________________________________________\n";
                 System.out.println(bye);
                 break;
             } else if(cmd.equals("list")){
                 System.out.println("____________________________________________________________\n\tHere are the tasks in the list:\n");
                 for(int j=0; j<i; j++) {
                     String index = Integer.toString(j + 1);
                     String thisCmd = lines[j].toString();
                     System.out.println("\t" + index + ". " + thisCmd + "\n");
                 }
                 System.out.println("____________________________________________________________\n");
             } else if(cmd.split(" ")[0].equals("done")) {
                 if(cmd.split(" ").length != 2) {
                     //format error
                     System.out.println("____________________________________________________________\n" +
                             "Oops, there seems to be some format error!\n" +
                             "please enter again! :)\n" +
                             "____________________________________________________________\n");
                 } else {
                     int itemDone = Integer.parseInt(cmd.split(" ")[1]);
                     lines[itemDone-1].markAsDone();
                     System.out.println("____________________________________________________________\n" +
                             "Task " + itemDone + " marked as done!\n" +
                             "____________________________________________________________\n");
                 }
             } else if(cmd.split(" ")[0].equals("deadline")){
                 String ddlItem, ddlTime;
                 if(cmd.split("/by ").length == 2) {
                     ddlTime = cmd.split("/by ")[1];
                 } else {
                     ddlTime = "";
                 }
                 String ddlFront = cmd.split(" /by ")[0];
                 if(ddlFront.split("deadline ").length == 2) {
                     ddlItem = ddlFront;
                 } else {
                     ddlItem = "";
                 }

                 Deadline thisDdl = null;
                 try {
                     thisDdl = new Deadline(ddlItem, ddlTime);
                 } catch (DukeFormatException e) {
                     System.out.println("____________________________________________________________\n" +
                             e.getMessage() +
                             "\nplease enter again! :)\n" +
                             "____________________________________________________________\n");
                     continue;
                 }
                 lines[i] = thisDdl;
                 i++;
                 String thisItem = thisDdl.toString();
                 String reply = "____________________________________________________________\n\tAdded deadline into list:\n" +
                         "\t"+thisItem+"\n" +
                         "\tCurently "+i+" items in the list"+"\n" +
                         "____________________________________________________________\n";
                 System.out.println(reply);
             } else if(cmd.split(" ")[0].equals("todo")){
                 String todoItem;
                 if(cmd.split("todo ").length == 2) {
                     todoItem = cmd;
                 } else {
                     todoItem = "";
                 }

                 Todo thisTodo = null;
                 try {
                     thisTodo = new Todo(todoItem);
                 } catch (DukeFormatException e) {
                     System.out.println("____________________________________________________________\n" +
                             e.getMessage() +
                             "\nplease enter again! :)\n" +
                             "____________________________________________________________\n");
                     continue;
                 }
                 lines[i] = thisTodo;
                 i++;
                 String thisItem = thisTodo.toString();
                 String reply = "____________________________________________________________\n\tAdded todo into list:\n" +
                         "\t"+thisItem+"\n" +
                         "\tCurently "+i+" items in the list"+"\n" +
                         "____________________________________________________________\n";
                 System.out.println(reply);
             } else if(cmd.split(" ")[0].equals("event")) {
                 String evtItem, evtTime;
                 if(cmd.split("/at ").length == 2) {
                     evtTime = cmd.split("/at ")[1];
                 } else {
                     evtTime = "";
                 }
                 String evtFront = cmd.split(" /at ")[0];
                 if(evtFront.split("event ").length == 2) {
                     evtItem = evtFront;
                 } else {
                     evtItem = "";
                 }

                 Event thisEvt = null;
                 try {
                     thisEvt = new Event(evtItem, evtTime);
                 } catch (DukeFormatException e) {
                     System.out.println("____________________________________________________________\n" +
                             e.getMessage() +
                             "\nplease enter again! :)\n" +
                             "____________________________________________________________\n");
                     continue;
                 }
                 lines[i] = thisEvt;
                 i++;
                 String thisItem = thisEvt.toString();
                 String reply = "____________________________________________________________\n\tAdded event into list:\n" +
                         "\t" + thisItem + "\n" +
                         "\tCurently "+i+" items in the list"+"\n" +
                         "____________________________________________________________\n";
                 System.out.println(reply);
             } else {
                 //generate a new task, then activate exception there(not here)
                 try {
                     Task falseTask = new Task(cmd);
                 }
                 catch (DukeFormatException | DukeCmdException e) {
                     System.out.println("____________________________________________________________\n" +
                             e.getMessage() +
                             "\nplease enter again! :)\n" +
                             "____________________________________________________________\n");
                     continue;
                 }
             }


        }
    }

    public static void main(String[] args) throws DukeCmdException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        try{
            Duke theDuke = new Duke();
        }
        catch (DukeCmdException e) {
            System.out.println(e.getMessage());
        }
    }

}
