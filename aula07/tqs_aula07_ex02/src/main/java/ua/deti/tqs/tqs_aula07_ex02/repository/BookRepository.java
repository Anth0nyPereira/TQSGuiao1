package ua.deti.tqs.tqs_aula07_ex02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.deti.tqs.tqs_aula07_ex02.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}