package security

import org.springframework.dao.DataIntegrityViolationException

class SecureDemoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [secureDemoInstanceList: SecureDemo.list(params), secureDemoInstanceTotal: SecureDemo.count()]
    }

    def create() {
        [secureDemoInstance: new SecureDemo(params)]
    }

    def save() {
        def secureDemoInstance = new SecureDemo(params)
        if (!secureDemoInstance.save(flush: true)) {
            render(view: "create", model: [secureDemoInstance: secureDemoInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'secureDemo.label', default: 'SecureDemo'), secureDemoInstance.id])
        redirect(action: "show", id: secureDemoInstance.id)
    }

    def show() {
        def secureDemoInstance = SecureDemo.get(params.id)
        if (!secureDemoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'secureDemo.label', default: 'SecureDemo'), params.id])
            redirect(action: "list")
            return
        }

        [secureDemoInstance: secureDemoInstance]
    }

    def edit() {
        def secureDemoInstance = SecureDemo.get(params.id)
        if (!secureDemoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'secureDemo.label', default: 'SecureDemo'), params.id])
            redirect(action: "list")
            return
        }

        [secureDemoInstance: secureDemoInstance]
    }

    def update() {
        def secureDemoInstance = SecureDemo.get(params.id)
        if (!secureDemoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'secureDemo.label', default: 'SecureDemo'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (secureDemoInstance.version > version) {
                secureDemoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'secureDemo.label', default: 'SecureDemo')] as Object[],
                          "Another user has updated this SecureDemo while you were editing")
                render(view: "edit", model: [secureDemoInstance: secureDemoInstance])
                return
            }
        }

        secureDemoInstance.properties = params

        if (!secureDemoInstance.save(flush: true)) {
            render(view: "edit", model: [secureDemoInstance: secureDemoInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'secureDemo.label', default: 'SecureDemo'), secureDemoInstance.id])
        redirect(action: "show", id: secureDemoInstance.id)
    }

    def delete() {
        def secureDemoInstance = SecureDemo.get(params.id)
        if (!secureDemoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'secureDemo.label', default: 'SecureDemo'), params.id])
            redirect(action: "list")
            return
        }

        try {
            secureDemoInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'secureDemo.label', default: 'SecureDemo'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'secureDemo.label', default: 'SecureDemo'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
