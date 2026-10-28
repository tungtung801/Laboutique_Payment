package com.structure.springboot.infrastructure.external.vnpay;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;
import java.time.ZoneId;

@Validated
@ConfigurationProperties(prefix = "app.payment.vnpay")
public record VnpayProperties(
        @NotBlank String tmnCode,
        @NotBlank String hashSecret,
        @NotBlank String paymentUrl,
        @NotBlank String apiUrl,
        @NotBlank String returnUrl,
        @NotNull Duration paymentTimeout,
        @NotBlank String version,
        @NotBlank String command,
        @NotBlank String currency,
        @NotBlank String locale,
        @NotBlank String orderType,
        @NotNull ZoneId zoneId
) {

    public VnpayProperties {
        if (paymentTimeout != null
                && (paymentTimeout.isNegative() || paymentTimeout.isZero())) {
            throw new IllegalArgumentException(
                    "VNPay payment timeout must be positive");
        }
    }

    @Override
    public String toString() {
        return "VnpayProperties[credentials=REDACTED]";
    }
}
