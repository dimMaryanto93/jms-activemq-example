package com.maryanto.dimas.example;

import javax.jms.*;
import java.util.Date;

/**
 * Hello world!
 */
public class SendMessage {

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = JMSConnection.getConnectionFactory();
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("example.topic");
        MessageProducer producer = session.createProducer(queue);

        TextMessage message = session.createTextMessage("ini dari message data dikirim pada " + new Date());
        producer.send(message);
        producer.close();

        session.close();
        connection.close();
    }
}