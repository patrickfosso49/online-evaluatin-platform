FROM ubuntu:22.04

RUN apt-get update -y && apt-get upgrade -y

RUN apt-get install mysql-server -y

RUN apt-get install openjdk-17-jdk -y

ADD init.sh /init.sh

RUN chmod 755 /*.sh

ADD password-file /home/password-file

RUN bash /init.sh

ADD config.sql /config.sql

EXPOSE 3306

RUN mysqld_safe

RUN mysql -u root mysql < config.sql

CMD ["bash", "-l"]
