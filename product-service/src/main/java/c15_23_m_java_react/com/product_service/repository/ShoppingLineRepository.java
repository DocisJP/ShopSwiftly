package c15_23_m_java_react.com.product_service.repository;

import c15_23_m_java_react.com.product_service.entities.ShoppingLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingLineRepository extends JpaRepository<ShoppingLine, Long> {
}
