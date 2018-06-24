package com.maryanto.dimas.example.topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;
import java.util.Date;

public class SentTopic {

    public static void main(String[] args) throws JMSException {
        TopicConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        TopicConnection connection = connectionFactory.createTopicConnection();
        Session session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        connection.start();

        Topic topic = new ActiveMQTopic("example.topic");
        TopicPublisher publisher = ((TopicSession) session).createPublisher(topic);
        for (int i = 0; i < 10; i++) {
            publisher.publish(topic, session.createTextMessage(String.format("message ke %d pada %s", i, new Date())));
        }
        publisher.close();
        connection.close();
    }
}
