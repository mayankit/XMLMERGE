Information regarding running this project:
1) git clone https://github.com/mayankit/XMLMERGE.git
2) Move into the respective folder
3) Edit file config.properties inside /src/main/java/resources
4) Set various parameters:
   url: this is the url of the file that need to be downloaded
   filesLocation: Location of the folder where we need to keep all the files
   downloadedFile: fileName
   existingFile:fileName
   mergedFile=FileName
5) Insure that all the properties are properly set before starting building application.
6) run command:
   mvn clean install
7) run command
    for windows:
    java -jar target\XMLMERGE-1.0-SNAPSHOT-jar-with-dependencies.jar
    
    for linux:
    java -jar target/XMLMERGE-1.0-SNAPSHOT-jar-with-dependencies.jar
