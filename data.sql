-- NIVEL --
INSERT INTO java_coach_api.nivel (nombre) VALUES
	 ('Básico'),
	 ('Intermedio'),
	 ('Avanzado');
	 
-- CAPITULOS --
INSERT INTO java_coach_api.capitulo (nombre,numero,nivel_id) VALUES
	 ('Fundamentos',1,1),
	 ('Operadores y Sentencias',2,1),
	 ('Core Java APIs',3,1),
	 ('Metodos y Encapsulamiento',4,2),
	 ('Diseño de Clase',5,2),
	 ('Excepciones',6,3);
	 
-- CONCEPTOS --
INSERT INTO java_coach_api.concepto (contenido,nombre,capitulo_id) VALUES
	 ('Los atributos, también llamados datos o variables, son porciones de informacion que un objeto posee o conoce de si mismo. Una clase puede tener cualquier numero de atributos o no tener ninguno. Se declaran con un identificador y el tipo de dato correspondiente.\nLos atributos son las caracteristicas individuales que diferencian un objeto de otro y determinan su apariencia, estado u otras cualidades.\nAdemás los atributos y tienen asociado un modificador que define su visibilidad según se muestra en la siguiente tabla.\n\nModificador\tVisibilidad\npublic\t\tPublica (+)\nprotected\tProtegida/en la herencia (#)\nprivate\t\tPrivada (-)\npackage\t\tDe paquete (~)\n','Atributos',1),
	 ('Los metodos son bloques de codigo que se pueden llamar para evitar reutilizar codigo.','Metodos',1),
	 ('Es texto dentro del codigo que no se ejecuta. Sirve para aclarar funcionamiento del mismo.','Comentarios',1),
	 ('Las clases son planos de construccion. Todos los nombres de las clases deben ser con mayuscula.\n Cuando definimos una clase, se describen todas las caracteristicas de esta.\n Para darle uso a una clase se tiene que crear/instanciar un objeto.\n Las clases tienen dos elementos importantes que la definen: los atributos y los metodos o funciones.','Clases',1),
	 ('Una aplicacion de Java está definida como una clase la cual contiene el metodo main. El metodo main tiene la funcion especial de ser el punto de arranque de un programa y es lo que diferencia una clase comun y corriente de una aplicacion','Metodo Main',1),
	 ('El asterisco (*) es un wildcard (comodín) que se usa como atajo para importar todas las clases de un paquete.','Comodines (Wildcards)',1),
	 ('Una declaración de importación es considerada redundante cuando: _Una clase es importada mas de una vez. _La clase importada pertenece al paquete java.lang. _La clase importada es del mismo paquete que el paquete actual.','Importaciones redundantes (Redundant imports)',1),
	 ('Se genera un conflicto de nombres cuando se importan clases con el mismo nombre pero de diferentes paquetes. A la hora de usarlas es necesario romper con la ambiguedad que se crea al tener el mismo identificador usando su nombre completo.','Conflicto de nombres (Naming conflicts)',1),
	 ('Un paquete es un contenedor de clases que permite agrupar las distintas partes de un programa. Un paquete se genera incluyendo la pablabra clave package al inicio de los módulos de código en los que se definen las clases que formarán parte del mismo.','Paquetes (Creating a new package)',1),
	 ('Un constructor es un método especial de una clase que se llama automáticamente siempre que se declara un objeto de esa clase. Su función es inicializar el objeto y sirve para asegurarnos que los objetos siempre contengan valores válidos.','Constructores',1);
