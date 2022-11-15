FROM airhacks/java

# Copy from https://github.com/AdamBien/docklands/blob/master/tomee/Dockerfile to use update TomEE
ENV ARCHIVE apache-tomee-plus-8.0.13
ENV INSTALL_DIR /opt
ENV SERVER_HOME ${INSTALL_DIR}/${ARCHIVE}

RUN curl --insecure -o ${SERVER_HOME}.zip -L https://dlcdn.apache.org/tomee/tomee-8.0.13/apache-tomee-8.0.13-plus.zip \
    && unzip ${SERVER_HOME}.zip -d /opt \
    && rm ${SERVER_HOME}.zip

ENV DEPLOYMENT_DIR ${SERVER_HOME}/webapps/
RUN chmod +x ${SERVER_HOME}/bin/catalina.sh
ENTRYPOINT ${SERVER_HOME}/bin/catalina.sh run

EXPOSE 8080

COPY ./target/jsf-chat.war ${DEPLOYMENT_DIR}
