package ru.practicum.confidence.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.confidence.dto.ProductDto;
import ru.practicum.confidence.exception.NotFoundException;
import ru.practicum.confidence.mapper.ProductMapperDto;
import ru.practicum.confidence.model.Product;
import ru.practicum.confidence.repository.ProductRepository;
import ru.practicum.confidence.service.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final String ERR_MESSAGE_USER_NOT_FOUND = "Продукта с таким id=%s не существует";

    private final ProductRepository productRepository;

    private final ProductMapperDto productMapperDto;

    @Override
    public ProductDto create(ProductDto productDto) {
        Product save = productRepository.save(productMapperDto.toDto(productDto));
        return productMapperDto.toProductDto(save);
    }

    @Override
    public ProductDto updateUser(ProductDto productDto) {
        Product product = getProduct(productDto.getId());
        productMapperDto.updateProductDto(productDto, product);
        Product updated = productRepository.save(product);
        return productMapperDto.toProductDto(updated);
    }

    @Override
    public void deleteProduct(Long productId) {
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
        } else {
            throw new NotFoundException(String.format(ERR_MESSAGE_USER_NOT_FOUND, productId));
        }
    }

    @Override
    public ProductDto getById(Long productId) {
        Product product = getProduct(productId);
        return productMapperDto.toProductDto(product);
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(String.format(ERR_MESSAGE_USER_NOT_FOUND, productId)));
    }

    @Override
    public List<ProductDto> getProductsByCategory(ProductDto productDto) {
        List<Product> allByProductTypes = productRepository.findAllByProductTypes(productDto.getProductTypes());
        return allByProductTypes.stream()
                .map(productMapperDto::toProductDto)
                .toList();
    }
}
