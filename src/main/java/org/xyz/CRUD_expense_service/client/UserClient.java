package org.xyz.CRUD_expense_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.xyz.CRUD_expense_service.dto.UserResponseDto;

@FeignClient(name = "user-service", url = "${user.service.url}")
public interface UserClient {

    @GetMapping("/user/{id}")
    UserResponseDto getUserById(@PathVariable("id") Long id);
}
