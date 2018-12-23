# Proof of Concept start code for P3-exercise

*Created from the Archetype: `jersey-quickstart-webapp`*

## Getting started:
- Create a persistence.xml file. Name the persistence-unit: **pu**
- Create a few test users: See the file `jwtdemo.utils.setupTestUsers.js`
- Build the project, either from within Netbeans or with maven: **mvn package**
- Run the project in any of these ways:
   1. Via Netbeans and it's Tomcat (or whatever) server
   1. Via Jetty, and maven: **mvn jetty:run**
   1. Directly with an embedded Jetty: See `server.RunServer`

## Testing the startcode
### Unit Tests:
**mvn test**

### Integration Tests
**mvn verify**
#### This runs the integration tests with Jetty. So far these tests verify behaviour for:
- Whether the server is up
- Behaviour when accessing a protected resource without the required permissions
- Behaviour when accessing a protecte resource with the required permissions 

## What this demo "proofs"
- JAX-RS (jersey) with Tomcat or Jetty, either as war-deployment or embedded server
- @provider  (used for cors-filters and Exception mappers)
- Unit-test with maven
- Integration test with maven and (in the provided code) Jetty


