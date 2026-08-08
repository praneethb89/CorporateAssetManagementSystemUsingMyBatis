package com.crimsonlogic.corporateassetmanagementsystem.dao.interfaces;

import com.crimsonlogic.corporateassetmanagementsystem.entity.DisposalRequest;
import java.util.List;

public interface DisposalRequestDao {
    void saveDisposalRequest(DisposalRequest request);
    DisposalRequest findById(Integer id);
    List<DisposalRequest> findAll();
    void updateDisposalRequest(DisposalRequest request);
}