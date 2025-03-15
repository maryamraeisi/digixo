package ir.digixo.discount.dto;

public record DiscountRequest(String code, float percentage, String expirationDate) {
}
