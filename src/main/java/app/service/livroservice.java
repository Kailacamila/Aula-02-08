package app.service;

import com.exemplo.livroscrud.model.Livro;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service

public class livroservice {
	 private List<Livro> livros = new ArrayList<>();
	    private AtomicLong counter = new AtomicLong();

	    public List<Livro> findAll() {
	        return livros;
	    }

	    public Optional<Livro> findById(Long id) {
	        return livros.stream().filter(livro -> livro.getId().equals(id)).findFirst();
	    }

	    public Livro save(Livro livro) {
	        livro.setId(counter.incrementAndGet());
	        livros.add(livro);
	        return livro;
	    }

	    public Optional<Livro> update(Long id, Livro livroDetails) {
	        return findById(id).map(livro -> {
	            livro.setTitulo(livroDetails.getTitulo());
	            livro.setAutor(livroDetails.getAutor());
	            livro.setEditora(livroDetails.getEditora());
	            livro.setAno(livroDetails.getAno());
	            return livro;
	        });
	    }

	    public boolean deleteById(Long id) {
	        return livros.removeIf(livro -> livro.getId().equals(id));
	    }

}
