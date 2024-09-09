package com.vortex.soft.sellproductsnew.domain.dto.register

data class RegisterDto(
        val username: String,
        val email: String,
        val password: String,
        val passwordConfirm: String)