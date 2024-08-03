package app.service;


import app.entity.Editora;
import app.entity.Livro;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EditoraService {
    private List<Editora> editoras = new ArrayList<>();
    private AtomicLong counter = new AtomicLong();

    public List<Editora> findAll() {
        return editoras;
    }

    public Optional<Editora> findById(Long id) {
        return editoras.stream().filter(editora -> editora.getId().equals(id)).findFirst();
    }

    public Editora save(Editora editora) {
        editora.setId(counter.incrementAndGet());
        editoras.add(editora);
        return editora;
    }

    public Optional<Editora> update(Long id, Editora editoraDetails) {
        return findById(id).map(editora -> {
            editora.setNome(editoraDetails.getNome());
            editora.setEndereco(editoraDetails.getEndereco());
            return editora;
        });
    }

    public boolean deleteById(Long id) {
        return editoras.removeIf(editora -> editora.getId().equals(id));
    }

}
