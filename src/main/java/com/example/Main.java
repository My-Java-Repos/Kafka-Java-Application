package com.example;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.TopicListing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ConnectException;
import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);

        logger.info("Main Method Is Running.");
        logger.info("Checking to See if Zookeeper Server is Running...");

        // First we need to initialize Kafka properties
        Properties properties = new Properties();
        properties.put("bootstrap.servers","localhost:9092");
        properties.put("client.id","java-admin-client");
        properties.put("request.timeout.ms","50");


        //Creating the AdminClient
        AdminClient client = AdminClient.create(properties);
        logger.info("Created AdminClient.");

        ListTopicsOptions options = new ListTopicsOptions();
        options.listInternal(true);

        Collection<TopicListing> listings = null;

        try {

            logger.info("Attempting to Fetch Topics...");

            listings = client.listTopics(options).listings().get();

        }


        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        catch (ExecutionException e) {
            logger.error( "Failed to get topic list {0}"+ e.getCause());
        }
        catch (Exception e){
            logger.error("Unknown Exception");
        }

        finally{
            listings.forEach(
                    topic -> System.out.println("Name: " + topic.name() + ", isInternal: " + topic.isInternal()));

        }

        MyConsumer consumer = new MyConsumer();

        try{

            System.out.println("Consumer starting up!...");

            consumer.runConsumer();

        }

        catch(Exception e){
            System.out.println("Beep Beep! Consumer Error!");

        }



        System.out.println("\nThe following message will be sent by the Producer: I am the message being sent!");

        //Sending the message
        System.out.println("Sending Producer Message...\n");

        MyProducer producer = new MyProducer();

        String message = "I am the message being sent!";

        try {

            producer.runProducer(message);

            System.out.println("Message Sent");
        }
        catch(Exception e){
            System.out.println("Error! Error! Beep! Boop!");
        }











    }
}
