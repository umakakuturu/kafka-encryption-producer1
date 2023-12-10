package com.example.kafkaencryptionproducer;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaEncryptionProducerApplication {

	public static void main(String[] args) {
		// Set the configuration properties
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "your-bootstrap-server:9092");
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
		properties.put("schema.registry.url", "https://your-schema-registry-url");
		properties.put("security.protocol", "SSL");  // Adjust security settings accordingly

		// Set encryption-related properties
		properties.put("rule.executors._default_.param.access.key.id", "your-aws-access-key");
		properties.put("rule.executors._default_.param.secret.access.key", "your-aws-secret-key");
		properties.put("auto.register.schemas", "false");
		properties.put("use.latest.version", "true");

		// Create the Kafka producer
		Producer<String, YourAvroRecordClass> producer = new org.apache.kafka.clients.producer.KafkaProducer<>(properties);

		// Create an instance of YourAvroRecordClass with encrypted fields
		YourAvroRecordClass record = new YourAvroRecordClass();
		// Set values for fields including sensitive information

		// Produce the record to the Kafka topic
		ProducerRecord<String, YourAvroRecordClass> producerRecord = new ProducerRecord<>("your-topic", record);
		producer.send(producerRecord);

		// Close the producer
		producer.close();
	}
}
