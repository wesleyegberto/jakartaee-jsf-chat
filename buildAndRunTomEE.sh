#!/bin/sh
set -e exit

mvn clean package
docker rm -f jsf-chat-tomee || true
docker build -t wesleyegberto/jsf-chat-tomee -f Dockerfile.tomee .
docker run -it -p 8080:8080 --name jsf-chat-tomee wesleyegberto/jsf-chat-tomee
