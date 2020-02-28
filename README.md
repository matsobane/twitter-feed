# twitter-feed
The program receives two seven-bit ASCII files. The first file contains a list of users and their followers. 
The second file contains tweets. Given the users, followers and tweets, the program is to display a simulated twitter feed for each user to the console.

## Build and package the project
`mvn clean package`


## Run the program
`java -jar target/twitter-feed-1.0-jar-with-dependencies.jar ./src/main/resources/user.txt ./src/main/resources/tweet.txt`