FROM openjdk:11
#base cua iamge co ten openjdk:11
#neu local k co image nay thi docker se tim kiem va pull image tu docker hub
#sau khi tim kiem thanh cong, docker se pull image co moi truong jdk17 ve local va san sang xay dung container voi moi truong do

EXPOSE 9100
#container tao ra se expose ra port 9100

COPY ./src/main/resources/templates/* /templates
COPY ./target/*.jar /docker-spring-boot-mysql.jar
ADD target/docker-spring-boot-mysql.jar docker-spring-boot-mysql.jar
#copy file docker-spring-boot-mysql.jar trong thu muc target sau khi dong goi ung dung vao trong container voi ten docker-spring-boot-mysql.jar
#"./wait-for-it.sh", "db:3306", "--",
ENTRYPOINT ["java", "-jar", "/docker-spring-boot-mysql.jar"]
# thuc thi execute file docker-spring-boot-mysql.jar duoc copy trong container
#sau khi cau truc project -> chay lenh mvn clean install
#buoc nay da xay dung duoc 1 image
#run
#gan tag name cho 1 image -> docker build -t docker-spring-boot-mysql .
#kiem tra danh sach image -> docker images
#run image -> docker run -p 9200:9100 docker-spring-boot-mysql
#


#get jenkins tu docker hub
#docker pull jenkins/jenkins
#run jenkins
#docker run -p 8080:8080 -p 50000:50000 jenkins/jenkins:latest
#
