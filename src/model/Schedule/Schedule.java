package model.Schedule;

import model.RegistrationException;
import model.User.User;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by CrayZay on 14.05.2015.
 */
public class Schedule implements Comparable{
    private Date beginning;
    private Date ending;
    private String information;
    private User user;

    /**
     * Constructor with int values, if you don't have a long variable
     * @param year
     * @param month
     * @param day
     * @param information
     * @param user
     * @throws Exception
     */
    public Schedule(int year, int month, int day, String information, User user) throws Exception {
        setBeginning(setToDate(year, month, day));
        setEnding(setToDate(year, month, day));
        setInformation(information);
        setUser(user);
    }

    /**
     * Constructor with int values. It also has an ending parameter, which shows when the appointment
     * ends.
     * @param yearBeginning
     * @param monthBeginning
     * @param dayBeginning
     * @param yearEnding
     * @param monthEnding
     * @param dayEnding
     * @param information
     * @param user
     * @throws Exception
     */
    public Schedule(int yearBeginning, int monthBeginning, int dayBeginning, int yearEnding, int monthEnding,
                    int dayEnding, String information, User user) throws Exception {
        setBeginning(setToDate(yearBeginning, monthBeginning, dayBeginning));
        setEnding(setToDate(yearEnding, monthEnding, dayEnding));
        setInformation(information);
        setUser(user);
    }

    /**
     * Constructor with a Date as parameter when you already have a long value for your appointment
     * @param date
     * @param information
     * @param user
     * @throws Exception
     */
    public Schedule(Date date, String information, User user) throws Exception {
        setBeginning(date);
        setEnding(date);
        setInformation(information);
        setUser(user);
    }

    /**
     * Constructor with a Date as parameter. It also has an ending Date for your appointment.
     * @param beginning
     * @param ending
     * @param information
     * @param user
     * @throws Exception
     */
    public Schedule(Date beginning, Date ending, String information, User user) throws Exception {
        setBeginning(beginning);
        setEnding(ending);
        setInformation(information);
        setUser(user);
    }

    /**
     * sets the int value to a long value
     * @param year
     * @param month
     * @param day
     * @return
     * @throws Exception
     */
    public Date setToDate(int year, int month, int day) throws Exception {
        GregorianCalendar gc = new GregorianCalendar(year, month, day);
        return gc.getTime();
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) throws Exception {
        if(user == null) throw new Exception("User is not allowed to be null!");
        this.user = user;
    }

    public Date getBeginning() {
        return beginning;
    }
    public void setBeginning(Date date) throws Exception {
        if(date == null) throw new Exception("Date is not allowed to be null!");
        this.beginning = date;
    }
    public String getInformation() {
        return information;
    }
    public void setInformation(String information) throws Exception {
        if(information == null) throw new Exception("Information is not allowed to be null!");
        this.information = information;
    }

    public Date getEnding() {
        return ending;
    }
    public void setEnding(Date ending) {
        this.ending = ending;
    }

    public String getEmailOfUser(User user) {
        return user.getEmail();
    }

    public String beginningToString() {
        return ""+beginning.getTime();
    }

    public String endingToString() {
        return ""+ending.getTime();
    }

    public void showAppointments() {
        System.out.println("################");
        System.out.println("##  "+beginning.toString());
        System.out.println("##  "+ending.toString());
        System.out.println("##  "+information);
        System.out.println("################");
    }

    @Override
    public String toString() {
        return beginningToString()+";"+endingToString()+";"+getInformation()+";"+getEmailOfUser(user)+";";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schedule schedule = (Schedule) o;

        if (beginning != null ? !beginning.equals(schedule.beginning) : schedule.beginning != null) return false;
        if (ending != null ? !ending.equals(schedule.ending) : schedule.ending != null) return false;
        return !(user != null ? !user.equals(schedule.user) : schedule.user != null);

    }
    @Override
    public int hashCode() {
        int result = beginning != null ? beginning.hashCode() : 0;
        result = 31 * result + (ending != null ? ending.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
    @Override
    public int compareTo(Object o) {
        return 0;
    }


    public static void main(String[] args){
        if(args.length == 0) {
            System.out.println("There is no file");
            System.exit(1);
        }

        Schedule sc = null;
        try {
            sc = new Schedule(115, 3, 8, "LEEEEEL", new User("asdl@asdlkj.asd"));
        } catch (RegistrationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sc.getBeginning().toString());
    }
}
