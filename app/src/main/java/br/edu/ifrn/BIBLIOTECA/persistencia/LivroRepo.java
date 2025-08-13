package br.edu.ifrn.biblioteca.persistencia.repositorio;

import br.edu.ifrn.biblioteca.modelo.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LivroRepo extends JpaRepository<Livro, Long> {
    Optional<Livro> findByIsbn(String isbn);
}