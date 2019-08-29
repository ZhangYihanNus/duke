import java.util.*;

public class Duke {

    static Scanner userInput = new Scanner(System.in);

    public Duke() {
        String startDuke = "____________________________________________________________\n" +
                "   Hello! I'm Duke\n" +
                "   What can I do for you?\n" +
                "____________________________________________________________";

        System.out.println(startDuke);
        String[] lines = new String[100];
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
                 String listString = "";
                 for(int j=0; j<i; j++){
                     String index = new Integer(j).toString();
                     listString += ("\t"+index+". "+lines[j]+"\n");
                 }
                 String list = "____________________________________________________________\n" +
                         listString +
                         "____________________________________________________________";
                 System.out.println(list);
             } else {

                 lines[i] = cmd;
                 i++;
                 String userCmd = "____________________________________________________________\n" +
                         "\tadded: "+cmd+"\n" +
                         "____________________________________________________________";
                 System.out.println(userCmd);
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