INSERT INTO java_coach_api.concepto (contenido,nombre,capitulo_id) VALUES
	 ('Los datos primitivos son heredados de lenguajes no orientados a objetos, representan  el armado de bloques para los obejetos en Java. Los de referencia, hacen los que su nombre, refieren a un objeto, a diferencia de los primitivos que tienen el valor en ellos, apuntan a un objeto guardado en una direccion de memoria donde se encuentra. En java solamente se usa la referencia para referir a un objeto.','Distincion entre datos de referencia y primitivos',1),
	 ('Un operador lleva a cabo operaciones entre uno (operador unario), dos (operador binario) o tres (operador ternario) datos u operandos devolviendo un valor determinado.','Operadores (Understanding Java operators)',2),
	 ('Los operadores aritméticos permiten realizar operaciones matemáticas: + suma, - resta, / división, * multiplicación y % módulo (resto).','Operadores aritméticos (Arithmetic operators)',2),
	 ('Al realizar una operación entre valores que tienen diferentes tipos de datos, Java automáticamente promoverá uno de los valores al mayor de los dos tipos de datos. Si uno de los valores es int y el otro es float, promoverá el valor int al tipo de dato float. Los tipos de datos byte, short y char, se promueven a int incluso si ninguno de los operandos es de tipo entero. Después de que se haya producido la promoción el valor resultante tendrá el mismo tipo de dato que sus operandos promocionados.','Promoción numérica (Numeric promotion)',2),
	 ('El operador de complemento lógico,!, invierte el valor de una expresión booleana. Por ejemplo,si el valor es verdadero, se convertirá en falso y viceversa. Asimismo, el operador de negación, -, invierte el signo de una expresión numérica.Según la descripción, puede resultar obvio que algunos operadores requieren la variable o expresión sobre la que están actuando para que sea de un tipo específico. Por ejemplo, no puede aplicar un operador de negación, -, a una expresión booleana, ni se puede aplicar un complemento lógico operador,!, a una expresión numérica.','Operadores de complemento lógico y negación',2),
	 ('Se utilizan para asignar el valor de una expresión a una variable. Tiene una asociación de derecha a izquierda es decir, el valor dado en el lado derecho del operador se asigna a la variable de la izquierda y, por lo tanto, el valor del lado derecho debe declararse antes de usarlo o debe ser una constante. Los operandos deben ser de tipo primitivo. Los operadores son: \n = Asignación, \n += Suma y asignación, \n –= Resta y asignación, \n *= Producto y asignación, \n /= División y asignación, \n %= Resto de la división entera y asignación, \n <<= Desplazamiento a la izquierda y asignación, \n >>= Desplazamiento a la derecha y asignación, \n >>>=  Desplazamiento a la derecha y asignación rellenando con ceros, \n &= AND sobre bits y asignación, \n |= OR sobre bits y asignación, \n ^= XOR sobre bits y asignación.','Operadores de Asignacion (Assignment Operators)',2),
	 ('Existe una variación del operador de asignación en Java que implica la combinación de este con otros operadores. Esto permite realizar asignaciones de operaciones de una forma más compacta.Por ej: +=, -=, *=, /= y %=- Estos operadores solo se pueden usar cuando la variable a la que se le asigna el valor está implicada en la operación que se va a asignar. Si nos fijamos en los ejemplos, la forma extendida de a += b es a = a + b. En este ejemplo a se suma con b y el resultado de esta operación se asigna de nuevo a la variable a, reemplazando su valor original.Teniendo en cuenta esto, expresiones como esta: a = b + c no se podrían comprimir usando este operador, puesto que la variable a no está implicada en la operación que se asigna.','Operadores de asignación compuesta (Compound Assignment Operators)',2),
	 ('Los operadores relacionales comparan dos operandos y dan como resultado de la comparación verdadero ó falso. Los operandos tienen que ser de tipo primitivo:  < Menor que, > Mayor que, <= Menor o igual, >= Mayor o igual,!= Distinto, == Igual.','Operadores Relacionales (Relational Operators)',2),
	 ('Los operadores lógicos se utilizan con operandos de tipo boolean. Se utilizan para construir expresiones lógicas, cuyo resultado es de tipo true o false. Estos son: \n&&-AND. El resultado es verdadero si los dos operandos son verdaderos. El resultado es falso en caso contrario. Si el primer operando es falso no se evalúa el segundo, ya que el resultado será falso.\n||-OR. El resultado es falso si los dos operandos son falsos. Si uno es verdadero el resultado es verdadero. Si el primer operando es verdadero no se evalúa el segundo. \n!-NOT. Se aplica sobre un solo operando. Cambia el valor del operando de verdadero a falso y viceversa.','Operadores Logicos (Logical Operators)',2),
	 ('Determinar la igualdad en Java puede ser un esfuerzo no trivial ya que existe una diferencia semántica entre "dos objetos son iguales" y "dos objetos son equivalentes". Se complica aún más por el hecho de que para las primitivas numéricas y booleanas, no existe tal distinción.Comencemos con lo básico, el operador igual == y el operador no igual! =. Como el operadores relacionales, comparan dos operandos y devuelven un valor booleano sobre si las expresiones o valores son iguales o no iguales, respectivamente.','Operadores de Igualdad (Equality Operators)',2);
