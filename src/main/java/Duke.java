import java.util.*;

public class Duke {

    static Scanner userInput = new Scanner(System.in);

    public Duke() {
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
                         "____________________________________________________________";
                 System.out.println(bye);
                 break;
             } else if(cmd.equals("list")){
                 System.out.println("____________________________________________________________\n\tHere are the tasks in the list:\n");
                 for(int j=0; j<i; j++) {
                     String index = new Integer(j+1).toString();
                     String thisCmd = lines[j].toString();
                     System.out.println("\t" + index + ". " + thisCmd + "\n");
                 }
                 System.out.println("____________________________________________________________\n");
             }
             /* else if(cmd.split(" ")[0].equals("done")){
                 String cmdTarget = cmd.split(" ")[1];
                 int intTarget = Integer.parseInt(cmdTarget);
                 lines[intTarget-1].markAsDone();
                 String thisCmd = lines[intTarget-1].getDescription();
                 String thisState = lines[intTarget-1].getStatusIcon();
                 String reply = "____________________________________________________________\n" +
                         "Nice! This task is done!\n" +
                         "\t["+thisState+"] "+thisCmd+"\n" +
                         "____________________________________________________________";
                 System.out.println(reply);
             }
             */else if(cmd.split(" ")[0].equals("deadline")){
                 String ddlTime = cmd.split("/by ")[1];
                 String ddlItem = cmd.split(" /by ")[0].split("deadline ")[1];
                 Deadline thisDdl = new Deadline(ddlItem, ddlTime);
                 lines[i] = thisDdl;
                 i++;
                 String thisItem = thisDdl.toString();
                 String reply = "____________________________________________________________\n\tAdded deadline into list:\n" +
                         "\t"+thisItem+"\n" +
                         "\tCurently "+i+" items in the list"+"\n" +
                         "____________________________________________________________";
                 System.out.println(reply);
             } else if(cmd.split(" ")[0].equals("todo")){
                 String todoItem = cmd.split("todo ")[1];
                 Todo thisTodo = new Todo(todoItem);
                 lines[i] = thisTodo;
                 i++;
                 String thisItem = thisTodo.toString();
                 String reply = "____________________________________________________________\n\tAdded todo into list:\n" +
                         "\t"+thisItem+"\n" +
                         "\tCurently "+i+" items in the list"+"\n" +
                         "____________________________________________________________";
                 System.out.println(reply);
             } else if(cmd.split(" ")[0].equals("event")) {
                 String evtTime = cmd.split("/at ")[1];
                 String evtItem = cmd.split(" /at ")[0].split("event ")[1];
                 Event thisEvt = new Event(evtItem, evtTime);
                 lines[i] = thisEvt;
                 i++;
                 String thisItem = thisEvt.toString();
                 String reply = "____________________________________________________________\n\tAdded event into list:\n" +
                         "\t" + thisItem + "\n" +
                         "\tCurently "+i+" items in the list"+"\n" +
                         "____________________________________________________________";
                 System.out.println(reply);
             } else {
                 String reply = "____________________________________________________________\n" +
                         "\t"+cmd+"\n" +
                         "\tCurently "+i+" items in the list"+"\n" +
                         "____________________________________________________________";
                 System.out.println(reply);
             }


        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke theDuke = new Duke();
    }

}
