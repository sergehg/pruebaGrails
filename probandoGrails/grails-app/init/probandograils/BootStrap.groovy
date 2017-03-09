package probandograils

class BootStrap {

    def init = { servletContext ->
    	new Ship(matricula:40,nombre:"Barco 1",pasajeros:5000).save()
    	new Ship(matricula:60,nombre:"Barco 2",pasajeros:9000).save()
    	new Ship(matricula:90,nombre:"Barco 3",pasajeros:9926265).save()
    }
    def destroy = {
    }
}
