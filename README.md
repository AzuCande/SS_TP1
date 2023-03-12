# SS_TP1 - Cell Index Method

## Integrantes
* [Kim, Azul Candelaria](https://github.com/AzuCande)
* [Larroudé Álvarez, Santiago](https://github.com/SSanti32)
* [Vasquéz Currie, Malena](https://github.com/malevasquez)

## Objetivo
El objetivo de este proyecto es implementar el algoritmo Cell Index Method (CIM) para la detección de vecinos de cada partícula.

## Requisitos
* Java 8+
* Maven 3.6.3+
* Python 3.7+
* Matplotlib 3.3.2+
* Numpy 1.19.2+

## Parámetros
Para correr el proyecto, incluir los siguientes parámetros en VM options:
* `-Dperiodic=value` donde `value` es un valor de tipo `boolean` para indicar si es periódico o no.
* `-DmatrixSize=value` donde `value` es un valor de tipo `int` para indicar el tamaño de la matriz.
* `-DstaticFile=filename` donde `filename` es el nombre del archivo de entrada de tipo estático. Se debe colocar el archivo dentro de la carpeta `src/main/resources`.
* `-DdynamicFile=filename` donde `filename` es el nombre del archivo de entrada de tipo dinámico. Se debe colocar el archivo dentro de la carpeta `src/main/resources`.

Estos parámetros tienen valores por defecto:
* `periodic` es `false`
* `matrixSize` es `2`
* `staticFile` es `staticInput.txt`
* `dynamicFile` es `dynamicInput.txt`

## Ejecución del análisis de datos
Para poder obtener el gráfico de los resultados de la simulación, ubicarse en el directorio `src/main/python` y ejecutar:
```bash
> python ./grapher.py
```
En macOS:
```bash
> python3 ./grapher.py
```