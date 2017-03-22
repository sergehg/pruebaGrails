package probandograils

class Ship {
	
	int matricula
	String nombre
	int pasajeros

    static constraints = {
 		matricula nullable:false,blank:false
 		nombre nullable:false,blank:false
 		pasajeros nullable:false,blank:false
    }

	static mapping = {
		table "ship"
        matricula column: "matricula"
        nombre column: "nombre"
        pasajeros column: "pasajeros"
    }


}
