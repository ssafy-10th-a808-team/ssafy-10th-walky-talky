package com.ssafy.backend.shareBoard.dto.response;

import com.ssafy.backend.record.dto.mapping.PointsMapping;
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
    private Long recordSeq;
    private String address;
    private List<PointsMapping> points;
    private String duration;
    private Double distance;
}