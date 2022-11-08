## Getting Started

The File Reader is a cross-platform program targeting java 11 that runs in a Docker container. 
The program exposes a RESTful interface on port 8080 and allows a client application to obtain the full directory 
listing of a given path in the container.

### Prerequisites
In order to build and run the program, please ensure that the latest version of following tools have been installed

* Java SDK
* Docker

### How to build the program

To build the program, follow the instructions below
* maven
  ```sh
  mvn install
  mvn mvn clean package
  ```
* docker
  ```sh
  docker build . -t filereader:latest
  ```
### How to run the program
* Run the program as a foreground process
  ```sh
  docker run -p 8080:8080 filereader:latest
  ```
* Run the program as a background process
  ```sh
  docker run -p 8080:8080 filereader:latest -d
  ```
### Accessing the RESTful endpoint
The application exposes a RESTful API endpoint on port 8080 under the url: http://localhost:8080/api/v1/get-file-listing

The following query parameters are required
* skip - This is an integer value indicating how many entries to skip (e.g **0**)
* limit - This is an integer value indicating how many entries should be included in the results (e.g **10**)
* path - This is a string value specifying the directory to query (e.g **/etc**)

The skip and limit parameters have been included to allow the API consumer to control how many results are returned per request to cater for cases where the specified directory may contain a huge number of files.

NB: A postman collection has been included with an example request.