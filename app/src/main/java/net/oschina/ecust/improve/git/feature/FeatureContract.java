package net.oschina.ecust.improve.git.feature;

import net.oschina.ecust.improve.base.BaseListPresenter;
import net.oschina.ecust.improve.base.BaseListView;
import net.oschina.ecust.improve.git.bean.Project;

/**
 * Created by haibin
 * on 2017/3/9.
 */
interface FeatureContract {

    interface View extends BaseListView<Presenter, Project> {

    }

    interface Presenter extends BaseListPresenter {

    }
}
