package model.Schedule;

import model.User.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by CrayZay on 14.05.2015.
 */
public class Schedules {
    private ArrayList<Schedule> schedules;
    private String fileAndPath;

    public Schedules(String fileAndPath) throws Exception {
        setFileAndPath(fileAndPath);
        schedules = new ArrayList<Schedule>();
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

    public void showConfirm(Schedule schedule) {
        System.out.println("#############################");
        System.out.println("##  Termin: "+schedule.getBeginning().toString());
        System.out.println("##  Has been added to user: "+schedule.getUser().getEmail());
        System.out.println("##  Information: "+schedule.getInformation());
        System.out.println("#############################");
    }

    public Schedule addSchedule(Schedule schedule) throws Exception {
        if(schedules.contains(schedule)) {
            throw new Exception("Schedule already exists");
        }

        schedules.add(schedule);
        showConfirm(schedule);
        return schedule;
    }

    public Schedule deleteSchedule(Schedule schedule) throws Exception {
        if (schedules.contains(schedule)) {
            schedules.remove(schedule);
            return schedule;
        } else throw new Exception("Schedule does not exist.");
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

        try {
            termine.addSchedule(new Schedule(new Date(), new User("sal15532@spengergasse.at"), "cool"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
