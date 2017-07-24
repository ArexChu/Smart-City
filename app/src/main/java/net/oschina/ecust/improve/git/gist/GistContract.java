package net.oschina.ecust.improve.git.gist;

import net.oschina.ecust.improve.base.BaseListPresenter;
import net.oschina.ecust.improve.base.BaseListView;
import net.oschina.ecust.improve.git.bean.Gist;

/**
 * 代码片段
 * Created by haibin on 2017/5/10.
 */
interface GistContract {
    interface View extends BaseListView<Presenter, Gist> {

    }

    interface Presenter extends BaseListPresenter {

    }
}
