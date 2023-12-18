package com.lhduc.inventoryservice.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessResponse<T> {
    private T data;

    public static <T> SuccessResponse<T> of (T data) {
        SuccessResponse<T> successResponse = new SuccessResponse<>();
        successResponse.data = data;
        return successResponse;
    }
}