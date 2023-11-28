(base) ➜  ~ docker start producer && docker attach producer
producer
root@90e6df7cfe00:/home# vim producer2.py 
































    value_serializer=lambda v: json.dumps(v).encode('utf-8')
)

# Función para enviar datos a Kafka
def send_to_kafka(topic, message):
    producer.send(topic, message)
    producer.flush()

# Modifica esta función para consumir el stream de Wikipedia y enviar los datos a Kafka
def consume_wikipedia_stream():
    url = "https://stream.wikimedia.org/v2/stream/recentchange"
    topic = "file_topic"  # Asegúrate de cambiar esto por el topic de tu elección

    try:
        with requests.get(url, stream=True) as response:
            if response.status_code == 200:
                for line in response.iter_lines():
                    if line and not line.startswith(b':'):
                        event_data = line.decode('utf-8')
                        print(event_data)
                        if "data" in event_data:
                            #print(event_data[6: len(event_data)])
                            y = json.loads(event_data[6: len(event_data)])
                          #  json_formatted_str = json.dumps(y, indent=2)
                          #  print(json_formatted_str)
                            send_to_kafka(topic,y)
            else:
                print(f"Error de conexión: {response.status_code}")
    except Exception as e:
        print(f"Error: {e}")

if __name__ == "__main__":
    consume_wikipedia_stream()

                                                                                                              41,0-1        Bot

