package com.ssafy.backend.shareBoard.dto.response;

import com.ssafy.backend.record.dto.PointMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRecordDto {
    private Long shareBoardSeq;
    private String address;
    private List<PointMapping> points;
    private String duration;
    private Double distance;
}
