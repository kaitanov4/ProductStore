package kz.kaitanov.setronica.config.init;

import kz.kaitanov.setronica.model.Price;
import kz.kaitanov.setronica.model.Product;
import kz.kaitanov.setronica.model.ProductDetails;
import kz.kaitanov.setronica.model.Rate;
import kz.kaitanov.setronica.model.Role;
import kz.kaitanov.setronica.model.User;
import kz.kaitanov.setronica.model.enums.CurrencyEnum;
import kz.kaitanov.setronica.model.enums.LocaleEnum;
import kz.kaitanov.setronica.model.enums.RoleNameEnum;
import kz.kaitanov.setronica.service.ProductService;
import kz.kaitanov.setronica.service.RateService;
import kz.kaitanov.setronica.service.RoleService;
import kz.kaitanov.setronica.service.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    private final UserService userService;
    private final RoleService roleService;
    private final ProductService productService;
    private final RateService rateService;

    public ApplicationRunnerImpl(UserService userService,
                                 RoleService roleService,
                                 ProductService productService,
                                 RateService rateService) {
        this.userService = userService;
        this.roleService = roleService;
        this.productService = productService;
        this.rateService = rateService;
    }

    @Override
    public void run(ApplicationArguments args) {
        Role userRole = new Role(RoleNameEnum.USER);
        Role adminRole = new Role(RoleNameEnum.ADMIN);
        roleService.addRole(userRole);
        roleService.addRole(adminRole);

        userService.addUser(new User("user", "user", userRole));
        userService.addUser(new User("admin", "admin", adminRole));

        Product apple = new Product();
        apple.getProductDetailsList().add(new ProductDetails("яблоко", "из Казахстана", LocaleEnum.RU));
        apple.getProductDetailsList().add(new ProductDetails("apple", "from Kazakhstan", LocaleEnum.ENG));
        apple.setPrice(new Price(BigDecimal.valueOf(100), CurrencyEnum.USD));

        Product banana = new Product();
        banana.getProductDetailsList().add(new ProductDetails("банан", "из Эквадора", LocaleEnum.RU));
        banana.setPrice(new Price(BigDecimal.valueOf(150), CurrencyEnum.USD));

        Product grape = new Product();
        grape.getProductDetailsList().add(new ProductDetails("grape", "from Argentina", LocaleEnum.ENG));
        grape.setPrice(new Price(BigDecimal.valueOf(200), CurrencyEnum.USD));

        productService.addProduct(apple);
        productService.addProduct(banana);
        productService.addProduct(grape);

        rateService.addRate(new Rate(77.12, CurrencyEnum.RUB));
        rateService.addRate(new Rate(0.88, CurrencyEnum.EUR));
        rateService.addRate(new Rate(430.06, CurrencyEnum.KZT));
    }

}