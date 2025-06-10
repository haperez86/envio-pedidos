package com.acme.envio.pedidos.dto;

import lombok.Data;

@Data
public class PedidoRequestDTO {
    private String numPedido;
    private String cantidadPedido;
    private String codigoEAN;
    private String nombreProducto;
    private String numDocumento;
    private String direccion;
}
