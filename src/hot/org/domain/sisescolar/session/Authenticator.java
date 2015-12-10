package org.domain.sisescolar.session;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

@Name("authenticator")
public class Authenticator
{
    @Logger private Log log;

    @In Identity identity;
    @In Credentials credentials;
    
    @In EntityManager entityManager;

    public boolean authenticate()
    {
        log.info("authenticating {0}", credentials.getUsername());
        
        Query query = entityManager.createQuery("from Usuario where login = :login AND senha = :senha");
        query.setParameter("login", credentials.getUsername());
        query.setParameter("senha", credentials.getPassword());
        
        if (!query.getResultList().isEmpty()){
        	 identity.addRole("admin");
             return true;
        }
        //write your authentication logic here,
        //return true if the authentication was
        //successful, false otherwise
        return false;
    }

}
