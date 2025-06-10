package com.acme.envio.pedidos.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mock-soap")
public class MockSoapController {

    @PostMapping
    public ResponseEntity<String> mockSoap(@RequestBody String xml) {
        String mockResponse = """
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:env="http://WSDLs/EnvioPedidos/EnvioPedidosAcme">
            <soapenv:Header/>
            <soapenv:Body>
                <env:EnvioPedidoAcmeResponse>
                    <EnvioPedidoResponse>
                        <Codigo>80375472</Codigo>
                        <Mensaje>Entregado exitosamente al cliente</Mensaje>
                    </EnvioPedidoResponse>
                </env:EnvioPedidoAcmeResponse>
            </soapenv:Body>
        </soapenv:Envelope>
        """;
        return ResponseEntity.ok()
            .contentType(MediaType.TEXT_XML)
            .body(mockResponse);
    }
}