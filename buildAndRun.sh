#!/bin/sh
mvn clean package && docker build -t wesleyegberto/jsf-chat .
docker rm -f jsf-chat || true && docker run -it -p 8080:8080 -p 4848:4848 --name jsf-chat wesleyegberto/jsf-chat
