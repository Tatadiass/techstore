package br.com.t2_fat.techstore.web;

import br.com.t2_fat.techstore.domain.Category;
import br.com.t2_fat.techstore.domain.Product;
import br.com.t2_fat.techstore.repository.CategoryRepository;
import br.com.t2_fat.techstore.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ProductController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductController(ProductRepository productRepository,
                             CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // lista
    @GetMapping("/products")
    public String list(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products/list";
    }

    // exibe formulário de cadastro
    @GetMapping("/products/new")
    public String showForm(Model model) {
        Product product = new Product();
        product.setPrice(BigDecimal.ZERO);

        model.addAttribute("product", product);

        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);

        return "products/form";
    }

    // processa envio do formulário
    @PostMapping("/products")
    public String save(@ModelAttribute("product") Product product,
                       BindingResult bindingResult,
                       Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "products/form";
        }

        productRepository.save(product);
        return "redirect:/products";
    }
}