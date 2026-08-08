package com.crimsonlogic.corporateassetmanagementsystem.mapper;

import com.crimsonlogic.corporateassetmanagementsystem.entity.DisposalRequest;
import java.util.List;

public interface DisposalRequestMapper {
    void insertDisposalRequest(DisposalRequest request);
    DisposalRequest getDisposalRequestById(Integer id);
    List<DisposalRequest> getAllDisposalRequests();
    void updateDisposalRequest(DisposalRequest request);
}