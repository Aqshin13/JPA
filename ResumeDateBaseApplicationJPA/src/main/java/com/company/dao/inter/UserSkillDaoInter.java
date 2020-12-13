package com.company.dao.inter;

import com.company.entity.UserSkill;
import java.util.List;

public interface UserSkillDaoInter {

    List<UserSkill> getAllUserSkill();

    UserSkill getUserSkillById(int id);

    boolean removeUserSkill(int id);

    boolean updateUserSkill(UserSkill userSkills);

    boolean addUserSkill(UserSkill skills);

}
