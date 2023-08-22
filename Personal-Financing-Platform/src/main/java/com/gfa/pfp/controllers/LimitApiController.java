package com.gfa.pfp.controllers;

import com.gfa.pfp.exception.PfpException;
import com.gfa.pfp.models.dto.MessageDTO;
import com.gfa.pfp.models.dto.limit.LimitDTO;
import com.gfa.pfp.models.dto.limit.LimitResponseDTO;
import com.gfa.pfp.models.dto.limit.LimitResponseListDTO;
import com.gfa.pfp.models.entities.finance.Limit;
import com.gfa.pfp.services.LimitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/limit")
@RequiredArgsConstructor
public class LimitApiController {

    private final LimitService limitService;

    @PostMapping("/set")
    public ResponseEntity<LimitResponseDTO> createLimit(@RequestBody LimitDTO limit,
                                                        @RequestHeader String authorization) throws PfpException {
        return ResponseEntity.ok().body(limitService.setLimit(limit, authorization));
    }

    @GetMapping("/checkAll")
    public ResponseEntity<LimitResponseListDTO> checkAllLimits(@RequestHeader String authorization) throws PfpException {
        return ResponseEntity.ok().body(limitService.checkAllLimits(authorization));
    }

    @GetMapping("/check")
    public ResponseEntity<LimitResponseDTO> checkLimitByType(@RequestBody Limit limit,
                                                             @RequestHeader String authorization) throws PfpException {
        return ResponseEntity.ok().body(limitService.checkLimitByType(limit, authorization));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<MessageDTO> deleteLimit(@RequestBody LimitDTO limit,
                                                  @RequestHeader String authorization) {
        return ResponseEntity.ok().body(limitService.deleteLimit(limit, authorization));
    }

}