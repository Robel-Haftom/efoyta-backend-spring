package com.wegagen.efoyta.mapper;

import com.wegagen.efoyta.dto.request.RemarkRequestDto;
import com.wegagen.efoyta.dto.response.RemarkResponseDto;
import com.wegagen.efoyta.entity.Remark;

import java.time.format.DateTimeFormatter;

public class RemarkMapper {

    public static Remark mapToRemark(RemarkRequestDto requestDto){
        return Remark.builder()
                .remarkText(requestDto.getRemarkText())
                .author(requestDto.getAuthor())
                .build();
    }

    public static RemarkResponseDto mapToRemarkResponseDto(Remark remark){
        return new RemarkResponseDto(
                remark.getRemarkId().toString(),
                remark.getRemarkText(),
                remark.getAuthor(),
                remark.getCreatedAt().format(DateTimeFormatter.ofPattern("MMM-dd-yyyy hh:mm:ss a"))
        );
    }
}
