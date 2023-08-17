package Service;


import DAO.MessageDAO;
import Model.Message;
import java.util.List;


public class MessageService {
   private MessageDAO messageDAO;
   /**
    * no-args constructor for creating a new AuthorService with a new AuthorDAO.
    */
   public MessageService(){
       messageDAO = new MessageDAO();
   }
   /**
    * Constructor for a AuthorService when a AuthorDAO is provided.
    * This is used for when a mock AuthorDAO that exhibits mock behavior is used in the test cases.
    * This would allow the testing of AuthorService independently of AuthorDAO.
    * @param messageDAO
    */
   public MessageService(MessageDAO messageDAO){
       this.messageDAO = messageDAO;
   }


   /**
    * ## 4: Our API should be able to retrieve all messages.
    * TODO: Use the MessageDAO to retrieve all messages.
    *
    * @return all authors
    */
   public List<Message> getAllMessages() {
       return messageDAO.getAllMessages(); // Call the getAllMessages() method from the MessageDAO class
   }
  
   // ## 3: Our API should be able to process the creation of new messages.
   public Message createMessage(Message message) {
       Message addedMessage = messageDAO.createMessage(message);
  
       // Return the persisted flight with the flight_id
       return addedMessage;
   }


   // ## 5: Our API should be able to retrieve a message by its ID.
   public Message getMessageById(int message_id) {
    
       return messageDAO.getMessageById(message_id); // Call the getAllMessageIDs() method from the MessageDAO class
   }


   // ## 7: Our API should be able to update a message text identified by a message ID.
   public Message updateMessage(int message_id, Message message){
       if (message.getMessage_text().length() >= 255 || message.getMessage_text().isBlank()) {
           return null;
       }
       return messageDAO.updateMessage(message_id, message);
   }


   // ## 6: Our API should be able to delete a message identified by a message ID.
   


   // ## 8: Our API should be able to retrieve all messages written by a particular user.
   public List<Message> getMessagesFromUserAccountId(int account_id) {
       List<Message> messages = messageDAO.getMessagesFromUserAccountId(account_id);
      if (messages == null) {
        return null;
      } else
       return messages;
   }
}

