package com.gfa.pfp.services;

import com.gfa.pfp.exception.PfpException;
import com.gfa.pfp.models.dto.MessageDTO;
import com.gfa.pfp.models.dto.limit.LimitDTO;
import com.gfa.pfp.models.dto.limit.LimitResponseDTO;
import com.gfa.pfp.models.dto.limit.LimitResponseListDTO;
import com.gfa.pfp.models.entities.finance.Limit;

public interface LimitService {

    LimitResponseDTO setLimit(LimitDTO limit, String request) throws PfpException;

    LimitResponseListDTO checkAllLimits(String authorization) throws PfpException;

    LimitResponseDTO checkLimitByType(Limit limit, String authorization) throws PfpException;

    MessageDTO deleteLimit(LimitDTO limit, String authorization);

}