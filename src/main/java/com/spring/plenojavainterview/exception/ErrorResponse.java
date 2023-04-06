package com.spring.plenojavainterview.exception;

public class ErrorResponse {
    private String mensagem;
    private int status;

    public ErrorResponse(String mensagem, int status) {
        this.mensagem = mensagem;
        this.status = status;
    }
}