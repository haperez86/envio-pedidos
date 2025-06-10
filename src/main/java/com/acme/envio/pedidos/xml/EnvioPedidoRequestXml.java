package com.acme.envio.pedidos.xml;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "EnvioPedidoRequest")
public class EnvioPedidoRequestXml {

    @XmlElement(name = "pedido")
    private String pedido;

    @XmlElement(name = "Cantidad")
    private String cantidad;

    @XmlElement(name = "EAN")
    private String ean;

    @XmlElement(name = "Producto")
    private String producto;

    @XmlElement(name = "Cedula")
    private String cedula;

    @XmlElement(name = "Direccion")
    private String direccion;
}