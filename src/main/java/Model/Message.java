package Model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * This class will represents an abstract message
 */
public abstract class Message {
    private String sender;//The user that sent the message
    private String receiver;//The user that received the message
    private String date;//The date the message was sent
    private String time;//The time the message was sent
    private int myId;
    private boolean hasbeenRead;
    private String text;
    private String course;
    private String year;
    private String semester;

    public String getCourse() {
        return course;
    }

    public String getYear() {
        return year;
    }

    public String getSemester() {
        return semester;
    }
    public String getText(){
        return text;
    }

    /**
     * This is the constructor of the class
     * @param sender - The user that sent the message
     * @param receiver - The user that received the message
     */
    protected Message(String sender,String receiver)
    {
        this.sender = sender;
        this.receiver = receiver;
        //Get the current time without the '.'. instead of 17:25:03.848 we will get 17:25:03
        this.time = LocalTime.now().toString();
        int index = time.indexOf('.');
        if(index!=-1)
            this.time = this.time.substring(0,index);

        //Get the time
        this.date = LocalDate.now().toString();
        this.myId = -1;
        this.hasbeenRead = false;


    }

    /**
     * This is the constructor of the class
     * @param sender - The user that sent the message
     * @param receiver - The user that received the message
     * @param date - The date of creation
     * @param time - The time of creation
     * @param myId - The id of the message
     * @param hasbeenRead - True if the message has been read
     */
    protected Message(String sender,String receiver, String date,String time,int myId, boolean hasbeenRead)
    {
        this.sender = sender;
        this.receiver=receiver;
        this.date=date;
        this.time = time;
        this.myId = myId;
        this.hasbeenRead = hasbeenRead;
    }

    /**
     * This function will return the kind of message
     * @return - The kind of message
     */
    public abstract String getKind();

    /**
     * This function will return the content of the message
     * @return - The content of the message
     */
    public abstract String getContent();

    /**
     * This function will return the Headline of the message
     * @return - The Headline of the message
     */
    public abstract String getHeadline();

    /**
     * This function will return the name of the fxml file that is related to the message
     * @return - The name of the fxml file that is related to the message
     */
    public abstract  String getViewName();

    /**
     * This function will return the id of the message
     * @return -The id of the message
     */
    public int getId() {
        return this.myId;
    }

    /**
     * This function will return the date that the message was created on
     * @return - The date that the message was created on
     */
    public String getDate() {
        return date;
    }

    /**
     * This function will return the username of the sender of the message
     * @return - The username of the sender of the message
     */
    public String getSender() {
        return sender;
    }

    /**
     * This function will return the username of the receiver of the message
     * @return - The username of the receiver of the message
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * This function will return the time that the message was created on
     * @return - The time that the message was created on
     */
    public String getTime() {
        return time;
    }

    /**
     * This function will mark the message as "read"
     */
    public void markAsRead()
    {
        this.hasbeenRead = true;
    }

    /**
     * This function will return whether the message has been read
     * @return - True - if the message has been read. False - if it hasn't
     */
    public boolean hasbeenRead() {
        return hasbeenRead;

    }

    protected void setText(String text)
    {
        this.text = text;
    }



}
