package com.eelly.contract

import com.eelly.core.base.XPresenter
import com.eelly.core.base.XView
import com.eelly.model.TheaterBean

/**
 * @author Vurtne on 21-Nov-17.
 */
interface IMainContract {

    interface IPresenter: XPresenter{
        fun onRefreshMovies()
        fun onLoadMore()
    }

    interface IView : XView<IPresenter> {
        fun showLoading()
        fun hideLoading()
        fun setAdapter(bean: TheaterBean)
        fun addAdapter(bean: TheaterBean)
    }

}