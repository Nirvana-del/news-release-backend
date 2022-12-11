package com.wq.service;

import com.wq.entity.SubRights;

import java.util.List;

public interface SubRightsService {
    List<SubRights> getSubRightsByParentId(Integer id);

    void deleteSubRights(Integer id);

    void updateSubRights(SubRights subRights);

    List<SubRights> getSubRightsList();

    List<String> getSubRightsListByRole(Integer roleId);
}
