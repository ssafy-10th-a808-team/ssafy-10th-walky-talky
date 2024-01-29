package com.ssafy.backend.clubMember.repository;

import com.ssafy.backend.clubMember.domain.ClubMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubMemberRepository extends JpaRepository<ClubMember, Long> {
    boolean existsByClubSeqAndMemberSeqAndRoleIn(Long seq, Long memberSeq, List<String> list);
}