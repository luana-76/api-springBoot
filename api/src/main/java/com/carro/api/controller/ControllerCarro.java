package com.carro.api.controller;

import com.carro.api.model.Carro;
import com.carro.api.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
public class ControllerCarro {

    @Autowired
    private CarroService carroService;

    @GetMapping
    public List<Carro> listarTodos() {
        return carroService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> buscarPorId(@PathVariable Long id) {
        Optional<Carro> carro = carroService.buscarPorId(id);
        return carro.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Carro> criar(@RequestBody Carro carro) {
        Carro carroSalvo = carroService.salvar(carro);
        return ResponseEntity.status(HttpStatus.CREATED).body(carroSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carro> atualizar(@PathVariable Long id, @RequestBody Carro carro) {
        if (!carroService.buscarPorId(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        carro.setId(id);
        Carro carroAtualizado = carroService.salvar(carro);
        return ResponseEntity.ok(carroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!carroService.buscarPorId(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        carroService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
