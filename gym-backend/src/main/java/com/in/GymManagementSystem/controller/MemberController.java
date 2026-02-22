package com.in.GymManagementSystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in.GymManagementSystem.dto.MemberDTO;
import com.in.GymManagementSystem.services.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = {"http://localhost:5173", "http://127.0.0.1:5173", "http://localhost:5174", "http://127.0.0.1:5174"})
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<MemberDTO>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> getMemberById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    @PostMapping
    public ResponseEntity<MemberDTO> createMember(@Valid @RequestBody MemberDTO memberDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createMember(memberDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> updateMember(@PathVariable Long id, @Valid @RequestBody MemberDTO memberDTO) {
        return ResponseEntity.ok(memberService.updateMember(id, memberDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{memberId}/trainers/{trainerId}")
    public ResponseEntity<MemberDTO> assignTrainer(@PathVariable Long memberId, @PathVariable Long trainerId) {
        return ResponseEntity.ok(memberService.assignTrainer(memberId, trainerId));
    }

    @DeleteMapping("/{memberId}/trainers/{trainerId}")
    public ResponseEntity<MemberDTO> removeTrainer(@PathVariable Long memberId, @PathVariable Long trainerId) {
        return ResponseEntity.ok(memberService.removeTrainer(memberId, trainerId));
    }
}
