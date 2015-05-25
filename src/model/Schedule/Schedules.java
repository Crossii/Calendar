package model.Schedule;

import model.User.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by CrayZay on 14.05.2015.
 */
public class Schedules {
    private ArrayList<Schedule> schedules;
    private String fileAndPath;
    private GregorianCalendar gc;


    public Schedules(String fileAndPath) throws Exception {
        setFileAndPath(fileAndPath);
        schedules = new ArrayList<Schedule>();
        loadSchedules();
        gc = new GregorianCalendar();
    }

    public String getFileAndPath() {
        return fileAndPath;
    }
    public void setFileAndPath(String fileAndPath) throws Exception {
        if(fileAndPath == null) throw new Exception("The File and Path is not allowed to be null!");
        this.fileAndPath = fileAndPath;
    }
    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }
    public void setSchedules(ArrayList<Schedule> schedules) {
        this.schedules = schedules;
    }
    public GregorianCalendar getGc() {
        return gc;
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
     * saves the information of the schedules in a file
     * @throws IOException
     */
    public void saveSchedules() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileAndPath));
        for(Schedule s : schedules) {
            bw.write(s.toString());
            bw.newLine();
        }
        bw.close();
    }

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
    }

    public void showSchedules() {
        for(Schedule s : schedules) {
            s.showAppointments();
            System.out.println("Month: "+getMonth());
        }
    }

    public void nextMonth() throws Exception {
        if(getMonth() >= 11) throw new Exception("You are already in the last month");
        setGregorianCalendar(getYear(), (getMonth()+1), getDay());
    }
    public void lastMonth() throws Exception {
        if(getMonth() <= 0) throw new Exception("You are already in the first month");
        setGregorianCalendar(getYear(), (getMonth()-1), getDay());
    }
    public void setToCurrentMonth() throws Exception {
        GregorianCalendar cal = new GregorianCalendar();
        if(getMonth() == cal.get(GregorianCalendar.MONTH)) throw new Exception("You are already in the current month");
        setGregorianCalendar(cal.get(GregorianCalendar.YEAR), cal.get(GregorianCalendar.MONTH), cal.get(GregorianCalendar.DATE));
    }
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
        int monthStart = 7-((getDay()-getActualDayOfWeek())%7);
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
    public int getCurrentColumnDay() {
        return getActualDayOfWeek()-1;
    }
    public int getCurrentRowDay() {
        int i = (getDay())/7;
        return i+1;
    }
    public int getActualDayOfWeek() {
        int i = gc.get(GregorianCalendar.DAY_OF_WEEK)-1;

        if(i == 0)
            i=7;

        return i;
    }

    public static void main(String[] args){
        if(args.length == 0) {
            System.out.println("There is no file");
            System.exit(1);
        }

        Schedules termine = null;
        try {
            termine = new Schedules(args[0]);
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

        System.out.println("Row: "+termine.getCurrentRowDay());
        System.out.println("Actual day: "+termine.getActualDayOfWeek());
        termine.setGregorianCalendar(2015, 4, 31);
        System.out.println("Actual day: " + termine.getActualDayOfWeek());
        termine.setGregorianCalendar(2015, 4, 30);
        System.out.println("Actual day: " + termine.getActualDayOfWeek());
        termine.setGregorianCalendar(2015, 4, 29);
        System.out.println("Actual day: " + termine.getActualDayOfWeek());
        termine.setGregorianCalendar(2015, 4, 28);
        System.out.println("Actual day: " + termine.getActualDayOfWeek());
        termine.setGregorianCalendar(2015, 4, 27);
        System.out.println("Actual day: " + termine.getActualDayOfWeek());
        termine.setGregorianCalendar(2015, 4, 26);
        System.out.println("Actual day: " + termine.getActualDayOfWeek());
        termine.setGregorianCalendar(2015, 4, 25);
        System.out.println("Actual day: " + termine.getActualDayOfWeek());
        System.out.println("Get current day: "+ termine.getDay());

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