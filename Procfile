web: java -Ddatabase.url=$DATABASE_URL -jar target/kevineatsgrapes-1.0-SNAPSHOT.jar db migrate config.yml && java -Ddatabase.url=$DATABASE_URL $JAVA_OPTS -Ddw.server.applicationConnectors[0].port=$PORT -jar target/kevineatsgrapes-1.0-SNAPSHOT.jar server config.yml
