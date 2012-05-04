package security



import grails.test.mixin.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(SecureDemo)
class SecureDemoTests {

    void testSomething() {
        def springSecurityService

        def auth = springSecurityService.authentication
        String username = auth.username
        def authorities = auth.authorities // a Collection of GrantedAuthority
        boolean authenticated = auth.authenticated
        assert authenticated
    }
}
