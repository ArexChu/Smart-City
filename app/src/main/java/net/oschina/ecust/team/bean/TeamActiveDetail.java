package net.oschina.ecust.team.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import net.oschina.ecust.bean.Entity;

/**
 * Team模块的动态JavaBean
 *
 * @author kymjs
 */
@SuppressWarnings("serial")
@XStreamAlias("oschina")
public class TeamActiveDetail extends Entity {

    @XStreamAlias("active")
    private TeamActive teamActive;

    public TeamActive getTeamActive() {
        return teamActive;
    }

    public void setTeamActive(TeamActive teamActive) {
        this.teamActive = teamActive;
    }
}
