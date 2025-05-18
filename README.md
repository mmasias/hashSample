# Verificación de integridad en actas académicas

> *Una implementación en Java*

## ¿Por qué?

En la gestión académica actual existe un problema significativo de seguridad e integridad de datos. Las actas de calificación, documentos oficiales que registran los resultados académicos de los estudiantes, son susceptibles a alteraciones no autorizadas después de su emisión. El proceso manual de verificación resulta ineficiente y propenso a errores, mientras que los sistemas digitales básicos no proporcionan mecanismos adecuados para detectar manipulaciones. Esta vulnerabilidad podría permitir modificaciones fraudulentas en las calificaciones, comprometiendo la fiabilidad del sistema educativo.

La ausencia de un método de verificación robusto genera incertidumbre sobre la autenticidad de las actas, dificulta los procesos de auditoría y puede provocar consecuencias académicas y administrativas graves. Se necesita una solución que garantice la inmutabilidad de estos documentos oficiales una vez que han sido generados y cerrados.

## ¿Qué?

La solución propuesta consiste en un sistema de verificación de integridad basado en técnicas criptográficas simplificadas, aplicado al contexto de actas académicas. Este enfoque se fundamenta en el concepto de "hashing", un método que permite generar una firma digital única para un conjunto de datos, de manera que cualquier modificación posterior, por mínima que sea, resulte detectable.

El sistema captura una "instantánea" inmutable de todos los datos relevantes en el momento de cerrar y generar el acta: información de la asignatura, datos de los alumnos y sus calificaciones. Esta instantánea se asocia con un código hash generado a partir de estos datos, que actúa como huella digital única del documento. Cualquier alteración posterior en el contenido provocará una discrepancia entre el hash almacenado y el que se generaría con los datos modificados.

## ¿Para qué?

Este sistema resuelve múltiples problemas identificados en la gestión académica:

- **Garantiza la inmutabilidad de las actas**: Una vez generada y cerrada, cualquier intento de modificación es detectable mediante la verificación del hash.
- **Facilita los procesos de auditoría**: Permite comprobar rápidamente si un acta ha sido manipulada sin necesidad de revisiones manuales exhaustivas.
- **Aumenta la confianza en el sistema académico**: Estudiantes, profesores y administradores pueden verificar la autenticidad de las calificaciones oficiales.
- **Previene el fraude académico**: Desalienta los intentos de alteración de calificaciones al hacer evidentes las manipulaciones.
- **Simplifica la gestión documental**: Proporciona un mecanismo uniforme para verificar la integridad de documentos oficiales.

Esta implementación representa un paso significativo hacia sistemas académicos más seguros y confiables, aprovechando conceptos de programación orientada a objetos y técnicas criptográficas básicas.

## ¿Cómo?

La implementación se ha realizado con un diseño orientado a objetos en Java, estructurando el sistema en varias clases que representan el modelo de datos educativo. A continuación, se detalla el diseño y funcionamiento:

### Diseño del modelo de datos

El sistema se estructura en las siguientes clases principales:

<div align=center>

|||
|-|-|
|`public class Alumno`<br>`public class Evaluacion`<br>`public class Asignatura`<br>`public class Matricula`<br>`public class Acta`|![](/images/modelosUML/DdC.svg)

</div>

Acta encapsula todos los datos necesarios para generar un documento oficial, implementando un sistema de hash para verificar su integridad.

### Simulación de manipulación

Para demostrar la efectividad del sistema de verificación se ha creado una clase específica (`ActaHackeada`) que permite simular diferentes tipos de manipulación.

### Demostración del sistema

La clase `Simulacion` muestra el funcionamiento completo del sistema.

El sistema demuestra cómo:

1. Se genera un acta oficial cuando se cierra una asignatura
1. Se verifica automáticamente la integridad del acta
1. Cualquier manipulación es detectable al comparar el hash original con el recalculado
1. Incluso técnicas avanzadas como la falsificación del hash pueden ser identificadas mediante comparaciones externas

### Consideraciones adicionales

Este sistema representa una implementación básica del concepto de verificación de integridad. En entornos de producción, debe complementarse con:

- Algoritmos de hash criptográficamente seguros como SHA-256
- Almacenamiento seguro de los hashes originales
- Sistemas de firmas digitales con infraestructura de clave pública
- Mecanismos de control de acceso robustos

## ¿Y ahora qué?

Se recomienda explorar:

1. **Implementación de algoritmos de hash avanzados**: Sustituir el algoritmo manual por implementaciones estándar como SHA-256 o SHA-3.

1. **Firmas digitales con criptografía asimétrica**: Incorporar sistemas de firma usando pares de claves pública/privada para añadir autenticación además de verificación de integridad.

1. **Blockchain educativo**: Expandir el concepto para crear una cadena de bloques donde cada acta sea un bloque enlazado criptográficamente con los anteriores, proporcionando inmutabilidad a nivel de sistema.

1. **Certificados digitales**: Integrar con sistemas de certificados X.509 para validar la identidad de quienes generan las actas.

1. **Interfaz de verificación**: Desarrollar una herramienta que permita a estudiantes y administradores verificar fácilmente la autenticidad de cualquier acta.

El sistema presentado constituye un primer paso hacia la creación de registros académicos digitales confiables y seguros, demostrando conceptos fundamentales de programación orientada a objetos, estructuras de datos y principios básicos de criptografía aplicada.
