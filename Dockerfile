FROM bellsoft/liberica-openjre-alpine:17.0.5
VOLUME /tmp
ADD target/*.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -jar /app.jar

#docker build -t kov-slave.int-dc1.o.nkpor.etris:6000/kotlin-camunda-demo:1.0.0 .
#docker push kov-slave.int-dc1.o.nkpor.etris:6000/kotlin-camunda-demo:1.0.0
