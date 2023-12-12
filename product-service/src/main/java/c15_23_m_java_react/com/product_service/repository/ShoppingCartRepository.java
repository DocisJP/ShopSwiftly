package c15_23_m_java_react.com.product_service.repository;

import c15_23_m_java_react.com.product_service.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
