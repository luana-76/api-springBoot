package com.carro.api.mService;

import com.carro.api.model.Moto;
import com.carro.api.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotooService {

    @Autowired
    private MotoRepository motoRepository;

    public List<Moto> listarTodos() {
        return motoRepository.findAll();
    }

    public Optional<Moto> buscarPorId(Long id) {
        return motoRepository.findById(id);
    }

    public Moto salvar(Moto moto) {
        return motoRepository.save(moto);
    }

    public void excluir(Long id) {
        motoRepository.deleteById(id);
    }
}
