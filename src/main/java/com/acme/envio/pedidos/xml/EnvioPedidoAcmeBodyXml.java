package com.acme.envio.pedidos.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "envioPedidoAcme" })
public class EnvioPedidoAcmeBodyXml {

    @XmlElement(name = "EnvioPedidoAcme", namespace = "http://WSDLs/EnvioPedidos/EnvioPedidosAcme")
    private EnvioPedidoAcmeXml envioPedidoAcme;
}