INSERT INTO java_coach_api.concepto (contenido,nombre,capitulo_id) VALUES
	 ('Una sentencia es la unidad mínima de ejecución de un programa. Un programa se compone de conjunto de sentencias que acaban resolviendo un problema. \nAl final de cada una de las sentencias encontraremos un punto y coma.','Sentencias',2),
	 ('Se usan para declarar variables','Sentencias de declaracion',2),
	 ('Los siguientes tipos de expresiones pueden ser hechas dentro de una sentencia terminando la expresión con punto y coma \n* Expresiones de asignación \n* Cualquier uso de los operadores ++ y -- \n* Llamada de métodos, \n* Expresiones de creación de objetos.','Sentencias de expresión',2),
	 ('Determinan el orden en que se ejecutarán las otras sentencias dentro del programa. \nEl lenguaje Java soporta varias sentencias de control de flujo, incluyendo \n* Toma de decisiones: if-else, switch-case \n* Bucles: for, while, do-while.','Sentencias de control de flujo',2),
	 ('Es un grupo de cero o más sentencias encerradas entre llaves ( { y } ). \nSe puede poner un bloque de sentencias en cualquier lugar en donde se pueda poner una sentencia individual.','Bloque de sentencias',2),
	 ('Es una estructura de control que nos permite ramificar nuestro código indicando qué se ejecuta si se cumple la condición (bloque if) y qué se ejecuta cuando no se cumple (bloque else).','Declaración if-else',2),
	 ('La clase String es una clase tan fundamental que sería difícil  escribir código sin eso.\nDespués de todo, ni siquiera puedes escribir un método main () sin usar la clase String.\nA cadena es básicamente una secuencia de caracteres,Por ahora, solo recuerde que la clase String es especial y no es necesario crear una instancia con new.','Creación y manipulación de cadenas',3),
	 ('Colocar una cadena antes de la otra cadena y combinarlas se llama cadena concatenación.\nEn la concatenacion de cadenas el operador + se puede utilizar de dos formas dentro de la misma línea de código.\n1. Si ambos operandos son numéricos, + significa suma numérica.\n2. Si alguno de los operandos es una cadena, + significa concatenación.','Concatenación',3),
	 ('Una vez que se crea un objeto String, no se le permite cambiar. No se puede hacer más grande o más pequeño, y no se puede cambiar uno de los caracteres que contiene. Puede pensar en una cuerda como una caja de almacenamiento que tiene perfectamente llena y cuyos lados no pueden abultarse. No hay forma de agregar objetos, ni puede reemplazar objetos sin alterar el arreglo completo. La compensación por un empaque óptimo es cero flexibilidad. Mutable es otra palabra para cambiante. Inmutable es lo opuesto: un objeto que no se puede cambiar una vez creado. En el examen OCA, debe saber que String es inmutable.','Inmutabilidad',3),
	 ('La clase String tiene docenas de métodos. Para todos estos métodos, debe recordar que una cadena es una secuencia de caracteres.','Métodos de cadena importantes',3);
