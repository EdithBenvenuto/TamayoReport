--------README---------------------
instalación previa.

Es necesario tener lo siguiente instalado.
	-nodejs (version 14.18.0)
	-mongodb(version 5.0.3)
	-android studio(latest version)
	-postman(opcional) para realizar queries directas por fuera de la app.


-------------------------Configuración api.-----------------------------------------------------

Es necesario realizar los siguientes módulos de npm (nodejs) para levantar el servidor con la api.
Puede instalarlos directamente copiando y pegando los siguientes comandos en terminal desde la dirección "/backend":
	
	npm install mongoose
	npm install -g nodemon 
	npm install express --save
	npm install -save validator
	npm install -save bcryptjs
	npm install dotenv
	npm install jsonwebtoken
	npm install -S app-root-path
	npm install --save multer

Estos comandos también se encuentran en la ruta C:\...\Backend\npmInstall.txt
-------------------------Ejecución de api y levantar server--------------------------------------

Para levantar el servidor local, en terminal es necesaio correr el comando: nodemon app.js
#NOTA: de marcar el error:

	"nodemon : No se puede cargar el archivo C:\Users...\nodemon.ps1 porque la ejecución de scripts está deshabilitada en este 
	sistema. Para obtener más información, consulta el tema about_Execution_Policies en https:/go.microsoft.com/fwlink/?LinkID=135170.
	nodemon : No se puede cargar el archivo C:\Users...\nodemon.ps1 porque la ejecución de scripts está deshabilitada en este 
	sistema. Para obtener más información, consulta el tema about_Execution_Policies en https:/go.microsoft.com/fwlink/?LinkID=135170.
	En línea: 1 Carácter: 1
	+ nodemon app.js
	+ ~~~
	    + CategoryInfo          : SecurityError: (:) [], PSSecurityException
	    + FullyQualifiedErrorId : UnauthorizedAccess"

Es necesario abrir el windows Powershell con persmisos de administrador  y pegar el siguiente comando:

	Set-ExecutionPolicy RemoteSigned -Scope CurrentUser

tras el mensaje de verificación de Powershell, introducir "Y".

Es necesario volver a ejecutar el comando "nodemon app.js" desde la dirección C:\...\Backend.

Sabremos que se ha levantado el servidor con los siguientes mensajes que lanza la terminal:
 
	Connecting to mongodb://localhost/tamayoReport
	Server up and running
	Connected to database server

Ahora podremos ejecutar la app y realizar cualquier acción.



