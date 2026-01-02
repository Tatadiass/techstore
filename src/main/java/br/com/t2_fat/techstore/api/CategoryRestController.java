package br.com.t2_fat.techstore.api;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.t2_fat.techstore.domain.Category;
import br.com.t2_fat.techstore.repository.CategoryRepository;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {
    private final CategoryRepository repo;
    public CategoryRestController(CategoryRepository repo){

    this.repo = repo;
 }
//GET /api/category ->lista todos
@GetMapping
public List<Category> getAll() {
 return repo.findAll();
}
//GET /api/category/{id}  -> busca por ID
@GetMapping("/{id}")
public ResponseEntity<Category> getById(
 @PathVariable Long id
) {
 return repo.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
}
//POST /api/category -> cria novo
@PostMapping
public ResponseEntity<Category> create(@RequestBody Category category) {
 Category saved = repo.save(category);
 return ResponseEntity.ok(saved);
}
@SuppressWarnings("unused")
@PutMapping("/{id}")
public ResponseEntity<Category> update(@PathVariable Long id,@RequestBody Category category) {

 return repo.findById(id).map(existing -> {
 existing.setName(category.getName());
 Category updated = repo.save(existing);
 return ResponseEntity.ok(repo.save(existing));
 }).orElse(ResponseEntity.notFound().build());
}

//DELETE /api/category/{id} -> apaga
@DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id) {
if (!repo.existsById(id)) {
 return ResponseEntity.notFound().build();
 }
  repo.deleteById(id);
 return ResponseEntity.noContent().build();
}

}

