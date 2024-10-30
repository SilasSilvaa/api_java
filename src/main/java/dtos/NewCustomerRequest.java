package dtos;

public record NewCustomerRequest(
        String name,
        String email,
        Integer age) {

}

