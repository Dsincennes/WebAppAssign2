package cst8218.sinc0138.bouncer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.PasswordHash;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configures JAX-RS for the application.
 * @author Juneau
 */


/*
This is our basic Authentication call.
Queries the database for if the user inputted a valid username and password
*/
@BasicAuthenticationMechanismDefinition
@DatabaseIdentityStoreDefinition(
   dataSourceLookup = "${'java:comp/DefaultDataSource'}",
   callerQuery = "#{'select password from app.appuser where userid = ?'}",
   groupsQuery = "select groupname from app.appuser where userid = ?",
   hashAlgorithm = PasswordHash.class,
   priority = 10
)

/*
Form authentication.
Alternative login feature
When activated, this will call up a seperate page names Login.html
Presents login fields for the user to put in a username and password. 
*/
/*
@FormAuthenticationMechanismDefinition(
    loginToContinue = @LoginToContinue(
    loginPage = "/Login.html",
    errorPage = "/Error.xhtml"))
    @DatabaseIdentityStoreDefinition(
    dataSourceLookup = "${'java:comp/DefaultDataSource'}",
    callerQuery = "#{'select password from app.appuser where userid = ?'}",
    groupsQuery = "select groupname from app.appuser where userid = ?",
    hashAlgorithm = PasswordHash.class,
    priority = 10
)
*/
@Named
@ApplicationScoped
@ApplicationPath("resources")
public class JAXRSConfiguration extends Application {
    
}
