# Kafka-Java-Small-Application
Designed to be  a small Java Microservice to handle incoming messages and send outgoing messages to topics. All local. 


## The Goal

- [x] Understand Kafka's Basics from the ground up (The Kafka processes running in the background).
- [x] See how to use Java to setup Consumers and Producers.
- [ ] Have a Producer write to multiple topics
- [ ] Have a Consumer read from multiple topics
- [ ] Integrate SpringBoot to develop an API that sends messages to a Kafka Topic and then displays them on a page. 
- [ ] Integrate Kafka Streams Potentially


## Documentation

I am hoping to maintain clear documentation to help as a reference for this project.

## Package Manager

This project will have Maven behind it for convenience, this lets me quickly swap Kafka Version and 
add new libraries depending on how I decide to scale it. s

## Running & Testing

In order to run this application, you will need to first start up Zookeeper and the Kafka Server. Navigate to the folder where you have
downloaded both of these. In my case it was under usr/local/Cellar as I used Homebrew to install them. 
Once there, run the following two commands, making sure you have created two properties files, one for Zookeeper and one for the Kafka server.

(Largely taken from the [Official Guide](https://kafka.apache.org/quickstart))

```sbtshell
 bin/zookeeper-server-start.sh config/zookeeper.properties
```

Followed by:

```sbtshell
bin/kafka-server-start.sh config/server.properties
```

To See what topics exist, you can run

```sbtshell
bin/kafka-topics.sh --bootstrap-server=localhost:9092 --list
```

It is important that you have a topic created that matches what's in the code i.e *test-2*

If both of these are running you can then run the Main function to see it work. 

Once The Producer sends messages, run this to check if its worked

```sbtshell
bin/kafka-console-consumer --topic test-2 --from-beginning --bootstrap-server localhost:9092
```


