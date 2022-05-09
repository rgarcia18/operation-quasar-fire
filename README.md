# operation-quasar-fire
Challenge - Operation Quasar Fire

## Requirements
1. Java 17
2. Maven installed
3. Data base MySQL

# Running the application

## Download
```bash
git clone https://github.com/rgarcia18/operation-quasar-fire.git
```

## Enter in the directory
```bash
cd quasar
```

## Command to run the project
```bash
mvn spring-boot:run
```

# URl Api Rest
[https://rgarcia-operation-quasar-fire.herokuapp.com](https://rgarcia-operation-quasar-fire.herokuapp.com/)

# Rest Api urls

## Top Secret method
```bash
POST -> /topsecret/
    {
    "satellites": [
        {
            "name": "kenobi",
            "distance": 100.0,
            "message": ["este", "", "", "mensaje", ""]
        },
        {
            "name": "skywalker",
            "distance": 115.5,
            "message": ["", "es", "", "", "secreto"]
        },
        {
            "name": "sato",
            "distance": 142.7,
            "message": ["este", "", "un", "", ""]
        }
    ]
}
```
```bash
RESPONSE CODE: 200
{
    "position": {
        "x": -100.0,
        "y": 75.5
    },
    "message": "este es un mensaje secreto"
}
```
## Top secret split
## POST method
```bash
POST -> /topsecret_split/{satellite_name}
{
    "distance": 100.0,
    "message": ["este", "", "", "mensaje", ""]
}
```
```bash
RESPONSE CODE: 200
{
    "position": null
    "message": "Message saved successfully."
}
```
## GET method
```bash
POST -> /topsecret_split
```
```bash
RESPONSE CODE: 200
{
    "position": {
        "x": -100.0,
        "y": 75.5
    },
    "message": "este es un mensaje secreto"
}
```
# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.7/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.7/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.7/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
