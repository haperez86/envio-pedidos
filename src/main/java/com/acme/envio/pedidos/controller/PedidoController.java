package com.acme.envio.pedidos.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.acme.envio.pedidos.dto.PedidoRequestDTO;
import com.acme.envio.pedidos.dto.PedidoResponseDTO;
import com.acme.envio.pedidos.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> enviarPedido(@RequestBody PedidoRequestDTO request) {
        try {
            PedidoResponseDTO respuesta = pedidoService.procesarPedido(request);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
