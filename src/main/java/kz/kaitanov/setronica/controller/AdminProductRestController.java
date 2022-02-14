package kz.kaitanov.setronica.controller;

import kz.kaitanov.setronica.model.Product;
import kz.kaitanov.setronica.model.dto.ResponseDto;
import kz.kaitanov.setronica.service.ProductService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/admin/product")
public class AdminProductRestController {

    private final ProductService productService;

    public AdminProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseDto<Void> addProduct(@Valid @RequestBody Product product) {
        productService.addProduct(product);
        return ResponseDto.ok();
    }

    @PatchMapping
    public ResponseDto<Void> updateProduct(@Valid @RequestBody Product product) {
        productService.updateProduct(product);
        return ResponseDto.ok();
    }

    @DeleteMapping("/{productId}")
    public ResponseDto<Void> removeProduct(@PathVariable("productId") Long productId) {
        productService.removeProduct(productId);
        return ResponseDto.ok();
    }

}