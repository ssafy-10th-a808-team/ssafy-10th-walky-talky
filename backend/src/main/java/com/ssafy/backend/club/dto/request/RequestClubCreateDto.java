package com.ssafy.backend.club.dto.request;

import com.ssafy.backend.club.domain.Club;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestClubCreateDto {

    private MultipartFile multipartFile;
    private String name;
    private String introduce;
    private String regionCd;
    private String young_birth;
    private String old_birth;
    private String gender_type;
    private int max_capacity;
    private Boolean is_auto_recruit;

    public Club toEntity() {
        return Club.builder()
                .seq(null)
                .name(name)
                .url(null)
                .introduce(introduce)
                .regionCd(regionCd)
                .youngBirth(young_birth)
                .oldBirth(old_birth)
                .genderType(gender_type)
                .nowCapacity(1)
                .maxCapacity(max_capacity)
                .isAutoRecruit(is_auto_recruit)
                .isOpenRecruit(true)
                .isDeleted(false)
                .build();
    }
}
