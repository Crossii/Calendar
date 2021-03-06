package model.Schedule;

import model.RegistrationException;
import model.User.User;
import model.User.Users;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Pavle and Ray on 14.05.2015.
 */
public class Schedules {
    private ArrayList<Schedule> schedules;
    private String fileAndPath = "./src/model/scheduleData.csv";
    private GregorianCalendar gc;


    public Schedules() throws Exception {
        schedules = new ArrayList<Schedule>();
        loadSchedules();
        gc = new GregorianCalendar();
    }

    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }
    public void setGregorianCalendar(int year, int month, int day) {
        gc = new GregorianCalendar(year, month, day);
    }
    public int getYear() {
        return gc.get(GregorianCalendar.YEAR);
    }
    public int getMonth() {
        return gc.get(GregorianCalendar.MONTH);
    }
    public int getDay() {
        return gc.get(GregorianCalendar.DATE);
    }


    /**
     * Shows this method, if adding a schedule was successfull
     * @param schedule
     */
    public void showConfirm(Schedule schedule) {
        System.out.println("#############################");
        System.out.println("##  Termin: " + schedule.getBeginning().toString());
        System.out.println("##  Information: " + schedule.getInformation());
        System.out.println("#############################");
    }
    /**
     * adds a schedule to the arraylist
     * @param schedule
     * @return
     * @throws Exception
     */
    public Schedule addSchedule(Schedule schedule) throws Exception {
        if(schedules.contains(schedule)) {
            throw new Exception("Schedule already exists: "+schedule.toString());
        }
        schedules.add(schedule);
        showConfirm(schedule);
        saveSchedules();
        return schedule;
    }
    /**
     * Deletes a schedule from the arraylist
     * @param schedule
     * @return
     * @throws Exception
     */
    public Schedule deleteSchedule(Schedule schedule) throws Exception {
        if (schedules.contains(schedule)) {
            schedules.remove(schedule);
            saveSchedules();
            return schedule;
        } else throw new Exception("Schedule does not exist.");

    }
    /**
     * Changes the Schedule. At first it deletes and then it adds the schedule
     * @return
     * @throws Exception
     */
    public Schedule changeSchedule(Schedule oldSchedule, Schedule newSchedule) throws Exception {
        if (schedules.contains(oldSchedule)) {
            schedules.remove(oldSchedule);
            schedules.add(newSchedule);
            saveSchedules();
            return newSchedule;
        } else throw new Exception("Schedule does not exist.");
    }
    /**
     * saves the information of the schedules in a file
     * @throws IOException
     */
    public void saveSchedules() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileAndPath));
        sortMembers();
        for(Schedule s : schedules) {
            bw.write(s.toString());
            bw.newLine();
        }
        bw.close();
    }

    /**
     * loads the schedules from the schedules file
     * @throws Exception
     */
    private void loadSchedules() throws Exception {
        BufferedReader bw = new BufferedReader(new FileReader(fileAndPath));
        String line = bw.readLine();
        String[] data = null;
        String[] beginningData = null;
        String[] endingData = null;
        while(line != null) {
            data = line.split(";");
            beginningData = data[0].split(" ");
            endingData = data[1].split(" ");

            boolean b = schedules.add(new Schedule(new Date(Long.parseLong(data[0])), new Date(Long.parseLong(data[1])), data[2], new User(data[3])));
            line = bw.readLine();
        }
        bw.close();
        sortMembers();
        saveSchedules();
    }
    private void sortMembers() {
        Collections.sort(schedules);
    }

    /**
     * gets to the next month
     * @throws Exception
     */
    public void nextMonth() throws Exception {
        if(getMonth() >= 11) setGregorianCalendar(getYear()+1, -1, getDay());
        setGregorianCalendar(getYear(), (getMonth() + 1), getDay());
    }

    /**
     * gets to the last month
     * @throws Exception
     */
    public void lastMonth() throws Exception {
        if(getMonth() <= 0) setGregorianCalendar(getYear()-1, 12, getDay());
        setGregorianCalendar(getYear(), (getMonth()-1), getDay());
    }

    /**
     * gets back to the current month
     * @throws Exception
     */
    public void setToCurrentMonth() throws Exception {
        GregorianCalendar cal = new GregorianCalendar();
        if(getMonth() == cal.get(GregorianCalendar.MONTH) && getYear() == cal.get(GregorianCalendar.YEAR)) throw new Exception("You are already in the current month of the year");
        setGregorianCalendar(cal.get(GregorianCalendar.YEAR), cal.get(GregorianCalendar.MONTH), cal.get(GregorianCalendar.DATE));
    }

    /**
     * returns the maximum day of the month
     * @return
     */
    public int getDayPerMonth() {
        return gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
    }

    /**
     * returns the whole month in a 2 dimensional array so it fits in the calendar
     * @return
     */
    public String[][] getTable(){
        int dayLines = 6;
        int day = 0;
        int monthStart = 0;
        if(getDay()>=7) {
            monthStart = 7 - ((getDay() - getActualDayOfWeek()) % 7);
        } else {
            monthStart = 7 - (((getDay()+7) - getActualDayOfWeek()) % 7);
        }
        String[][] s = new String[dayLines][7];
        for(int o = 0; dayLines > o; o++) {
            for(; 7 > monthStart && day < getDayPerMonth(); monthStart++) {
                s[o][monthStart] = ""+(day+1);
                day++;
            }
            monthStart = 0;
        }
        return s;
    }

    /**
     * returns the table head
     * @return
     */
    public String[] getTableHead() {
        String[] s = new String[7];
        s[0] = "Monday";
        s[1] = "Tuesday";
        s[2] = "Wednesday";
        s[3] = "Thrusday";
        s[4] = "Friday";
        s[5] = "Saturday";
        s[6] = "Sunday";
        return s;
    }

    /**
     * returns the current month as a string
     * @return
     */
    public String getCurrentMonth() {
        switch(this.getMonth()) {
            case 0: return "January";
            case 1: return "February";
            case 2: return "March";
            case 3: return "April";
            case 4: return "May";
            case 5: return "June";
            case 6: return "July";
            case 7: return "August";
            case 8: return "September";
            case 9: return "October";
            case 10: return "November";
            case 11: return "December";
        }
        return null;
    }

    /**
     * returns a string month as a int variable
     * @param month
     * @return
     */
    public int getMonth(String month) {
        switch(month) {
            case "January": return 0;
            case "February": return 1;
            case "March": return 2;
            case "April": return 3;
            case "May": return 4;
            case "June": return 5;
            case "July": return 6;
            case "August": return 7;
            case "September": return 8;
            case "October": return 9;
            case "November": return 10;
            case "December": return 11;
        }
        return -1;
    }

    /**
     * you get the position of the current day in the table as an array. The first index is the column the second
     * is the row.
     * @return
     */
    public int[] getCurrentDayPosition() {
        String[][] table = getTable();
        int column = 0;
        int row = 0;
        int[] select = new int[2];
        for(String[] firstArr : table) {
            for(String secArr : firstArr) {
                if(secArr != null) {
                    if (getDay() == Integer.parseInt(secArr)) {
                        select[0] = column;
                        select[1] = row;
                    }
                    column++;
                }
            }
            row++;
            column=0;
        }
        return select;
    }

    /**
     * returns the actual day in the week.
     * @return
     */
    public int getActualDayOfWeek() {
        int i = gc.get(GregorianCalendar.DAY_OF_WEEK)-1;

        if(i == 0)
            i=7;

        return i;
    }

    /**
     * returns the schedules for this month in an arraylist.
     * @param user
     * @return
     */
    public ArrayList<Schedule> getSchedulesForThisMonth(User user) {
        ArrayList<Schedule> list = new ArrayList<Schedule>();
        for(Schedule s : schedules) {
            if(s.getBeginning().getMonth() == this.getMonth() && s.getUser().equals(user)) {
                list.add(s);
            }
        }
        return list;
    }

    /**
     * it checks if the string is a number
     * @param str
     * @return
     */
    public static boolean isNumeric(String str)
    {
        try
        {
            int d = Integer.parseInt(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    /**
     * just used for testing
     */
    public static void main(String[] args){
        if(args.length == 0) {
            System.out.println("There is no file");
            System.exit(1);
        }

        Schedules termine = null;
        try {
            termine = new Schedules();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* System.out.println(termine.getMonth());
        System.out.println(termine.getDayPerMonth());
        try {
            termine.nextMonth();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(termine.getMonth());
        System.out.println(termine.getDayPerMonth());
        try {
            termine.lastMonth();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(termine.getMonth());
        System.out.println(termine.getDayPerMonth()); */





        /*Users u = new Users();
        User user = null;

        for(User n : u.getUsers()) {
            if(n.getEmail().equals("sal15532@spengergasse.at")) {
                user = n;
            }
        }

        Schedule s = null;
        try {
            s = termine.getScheduleAtDay(115, 5, 3, user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(s.toString());*/

        try {
            for(Schedule p : termine.getSchedulesForThisMonth(new User("sal15532@spengergasse.at"))) {
                p.showAppointments();
            }
        } catch (RegistrationException e) {
            e.printStackTrace();
        }

        /*GregorianCalendar gc = new GregorianCalendar();
        int i = gc.get(GregorianCalendar.MONTH);
        int u = gc.get(GregorianCalendar.YEAR);
        int o = gc.getWeeksInWeekYear();
        System.out.println(i);
        System.out.println(o);
        System.out.println(u);
        gc = new GregorianCalendar(2015, i+1, 8);
        i = gc.get(GregorianCalendar.MONTH);
        u = gc.get(GregorianCalendar.YEAR);
        o = gc.getWeekYear();
        System.out.println(i);
        System.out.println(o);
        System.out.println(u); */
    }
}