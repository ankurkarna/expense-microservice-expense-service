package org.xyz.CRUD_expense_service.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.xyz.CRUD_expense_service.exception.UserNotFoundException;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 404) {
            // You might want to extract more details from response.body() if available
            return new UserNotFoundException("User not found");
        }
        return new ErrorDecoder.Default().decode(methodKey, response);
    }
}
