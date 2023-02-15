package com.wq.service;

import com.wq.entity.Rights;

import java.util.List;

public interface RightsService {
    void deleteRights(Integer id);

    void updateRights(Rights rights);

    void changeRights(List<Rights> parentRights);

    List<Rights> getRightsList();

    List<String> getRightsListByRole(Integer roleId);
}
