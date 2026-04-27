package com.mb.controller;

import com.mb.dto.ApiResponse;
import com.mb.dto.ProductInDto;
import com.mb.entities.Product;
import com.mb.service.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/products")
@Validated
public class ProductController {
  private final ProductService productService;

  @PostMapping
  public ResponseEntity<ApiResponse<Product>> createDepartment(
    @RequestBody @Valid ProductInDto dto
  ) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(
        new ApiResponse<Product>(true, "Product Saved!", productService.saveProduct(dto))
      );
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<Product>>> findDepartments() {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(new ApiResponse<List<Product>>(true, null, productService.findAllProducts()));
  }

  @GetMapping("/filter")
  public ResponseEntity<ApiResponse<List<Product>>> filterProducts(
    @RequestParam(required = false) String category,
    @RequestParam(required = false) String type
  ) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(
        new ApiResponse<List<Product>>(
          true,
          null,
          productService.filterProducts(category, type)
        )
      );
  }

  //doubt
  @GetMapping("/search")
  public ResponseEntity<ApiResponse<List<Product>>> filterProducts(
    @RequestParam(required = false) String name
  ) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(
        new ApiResponse<List<Product>>(true, null, productService.findProductByName(name))
      );
  }
  
  
  @GetMapping("/paginated")
  public ResponseEntity<ApiResponse<Page<Product>>> findPaginatedProducts(@RequestParam(defaultValue = "0") int page,
  @RequestParam(defaultValue = "10") int size) {

      Page<Product > product = productService.findProductsPaginated(page, size);
    return ResponseEntity
    .status(HttpStatus.CREATED)
    .body(new ApiResponse<Page<Product>>(true, null, product));
  }
   @GetMapping("/paginated/filter")
  public ResponseEntity<ApiResponse<Page<Product>>> findPaginatedFilterProducts(@RequestParam(defaultValue = "0") int page,
  @RequestParam(defaultValue = "10") int size,  @RequestParam(required = false) String category,
    @RequestParam(required = false) String type) {


    Page<Product > product = productService.filteredProductPaginated(page, size,category,type);

    
    return ResponseEntity
    .status(HttpStatus.CREATED)
    .body(new ApiResponse<Page<Product>>(true, null, product));
  }
}