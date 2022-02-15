# Web-services-applications
Repo for a project for web services applications subject on university

# Run
To run this project clone the repo and run:

``` $ docker-compose up --build ```

# About
It's a simple CRUD app based on 3 separate spring boot modules. Everything is contenerized with Docker and apps are packed with Maven. There are 3 containers:
1. Frontend server based on apache httpd
2. Backend written in spring, consists of:
    1. Gateway app to dispatch http requests
    2. Workers app to manage Workers class objects
    3. Jobs app to manage Jobs class objects
3. Reverse proxy server based on Nginx

Frontend is very simple and not very nice, but is's only purpose is to make using app easier.

# What is planned
- separate database
- adding ci cd to a project
- further extension of app with any idea i will think of
