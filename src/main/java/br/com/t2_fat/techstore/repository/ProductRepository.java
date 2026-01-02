package br.com.t2_fat.techstore.repository;

import br.com.t2_fat.techstore.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}