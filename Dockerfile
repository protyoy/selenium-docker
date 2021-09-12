FROM openjdk:8u191-jre-alpine

RUN apk add curl jq

#Workspace
WORKDIR  /usr/share/udemy

#ADD .jar(required project files) under target from host into this image

ADD target/selenium-docker.jar      	selenium-docker.jar
ADD target/selenium-docker-tests.jar  	selenium-docker-tests.jar
ADD target/libs							libs

# in case of any other other dependency like .csv / .json / .xls
# please ADD that as well 

# ADD suite files
ADD book-flight-tesntng.xml		book-flight-module.xml
ADD search-module-testng.xml			search-module.xml

#ADD health check script
ADD healthcheck.sh              healthcheck.sh

# Environment Variables - 
# BROWSER
# HUB_HOST
# MODULE

ENTRYPOINT sh healthcheck.sh