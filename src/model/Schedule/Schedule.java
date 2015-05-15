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
    private User user;
    private String information;

    public Schedule(Date beginning, User user, String information) throws Exception {
        setBeginning(beginning);
        setUser(user);
        setInformation(information);
    }
    public Schedule(Date beginning, Date ending, User user, String information) throws Exception {
        setBeginning(beginning);
        setEnding(ending);
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


    public static void main(String[] args){
        if(args.length == 0) {
            System.out.println("There is no file");
            System.exit(1);
        }

        Schedule sc = null;
        try {
            sc = new Schedule(new Date(), new User("sal15532@spengergasse.at"), "LEEEEEL");
        } catch (RegistrationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sc.getBeginning().toString());
    }
}
