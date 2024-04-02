package ru.practicum.confidence.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.confidence.dto.ProductDto;
import ru.practicum.confidence.service.ProductService;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    public ProductDto create(@RequestBody @Validated ProductDto productDto) {
        return productService.create(productDto);
    }

    @PatchMapping("/update")
    public ProductDto update(@RequestBody ProductDto productDto) {
        return productService.updateUser(productDto);
    }

    @DeleteMapping("/{productId}")
    public void delete(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }

    @GetMapping("/{productId}")
    public ProductDto getById(@PathVariable Long productId) {
        return productService.getById(productId);
    }

    @GetMapping("/{productId}")
    public List<ProductDto> getProductsByCategory(@RequestBody ProductDto productDto) {
        return productService.getProductsByCategory(productDto);
    }
}
