# Conversor de Monedas Alura

Hola, este es mi proyecto de "Conversor de Monedas" en Java propuesto por Alura Latam en Oracle Next Education.

El programa permite hacer la conversión entre las siguientes monedas:

- USD (Dólares)
- COP (Pesos Colombianos)
- CLP (Pesos Chilenos)
- MXN (Pesos Mexicanos)
- EUR (Euros)

El programa durante la ejecución usará la información que ha consultado por primera vez, ahorrando hacer muchas consultas a la API de <b>ExchangeRate</b>. 
Si se desea actualizar la tasa de cambio de las monedas, se debe volver a ejecutar el programa.

De igual manera, se ha implementado el patrón de diseño <b>Singleton</b> para evitar multiples instancias tanto de la obtención de la llave de la API como de la consulta hacia esta.

El usuario tiene la opción de elegir como prefiere ingresar decimales según su preferencia, sean puntos o comas en un inicio del programa.

Para obtener una llave de la API de <b>ExchangeRate</b>, lo puede hacer mediante el siguiente enlace: [ExchangeRate API](https://www.exchangerate-api.com)

Para poder usar la llave en el programa, el usuario debe crear un archivo con nombre <b>".env"</b> en el directorio del proyecto (idealmente al mismo nivel del README). Dentro del <b>".env"</b> debe colocar lo siguiente

```
API_KEY=REEMPLAZA_AQUÍ_CON_TU_LLAVE_DE_LA_API
```
