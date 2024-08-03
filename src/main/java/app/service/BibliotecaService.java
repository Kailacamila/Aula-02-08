package app.service;

import app.entity.Biblioteca;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BibliotecaService {
    private List<Biblioteca> bibliotecas = new ArrayList<>();
    private AtomicLong counter = new AtomicLong();

    public List<Biblioteca> findAll() {
        return bibliotecas;
    }

    public Optional<Biblioteca> findById(Long id) {
        return bibliotecas.stream().filter(biblioteca -> biblioteca.getId().equals(id)).findFirst();
    }

    public Biblioteca save(Biblioteca biblioteca) {
        biblioteca.setId(counter.incrementAndGet());
        bibliotecas.add(biblioteca);
        return biblioteca;
    }

    public Optional<Biblioteca> update(Long id, Biblioteca bibliotecaDetails) {
        return findById(id).map(biblioteca -> {
            biblioteca.setNome(bibliotecaDetails.getNome());
            biblioteca.setEndereco(bibliotecaDetails.getEndereco());
            return biblioteca;
        });
    }

    public boolean deleteById(Long id) {
        return bibliotecas.removeIf(biblioteca -> biblioteca.getId().equals(id));
    }
}
