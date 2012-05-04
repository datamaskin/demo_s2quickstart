package security



import org.junit.*
import grails.test.mixin.*

@TestFor(SecureDemoController)
@Mock(SecureDemo)
class SecureDemoControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/secureDemo/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.secureDemoInstanceList.size() == 0
        assert model.secureDemoInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.secureDemoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.secureDemoInstance != null
        assert view == '/secureDemo/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/secureDemo/show/1'
        assert controller.flash.message != null
        assert SecureDemo.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/secureDemo/list'


        populateValidParams(params)
        def secureDemo = new SecureDemo(params)

        assert secureDemo.save() != null

        params.id = secureDemo.id

        def model = controller.show()

        assert model.secureDemoInstance == secureDemo
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/secureDemo/list'


        populateValidParams(params)
        def secureDemo = new SecureDemo(params)

        assert secureDemo.save() != null

        params.id = secureDemo.id

        def model = controller.edit()

        assert model.secureDemoInstance == secureDemo
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/secureDemo/list'

        response.reset()


        populateValidParams(params)
        def secureDemo = new SecureDemo(params)

        assert secureDemo.save() != null

        // test invalid parameters in update
        params.id = secureDemo.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/secureDemo/edit"
        assert model.secureDemoInstance != null

        secureDemo.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/secureDemo/show/$secureDemo.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        secureDemo.clearErrors()

        populateValidParams(params)
        params.id = secureDemo.id
        params.version = -1
        controller.update()

        assert view == "/secureDemo/edit"
        assert model.secureDemoInstance != null
        assert model.secureDemoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/secureDemo/list'

        response.reset()

        populateValidParams(params)
        def secureDemo = new SecureDemo(params)

        assert secureDemo.save() != null
        assert SecureDemo.count() == 1

        params.id = secureDemo.id

        controller.delete()

        assert SecureDemo.count() == 0
        assert SecureDemo.get(secureDemo.id) == null
        assert response.redirectedUrl == '/secureDemo/list'
    }
}
