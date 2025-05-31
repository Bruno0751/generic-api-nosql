package com.developer.generic_api_nosql.email.dtos;

import java.util.UUID;

public record EmailDto(String id, String emailTo, String subject, String text) {
}
