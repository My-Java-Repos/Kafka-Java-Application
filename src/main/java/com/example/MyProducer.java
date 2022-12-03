package com.example;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;

public class MyProducer{
    private final static String TOPIC = "test-2";
    private final static String BOOTSTRAP_SERVER = "localhost:9092";


    //Writing up a Method that created a Producer, using the Properties Object for configuring it.
    private static Producer<String, String> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);

        props.put(ProducerConfig.CLIENT_ID_CONFIG, "ExampleProducer");

        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return new KafkaProducer<>(props);

    }

    //A Method for Sending Messages Synchronously
    public static void runProducer(String message) throws Exception {

        //we use the final keyword here because the producer configuration should be fixed.
        final Producer<String, String> producer = createProducer();


        try {
                final ProducerRecord<String, String> record = new ProducerRecord<String, String>(TOPIC, message);

                producer.send(record);

        } finally {

            //this is an optional command
            producer.flush();

            //this is to close the Connection
            producer.close();
        }
    }

}