INSERT INTO java_coach_api.concepto (contenido,nombre,capitulo_id) VALUES
	 ('El método length () devuelve el número de caracteres de la cadena. La firma del método es como sigue:longitud int ().\nEl método charAt () le permite consultar la cadena para averiguar qué carácter se encuentra en un punto específico index.\nLa firma del método es la siguiente:char charAt(int index).','length() Y charAt ()',3),
	 ('El método indexOf() busca el caracter dado como argumento y devuelve el primer índice que coincide con el valor deseado en una cadena, sino encuentra coincidencia entonces retorna -1. Este método puede tener como entrada un solo caracter (char) o una cadena completa (String). Y también puede buscar a partir de una posicion dada.\nA diferencia del método charAt(), no arroja una excepción sino encuentra coincidencia.\nPuede implementarse como alguna de las siguientes maneras: \nint indexOf(char ch)\nint indexOf(char ch, int indice)\nint indexOf(String cadena)\nint indexOf(String cadena, int indice).','indexOf()',3),
	 ('Este método puede extraer una subcadena de texto de una cadena más grande. El primer parámetro es el indice donde va a comenzar a extraer la cadena, recuerda que comenzaremos a contar la primera posición en 0. El segundo parámetro se refiere a la posición donde la extracción finaliza (sin ser incluida), siendo este opcional. Si este parametro es omitido por defecto retornaría el resto de la cadena. La sintaxis de este método puede ser alguna de las siguientes: \ncadenaARecortar.substring(int indiceInicial)\ncadenaARecortar.substring(int indiceInicial, int indiceFinal).','substring()',3),
	 ('Este método convierte todos los caracteres en minúscula. Se usa así: String.toLowerCase() donde String es nuestra cadena a modificar.','toLowerCase()',3),
	 ('Este método convierte todos los caracteres en mayúscula. Se usa así: String.toUpperCase() donde String es nuestra cadena a modificar.','toUpperCase()',3),
	 ('Este método chequea que dos String cualquiera contegan exactamente los mismos caracteres en el mismo orden. Devuelve un valor booleano.','equals()',3),
	 ('Este método chequea que dos String cualquiera contengan exactamente los mismos caracteres en el mismo orden pero sin distinguir minúsculas/mayúsculas. Devuelve un valor booleano.','equalsIgnoreCase()',3),
	 ('Los métodos startsWith() y endsWith() analizan si el valor proporcionado coincide parte de la cadena. Las firmas de método son las siguientes: boolean startsWith(String prefix) boolean endsWith(String suffix).','startsWith() and endsWith()',3),
	 ('El método contains() también busca coincidencias en String. No es tan particular como startsWith() y endsWith(): la coincidencia puede estar en cualquier parte de la cadena. Las firmas del metodo son las siguientes: boolean contains(String str).','contains()',3),
	 ('El método replace() realiza una búsqueda simple y reemplaza en la cadena. Hay una versión que toma parámetros char,así como una versión que toma parámetros CharSequence.Una CharSequence es una forma general de representar varias clases, incluidas String y StringBuilder.\nLas firmas del método son las siguientes: String replace (char oldChar, char newChar).\nString replace (CharSequence oldChar, CharSequence newChar).','replace()',3);
