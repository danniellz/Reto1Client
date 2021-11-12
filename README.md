========================
Client App - Server App
========================

GitHub:
================================
	Cliente -- URL: https://github.com/danniellz/Reto1Client.git

	Servidor -- URL: https://github.com/danniellz/Reto1Server.git

	Libreria -- URL: https://github.com/danniellz/Reto1Library.git (solo branch "master" porque se commiteó al terminarse aunque hubo cambios más adelante - 
									aportacion de todos en el mismo ordenador)
	
	USUARIOS
		Daniel Brizuela: danniellz (Aportaciones con el nombre "Daniel")
		Aritz Arrieta: AritzAC
		Mikel Matilla: mikelmatilla (Aportaciones con el nombre "2dam")
		Jonathan Viñan: jony-ecua
================================
Librerias *Cliente*:
	*SignUpSignInLibrary

	TEST Libraries:
	
	*TestFX - hamcrest-junit-2.0.0.0.jar
	*TestFX - java-hamcrest-2.0.0.0.jar
	*TestFX - testfx-core-4.0.13-alpha.jar
	*TestFX - testfx-junit-4.0.13-alpha.jar
	*JUnit.4.12 - junit-4.12.jar
	*Hamcrest 1.3 - hamcrest-core-1.3.jar
		

Librerias *Servidor*:
	*SignUpSignInLibrary
	*mysql-connector-java-5.1.23-bin

	Necesario para BasicDataSource:

	*commons-dbcp2-2.9.0
	*commons-logging-1.2
	*commons-pool2-2.11.1

================================
Archivo de Propiedades Del *Cliente*:

	Está en el paquete "file" con el nombre "config"
			PORT - puerto de escucha del servidor
			SERVER - servidor local

Archivo de Propiedades Del *Servidor*:

	Está en el paquete "pool" con el nombre "poolData"
			PORT - puerto de escucha del servidor
			BD - nombre de la base de datos
			URL - direccion de la base de datos
			USER - usuario servidor
			PASS - contraseña servidor
			DRIVER - jdbc driver
			MAXCONNECTIONS - maximo de conexiones permitidas (Integer - afecta al tamaño del pool y la cantidad de hilos)

Patrones:
	Factoria
	DAO
	MVC
	Singleton
	Pool(?)
