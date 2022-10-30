package com.molla.controller.admin;

import com.molla.entity.Brand;
import com.molla.entity.Category;
import com.molla.entity.Product;
import com.molla.service.BrandService;
import com.molla.service.CategoryService;
import com.molla.service.ProductService;
import com.molla.service.UserService;
import com.molla.util.AuthenticationUser;
import com.molla.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
    @Autowired
    AuthenticationUser authenticationUser;
    @Autowired
    private ProductService productService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    public void common(Model model) {
        authenticationUser.filterUser(model);
    }


    @ModelAttribute("brands")
    public List<Brand> getAllBrands() {
        return brandService.findAll();
    }

    @ModelAttribute("categories")
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/list")
    public String viewProductTable(Model model) {
        common(model);
        System.out.println(productService.findAll());
        model.addAttribute("products", productService.findAll());
        return "admin/products";
    }

    @GetMapping("/addOrEdit")
    public String viewAddOrEdit(Product product,
                                @RequestParam(value = "id", required = false) Long productId,
                                Model model) {
        common(model);
        System.out.println(product.toString());
        model.addAttribute("product", new Product());
        if (productId != null) {
            System.out.println("Edit");
        }
        return "admin/product-form";
    }

    @PostMapping("/save")
    public String save(Model model, Product product,
                       @RequestParam("main_image") MultipartFile mainImage,
                       @RequestParam("extraImage") MultipartFile[] multipartFiles
    ) throws IOException {
        product.setMainImage(StringUtils.cleanPath(mainImage.getOriginalFilename()));
        product.setExtraImage1(StringUtils.cleanPath(multipartFiles[0].getOriginalFilename()));
        product.setExtraImage2(StringUtils.cleanPath(multipartFiles[1].getOriginalFilename()));
        product.setExtraImage3(StringUtils.cleanPath(multipartFiles[2].getOriginalFilename()));
        product.setBrand(brandService.findById(product.getBrandId()));
        product.setUser(userService.findByEmail("admin@gmail.com"));
        product.setCategory(categoryService.findById(product.getCategoryId()));

        product = productService.save(product);
        String uploadDir = "./product-images/" + product.getId();

        FileUploadUtil.saveFile(uploadDir, product.getMainImage(), mainImage);
        for (MultipartFile extraFile : multipartFiles) {
            String fileName = StringUtils.cleanPath(extraFile.getOriginalFilename());
            FileUploadUtil.saveFile(uploadDir, fileName, extraFile);
        }
        return "redirect:/admin/product/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(Model model, @PathVariable("id") Long productId) {
        return "redirect:/admin/product/list";
    }
}
