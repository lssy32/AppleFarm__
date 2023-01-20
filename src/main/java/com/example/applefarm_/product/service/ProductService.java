package com.example.applefarm_.product.service;

import com.example.applefarm_.exception.CustomException;
import com.example.applefarm_.exception.ExceptionStatus;
import com.example.applefarm_.product.dto.ProductRequest;
import com.example.applefarm_.product.dto.ProductResponse;
import com.example.applefarm_.product.entitiy.Product;
import com.example.applefarm_.product.repository.ProductRepository;
import com.example.applefarm_.user.entitiy.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductResponse saveProduct(ProductRequest request, Long sellerId) {
        Product product = productRepository.save(request.toEntity(sellerId));
        return new ProductResponse(product);
    }
    public Product reduceQuantity (Long productid, int orderQauntity){
        Product product = productRepository.findById(productid).get();
        product.subtractQuantity(orderQauntity);
        return product;
    }

    @Transactional
    public List<ProductResponse> getProducts(int pageChoice, User user) {
        Page<Product> products= productRepository.findAllBySellerId(user.getId(),pageableSetting(pageChoice));
        if(products.isEmpty()){
            throw new CustomException(ExceptionStatus.PAGINATION_IS_NOT_EXIST);
        }
        List<ProductResponse> productResponseList = products.stream().map(ProductResponse::new).collect(Collectors.toList());
        return productResponseList;
    }

    private Pageable pageableSetting(int pageChoice) {
        Sort.Direction direction = Sort.Direction.DESC;
        Sort sort = Sort.by(direction,"id");
        Pageable pageable = PageRequest.of(pageChoice-1,4,sort);
        return pageable;
    }

    @Transactional
    public void updateProduct(Long id, User user,ProductRequest productRequest) {
        Product foundProduct = productRepository.findByIdAndSellerId(id,user.getId()).orElseThrow(
                ()-> new CustomException(ExceptionStatus.Product_IS_NOT_EXIST)
        );
        foundProduct.updateProduct(productRequest);
    }

    @Transactional
    public void deleteProduct(Long id, User user) {
        Product foundProduct = productRepository.findByIdAndSellerId(id,user.getId()).orElseThrow(
                ()-> new CustomException(ExceptionStatus.Product_IS_NOT_EXIST)
        );
        productRepository.deleteById(id);
    }


}
