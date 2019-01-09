package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a mailbox of a user
 */
public class MailBox {

    private LinkedList<Message> messages;//the list of messages

    /**
     * The constructor of the class
     * @param messages - The given messages list
     */
    public MailBox(List<Message> messages)
    {
        this.messages = new LinkedList<>();
        this.messages.addAll(messages);
        this.messages.sort(new MessageComperator());
    }

    /**
     * Ths function will simulate a reading of a message
     * @param messageIndex - The index of the message in the list
     * @return - The message that we want to read
     */
    public Message readMessage(int messageIndex)
    {
        try {
            Message theWantedMessage = this.messages.get(messageIndex);
            theWantedMessage.markAsRead();
            return theWantedMessage;
        }
        catch (Exception e)
        {
            return null;
        }

    }
    /**
     * Ths function will return the message in the given index
     * @param messageIndex - The index of the message in the list
     * @return - The message
     */
    public Message getMessage(int messageIndex)
    {
        try {
            Message theWantedMessage = this.messages.get(messageIndex);
            return theWantedMessage;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * This function will remove a message from the mailbox
     * @param messageId - The message index in the list
     * @return - The message that was removed
     */
    public void removeMessage(int messageId)
    {
        Message message;
        for(int i=0;i<this.messages.size();i++)
        {
            message = this.messages.get(i);
            if(message.getId()==messageId)
                this.messages.remove(message);
        }


    }

    /**
     * This function will return the number of unread messages in the mailBox
     * @return - The number of unread messages in the mailBox
     */
    public int numOfUnreadMesages()
    {
        int counter =0;
        for(int i=0;i<this.messages.size();i++)
        {
            if(!this.messages.get(i).hasbeenRead())
                counter++;
        }
        return counter;
    }

    /**
     * This function will return the size of the mail box (the number of messages in the mailbox)
     * @return - The size of the mail box
     */
    public int size()
    {
        return this.messages.size();
    }

    public Message getMesseageById(int id)
    {
        Message message=null;
        for(int i=0;i<this.messages.size();i++)
        {
            message = this.messages.get(i);
            if(message.getId() == id)
                return message;
        }
        return null;
    }

    /**
     * This class is the Comparator of messages, to sort the according to relevance / date
     */
    class MessageComperator implements Comparator<Message>
    {

        /**
         * If message x has not been read and message y has, then x < y.
         * Else, if date and time of x < date and time of y then x > y.
         * @param message1 - The first message
         * @param message2 - The second message
         * @return the comparing number
         */
        @Override
        public int compare(Message message1, Message message2) {
            if(!message1.hasbeenRead() && message2.hasbeenRead()) return -1;
            if(message1.hasbeenRead() && !message2.hasbeenRead()) return 1;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date1 = LocalDate.parse(message1.getDate(), formatter);
            LocalDate date2 = LocalDate.parse(message2.getDate(), formatter);
            if (date1.isBefore(date2)) return 1;
            if (date2.isBefore(date1)) return -1;
            String time1 = message1.getTime();
            String time2 = message2.getTime();
            return  time2.compareTo(time1);
        }
    }
}
