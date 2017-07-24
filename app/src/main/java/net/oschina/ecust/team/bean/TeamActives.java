package net.oschina.ecust.team.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import net.oschina.ecust.bean.Entity;
import net.oschina.ecust.bean.ListEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态列表
 *
 * @author kymjs
 */
@XStreamAlias("oschina")
public class TeamActives extends Entity implements ListEntity<TeamActive> {
    private static final long serialVersionUID = 1L;

    @XStreamAlias("actives")
    ArrayList<TeamActive> actives = new ArrayList<TeamActive>();

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public List<TeamActive> getList() {
        return actives;
    }

    public ArrayList<TeamActive> getActives() {
        return actives;
    }

    public void setActives(ArrayList<TeamActive> actives) {
        this.actives = actives;
    }

}
