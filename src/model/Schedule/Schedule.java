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
        if(yearBeginning > yearEnding || monthBeginning > monthEnding || dayBeginning > dayEnding) {
            setBeginning(setToDate(yearEnding, monthEnding, dayEnding));
            setEnding(setToDate(yearBeginning, monthBeginning, dayBeginning));
        } else {
            setBeginning(setToDate(yearBeginning, monthBeginning, dayBeginning));
            setEnding(setToDate(yearEnding, monthEnding, dayEnding));
        }
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

    public boolean isInbetween(Schedule s) {
        if(s.getBeginning().getYear() >= beginning.getYear() && s.getEnding().getYear() <= ending.getYear()) {
            if(s.getBeginning().getMonth() >= beginning.getMonth() && s.getEnding().getMonth() <= ending.getMonth()) {
                if(s.getBeginning().getDate() >= beginning.getDay() && s.getEnding().getDate() <= ending.getDate()) {
                    return true;
                }
            }
        }
        return false;
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

        if (!beginning.equals(schedule.beginning)) return false;
        if (!ending.equals(schedule.ending)) return false;
        return user.equals(schedule.user);

    }

    @Override
    public int hashCode() {
        int result = beginning.hashCode();
        result = 31 * result + ending.hashCode();
        result = 31 * result + user.hashCode();
        return result;
    }

    @Override
    public int compareTo(Object o) {

        if(o instanceof Schedule) {
            Schedule s = (Schedule)o;

            if(beginning.getYear() > s.getBeginning().getYear()) {
                if(beginning.getMonth() > s.getBeginning().getMonth()) {
                    if(beginning.getDate() > s.getBeginning().getDate())
                        return -1;
                }
            }
            if(beginning.getYear() < s.getBeginning().getYear()) {
                if(beginning.getMonth() < s.getBeginning().getMonth()) {
                    if(beginning.getDate() < s.getBeginning().getDate())
                        return 1;
                }
            }
        }
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

        System.out.println(sc.getBeginning().getDate());
    }
}
