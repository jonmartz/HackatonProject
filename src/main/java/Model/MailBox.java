package Model;

import java.util.Comparator;
import java.util.List;

/**
 * This class represents a mailbox of a user
 */
public class MailBox {

    private List<Message> messages;//the list of messages

    /**
     * The constructor of the class
     * @param messages - The given messages list
     */
    public MailBox(List<Message> messages)
    {

        this.messages = messages;
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
     * This class is the Comparator of messages
     */
    class MessageComperator implements Comparator<Message>
    {

        /**
         * If positive than message1 > message2, If negative than message1 < message2, 0 means equal
         * @param message1 - The first message
         * @param message2 - The second message
         * @return
         */
        @Override
        public int compare(Message message1, Message message2) {

            if(!message1.hasbeenRead() && message2.hasbeenRead())
                return -1;
            if(message1.hasbeenRead() && !message2.hasbeenRead())
                return 1;
            return dateComp(message1.getDate(),message2.getDate());
        }
        private int dateComp(String date1,String date2)
        {
            int index1 = date1.lastIndexOf('-');
            int index2 = date2.lastIndexOf('-');
            int comp =date1.substring(index1).compareTo(date2.substring(index2));
            if(comp!=0)
                return comp;
            int index3 = date1.indexOf('-');
            int index4 = date2.indexOf('-');
            comp =date1.substring(index3+1,index1).compareTo(date2.substring(index4+1,index2));
            if(comp!=0)
                return comp;
            return date1.substring(0,index3).compareTo(date2.substring(0,index4));


        }
    }


}
