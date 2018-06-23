package com.maryanto.dimas.example;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.ConnectionFactory;

public class JMSConnection {

    public static ConnectionFactory getConnectionFactory() {
        return new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
    }

}
