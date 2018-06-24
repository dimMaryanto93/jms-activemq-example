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

        for (int i = 0; i < 10; i++) {
            TextMessage message = session.createTextMessage(String.format("message ke %d pada %s", i, new Date()));
            producer.send(message);
        }
        producer.close();

        session.close();
        connection.close();
    }
}
