FROM openjdk:11

# copy the packaged jar file into our docker image
COPY target/laboratoryWork-0.0.1-SNAPSHOT.jar /laboratoryWork.jar

# set the startup command to execute the jar file
# cdm: docker build . -t fedor/laboratory:latest
CMD ["java", "-jar", "/laboratoryWork.jar"]