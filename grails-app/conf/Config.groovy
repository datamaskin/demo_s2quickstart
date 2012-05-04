// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }


grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']


// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// enable query caching by default
grails.hibernate.cache.queries = true

// set per-environment serverURL stem for creating absolute links
environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
}

//grails.plugins.springsecurity.interceptUrlMap = [
//        '/static/**':       ['IS_AUTHENTICATED_ANONYMOUSLY,IS_AUTHENTICATED_REMEMBERED'],
//        '/**/*.js':         ['IS_AUTHENTICATED_ANONYMOUSLY,IS_AUTHENTICATED_REMEMBERED'],
//        '/**/*.css':        ['IS_AUTHENTICATED_ANONYMOUSLY,IS_AUTHENTICATED_REMEMBERED'],
//        '/**/*.jar':        ['IS_AUTHENTICATED_ANONYMOUSLY,IS_AUTHENTICATED_REMEMBERED'],  // all applets must be public readable
//        '/dbconsole/**':    ['IS_AUTHENTICATED_ANONYMOUSLY,IS_AUTHENTICATED_REMEMBERED'], //TODO: use ROLE_ADMIN later on
//        '/login/**':        ['IS_AUTHENTICATED_ANONYMOUSLY'],
//        '/logout/**':       ['IS_AUTHENTICATED_REMEMBERED'],
//        '/':                ['IS_AUTHENTICATED_REMEMBERED'],
//        '/admin/**':        ['ROLE_ADMIN'],
//        '/user/**':         ['ROLE_ADMIN'],
//        '/role/**':         ['ROLE_ADMIN'],
//        '/requestmap/**':   ['ROLE_ADMIN'],
//        '/location/**':     ['ROLE_ADMIN'],
//        '/lookup/**':       ['ROLE_ADMIN'],
//        '/lookupValue/**':  ['ROLE_ADMIN'],
//        '/console/**':      ['ROLE_USER'],
//        '/SecureDemo/**':   ['ROLE_ADMIN']
//]

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'demo_s2quickstart.User'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'demo_s2quickstart.UserRole'
grails.plugins.springsecurity.securityConfigType = "Requestmap"
//grails.plugins.springsecurity.securityConfigType = "InterceptUrlMap"
grails.plugins.springsecurity.authority.className = 'demo_s2quickstart.Role'
