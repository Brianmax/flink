/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mycompany.app;

import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * Skeleton for a Flink Batch Job.
 *
 * <p>For a tutorial how to write a Flink batch application, check the
 * tutorials and examples on the <a href="https://flink.apache.org/docs/stable/">Flink Website</a>.
 *
 * <p>To package your application into a JAR file for execution,
 * change the main class in the POM.xml file to this class (simply search for 'mainClass')
 * and run 'mvn clean package' on the command line.
 */
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.common.functions.FilterFunction;
import java.util.Properties;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;

public class BatchJob {

    public static void main(String[] args) throws Exception {
        // Establecer el entorno de ejecución por lotes
	final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // Configurar las propiedades de Kafka
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "44.200.26.74:9092");
        properties.setProperty("group.id", "test");

        // Crear un consumidor de Kafka
        FlinkKafkaConsumer<String> kafkaConsumer = new FlinkKafkaConsumer<>(
                "file_topic",                // nombre del tópico de Kafka
                new SimpleStringSchema(), // deserializador
                properties);              // propiedades del consumidor

        // Añadir el consumidor de Kafka como fuente de datos
        DataStream<String> stream = env.addSource(kafkaConsumer);

        // Procesar y imprimir los datos recibidos
        stream.print();

        // Ejecutar la aplicación
        env.execute("Flink Kafka Consumer");
    }
}