INSERT INTO java_coach_api.concepto (contenido,nombre,capitulo_id) VALUES
	 ('El método trim() El método trim( ) elimina los espacios en blanco en ambos extremos del string. Los espacios en blanco constan de espacios junto con \\t (tabulador) y \\n (nueva línea) caracteres. Otros caracteres, como \\r (retorno de carro), también se incluyen en lo que se recorta.\nLa firma del método es la siguiente:public String trim().','trim()',3),
	 ('Los nombres de los métodos sólo pueden contener letras, números, $ o _.  El primer carácter no puede ser un numero y las palabras reservadas no están permitidas./n El nombre del método debe ubicarse después del tipo de retorno.','Nombre de los métodos (Method Name)',4),
	 ('Si bien, la lista de parámetros es necesaria, no tiene por qué contener ningún parámetro. Es decir, que puede tener un par de paréntesis vacíos después del nombre del método./n Si tiene dos o más parámetros, estos se separan mediante una coma.','Lista de parámetros (Parameter List)',4),
	 ('La herencia es el proceso mediante el cual la nueva subclase secundaria incluye automáticamente cualquier primitivas, objetos o métodos públicos o protegidos definidos en la clase padre. nos referimos a cualquier clase que herede de otra clase como hijo.','Herencia',5),
	 ('Puede extender una clase agregando el nombre de la clase principal en la definición usando el extend la palabra clave.\nSe muestra la sintaxis de definir y extender una clase.\nUna clase pública por archivo, podemos crear dos archivos, Animal.java y Lion.java, en el que la clase Lion amplía la clase Animal.\nAsumiendo que están en el mismo paquete, no se requiere una declaración de importación en Lion.java para acceder a la clase Animal.\nObserve el uso de la palabra clave extensions en Lion.java para indicar que la clase Lion se extiende desde la clase Animal.','Extension de la clases',5),
	 ('Como se ve en el capítulo 4, podemos aplicar modificadores de acceso (public, private, protected, default) tanto a los métodos de la clase como a las variables. Probablemente no resulte una sorpresa que también puedan aplicarse estos modificadores de acceso a las definiciones de clase, ya que estuvimos añadiendo el modificador de acceso "public" a casi todas las clases. \nEl modificador de acceso "public" aplicado a una clase indica que ésta puede ser referenciada y usada en cualquier clase. El modifcador privado del paquete "default" - que indica la falta de cualquier modificador de acceso - indica que la clase puede ser accesidad sólo por una subclase u otra clase dentro del mismo paquete.\nComo saben, un archivo Java puede tener varias clases pero sólo una puede ser pública. De hecho, puede no tener ninguna clase pública. Una característica de usar el modificador privado del paquete "default" es que podemos definir varias clases dentro del mismo archivo Java.','Aplicar modificadores de acceso a las clases',5),
	 ('Una excepción es la forma en la que Java nos dice "Me rindo. No sé qué hacer en este momento. Tu lidia con esto". Cuando escribimos un método, podemos lidiar con la excepción manualmente o hacerla un problema del código de llamada. Como ejemplo, pensemos en Java como un niño que va al zoológico. El camino feliz será cuando nada sale mal. El niño mirará los animales hasta que el programa felizmente termine. Nada salió mal y no hubo excepciones con las que lidiar. La hermana menor del niño, sin embarno, no experimenta el camino feliz. En medio de la emoción se tropieza y cae. Por suerte, la caída no es muy mala. La niña se levanta y procede a continuar su visita por el zoo. Manejó el problema por su cuenta. Desafortunadamente, durante el día se cae de nuevo y comienza a llorar. Esta vez, manifestó que necesita ayuda mediante el llanto. La historia temrina bien: su papá le curó la rodilla y le dió un abrazo. Luego volvieron a ver los animales y disfrutar del resto del día. Estas son las dos maneras que usa Java cuando se enfrenta a excepciones. Un método puede manejar la excepción por sí mismo o hacerlo responsabilidad del código de llamada. Vimos ambos casos en nuestro paseo por el zoológico.','El rol de las excepciones',6),
	 ('Una excepción es un evento que altera el flujo del programa. Java tiene una superclase Throwable para todos los objetos que representan estos eventos. No todos tienen la palabra excepción en su nombre de clase, lo que puede resultar confuso.\nError significa que algo salió tan terriblemente mal que su programa no debería intentar recuperarse. Por ejemplo, la unidad de disco "desapareció". Estas son condiciones anormales que probablemente no encontrará.\nUna excepción de tiempo de ejecución se define como la clase RuntimeException y sus subclases. Las excepciones en tiempo de ejecución tienden a ser inesperadas pero no necesariamente fatales. Por ejemplo, el acceso a un índice de matriz no válido es inesperado. Las excepciones en tiempo de ejecución también se conocen como excepciones no comprobadas.\nUna excepción comprobada incluye Exception y todas las subclases que no extienden RuntimeException. Las excepciones comprobadas tienden a anticiparse más, por ejemplo, al intentar leer un archivo que no existe.\n¿Excepciones comprobadas? ¿Qué estamos comprobando? Java tiene una regla llamada manejar o declarar regla. Para las excepciones comprobadas, Java requiere que el código las maneje o las declare en la firma del método.','Entendiendo los tipos de excepciones',6);
 
 -- PREGUNTAS --
 INSERT INTO java_coach_api.pregunta (pregunta,concepto_id) VALUES
	 ('¿Qué son los atributos?',1),
	 ('¿Cuál es la forma correcta de crear un método?',2),
	 ('¿Con cuál de los siguientes caracteres puedo importar todas las clases de un paquete?',6),
	 ('¿Cuál de los siguientes ejemplos es considerado una declaración de importación redundante?',7),
	 ('¿Qué palabra clave se utiliza para declarar paquetes en Java?',9),
	 ('¿A qué tipo de operador corresponde la siguiente expresión?: int x= 2*(32-8)?',12),
	 ('¿Cuál de los siguientes ejemplos es un operador aritmético?',13),
	 ('¿Qué tipo de dato será x*y? int x=1\; long y=33\;',14),
	 ('¿Cuál es el límite ifs que puedo tener anidados?',24),
	 ('Elige el valor de x para que se ejecute el bloque else:\nif (x<4){\n\t...\n} else {\n\t...\n}',26);
