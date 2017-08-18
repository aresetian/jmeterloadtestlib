# jmeterloadtestlib

Version de Java 1.8 u144
Se encontro en http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

Spring Tool Suite Version: 3.8.4.RELEASE

Desde eclipse ir a 
Import > General >Existing Projects into Workspace

Ir a Eclipse y en el poyecto importado click derecho e ir a 
Build Path > Configure Build Path...
Esto con el fin de congiurar java 8 dentro del proyecto

Descargar JMETER from http://jmeter.apache.org/download_jmeter.cgi
La prueba se hizo con la version 2.9 y 3.4
Bajar las fuentes binarias, luego descomprimirlar e ir a \lib\ext  donde debera estar el 
archivo ApacheJMeter_components y ApacheJMeter_core(Importaremos estos archivos al workspace del proyecto)

vamos a Build Path > Add External Archives... 
Buscamos los archivos ApacheJMeter_components y ApacheJMeter_core \lib\ext en y lo agregamos al proyecto
Buscamos los archivos de  log(log4j-1.2-api-2.8.1.jar y log4j-api-2.8.1.jar y log4j-core-2.8.1.jar y log4j-slf4j-impl-2.8.1.jar) en \lib\  y lo agregamos al proyecto
Buscamos el archivo de jorphan.jar en \lib\  y lo agregamos al proyecto


Debido a que el proyecto esta en gradle, se debe instalar gradle en el IDE, en este caso STS. Si ya se tiene grado 
no es necesario este paso

Ir a Help  > Eeclipse MarketPlace...
Instalar Minimalist Gradle Editor
Instalar Buildship Gradle Integration 2.0

Ir a Windows > Short View > Gradle Task 
Ejcutar el comando Build
Asegurarse que si hay JAVA_HOME que apunte al JDK de java


Si todo va bien  se creara una carpeta build al mismo nivel que la carpeta src del proyecto con el  jar que vamos a 
ingresar a JMETER


copiar el jar creado a la carpeta \lib\ext de jmeter

Abrir el Jmeter.

PAra verificar que todo funciona ir agregamos
Opciones > Dialogo de ayuda en funcion 
Alli buscar en nombre de las funciones creadas en el jar, solo que aca todas empiezan por "__"

Luego  en tu peticion puedes llamar las fucniones que creamos como se muestra enseguida

<request type="ACCOMMODATIONS_AVAIL" version="4.1" cache="yes" accessPoint="10" checkLogin="false" >
	<client code="EEMISO" branch="4905" groupCode="" password="zipvhbm9rr"/>
	<language code="SPA"/>
	<searchCriteria>
		${__GetSearchCriteria()}
		<criterion code="priceType" type="2" value="3"/>
		<criterion code="showPvp" type="2" value="0"/>
	</searchCriteria>
	<period start="${__GetPeriodStart(PERIODSTART)}" end="${__GetPeriodEnd(${PERIODSTART})}"/>
	<rooms>
		<room type="1" adults="${__Random(1,4, ADULTSR1)}" children="${__GetChildrenFromAdults(${ADULTSR1})}"/>
		<room type="2" adults="${__Random(1,4, ADULTSR2)}" children="${__GetChildrenFromAdults(${ADULTSR2})}"/>
	</rooms>
</request>

EL ejmplo ya configurado se enceuntra en el archivo [planprueba] Thread Group.jmx




