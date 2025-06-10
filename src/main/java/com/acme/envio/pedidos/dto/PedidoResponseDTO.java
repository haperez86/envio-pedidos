package com.acme.envio.pedidos.dto;

import lombok.Data;

@Data
public class PedidoResponseDTO {
    private EnviarPedidoRespuesta enviarPedidoRespuesta;

    @Data
    public static class EnviarPedidoRespuesta {
        private String codigoEnvio;
        private String estado;
    }
}
