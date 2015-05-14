package model.Schedule;

import model.RegistrationException;
import model.User.User;
import java.util.Date;

/**
 * Created by CrayZay on 14.05.2015.
 */
public class Schedule {
    private Date date;
    private User user;
    private String information;

    public Schedule(Date date, User user, String information) throws Exception {
        setDate(date);
        setUser(user);
        setInformation(information);
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) throws Exception {
        if(user == null) throw new Exception("User is not allowed to be null!");
        this.user = user;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) throws Exception {
        if(date == null) throw new Exception("Date is not allowed to be null!");
        this.date = date;
    }
    public String getInformation() {
        return information;
    }
    public void setInformation(String information) throws Exception {
        if(information == null) throw new Exception("Information is not allowed to be null!");
        this.information = information;
    }

    public static void main(String[] args){
        Schedule sc = null;
        try {
            sc = new Schedule(new Date(), new User("sal15532@spengergasse.at"), "LEEEEEL");
        } catch (RegistrationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sc.getDate().toString());
    }
}
