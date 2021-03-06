package org.mushare.wooder.service;

import org.mushare.wooder.bean.ProjectBean;
import org.mushare.wooder.service.common.Result;
import org.mushare.wooder.service.common.ResultList;

import java.util.Map;

public interface ProjectManager {

    Result<ProjectBean> add(String name, String groupId);

    Result<ProjectBean> projectInfo(String projectId, String memberId);

    ResultList<ProjectBean> getProjectsByGroupId(String groupId);

    ResultList<ProjectBean> getProjectsByMemberId(String memberId);

    Result<Map<String, String>> xcodeText(String projectId);

}
