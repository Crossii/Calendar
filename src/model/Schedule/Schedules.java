package model.Schedule;

import java.util.ArrayList;

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

    public Schedule addSchedule(Schedule schedule) throws Exception {
        if(schedules.contains(schedule)) {
            throw new Exception("Schedule already exists");
        }

        schedules.add(schedule);
        return schedule;
    }

    public Schedule deleteSchedule(Schedule schedule) throws Exception {
        if(schedules.contains(schedule)) {
            schedules.remove(schedule);
            return schedule;
        }
        else throw new Exception("Schedule does not exist.");
    }
}
