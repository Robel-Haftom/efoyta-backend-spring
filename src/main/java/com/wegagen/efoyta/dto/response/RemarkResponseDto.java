package com.wegagen.efoyta.dto.response;

import java.time.LocalDateTime;

public record RemarkResponseDto (
        String remarkId,
        String remarkText,
        String author,
        String createdAt
){
}
