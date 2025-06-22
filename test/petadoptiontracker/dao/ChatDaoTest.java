/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package petadoptiontracker.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import petadoptiontracker.model.ChatMessage;

import java.util.List;
import java.util.Map;

public class ChatDaoTest {

    private ChatDao chatDao;
    private int senderId = 1;
    private int receiverId = 2;
    private String testMessage = "JUnit test message";

    @Before
    public void setUp() {
        chatDao = new ChatDao();
    }

    @Test
    public void testSendMessage() {
        boolean result = chatDao.sendMessage(senderId, receiverId, testMessage, false);
        Assert.assertTrue(result);
    }

    @Test
    public void testGetChatHistory() {
        chatDao.sendMessage(senderId, receiverId, testMessage, false);
        List<ChatMessage> history = chatDao.getChatHistory(senderId);
        boolean found = false;
        for (ChatMessage msg : history) {
            if (testMessage.equals(msg.getMessage()) &&
                msg.getSenderId() == senderId &&
                msg.getReceiverId() == receiverId) {
                found = true;
                break;
            }
        }
        Assert.assertTrue(found);
    }

    @Test
    public void testMarkMessagesAsRead() {
        chatDao.sendMessage(senderId, receiverId, testMessage, false);
        boolean marked = chatDao.markMessagesAsRead(senderId, receiverId);
        Assert.assertTrue(marked);
    }

    @Test
    public void testGetUsersWithChatHistory() {
        List<Map<String, Object>> usersWithChat = chatDao.getUsersWithChatHistory();
        Assert.assertNotNull(usersWithChat);
    }
}
