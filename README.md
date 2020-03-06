# `What is this application?`
This application lets the users upload files to server.

# `How to build ?`
./gradlew clean build bootRun or ./run-local.sh

# `Assumptions`
Currently, the files are stored to temporary directory on the server. Later on the plan is to develop a new file service that could store in AWS S3.

# `Trade offs`
The service is built keeping ony one user in mind due to time constraints. Also, I do not have a free AWS account at this point of time so I did not try uploading the file to S3.
