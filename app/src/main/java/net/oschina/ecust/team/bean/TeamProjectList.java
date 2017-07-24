package net.oschina.ecust.team.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import net.oschina.ecust.bean.Entity;
import net.oschina.ecust.bean.ListEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 团队项目实体类
 *
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @version 创建时间：2015年1月14日 下午3:24:15
 */
@SuppressWarnings("serial")
@XStreamAlias("oschina")
public class TeamProjectList extends Entity implements ListEntity<TeamProject> {

    @XStreamAlias("pagesize")
    private int pageSize;

    @XStreamAlias("projects")
    private List<TeamProject> list = new ArrayList<TeamProject>();

    @Override
    public List<TeamProject> getList() {
        return list;
    }
}
