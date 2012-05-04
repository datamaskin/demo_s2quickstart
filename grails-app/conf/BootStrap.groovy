import demo_s2quickstart.Requestmap
import demo_s2quickstart.Role
import demo_s2quickstart.User
import demo_s2quickstart.UserRole
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsWebRequest
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.web.context.request.RequestContextHolder

class BootStrap {

    def init = { servletContext ->
        Role.withNewSession {
                    initRoles()
                    initRequestMap()
//                    initLookups()
                    initAdminUser() // creates only admin

                }

                // we need to fake a requestAttributes here, otherwise if BootStrap produces a validation
                // error, it won't show up, since the validation code delegates to Lookup.load.
                // The latter requires a reference to the servletcontext via RequestContextHolder
                RequestContextHolder.requestAttributes = new GrailsWebRequest(new MockHttpServletRequest(), new MockHttpServletResponse(), servletContext)
                initUsersAndUserRoles() // creates users
    }
    def destroy = {
    }

    def initRoles() {
    	    [
            'ROLE_ADMIN',
            'ROLE_USER'
            ].each {
            if (Role.countByAuthority(it)==0) {
                new Role(authority: it).save(failOnError: true)
            }
        }
    }

    def initRequestMap() {
    		[
    				//'/**': 'ROLE_ADMIN', //TODO find the reason why this does not work
                    '/static/**': 'IS_AUTHENTICATED_ANONYMOUSLY,IS_AUTHENTICATED_REMEMBERED',
                    '/**/*.js': 'IS_AUTHENTICATED_ANONYMOUSLY,IS_AUTHENTICATED_REMEMBERED',
                    '/**/*.css': 'IS_AUTHENTICATED_ANONYMOUSLY,IS_AUTHENTICATED_REMEMBERED',
                    '/**/*.jar': 'IS_AUTHENTICATED_ANONYMOUSLY,IS_AUTHENTICATED_REMEMBERED',  // all applets must be public readable
//                    '/dbconsole/**': 'IS_AUTHENTICATED_ANONYMOUSLY,IS_AUTHENTICATED_REMEMBERED', //TODO: use ROLE_ADMIN later on
                    '/dbconsole/**': 'ROLE_ADMIN',
                    '/login/**': 'IS_AUTHENTICATED_ANONYMOUSLY',
                    '/logout/**': 'IS_AUTHENTICATED_REMEMBERED',
                    '/': 'IS_AUTHENTICATED_REMEMBERED',
                    '/admin/**': 'ROLE_ADMIN',
                    '/user/**': 'ROLE_ADMIN',
                    '/role/**': 'ROLE_ADMIN',
                    '/requestmap/**': 'ROLE_ADMIN',
                    '/location/**': 'ROLE_ADMIN',
                    '/lookup/**': 'ROLE_ADMIN',
                    '/lookupValue/**': 'ROLE_ADMIN',
                    '/console/**': 'ROLE_USER',
                    '/SecureDemo/**': "hasRole('ROLE_ADMIN')"
    		].each { url, role ->
    			def requestMap = Requestmap.findByUrl(url) ?: new Requestmap()
    			requestMap.url = url
    			requestMap.configAttribute = role
    			requestMap.save(failOnError: true)
    		}
        }

    /**
         * initialize all lookups
         * @return
         */
        def initLookups() {
//            initLookup("billcity", ["college station","dallas","navasota","cleveland","magnolia"])
//            initLookup("billstate", ["texas","ohio","louisiana"])
        }

    /**
         * create a single lookup with its possible values, checks if lookup already exists
         * TODO: provide support for internationalizable lookups
         * @param lookupName
         * @param lookupValues
         * @return
         */
        /*def initLookup(String lookupName, lookupValues) {
            if (!CodeIterator.Lookup.findByRealm(lookupName)) {
                def lookup
                switch (lookupName) {

                    default:
                        lookup = new CodeIterator.Lookup(realm: lookupName, codeType: 'string', valueType: 'string', ordering: 'value', internationalize: false)
                }
                assert lookup.save(failOnError:true),  lookup.errors.allErrors
                lookupValues.each {
                    def lookupValue = it instanceof Map.Entry ?
                      new LookupValue(lookup:lookup, code: it.key, value: it.value) : new LookupValue(lookup:lookup, code: it, value: it)
                    assert lookupValue.save(failOnError: true),  lookupValue.errors.allErrors
                }
            }
        }*/

    void initAdminUser() {
        if (!User.findByUsername("admin")) {
            // admin is required in each environment
//            String adminpass = springSecurityService.encodePassword('sex1ng')
            String adminpass = "killroy"
            User adminUser = new User(username: 'admin',
                enabled: true,
                password: adminpass,
                emailaddress: 'david@davidwbrown.name',
                firstname: 'David',
                lastname: 'Brown')
            adminUser.save(failOnError: true)
            UserRole.create adminUser, Role.findByAuthority("ROLE_ADMIN"), true
        }
    }

        def initUsersAndUserRoles() {

//        String password = springSecurityService.encodePassword('lutefisk')
        String password = "lutefisk"
        if (!User.findByUsername("david")) {
            User david = new User(username: 'david',
                    enabled: true,
                    password: password,
                    emailaddress: 'david@davidwbrown.name',
                    firstname: 'David',
                    lastname: 'Brown'
                    )
            david.save(failOnError: true);
            ["ROLE_USER", 'ROLE_SITE_ADMIN'].each {
                UserRole.create david, Role.findByAuthority(it), true
            }
        }
    }
}
