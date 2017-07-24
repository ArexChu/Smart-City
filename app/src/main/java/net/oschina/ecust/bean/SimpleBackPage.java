package net.oschina.ecust.bean;

import net.oschina.ecust.R;
import net.oschina.ecust.fragment.AboutOSCFragment;
import net.oschina.ecust.fragment.BrowserFragment;
import net.oschina.ecust.fragment.EventAppliesFragment;
import net.oschina.ecust.fragment.EventFragment;
import net.oschina.ecust.fragment.MyInformationFragmentDetail;
import net.oschina.ecust.fragment.QuestionTagFragment;
import net.oschina.ecust.fragment.SettingsFragment;
import net.oschina.ecust.improve.main.subscription.SubFragment;
import net.oschina.ecust.improve.tweet.fragments.TweetFragment;
import net.oschina.ecust.improve.user.fragments.UserBlogFragment;
import net.oschina.ecust.improve.user.fragments.UserQuestionFragment;
import net.oschina.ecust.team.fragment.NoteBookFragment;
import net.oschina.ecust.team.fragment.NoteEditFragment;
import net.oschina.ecust.team.fragment.TeamActiveFragment;
import net.oschina.ecust.team.fragment.TeamDiaryDetailFragment;
import net.oschina.ecust.team.fragment.TeamDiscussFragment;
import net.oschina.ecust.team.fragment.TeamIssueFragment;
import net.oschina.ecust.team.fragment.TeamMemberInformationFragment;
import net.oschina.ecust.team.fragment.TeamProjectFragment;
import net.oschina.ecust.team.fragment.TeamProjectMemberSelectFragment;
import net.oschina.ecust.team.viewpagefragment.MyIssuePagerfragment;
import net.oschina.ecust.team.viewpagefragment.TeamDiaryFragment;
import net.oschina.ecust.team.viewpagefragment.TeamIssueViewPageFragment;
import net.oschina.ecust.team.viewpagefragment.TeamProjectViewPagerFragment;
import net.oschina.ecust.viewpagerfragment.EventViewPagerFragment;
import net.oschina.ecust.viewpagerfragment.OpenSoftwareFragment;

public enum SimpleBackPage {

    USER_BLOG(6, R.string.actionbar_title_user_blog, UserBlogFragment.class),

    OPEN_SOURCE_SOFTWARE(10, R.string.actionbar_title_software_list,
            OpenSoftwareFragment.class),

    QUESTION_TAG(12, R.string.actionbar_title_question,
            QuestionTagFragment.class),

    SETTING(15, R.string.actionbar_title_setting, SettingsFragment.class),

    ABOUT_OSC(17, R.string.actionbar_title_about_osc, AboutOSCFragment.class),

    EVENT_APPLY(22, R.string.actionbar_title_event_apply,
            EventAppliesFragment.class),

    SAME_CITY(23, R.string.actionbar_title_same_city, EventFragment.class),

    NOTE(24, R.string.actionbar_title_note, NoteBookFragment.class),

    NOTE_EDIT(25, R.string.actionbar_title_note_edit, NoteEditFragment.class),

    BROWSER(26, R.string.app_name, BrowserFragment.class),

    DYNAMIC(27, R.string.team_dynamic, TeamActiveFragment.class),

    MY_INFORMATION_DETAIL(28, R.string.actionbar_title_my_information,
            MyInformationFragmentDetail.class),

    TEAM_USER_INFO(30, R.string.str_team_user_info,
            TeamMemberInformationFragment.class),

    MY_ISSUE_PAGER(31, R.string.str_team_my_issue, MyIssuePagerfragment.class),

    TEAM_PROJECT_MAIN(32, 0, TeamProjectViewPagerFragment.class),

    TEAM_ISSUECATALOG_ISSUE_LIST(33, 0, TeamIssueFragment.class),

    TEAM_ACTIVE(34, R.string.team_actvie, TeamActiveFragment.class),

    TEAM_ISSUE(35, R.string.team_issue, TeamIssueViewPageFragment.class),

    TEAM_DISCUSS(36, R.string.team_discuss, TeamDiscussFragment.class),

    TEAM_DIRAY(37, R.string.team_diary, TeamDiaryFragment.class),

    TEAM_DIRAY_DETAIL(38, R.string.team_diary_detail, TeamDiaryDetailFragment.class),

    TEAM_PROJECT_MEMBER_SELECT(39, 0, TeamProjectMemberSelectFragment.class),

    TEAM_PROJECT(40, R.string.team_project, TeamProjectFragment.class),

    TWEET_TOPIC_LIST(42, R.string.topic_list, TweetFragment.class),

    MY_EVENT(43, R.string.actionbar_title_my_event, EventViewPagerFragment.class),

    MY_QUESTION(44, R.string.question, UserQuestionFragment.class),

    OUTLINE_EVENTS(45, R.string.event_type_outline, SubFragment.class);

    private int title;
    private Class<?> clz;
    private int value;

    private SimpleBackPage(int value, int title, Class<?> clz) {
        this.value = value;
        this.title = title;
        this.clz = clz;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static SimpleBackPage getPageByValue(int val) {
        for (SimpleBackPage p : values()) {
            if (p.getValue() == val)
                return p;
        }
        return null;
    }


}
