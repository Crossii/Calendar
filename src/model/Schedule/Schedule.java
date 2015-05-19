package model.Schedule;

import model.RegistrationException;
import model.User.User;
import java.util.Date;

/**
 * Created by CrayZay on 14.05.2015.
 */
public class Schedule {
    private Date beginning;
    private Date ending;
    private String information;
    private User user;

    public Schedule(Date beginning, String information, User user) throws Exception {
        setBeginning(beginning);
        setEnding(beginning);
        setInformation(information);
        setUser(user);
    }
    public Schedule(Date beginning, Date ending, String information, User user) throws Exception {
        setBeginning(beginning);
        setEnding(ending);
        setInformation(information);
        setUser(user);
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
        return getBeginning().getYear()+" "+getBeginning().getMonth()+" "+getBeginning().getDate();
    }

    public String endingToString() {
        return getEnding().getYear()+" "+getEnding().getMonth()+" "+getEnding().getDate();
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

    public static void main(String[] args){
        if(args.length == 0) {
            System.out.println("There is no file");
            System.exit(1);
        }

        Schedule sc = null;
        try {
            sc = new Schedule(new Date(), "LEEEEEL", new User("asdl@asdlkj.asd"));
        } catch (RegistrationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sc.getBeginning().toString());
    }
}
