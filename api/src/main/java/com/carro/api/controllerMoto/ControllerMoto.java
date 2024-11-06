package com.carro.api.controllermoto;

import com.carro.api.model.Moto;
import com.carro.api.mService.MotooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/motos")
public class ControllerMoto {

    @Autowired
    private MotooService motoService;

    @GetMapping
    public List<Moto> listarTodos() {
        return motoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> buscarPorId(@PathVariable Long id) {
        Optional<Moto> moto = motoService.buscarPorId(id);
        return moto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Moto> criar(@RequestBody Moto moto) {
        Moto motoSalvo = motoService.salvar(moto);
        return ResponseEntity.status(HttpStatus.CREATED).body(motoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Moto> atualizar(@PathVariable Long id, @RequestBody Moto moto) {
        if (!motoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        moto.setId(id);
        Moto motoAtualizado = motoService.salvar(moto);
        return ResponseEntity.ok(motoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!motoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        motoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
