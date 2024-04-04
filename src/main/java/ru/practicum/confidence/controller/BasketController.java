package ru.practicum.confidence.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.confidence.dto.BasketDto;
import ru.practicum.confidence.service.BasketService;

@RestController
@RequestMapping(path = "/basket")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    @PatchMapping("/update")
    public BasketDto addProductToBasket(@RequestBody BasketDto basketDto, Long productId, Long userId) {
        return basketService.updateBasket(basketDto,productId,userId);
    }

    @DeleteMapping("/{basketId}")
    public void delete(@PathVariable Long basketId,Long productId) {
        basketService.deleteProductsInBasket(basketId,productId);
    }
}
