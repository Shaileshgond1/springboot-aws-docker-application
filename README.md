# 🚀 Flipkart Clone Deployment — Spring Boot + MySQL + Docker + AWS EC2

##Overview
This project is a simple **Flipkart-style e-commerce backend** built using **Spring Boot** and **MySQL**, containerized using **Docker**, and deployed on **AWS EC2**.  
The backend handles authentication using JWT and basic CRUD operations for users, products, and carts.

---

## 🧱 Tech Stack
- Java 17  
- Spring Boot  
- MySQL  
- Docker  
- AWS EC2  
- Maven 

## ⚙️ Step 1: Build Spring Boot Application
Open project in **Spring Tool Suite (STS)** and verify it runs locally.  
Once everything works fine, create the `.jar` file.

    Command to build JAR --> $mvn clean install
    After build, the JAR will be created inside: /target
    You can test it locally: java -jar target/flipkart-0.0.1-SNAPSHOT.jar ( will start on port 9099)


 ## ⚙️Step 2: Install Docker on EC2

    $yum update -y
    $yum install docker -y
    
    Verify installation: 
        $docker --version

    $systemctl start docker  (to start docker)
    
 ## ⚙️Step 3: Using docker pulled required images
     1) $docker pull mysql:8.0
     2) $docker pull openjdk:17-jdk-slim 
     
 ## ⚙️Step 4: created N/W 

      $docker network create mynet
     
 ## ⚙️Step 5: Run MySQL Container

     $docker run -d --name mymysql \
        --network mynet \
        -e MYSQL_ROOT_PASSWORD=Pass@1234 \
        -e MYSQL_DATABASE=flipkart \
        -v mysql_data:/var/lib/mysql \
        mysql:8.0

 ## ⚙️Step 6: Import Existing Database

      First exported data form my local mysql workbench then imported using below cmd:  
             $docker exec -i mymysql mysql -u root -pPass@1234 flipkart < /tmp/flipkart_customer.sql
             $docker exec -i mymysql mysql -u root -pPass@1234 flipkart < /tmp/seller_customer.sql

## ⚙️Step 7: Configure AWS EC2 Security Group

   | Type       | Port Range | Source    |
   | ---------- | ---------- | --------- |
   | HTTP       | 80         | 0.0.0.0/0 |
   | Custom TCP | 9099       | 0.0.0.0/0 |

   
## ⚙️Step 8: Run application container
     1> Previously we builded jar that jar take here in ec2 machine in current directoy 
           $docker run -d --name myapp --network mynet openjdk:17-jdk-slim /bin/bash
            here one window will appear give cmd $exit and give below cmd

            $docker cp flipkart-0.0.1-SNAPSHOT.jar myapp:/tmp/

     2> now we have taken this jar inside container now go inside container using below cmd 
        $docker exec -it b0dcc6887c70 /bin/bash
        $cd /tmp
        $java -jar target/flipkart-0.0.1-SNAPSHOT.jar --spring.datasource.url=jdbc:mysql://43.204.174.76:3306/flipkart

        now your app started !


        NOTE : In my case app is accessible on http://43.204.174.76:9099/login
        





 
 
 
    





 
