package com.acme.envio.pedidos.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;

import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "EnvioPedidoAcme", namespace = "http://WSDLs/EnvioPedidos/EnvioPedidosAcme")
@XmlType(name = "", propOrder = { "envioPedidoRequest" })
public class EnvioPedidoAcmeXml {

    @XmlElement(name = "EnvioPedidoRequest")
    private List<EnvioPedidoRequestXml> envioPedidoRequest;
}
