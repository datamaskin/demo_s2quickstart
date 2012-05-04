grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
        inherits true // Whether to inherit repository definitions from plugins
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenCentral()

        // uncomment these to enable remote dependency resolution from public Maven repositories
        //mavenCentral()
        //mavenLocal()
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        // runtime 'mysql:mysql-connector-java:5.1.16'
    }

    plugins {
        runtime ":hibernate:$grailsVersion"
        runtime ":jquery:1.7.1"
        runtime ':constraints:0.8.0'
        runtime ':database-migration:1.0'

        runtime ':famfamfam:1.0.1'
        runtime ':filterpane:2.0.1.1'
        //runtime ':geb:0.6'
        runtime ":hibernate:$grailsVersion"
        runtime ':jqgrid:3.8.0.1'
        runtime ':jquery:1.7.1'
        runtime ':jquery-ui:1.8.15'
        runtime ':localizations:1.4.1'
        runtime ':lookups:1.4'
        runtime ':mail:1.0'
        runtime ':modernizr:2.0.6'
        runtime ':spock:0.6'
        runtime ':spring-security-core:1.2.7.3'
        runtime ':spring-security-ui:0.2'
        runtime ":tomcat:$grailsVersion"
        runtime ':codenarc:0.16.1'
        runtime ':resources:1.1.6'
        runtime ':svn:1.0.1'
        runtime ":resources:1.1.6"

        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0"
        //runtime ":cached-resources:1.0"
        //runtime ":yui-minify-resources:0.1.4"

        build ":tomcat:$grailsVersion"
    }
}
