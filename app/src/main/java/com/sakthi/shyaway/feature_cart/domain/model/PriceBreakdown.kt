package com.sakthi.shyaway.feature_cart.domain.model

data class PriceBreakdown(
    val total: Double = 0.0,
    val discount: Double = 0.0,
    val subtotal: Double = 0.0,
    val gst: Double = 0.0,
    val shipping: String = "FREE",
    val roundedUp: Double = 0.0,
    val totalPayable: Double = 0.0,
    val totalSavings: Double = 0.0,
    val savingsPercentage: Int = 0
)