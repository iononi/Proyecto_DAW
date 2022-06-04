# Proyecto_DAW

Aplicación web para gestión y control de reportes para recolección de residuos especiales.

Este proyecto es producto de la asignatura ***Diseño de Aplicaciones Web***. El propósito
de este proyecto es aplicar lo aprendido sobre los siguientes temas:

- Patrón de diseño MVC *(Model - View - Controller)*.
- JavaServer Pages *(JSP)*.
- JavaServer Pages Standard Tag Library *(JSTL)*.
- Implementación de Servlets.
- Conexión a bases de datos *(PostgreSQL)*
- Arquitectura cliente-servidor.
- Hypertext Markup Language *(HTML)*.
- Cascade Style Sheets *(CSS)*.
- Manejar dependencias con Maven.
- Manejo de [Git](https://git-scm.com/) y [GitHub](https://github.com/).

## El problema 
La cultura en México es sacar la basura a cierta hora del día dependiendo de la colonia, 
que es cuando pasa el camión que la recolecta. El problema es que muchas veces se pueden
observar residuos que no son de origen doméstico, por ejemplo: escombros resultado de 
trabajos de construcción, muebles, piezas de WC, etcétera. Este tipo de escombro no pueden
ser recolectados por el camión recolector por dos motivos principales: **1) su tamaño** y/o **2)
el material** del que está fabricado es tan resistente que la prensa podría sufrir daños al
tratar de triturarlos. Es por ello que suelen quedarse varios días en incluso semanas en las
calles, pudiendo llegar a caer, en el caso de los residuos de construcción, a los drenajes
públicos ocasionando obstrucciones o simplemente una mala imagen.

## Solución propuesta
Dado el reciente incremento en el uso de la tecnología, se apuesta por una aplicación web
capaz de registrar los reportes realizados por los usuarios. La aplicación web da la
posibilidad de realizar reportes anónimamente o crear una cuenta para realizar un reporte.
En ambos casos, el usuario contará con la capacidad de consultar el estado en el que se
encuentran sus reportes desde la misma aplicación. Por otra parte, existe una vista especial
para un administrador el cual tendrá acceso tanto a los reportes realizados anónimamente como
los que estén a nombre de algún cliente (usuario registrado). Podrá ser capaz de modificar el
estado de los mismos y podrá registrar nuevos usuarios, ya sean clientes u otro administrador.

## Como utilizarlo
Para hacer uso de este proyecto es necesario clonar este repositorio. Para ello, abrimos la terminal
y ejecutamos el siguiente snippet:
`````shell
git clone https://github.com/iononi/Proyecto_DAW.git
`````

Una vez clonado el repositorio, procedemos a abrir nuestro IDE favorito *(IntelliJ, NetBeans,
Eclipse)* y tendremos que seguir la siguiente secuencia de comandos de Maven:
````shell
mvn clean
mvn compile
mvn install
mvn tomcat7:run
````

***NOTA:*** El cómo ejecutar las acciones de Maven depende de cada IDE. Para mayor información, revisar
la documentación al respecto del IDE en cuestión.