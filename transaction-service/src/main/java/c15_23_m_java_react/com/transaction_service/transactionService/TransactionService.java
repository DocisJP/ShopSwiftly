
package c15_23_m_java_react.com.transaction_service.transactionService;


import c15_23_m_java_react.com.transaction_service.dtos.ProductDto;
import c15_23_m_java_react.com.transaction_service.dtos.TransactionDto;
import c15_23_m_java_react.com.transaction_service.dtos.TransactionItemDto;
import c15_23_m_java_react.com.transaction_service.dtos.UserDto;
import c15_23_m_java_react.com.transaction_service.entitys.TransactionEntity;
import c15_23_m_java_react.com.transaction_service.entitys.TransactionItem;
import c15_23_m_java_react.com.transaction_service.exception.UserNotFoundException;
import c15_23_m_java_react.com.transaction_service.repository.TransactionRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ProductService productService;
    private final UserService userService;
    private final ModelMapper modelMapper; 

    public TransactionService(TransactionRepository transactionRepository,
                              ProductService productService,
                              UserService userService,
                              ModelMapper modelMapper) { 
        this.transactionRepository = transactionRepository;
        this.productService = productService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public TransactionEntity createTransaction(TransactionDto transactionDto) {
        TransactionEntity transactionEntity = convertToEntity(transactionDto);
        updateTransactionDetails(transactionEntity, transactionDto.getItemDtos());
        return transactionRepository.save(transactionEntity);
    }

    private TransactionEntity convertToEntity(TransactionDto transactionDto) {
        TransactionEntity transactionEntity = modelMapper.map(transactionDto, TransactionEntity.class);

        if (transactionDto.getItemDtos() != null) {
            List<TransactionItem> transactionItems = transactionDto.getItemDtos().stream()
                .map(itemDto -> {
                    TransactionItem item = modelMapper.map(itemDto, TransactionItem.class);
                    item.setTransaction(transactionEntity);
                    // You may need to fetch and set additional details about the product here
                    return item;
                }).collect(Collectors.toList());
            transactionEntity.setItems(transactionItems); 
        }

        return transactionEntity;
    }
    
    

    private List<TransactionItem> convertItemDtosToItems(List<TransactionItemDto> itemDtos, TransactionEntity transaction) {
        return itemDtos.stream()
                .map(itemDto -> {
                    ProductDto product = productService.getProductDto(itemDto.getProductId());
                    TransactionItem item = new TransactionItem();
                    item.setTransaction(transaction);
                    item.setProductId(itemDto.getProductId());
                    item.setQuantity(itemDto.getQuantity());
                    item.setPrice(product.getPrice()); 
                    return item;
                })
                .collect(Collectors.toList());
    }

    private void updateTransactionDetails(TransactionEntity transaction, List<TransactionItemDto> itemDtos) {
        UserDto user = userService.getUserDto(transaction.getUser_id());
        if (user == null) {
            throw new UserNotFoundException("User not found with ID: " + transaction.getUser_id());
        }
        transaction.setUserUsername(user.getUsername());
        transaction.setUserEmail(user.getEmail());

        // If updating an existing transaction, clear previous items
        if (!transaction.getItems().isEmpty()) {
            transaction.getItems().clear();
        }

        List<TransactionItem> transactionItems = convertItemDtosToItems(itemDtos, transaction);
        transaction.getItems().addAll(transactionItems); // Add all new items to the transaction
    }
    

    public TransactionEntity findById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public TransactionEntity updateTransaction(TransactionEntity transaction, List<TransactionItemDto> itemDtos) {
        updateTransactionDetails(transaction, itemDtos);
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

	
