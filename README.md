📦 Envio de Pedidos - Spring Boot + Docker

Este proyecto implementa un servicio REST con Spring Boot que recibe pedidos en formato JSON, los transforma en XML compatible con SOAP, y los envía a un servicio externo. La aplicación está contenida en Docker y puede ejecutarse fácilmente localmente o en la nube.

🔧 Tecnologías utilizadas

Java 17

Spring Boot 3.x

Jakarta JAXB para transformar objetos Java a XML

WebClient (Spring Reactive) para enviar peticiones SOAP

Lombok para reducir código repetitivo

Docker para contenerización

🚀 Flujo de desarrollo

1. Creación del endpoint REST - PedidoController

El controlador expone una ruta POST en /api/pedidos que recibe un objeto PedidoRequestDTO desde el frontend o Postman.

@PostMapping
public ResponseEntity<PedidoResponseDTO> enviarPedido(@RequestBody PedidoRequestDTO request) {
    PedidoResponseDTO respuesta = pedidoService.procesarPedido(request);
    return ResponseEntity.ok(respuesta);
}

2. Servicio de negocio - PedidoService

Este servicio realiza tres tareas principales:

Transforma JSON a JAXB (XML)

Serializa a String y envía por WebClient al endpoint SOAP

Parsea la respuesta XML para devolver un DTO JSON

JAXBContext context = JAXBContext.newInstance(EnvioPedidoAcmeEnvelopeXml.class);
Marshaller marshaller = context.createMarshaller();
marshaller.marshal(objetoXml, stringWriter);

WebClient.create()
  .post()
  .uri(SOAP_ENDPOINT)
  .header("Content-Type", "text/xml;charset=UTF-8")
  .bodyValue(xmlString)
  .retrieve()
  .bodyToMono(String.class)
  .block();

3. Estructura de objetos JAXB (XML)

Los datos se transforman a XML usando clases como:

EnvioPedidoAcmeEnvelopeXml

EnvioPedidoAcmeBodyXml

EnvioPedidoAcmeXml

EnvioPedidoRequestXml

Todos estos objetos están anotados con JAXB y usan @XmlAccessorType(XmlAccessType.FIELD) para evitar conflictos con Lombok.

4. Respuesta final al cliente

El XML recibido se procesa con expresiones regulares (o parsing DOM en versiones futuras) para extraer:

{
  "enviarPedidoRespuesta": {
    "codigoEnvio": "80375472",
    "estado": "Entregado exitosamente al cliente"
  }
}

📢 Ejecución local con Docker

1. Generar el JAR:

mvn clean package

2. Construir imagen Docker:

docker build -t envio-pedidos .

3. Ejecutar contenedor:

docker run -p 8080:8080 envio-pedidos

📄 Ejemplo de prueba (Postman)

POST http://localhost:8080/api/pedidos

{
  "numPedido": "75630275",
  "cantidadPedido": "1",
  "codigoEAN": "00110000765191002104587",
  "nombreProducto": "Armario INVAL",
  "numDocumento": "1113987400",
  "direccion": "CR 72B 45 12 APT 301"
}

📁 Estructura del proyecto

src/
├── controller/       # PedidoController
├── service/          # PedidoService
├── dto/              # PedidoRequestDTO y PedidoResponseDTO
├── xml/              # JAXB (EnvelopeXml, BodyXml, etc.)
└── resources/
    └── application.properties

👨‍💼 Autor

Henry A. - 2025


