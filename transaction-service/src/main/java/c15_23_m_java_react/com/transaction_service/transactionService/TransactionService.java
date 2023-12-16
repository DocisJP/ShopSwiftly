
package c15_23_m_java_react.com.transaction_service.transactionService;

import c15_23_m_java_react.com.transaction_service.dtos.ProductDto;
import c15_23_m_java_react.com.transaction_service.dtos.UserDto;
import c15_23_m_java_react.com.transaction_service.entitys.TransactionEntity;
import c15_23_m_java_react.com.transaction_service.exception.ProductNotFoundException;
import c15_23_m_java_react.com.transaction_service.exception.UserNotFoundException;
import c15_23_m_java_react.com.transaction_service.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TransactionService {
   
	@Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    private void updateTransactionDetails(TransactionEntity transaction) {
        ProductDto product = productService.getProductDto(transaction.getProductId());
        UserDto user = userService.getUserDto(transaction.getUser_id());

        if (product == null) {
            throw new ProductNotFoundException("Producto no encontrado con ID: " + transaction.getProductId());
        }

        if (user == null) {
            throw new UserNotFoundException("User no encontrado con ID: " + transaction.getUser_id());
        }

        // Update transaction with product details
        transaction.setProductName(product.getName());
        transaction.setProductPrice(product.getPrice());
        transaction.setProductDiscount(product.getDiscount());

        // Update transaction with user details
        transaction.setUserUsername(user.getUsername());
        transaction.setUserEmail(user.getEmail());

        // ... any other fields if the need arises.
    }
    
    public TransactionEntity createTransaction(TransactionEntity transaction) {
        updateTransactionDetails(transaction);
        return transactionRepository.save(transaction);
    }

    public TransactionEntity findById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public TransactionEntity updateTransaction(TransactionEntity transaction) {
        updateTransactionDetails(transaction);
        return transactionRepository.save(transaction);
    }


    public TransactionEntity changeTransactionStatus(Long id, Boolean newStatus) {
        TransactionEntity transaction = transactionRepository.findById(id).orElse(null);
        if (transaction != null) {
            transaction.setEstado(newStatus);
            return transactionRepository.save(transaction);
        }
        return null;
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}

	
