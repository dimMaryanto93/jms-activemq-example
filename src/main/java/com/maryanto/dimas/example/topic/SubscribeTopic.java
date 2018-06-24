package com.maryanto.dimas.example.topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;

public class SubscribeTopic implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage tm = (TextMessage) message;
            try {
                System.out.printf("Message received: %s, Thread: %s%n", tm.getText(), Thread.currentThread().getName());
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws JMSException {
        SubscribeTopic subscribeTopicListener = new SubscribeTopic();
        TopicConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        TopicConnection connection = connectionFactory.createTopicConnection();
        Session session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        connection.start();

        Topic topic = new ActiveMQTopic("example.topic");
        TopicSubscriber subscriber = ((TopicSession) session).createSubscriber(topic);
        subscriber.setMessageListener(subscribeTopicListener);
    }
}
