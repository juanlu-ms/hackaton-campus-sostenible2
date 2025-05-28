# Repo erroneo

# Reto: Campus Sostenible – Aplicación Web para la Gestión Sostenible de Residuos
**Objetivo**: Desarrollar una aplicación web que permita visualizar, analizar y gestionar el estado de los contenedores inteligentes del campus, con el fin de optimizar la recogida de residuos y contribuir a una gestión más sostenible.

## 🚀 Cómo empezar
1. Haz un fork de este repositorio en tu cuenta de GitHub.
2. Clona tu fork en local y comienza el desarrollo desde ahí.
3. Utiliza el archivo containers.json incluido en /data y consulta la API externa con el token proporcionado.

> Puedes organizar el proyecto como prefieras, siempre que cumplas los requisitos funcionales

## 📌 Importante: Modifica este README
Este archivo README debe ser modificado para incluir instrucciones claras sobre cómo ejecutar tu proyecto. Asegúrate de documentar lo siguiente:

- Requisitos previos (versiones de Node.js, Python, Java, etc.)
- Cómo instalar las dependencias
- Cómo iniciar la aplicacion (frontend y/o backend)
- Cómo configurar variables de entorno (si es necesario)
- Cualquier otra información relevante para ejecutar y probar tu aplicación (p.ej. si utilizas docker)

## 📦 Material complementario
### 1. API rest de consulta del nivel de llenado de los contenedores
```
GET https://hackaton-campus-sostenible-api.mmartinez-d6a.workers.dev/containers/measurements
[
  {
    "id": "CONT-001",
    "history": [
	 {
        "timestamp": "2025-05-07T08:00:00Z",
        "levelPercent": 27
      },
      ...
    ]
  },
  ...
]
```

Las peticiones deben incluir la cabecera: `Authorization: Bearer campus-sostenible-2025`

> Esta API no incluye información de ubicación ni facultad. Esa información se entrega en un archivo .json aparte, que debe importarse y gestionarse desde tu backend.


### 2. Archivo de datos
Archivo data/containers.json con la información estática de cada contenedor

Campo      | Tipo    | Descripción
| -------- | ------- | -------------------------------------------------- |
id         | string  | Identificador único del contenedor (coincide con el ID usado por la API externa)
type       | string  | Tipo de residuo (paper, plastic, organic, glass, other)
center     | string  | Escuela o centro donde está ubicado el contenedor
location   | string  | Descripción la ubicación exacta dentro de la facultad
latitude   | number  | Latitud donde está situado el contenedor
longitude  | number  | Longitud donde está situado el contenedor
capacity   | number  | Capacidad total del contenedor (en la unidad especificada)
unit       | string  | Unidad de medida de la capacidad (liters, kg, etc.)
