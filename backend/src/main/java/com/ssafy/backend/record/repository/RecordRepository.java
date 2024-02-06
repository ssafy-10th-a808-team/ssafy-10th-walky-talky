package com.ssafy.backend.record.repository;

import com.ssafy.backend.record.domain.Record;
import com.ssafy.backend.record.dto.mapping.ListMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    Record findMemberSeqBySeq(Long seq);

    boolean existsBySeqAndMemberSeqAndIsDeletedFalse(Long seq, Long memberSeq);

    boolean existsBySeqAndIsDeletedTrue(Long seq);

    List<ListMapping> findResponseListDtoByMemberSeqAndIsDeletedFalse(Long memberSeq);

    List<ListMapping> findBySeqIn(List<Long> recordSeq);

    List<ListMapping> findByRegionCdAndSeqNotIn(String regionCd, List<Long> recordSeq);

    List<ListMapping> findByMemberSeqInAndSeqNotIn(List<Long> memberSeq, List<Long> recordSeq);

}