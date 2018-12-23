# Proof of Concept start code for P3-exercise

*Created from the Archetype: `jersey-quickstart-webapp`*

## Getting started:
- Create a persistence.xml file. Name the persistence-unit: "pu"
- Create a few test users: See the file `jwtdemo.utils.setupTestUsers.js`
- Build the project, either from within Netbeans or with maven: **mvn clean**
- Run the project in any of these ways:
   1. Via Netbeans and it's Tomcat (or whatever) server
   1. Via Jetty, and maven: mvn jetty:run
   1. Directly with an embedded Jetty: See `server.RunServer`

## Testing the startcode
### Unit Tests:
**mvn test**

### Integration Tests
**mvn verify**

This runs the test with Jetty

