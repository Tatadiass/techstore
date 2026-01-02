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

import br.com.t2_fat.techstore.domain.Product;
import br.com.t2_fat.techstore.repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    private final ProductRepository repo;
    public ProductRestController(ProductRepository repo){

    this.repo = repo;
 }
//GET /api/products ->lista todos
@GetMapping
public List<Product> getAll() {
 return repo.findAll();
}
//GET /api/products/{id}  -> busca por ID
@GetMapping("/{id}")
public ResponseEntity<Product> getById(
 @PathVariable Long id
) {
 return repo.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
}
//POST /api/products -> c
@PostMapping
public ResponseEntity<Product> create(
 @RequestBody Product p
) {
 Product saved = repo.save(p);
 return ResponseEntity.ok(saved);
}
@SuppressWarnings("unused")
@PutMapping("/{id}")
public ResponseEntity<Product> update(
 @PathVariable Long id,
 @RequestBody Product p
) {
 return repo.findById(id).map(existing -> {
 existing.setName(p.getName());
 existing.setDescription(p.getDescription());
 existing.setPrice(p.getPrice());
 Product updated = repo.save(existing);
 return ResponseEntity.ok(repo.save(existing));
 }).orElse(ResponseEntity.notFound().build());
}

//DELETE /api/products/{id} -> apaga
@DeleteMapping("/{id}")
public ResponseEntity<Void> delete(
 @PathVariable Long id
) {
if (!repo.existsById(id)) {
 return ResponseEntity.notFound().build();
 }
  repo.deleteById(id);
 return ResponseEntity.noContent().build();
}

}

