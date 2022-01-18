FROM httpd

COPY ./Lab4/Front/ /usr/local/apache2/htdocs/
COPY ./Lab3/Gateway/target/Gateaway-0.0.1-SNAPSHOT.jar /usr/local/apache2/
COPY ./Lab3/Jobs/target/Jobs-0.0.1-SNAPSHOT.jar /usr/local/apache2/
COPY ./Lab3/Workers/target/Workers-0.0.1-SNAPSHOT.jar /usr/local/apache2/

# Install basic software support
RUN apt-get update && \
    apt-get install --yes software-properties-common

# Add the JDK 8 and accept licenses (mandatory)
RUN add-apt-repository ppa:webupd8team/java && \
    echo debconf shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
    echo debconf shared/accepted-oracle-license-v1-1 seen true | debconf-set-selections

# Install Java 8
RUN apt-get update && \
    apt-get --yes --no-install-recommends install oracle-java8-installer