INSERT INTO java_coach_api.pregunta (pregunta,concepto_id) VALUES
	 ('Según la siguiente cadena:\nString cadena="Estoy segura que Spiderman va a ser una gran película"\;\n¿Qué valor va a retornar el siguiente método cadena.indexOf("e", 5)?',32),
	 ('Si ejecuto el siguiente método que valor se va a visualizar por pantalla.\nString cadena = "Electroencefalografista"\;\nSystem.out.println(cadena.substring(0, 7))\;',33),
	 ('¿Qué método usarías para convertir todos los caracteres en minúscula?',34),
	 ('¿Qué se imprimirá por pantalla si se usa .toUpperCase() en "hola mundo"?',35),
	 ('¿Qué valor devuelve el método equals()?',36),
	 ('¿Cuál es el retorno?: "abc".equalsIgnoreCase("ABC")?',37),
	 ('¿Que tipo de parámetros usa la siguiente sentencia? String myString = "__x___x___x_x____xx_";\nchar oldChar = ''x''\;\nchar newChar = ''o''\;\nString newString = myString.replace(oldChar, newChar)\;// __o___o___o_o____oo_\;',40),
	 ('¿Para que se utiliza el método trim() en Java?',41),
	 ('¿Cuál de los siguientes ejemplos esta expresado correctamente?',42),
	 ('¿Cuál de los siguientes ejemplos es correcto?',43);
INSERT INTO java_coach_api.pregunta (pregunta,concepto_id) VALUES
	 ('Todas las clases heredan de...',44),
	 ('Es correcta? public class Animal extends Lion{\nprivate void roar() {\nSystem.out.println("The " + getAge() + " year old lion says: Roar!")\;\n}\n}',45),
	 ('¿Qué modificadores se añaden implícitamente a todos los métodos de una interface?',46),
	 ('¿Cuál de las siguientes afirmaciones es correcta?',48),
	 ('¿Cuál de los siguientes pares completa los espacios en blanco para compilar este código? (Elija todo lo que corresponda) 7: public void ohNo () _____ Exception {8: _____________ Exception (); 9:}',48);
 
 -- RESPUESTAS --
 INSERT INTO java_coach_api.respuesta (respuesta,valida) VALUES
	 ('Son las características que da la clase',1),
	 ('Son las operaciones que realiza la clase',0),
	 ('Son las instancias de una clase',0),
	 ('nombreClase(método){lista Parámetros}',0),
	 ('tipo-retorno nombreClase{Cuerpo del método}',0),
	 ('tipo-retorno nombreClase(lista parámetros){Cuerpo del método}',1),
	 ('\\n',0),
	 ('*',1),
	 ('()',0),
	 ('import java.util.Date\;',0);
INSERT INTO java_coach_api.respuesta (respuesta,valida) VALUES
	 ('import java.lang.System\;',1),
	 ('import java.util.Scanner\;',0),
	 ('package',1),
	 ('packages',0),
	 ('Ambas son correctas',0),
	 ('Operador ternario',0),
	 ('Operador binario',1),
	 ('Operador unario',0),
	 ('/',1),
	 ('\;',0);
INSERT INTO java_coach_api.respuesta (respuesta,valida) VALUES
	 ('0',0),
	 ('int',0),
	 ('long',1),
	 ('double',0),
	 ('8',0),
	 ('64',0),
	 ('No hay límite',1),
	 ('1',0),
	 ('4',1),
	 ('-1',0);
INSERT INTO java_coach_api.respuesta (respuesta,valida) VALUES
	 ('1',0),
	 ('7',1),
	 ('0',0),
	 ('"Electro"',1),
	 ('"Electroe"',0),
	 ('"encefalografista"',0),
	 ('.toLowerCamelCase()',0),
	 ('.toLowerCase()',1),
	 ('.toCaseLower()',0),
	 ('"HOLA MUNDO"',1);
INSERT INTO java_coach_api.respuesta (respuesta,valida) VALUES
	 ('"Hola mundo"',0),
	 ('"Hola Mundo"',0),
	 ('boolean',1),
	 ('int',0),
	 ('String',0),
	 ('true',1),
	 ('false',0),
	 ('ABC',0),
	 ('String',0),
	 ('char',1);
