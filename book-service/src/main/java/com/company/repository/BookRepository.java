package com.company.repository;

import com.company.constants.Category;
import com.company.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    @Query(value = "Select * from book b where " +
            "(b.title like %?1% OR CAST(b.id as CHAR) like %?1% OR LOWER(b.author) like %?1%) " +
            "AND b.category=?2",
            nativeQuery = true)
    List<Book> findAllBookByCategoryAndKeyword(String keyword, int category);

    boolean existsByTitleAndCategory(String title, Category category);

    Optional<Book> findByUuid(String uuid);
}
