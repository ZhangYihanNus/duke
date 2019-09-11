public class DateTime {
    private int day;
    private int month;
    private int year;
    private int time;

    Months months;
//    public enum Months {
//        JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER
//    }

    public DateTime(String dayInfo, int timeInfo) throws DukeFormatException{
        //transfer string of dayInfo into day, month, and year
        String[] dayItems = dayInfo.split("/");
        int dayDay = 0, dayMonth = 0, dayYear = 0;
        try {
            dayDay = Integer.parseInt(dayItems[0]);
            dayMonth = Integer.parseInt(dayItems[1]);
            dayYear = Integer.parseInt(dayItems[2]);
        }
        catch (Exception e){
            throw new DukeFormatException("This is not a correct date format, please check your date input!");
        }
        if ((dayDay<32 && dayDay>0) && (dayMonth<13 && dayMonth>0) && (dayYear>0)) {
            if((timeInfo>=0 && timeInfo<2400)) {
                this.day = dayDay;
                this.month = dayMonth;
                this.year = dayYear;
                this.time = timeInfo;
            } else {
                throw new DukeFormatException("This is not a correct time format, please check your time input!");
            }
        } else {
            throw new DukeFormatException("This is not a correct date format, please check your date input!");
        }
        //check validity of dayInfo and timeInfo
    }

    @Override
    public String toString() {
        return day+"/"+month+"/"+year+" "+time;
    }

    public String printDateTime() {
        switch (month-1) {
            case 0: months = Months.JANUARY;
                break;
            case 1: months = Months.FEBRUARY;
                break;
            case 2: months = Months.MARCH;
                break;
            case 3: months = Months.APRIL;
                break;
            case 4: months = Months.MAY;
                break;
            case 5: months = Months.JUNE;
                break;
            case 6: months = Months.JULY;
                break;
            case 7: months = Months.AUGUST;
                break;
            case 8: months = Months.SEPTEMBER;
                break;
            case 9: months = Months.OCTOBER;
                break;
            case 10: months = Months.NOVEMBER;
                break;
            case 11: months = Months.DECEMBER;
                break;
        }
        String dayName = (day==1||day==21||day==31)?day+"st":((day==2||day==22)?day+"nd":((day==3||day==23)?day+"rd":day+"th"));
        String timeName = (time<=1159)?(time/100+":"+time%100+"am"):(time/100+":"+time%100+"pm");
        return dayName + " of " + months.toString() + ", " + year + " at " + timeName;
    }
}
