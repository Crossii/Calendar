package model.Schedule;

import model.User.User;

import java.io.*;
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
        loadSchedules();
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
        }
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
            termine.addSchedule(new Schedule(2015, 3, 8, "cool", new User("asdw@asd.as")));
        } catch (Exception e) {
            System.out.println("Already exists");
        }

        try {
            termine.addSchedule(new Schedule(2015, 3, 8, 2015, 3, 10, "cool", new User("asdw@asd.as")));
        } catch (Exception e) {
            System.out.println("Already exists");
        }

        System.out.println(new Date().getTime());
    }
}
