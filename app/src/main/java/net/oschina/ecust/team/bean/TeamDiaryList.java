package net.oschina.ecust.team.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import net.oschina.ecust.bean.Entity;
import net.oschina.ecust.bean.ListEntity;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
@XStreamAlias("oschina")
public class TeamDiaryList extends Entity implements ListEntity<TeamDiary> {

    @XStreamAlias("pagesize")
    private int pageSize;

    @XStreamAlias("totalCount")
    private int totalCount;

    @XStreamAlias("diaries")
    private List<TeamDiary> list = new ArrayList<TeamDiary>();

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<TeamDiary> getList() {
        return list;
    }

    public void setList(List<TeamDiary> list) {
        this.list = list;
    }

}
