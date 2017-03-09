package probandograils

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ShipController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Ship.list(params), model:[shipCount: Ship.count()]
    }

    def show(Ship ship) {
        respond ship
    }

    def create() {
        respond new Ship(params)
    }

    @Transactional
    def save(Ship ship) {
        if (ship == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (ship.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond ship.errors, view:'create'
            return
        }

        ship.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'ship.label', default: 'Ship'), ship.id])
                redirect ship
            }
            '*' { respond ship, [status: CREATED] }
        }
    }

    def edit(Ship ship) {
        respond ship
    }

    @Transactional
    def update(Ship ship) {
        if (ship == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (ship.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond ship.errors, view:'edit'
            return
        }

        ship.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ship.label', default: 'Ship'), ship.id])
                redirect ship
            }
            '*'{ respond ship, [status: OK] }
        }
    }

    @Transactional
    def delete(Ship ship) {

        if (ship == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        ship.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ship.label', default: 'Ship'), ship.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ship.label', default: 'Ship'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
