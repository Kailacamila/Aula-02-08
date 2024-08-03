package app.controller;

import app.entity.Editora;
import app.service.EditoraService;
import app.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/editoras")
public class EditoraController {

    @Autowired
private EditoraService editoraService;

    @GetMapping
    public List<Editora> getAllLivros() {
        return editoraService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Editora> getEditoraById(@PathVariable Long id) {
        Optional<Editora> editora = editoraService.findById(id);
        return editora.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Editora createEditora(@RequestBody Editora editora) {
        return editoraService.save(editora);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Editora> updateEditora(@PathVariable Long id, @RequestBody Editora editoraDetails) {
        Optional<Editora> editora = editoraService.update(id, editoraDetails);
        return editora.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivro(@PathVariable Long id) {
        if (editoraService.deleteById(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
