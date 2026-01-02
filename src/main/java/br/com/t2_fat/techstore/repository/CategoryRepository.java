package br.com.t2_fat.techstore.repository;

import br.com.t2_fat.techstore.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}