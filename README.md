# flink
## Instalar maven:
sudo yum install maven

## Ingresar a la carpeta proyecto-flink-kafka
cd proyecto-flink-kafka

## Compilar el proyecto
mvn clean install &&
mvn clean package

## Iniciar el cluster de flink
cd /usr/lib/bin &&
sudo ./start-cluster.sh

## Ejecutar el proyecto principal
cd && cd proyecto-flink-kafka &&
flink run -c com.tuempresa.KafkaFlinkConsumer target/proyecto-flink-kafka-1.0-SNAPSHOT.jar
