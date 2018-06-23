package com.maryanto.dimas.example;

import javax.jms.*;

public class AcknowloageMessage implements MessageListener {

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
        AcknowloageMessage messageReciver = new AcknowloageMessage();
        ConnectionFactory connectionFactory = JMSConnection.getConnectionFactory();
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("example.topic");
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(messageReciver);
    }
}
