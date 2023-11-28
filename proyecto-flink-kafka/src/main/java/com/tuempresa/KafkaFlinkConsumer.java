package com.tuempresa;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import java.util.Properties;
import java.util.Arrays;
public class KafkaFlinkConsumer {
    public static void main(String[] args) throws Exception {
        // Configuración del entorno de Flink
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
	env.setParallelism(1);
        // Propiedades de Kafka
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "44.200.26.74:9092");
        properties.setProperty("group.id", "test");
        // Crear un stream de Flink para consumir datos de Kafka
       	DataStream<String> stream = env
                .addSource(new FlinkKafkaConsumer<>("test", new SimpleStringSchema(), properties));
        stream.print();
		

        env.execute("Kafka Flink Consumer");
    }
}

