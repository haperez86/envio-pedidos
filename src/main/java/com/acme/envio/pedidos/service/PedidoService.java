package com.acme.envio.pedidos.service;

import java.io.StringWriter;
import java.util.Collections;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.acme.envio.pedidos.dto.PedidoRequestDTO;
import com.acme.envio.pedidos.dto.PedidoResponseDTO;
import com.acme.envio.pedidos.xml.EnvioPedidoAcmeBodyXml;
import com.acme.envio.pedidos.xml.EnvioPedidoAcmeEnvelopeXml;
import com.acme.envio.pedidos.xml.EnvioPedidoAcmeXml;
import com.acme.envio.pedidos.xml.EnvioPedidoRequestXml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PedidoService {

    private static final String SOAP_ENDPOINT = "http://localhost:8080/mock-soap";

    public PedidoResponseDTO procesarPedido(PedidoRequestDTO request) throws Exception {

        // 1. Convertir JSON DTO a JAXB XML object
        EnvioPedidoRequestXml xmlRequest = new EnvioPedidoRequestXml();
        xmlRequest.setPedido(request.getNumPedido());
        xmlRequest.setCantidad(request.getCantidadPedido());
        xmlRequest.setEan(request.getCodigoEAN());
        xmlRequest.setProducto(request.getNombreProducto());
        xmlRequest.setCedula(request.getNumDocumento());
        xmlRequest.setDireccion(request.getDireccion());

        EnvioPedidoAcmeXml acmeXml = new EnvioPedidoAcmeXml();
        acmeXml.setEnvioPedidoRequest(Collections.singletonList(xmlRequest));

        EnvioPedidoAcmeBodyXml body = new EnvioPedidoAcmeBodyXml();
        body.setEnvioPedidoAcme(acmeXml);

        EnvioPedidoAcmeEnvelopeXml envelope = new EnvioPedidoAcmeEnvelopeXml();
        envelope.setBody(body);

        // 2. Marshall: JAXB object → XML String
        JAXBContext jaxbContext = JAXBContext.newInstance(EnvioPedidoAcmeEnvelopeXml.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter writer = new StringWriter();
        marshaller.marshal(envelope, writer);
        String xmlString = writer.toString();

        log.info("XML enviado:\n{}", xmlString);

        // 3. Enviar XML a endpoint SOAP usando WebClient
        WebClient client = WebClient.create();
        String xmlResponse = client.post()
                .uri(SOAP_ENDPOINT)
                .header("Content-Type", "text/xml;charset=UTF-8")
                .bodyValue(xmlString)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.info("XML recibido:\n{}", xmlResponse);

        // 4. Extraer manualmente la respuesta XML → JSON
        // Simplificación: parseo manual porque la respuesta es fija
        String codigo = xmlResponse.replaceAll("(?s).*<Codigo>(.*?)</Codigo>.*", "$1");
        String mensaje = xmlResponse.replaceAll("(?s).*<Mensaje>(.*?)</Mensaje>.*", "$1");

        PedidoResponseDTO.EnviarPedidoRespuesta respuestaInterna = new PedidoResponseDTO.EnviarPedidoRespuesta();
        respuestaInterna.setCodigoEnvio(codigo);
        respuestaInterna.setEstado(mensaje);

        PedidoResponseDTO responseDTO = new PedidoResponseDTO();
        responseDTO.setEnviarPedidoRespuesta(respuestaInterna);

        return responseDTO;

    }
    
}