INSERT INTO java_coach_api.respuesta (respuesta,valida) VALUES
	 ('Ambas son correctas',0),
	 ('Eliminar espacios en blanco en ambos extremos de la cadena',1),
	 ('Reemplazar espacios de la cadena',0),
	 ('Eliminar espacios y caracteres especiales dentro de la cadena',0),
	 ('public void 2walk() { }',0),
	 ('public void walk2() { }',1),
	 ('public void walk4(int a\; int b) { }',0),
	 ('public void walk4(int a, int b) { }',1),
	 ('clase hija',0),
	 ('clase padre',0);
INSERT INTO java_coach_api.respuesta (respuesta,valida) VALUES
	 ('clase Object',1),
	 ('Falso',1),
	 ('Verdadero',0),
	 ('protected abstract',0),
	 ('public abstract',1),
	 ('public static',0),
	 ('public void',0),
	 ('private abstract',0),
	 ('default',0),
	 ('Las excepciones en tiempo de ejecución son lo mismo que las excepciones chequeadas',0);
INSERT INTO java_coach_api.respuesta (respuesta,valida) VALUES
	 ('Las excepciones en tiempo de ejecución son lo mismo que las excepciones no chequeadas',1),
	 ('Podés declarar únicamente excepciones chequeadas',0),
	 ('Podés declarar únicamente excepciones no chequeadas',0),
	 ('En la línea 7, complete el lanzamiento ',0),
	 ('En la línea 7, complete los lanzamientos',0),
	 ('En la línea 8, complete el lanzamiento',0),
	 ('En la línea 8, complete el lanzamiento nuevo',0),
	 ('En la línea 8, complete los lanzamientos',0),
	 ('En la línea 8, complete los lanzamientos nuevos',1);

 -- PREGUNTA_RESPUESTAS --
 INSERT INTO java_coach_api.pregunta_respuestas (pregunta_id,respuestas_id) VALUES
	 (1,1),
	 (1,2),
	 (1,3),
	 (2,4),
	 (2,5),
	 (2,6),
	 (3,7),
	 (3,8),
	 (3,9),
	 (4,10);
INSERT INTO java_coach_api.pregunta_respuestas (pregunta_id,respuestas_id) VALUES
	 (4,11),
	 (4,12),
	 (5,13),
	 (5,14),
	 (5,15),
	 (6,16),
	 (6,17),
	 (6,18),
	 (7,19),
	 (7,20);
INSERT INTO java_coach_api.pregunta_respuestas (pregunta_id,respuestas_id) VALUES
	 (7,21),
	 (8,22),
	 (8,23),
	 (8,24),
	 (9,25),
	 (9,26),
	 (9,27),
	 (10,28),
	 (10,29),
	 (10,30);
INSERT INTO java_coach_api.pregunta_respuestas (pregunta_id,respuestas_id) VALUES
	 (11,31),
	 (11,32),
	 (11,33),
	 (12,34),
	 (12,35),
	 (12,36),
	 (13,37),
	 (13,38),
	 (13,39),
	 (14,40);
INSERT INTO java_coach_api.pregunta_respuestas (pregunta_id,respuestas_id) VALUES
	 (14,41),
	 (14,42),
	 (15,43),
	 (15,44),
	 (15,45),
	 (16,46),
	 (16,47),
	 (16,48),
	 (17,49),
	 (17,50);
INSERT INTO java_coach_api.pregunta_respuestas (pregunta_id,respuestas_id) VALUES
	 (17,51),
	 (18,52),
	 (18,53),
	 (18,54),
	 (19,55),
	 (19,56),
	 (20,57),
	 (20,58),
	 (21,59),
	 (21,60);
INSERT INTO java_coach_api.pregunta_respuestas (pregunta_id,respuestas_id) VALUES
	 (21,61),
	 (22,62),
	 (22,63),
	 (23,64),
	 (23,65),
	 (23,66),
	 (23,67),
	 (23,68),
	 (23,69),
	 (24,70);
INSERT INTO java_coach_api.pregunta_respuestas (pregunta_id,respuestas_id) VALUES
	 (24,71),
	 (24,72),
	 (24,73),
	 (25,74),
	 (25,75),
	 (25,76),
	 (25,77),
	 (25,78),
	 (25,79);
	 
 -- EJEMPLOS --
 