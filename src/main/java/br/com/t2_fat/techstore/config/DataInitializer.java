package br.com.t2_fat.techstore.config;

import br.com.t2_fat.techstore.domain.Category;
import br.com.t2_fat.techstore.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public DataInitializer(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {
        if (categoryRepository.count() == 0) {
            categoryRepository.save(Category.builder().name("Livros").build());
            categoryRepository.save(Category.builder().name("Eletrônicos").build());
            categoryRepository.save(Category.builder().name("Acessórios").build());
        }
    }
}