# Java Message Service with ActiveMQ

JMS atau Java Message Service, biasanya digunakan untuk mengirimkan pesan atau message minimal 2 atau lebih aplikasi. JMS berbeda dengan email.

## System Requirement

- Java
- Maven
- [Apache ActiveMQ](http://activemq.apache.org/download.html)

## Apache ActiveMQ getting started

How to run application:

- extract
- run

```bash
bin/activemq start
```

- launch [http://localhost:8161](http://localhost:8161)

![activemq webapp](imgs/webapp-activemq.png)

## Istilah JMS

- Provider, yaitu _Message Oriented Midleware_ atau aplikasi yang bertindak sebagai broker salah satu contohnya yaitu Apache ActiveMQ, RabitMQ, Hive MQTT, IBM MQ, JBoss Messaging dan lain-lain masih banyak lagi.
- Client, yaitu aplikasi yang bertindak sebagai pengirim atau penerima pesan.
- Producer / Publisher, yaitu aplikasi client atau JMS Client yang bertindak sebagai pengirim.
- Consumer / Subscriber, yaitu aplikasi client atau JMS Client yang bertindak sebagai penerima.
- Message, yaitu message atau data.
- queue, yaitu message yang diterima oleh client **diantrikan** dan dikirim satu-per-satu.
- topic, yaitu message yang diterima oleh client **disebarkan** ke setiap client yang terkonek.

## Model Java Message Service

- Point To Point (queue)
- Publisher & Subscribe (topic)

### Model PTP (Point to Point)

![model point to point](imgs/model-point-to-point.jpg)

Point to Point atau istilah lainnya peer to peer, Jadi dengan model ini messagenya hanya dapat diterima oleh satu consumer atau reciever. Jika ada lebih dari satu instance reciever maka message yang diterima akan unique. contohnya seperti berikut flownya:

1. Web 1, Web 2, dan web 3 mengirimkan message ke broker
2. Broker akan mengantrikan message yang di kirim oleh producer
3. Setelah mengantrikan message kemudian dikirimkan ke client
4. Client menerima message dari broker berdasarkan antrian, contohnya ada 2 kasus single instance dan multiple instance:

### Single Instance

#### Sending message

```bash
mvn exec:java -Dexec.mainClass=com.maryanto.dimas.example.queue.SentQueue

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 11.150 s
[INFO] Finished at: 2018-06-24T12:31:39+07:00
[INFO] ------------------------------------------------------------------------
```

#### Recived message

```bash
mvn exec:java -Dexec.mainClass=com.maryanto.dimas.example.queue.RecivedQueue

Message received: message ke 1 pada Sun Jun 24 12:39:38 WIB 2018, Thread: ActiveMQ Session Task
Message received: message ke 2 pada Sun Jun 24 12:39:38 WIB 2018, Thread: ActiveMQ Session Task
Message received: message ke 3 pada Sun Jun 24 12:39:38 WIB 2018, Thread: ActiveMQ Session Task
Message received: message ke 4 pada Sun Jun 24 12:39:38 WIB 2018, Thread: ActiveMQ Session Task
Message received: message ke 5 pada Sun Jun 24 12:39:38 WIB 2018, Thread: ActiveMQ Session Task
Message received: message ke 6 pada Sun Jun 24 12:39:38 WIB 2018, Thread: ActiveMQ Session Task
Message received: message ke 7 pada Sun Jun 24 12:39:38 WIB 2018, Thread: ActiveMQ Session Task
Message received: message ke 8 pada Sun Jun 24 12:39:38 WIB 2018, Thread: ActiveMQ Session Task
Message received: message ke 9 pada Sun Jun 24 12:39:38 WIB 2018, Thread: ActiveMQ Session Task
```
  
### Multiple Instance

#### Recieved message 1

```bash
mvn exec:java -Dexec.mainClass=com.maryanto.dimas.example.queue.RecivedQueue

Message received: message ke 1 pada Sun Jun 24 12:28:00 WIB 2018, Thread: ActiveMQ Session Task
Message received: message ke 4 pada Sun Jun 24 12:28:00 WIB 2018, Thread: ActiveMQ Session Task
Message received: message ke 7 pada Sun Jun 24 12:28:00 WIB 2018, Thread: ActiveMQ Session Task
```

#### Recieved message 2

```bash
mvn exec:java -Dexec.mainClass=com.maryanto.dimas.example.queue.RecivedQueue

Message received: message ke 2 pada Sun Jun 24 12:28:00 WIB 2018, Thread: ActiveMQ Session Task
Message received: message ke 5 pada Sun Jun 24 12:28:00 WIB 2018, Thread: ActiveMQ Session Task
Message received: message ke 8 pada Sun Jun 24 12:28:00 WIB 2018, Thread: ActiveMQ Session Task
```

#### Sending message

```bash
mvn exec:java -Dexec.mainClass=com.maryanto.dimas.example.queue.SentQueue

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 11.032 s
[INFO] Finished at: 2018-06-24T12:28:10+07:00
[INFO] ------------------------------------------------------------------------
```

## Message Queue

Message queue, dilewatkan melalui HTTP. ada beberapa protocol standar untuk menggunakan JMS yaitu 

- AMQP (Advanced Message Queuing Protocol), 
- STOMP (Streaming Text Oriented Messaging Protocol), dan 
- MQTT. 

## Referensi

- https://en.wikipedia.org/wiki/Java_Message_Service
- https://en.wikipedia.org/wiki/Message_queue
- https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html
- https://en.wikipedia.org/wiki/Message-oriented_middleware
- https://en.wikipedia.org/wiki/Advanced_Message_Queuing_Protocol
- https://en.wikipedia.org/wiki/Streaming_Text_Oriented_Messaging_Protocol
- https://en.wikipedia.org/wiki/MQTT
- https://en.wikipedia.org/wiki/Apache_ActiveMQ
