package c15_23_m_java_react.com.transaction_service.transactionService;


import org.springframework.stereotype.Service;

import c15_23_m_java_react.com.transaction_service.dtos.UserDto;

@Service
public class UserService {
    
    private final UserClient userClient;

    public UserService(UserClient userClient) {
        this.userClient = userClient;
    }

    public UserDto getUserDto(Long id) {
        return userClient.getUsertDto(id);
    }
}

