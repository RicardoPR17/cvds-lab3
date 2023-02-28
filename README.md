# Laboratorio 3 - PBT
## CVDS 2023-1
#### Integrantes
+ Jessica Muñoz
+ Ricardo Pulido
## Desarrollo
### CREAR EL PROYECTO EN MAVEN
Se crea el proyecto con el arquetipo indicado en la guía del laboratorio con el comando `mvn archetype:generate -Dfilter=maven-archetype-quickstart` donde fuimos
ingresando los valores que solicitaban acorde al enunciado del laboratorio, los demás se asignaron con la opción por defecto.

Al terminar este proceso, accedemos con el comando `cd` al directorio ClasesEquivalencia que fue generado con Maven.

![Creación del proyecto con Maven](https://user-images.githubusercontent.com/123812772/221957550-bbcae0c8-48e7-4094-9fc8-83d15158daf1.png)

Ahora, ingresamos al archivo pom.xml con cualquier editor de texto, en este caso usamos notepad, y modificamos las dependencias para usar la versión 8 de Java.

![image](https://user-images.githubusercontent.com/123812772/221958190-37a7ff32-1a0a-4477-a5ee-892c04540c78.png)

Hecho esto, vamos a compilar nuestro proyecto de Maven. Para esto se usa el comando `mvn package` lo cual nos mostró que todo se compiló correctamente

![Comando mvn package](https://user-images.githubusercontent.com/123812772/221958426-2175fcaf-0a9f-4b25-8778-e8be2574f3e2.png)

Para correr solamente las pruebas que se encuentras en el proyecto, se usa el comando `mvn test`. Este nos mostrará la cantidad de pruebas corridas, exitosas y
fallidas, además de mostrarnos el nombre de las pruebas. En este caso, veremos que se corrió la prueba de AppTest.

![Comando mvn test](https://user-images.githubusercontent.com/123812772/221958841-dcb2d0cd-5de8-4174-ae30-ed77ce0917b4.png)

### EJERCICIO "REGISTRADURÍA"
Sobre el proyecto que hemos creado, accedemos desde la carpeta *ClasesEquivalencia\src\main\java\edu\eci\cvds\tdd* y creamos una nueva carpeta llamada *registry*.
En ella se crean los archivos *RegisterResult.java* y *Gender.java* para crear las enumeraciones respectivas, además de las clases *Person.java* y *Registry.java*.
El código de cada una se añade tal como se presentó en la guía del laboratorio
+ **RegisterResult.java**
```
package edu.eci.cvds.tdd.registry;

public enum RegisterResult {
    DEAD, UNDERAGE, INVALID_AGE, VALID, DUPLICATED
}
```
+ **Gender.java**
```
package edu.eci.cvds.tdd.registry;

public enum Gender {
    MALE, FEMALE, UNIDENTIFIED;
}
```
+ **Person.java**
```
package edu.eci.cvds.tdd.registry;

/**
 * Person representation Class
 */
public class Person {
    /**
     * Person's name
     */
    private String name;
    /**
     * A person's identification number
     */
    private int id;
    /**
     * Person's age
     */
    private int age;
    /**
     * Person's gender
     */
    private Gender gender;
    /**
     * 
     * Flag to specify if a person is alive
     */
    private boolean alive;

    /**
     * The class' default constructor
     */
    public Person() {
        super();
    }

    /**
     * A person constructor with all the information
     *
     * @param name   the name
     * @param id     the identification number
     * @param age    the age
     * @param gender the gender
     * @param alive  if the person is alive
     */
    public Person(String name, int id, int age, Gender gender, boolean alive) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.alive = alive;
    }

    /**
     * Returns the person name
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the person identification number *
     * 
     * @return the identification Number
     */
    public int getId() {
        return id;
    }

    /**
     * Returns this person's age
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns the gender
     *
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Returns if the person is alive *
     * 
     * @return the alive
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Sets the person name
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the person identification number *
     * 
     * @param id the identification Number to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the person age
     *
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Sets the person gender
     *
     * @param gender the gender to set
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Sets the flag to specify if this person is alive
     *
     * @param alive the alive to set
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * @{inheritdoc}
     */
    @Override
    public String toString() {
        return "Person [name=" + name + ", id=" + id + ", age=" + age + ", gender=" + gender + ", alive=" + alive + "]";
    }
}
```
+ **Registry.java**
```
package edu.eci.cvds.tdd.registry;

public class Registry {
    public RegisterResult registerVoter(Person p) {
        // TODO Validate person and return real result.
        return RegisterResult.VALID;
    }
}
```

Adicional a esto, se crea en la ruta *ClasesEquivalencia\src\test\java\edu\eci\cvds\tdd* el directorio registry. Esto se hace con el fin de organizar los códigos
que prueban las clases que se diseñaron. En este caso, vamos a crear un archivo de pruebas llamado *RegistryTest.java* donde se verificará el cumplimiento de las
características dadas para el método de `registerVoter`. Este archivo de prueba es el siguiente:

```
package edu.eci.cvds.tdd.registry;

import org.junit.Assert;
import org.junit.Test;

public class RegistryTest {
    private Registry registry = new Registry();

    @Test
    public void validateRegistryResult() {
        Person person = new Person();
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.VALID, result);
    }
    // TODO Complete with more test cases
}
```

Si ejecutamos los comandos de `mvn package` y `mvn test`, veremos que las pruebas corren de forma satisfactoria. La diferencia entre uno y otro es que el comando de
`mvn test` está enfocado directamente en la ejecución de las pruebas sobre el código ya compilado, sobre los cambios que se hagan al archivo de pruebas y no requiere
de compilar la totalidad del proyecto nuevamente. En él encontramos datos de cada prueba ejecutada, donde si falla nos muestra el valor esperado y el recibido según
el diseño de la prueba unitaria, nos muestra el tiempo de ejecución, fecha y hora de la finalización de la totalidad de las pruebas.

El comando de `mvn test` es especial para hacer el análisis adecuado de los resultados de nuestros test y poder actuar en consecuencia ya sea refactorizando el código
o cambiando el diseño de la prueba según sea el caso.

### FINALIZAR EL EJERCICIO
Los casos de equivalencia contemplados fueron:
* Persona viva o muerta
* La edad de la persona, válida si tiene 18 años o más
* Si la edad es negativa, sería una edad inválida
* Si es menor a 18 años pero mayor a 0, sería una edad menor a la válida
* Para los duplicados, se revisa que esa identificación no se haya registrado en un voto anterior

Respecto a los parámetros que presenta la clase `Person` y los casos de equivalencia mencionados antes, se diseñan las pruebas unitarias
* validateRegistryResult
```
    @Test
    public void validateRegistryResult() {
        Person person = new Person("Pepe", 123, 20, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.VALID, result);
    }
```
Esta prueba es la que traía por defecto, se modificaron los parámetros de la instancia de la persona para que esta fuera válida.

* registerVoter_PersonaInvaldiaMuerta_VotoNoValido
```
    @Test
    public void registerVoter_PersonaInvalidaMuerta_VotoNoValido() {
        Person person = new Person("Pipo", 124, 25, Gender.MALE, false);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.DEAD, result);
    }
```
En esta prueba se evalúa el caso de equivalencia de si la persona se encuentra viva o muerta, verificado con el parámetro *alive* de la instancia de persona.

* registerVoter_PersonaInvalidaMenor_VotoNoValido
```
    @Test
    public void registerVoter_PersonaInvalidaMenor_VotoNoValido() {
        Person person = new Person("Juan", 125, 10, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.UNDERAGE, result);
    }
```
En esta prueba probamos el caso de equivalencia de que sea una persona viva con una edad entre 0 y 17 años, es decir un menor de edad.

* registerVoter_PersonaInvalidaEdadIncorrecta_VotoNoValido
```
    @Test
    public void registerVoter_PersonaInvalidaEdadIncorrecta_VotoNoValido() {
        Person person = new Person("Maria", 126, -1, Gender.FEMALE, true);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.INVALID_AGE, result);
    }
```
En esta prueba probamos el caso de edad inválida puesto que la edad de una persona no puede ser negativa.

* registerVoter_PersonaInvalidaDuplicada_VotoNoValido
```
    @Test
    public void registerVoter_PersonaInvalidaIdDuplicada_VotoNoValido() {
        Person person = new Person("Pepe", 123, 20, Gender.MALE, true);
        Person person2 = new Person("Juana", 123, 24, Gender.FEMALE, true);
        registry.registerVoter(person);
        RegisterResult result = registry.registerVoter(person2);
        Assert.assertEquals(RegisterResult.DUPLICATED, result);
    }
```
Es esta prueba se verifica el caso de equivaliencia de los duplicados mediante el registro de las identificaciones de los votantes.

* registerVoter_PersonaValida18_VotoValido
```
    @Test
    public void registerVoter_PersonaValida18_VotoValido() {
        Person person = new Person("Santiago", 127, 18, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.VALID, result);
    }
```
Esta prueba revisa el caso de equivalencia de la edad válida tomando el límite inferior que es de 18 años.

Teniendo en cuenta el diseño de pruebas y casos presentados anteriormente, se muestra la implementación del método *registerVoter* de la clase `Registry` que cumple
que cumple con las propiedades solicitadas para esta clase
```
package edu.eci.cvds.tdd.registry;

import java.util.*;

public class Registry {
    private ArrayList<Integer> identificaciones = new ArrayList<>();
    public RegisterResult registerVoter(Person p) {
        if (p.isAlive()) {
            if (p.getAge() <= 0) {
                return RegisterResult.INVALID_AGE;
            } else if (p.getAge() >= 18) {
                if (identificaciones.contains(p.getId())) {
                    return RegisterResult.DUPLICATED;
                } else {
                    identificaciones.add(p.getId());
                    return RegisterResult.VALID;
                }
            }
            return RegisterResult.UNDERAGE;
        }
        return RegisterResult.DEAD;
    }
}
```

Ahora, al ejecutar las pruebas con el comando `mvn test` se obtiene el siguiente resultado

![Pruebas finalizadas](https://user-images.githubusercontent.com/123812772/221983395-4de81f0c-9aec-4ba9-b87f-84239855e30b.png)


## Bibliografía
+ Mktong. (5 de noviembre del 2018). *How to run unit test with Maven*. Recuperado de: <https://mkyong.com/maven/how-to-run-unit-test-with-maven/>
