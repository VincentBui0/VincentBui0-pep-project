package DAO;


import Model.Message;
import Util.ConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MessageDAO {
   /** ## 4: Our API should be able to retrieve all messages.
    * TODO: Use the MessageDAO to retrieve all messages.
    *
    * @return all authors
    */
   public List<Message> getAllMessages(){
   Connection connection = ConnectionUtil.getConnection();
       List<Message> messages = new ArrayList<>();
       try {
           //Write SQL logic here
           String sql = "SELECT * FROM message";
           PreparedStatement preparedStatement = connection.prepareStatement(sql);
           ResultSet rs = preparedStatement.executeQuery();
           while(rs.next()){
               Message message = new Message(rs.getInt("message_id"),
                       rs.getInt("posted_by"),
                       rs.getString("message_text"),
                       rs.getLong("time_posted_epoch"));
               messages.add(message);
           }
       }catch(SQLException e){
           System.out.println(e.getMessage());
       }
       return messages;
   }


   // ## 3: Our API should be able to process the creation of new messages.
   public Message createMessage(Message message){
       Connection connection = ConnectionUtil.getConnection();
       try {
           //Write SQL logic here
           String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?)" ;
           PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


           //write preparedStatement's setString and setInt methods here.
           preparedStatement.setInt(1, message.getPosted_by());
           preparedStatement.setString(2, message.getMessage_text());
           preparedStatement.setLong(3, message.getTime_posted_epoch());


           preparedStatement.executeUpdate();
           ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
           if (message.getMessage_text().length() <= 254 && !(message.getMessage_text()).isBlank() && pkeyResultSet.next()){
                int generated_message_id = (int) pkeyResultSet.getLong(1);
                return new Message(generated_message_id, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
            }
       }catch(SQLException e){
           System.out.println(e.getMessage());
       }
       return null;
   }


   // ## 5: Our API should be able to retrieve a message by its ID.
   public Message getMessageById(int message_id){
       Connection connection = ConnectionUtil.getConnection();
       try {
           //Write SQL logic here
           String sql = "SELECT * FROM message WHERE message_id = ?";
           PreparedStatement preparedStatement = connection.prepareStatement(sql);


           //write preparedStatement's setInt method here.
           preparedStatement.setInt(1, message_id);


           ResultSet rs = preparedStatement.executeQuery();
           if(rs.next()){
                return new Message(rs.getInt("message_id"),
                       rs.getInt("posted_by"),
                       rs.getString("message_text"),
                       rs.getLong("time_posted_epoch"));
           }
       }catch(SQLException e){
           System.out.println(e.getMessage());
       }
       return null;
   }


   // ## 6: Our API should be able to delete a message identified by a message ID.
   


   // ## 7: Our API should be able to update a message text identified by a message ID.
   public Message updateMessage(int message_id, Message message){
       Connection connection = ConnectionUtil.getConnection();
       try {
           //Write SQL logic here
           String sql = "UPDATE message SET message_text=? WHERE message_id=?";
           PreparedStatement preparedStatement = connection.prepareStatement(sql);


           //write PreparedStatement setString and setInt methods here.
           preparedStatement.setString(1, message.getMessage_text());
           preparedStatement.setInt(2, message_id);


           preparedStatement.executeUpdate();
           String sql2 = "SELECT * WHERE message_id=?";
           PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
           preparedStatement2.setInt(1, message_id);
           ResultSet rs = preparedStatement2.executeQuery();
           if (rs.next()){
                return new Message(rs.getInt("message_id"),
                rs.getInt("posted_by"),
                rs.getString("message_text"),
                rs.getLong("time_posted_epoch"));
           } 
       }catch(SQLException e){
           System.out.println(e.getMessage());
       } return null;
   }


   // ## 8: Our API should be able to retrieve all messages written by a particular user.
   public List<Message> getMessagesFromUserAccountId(int account_id){
       Connection connection = ConnectionUtil.getConnection();
       List<Message> messages = new ArrayList<>();
       try {
           //Write SQL logic here
           String sql = "SELECT * FROM message WHERE posted_by = ?";
          
           PreparedStatement preparedStatement = connection.prepareStatement(sql);


           //write preparedStatement's setString and setInt methods here.
           preparedStatement.setInt(1, account_id);


           ResultSet rs = preparedStatement.executeQuery();
           while(rs.next()){
               Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"),
                       rs.getString("message_text"), rs.getLong("time_posted_epoch"));
                messages.add(message);
           }
       }catch(SQLException e){
           System.out.println(e.getMessage());
       }
       return messages;
   }
}
