# `What is this application?`
This application lets the users upload files to server.

# `How to build and run?`
With out docker: ./gradlew clean build bootRun 
With docker: ./run-local.sh

The application is designed to run on port 5555 and the url to upload a file is http://localhost:5555/files (Do post a file from postman)

# `Assumptions`
Currently, the files are stored to temporary directory on the server. Later on the plan is to develop a new file service that could store in AWS S3.

# `Trade offs`
The service is built keeping ony one user in mind due to time constraints. Also, I do not have a free AWS account at this point of time so I did not try uploading the file to S3.

# `Documentation`
The endpoints are documented with Swagger. The url is http://localhost:5555/swagger-ui.html
