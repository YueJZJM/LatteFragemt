package com.yuejianzhong.latte_ec.main.index

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.annotation.MainThread
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.Toast
import com.blankj.utilcode.util.LogUtils
import com.yuejianzhong.latte_core.delegate.bottom.BottomItemDelegate
import com.yuejianzhong.latte_core.net.RestClient
import com.yuejianzhong.latte_core.net.callback.ISuccess
import com.yuejianzhong.latte_core.ui.recycler.BaseDecoration
import com.yuejianzhong.latte_core.ui.recycler.MultipleFields
import com.yuejianzhong.latte_core.ui.refresh.RefreshHandler
import com.yuejianzhong.latte_core.util.callback.CallbackManager
import com.yuejianzhong.latte_core.util.callback.CallbackType
import com.yuejianzhong.latte_ec.main.EcBottomDelegate
import kotlinx.android.synthetic.main.delegate_index.*
import com.yuejianzhong.latte_core.delegate.LatteDelegate
import com.yuejianzhong.latte_core.net.RestCreator
import com.yuejianzhong.latte_ec.main.index.search.SearchDelegate
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlin.math.log10


class IndexDelegate : BottomItemDelegate(), OnFocusChangeListener {


    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (hasFocus) {
            getParentDelegate<LatteDelegate>().start(SearchDelegate())
        }
    }
//    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
//    }

    private var mRefreshHandler: RefreshHandler ?= null


    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
//        initRefreshLayout()
    }

    override fun setLayout(): Any {
        return com.yuejianzhong.latte_ec.R.layout.delegate_index
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
//        mRefreshHandler= RefreshHandler(srl_index)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRefreshLayout()
        initRecyclerView()
        mRefreshHandler= RefreshHandler.create(srl_index,rv_index,IndexDataConverter())

        mRefreshHandler!!.firstPage("http://mock.fulingjie.com/mock-android/data/index_data.json")
        tb_index.background.mutate().alpha = 0

        icon_index_scan.setOnClickListener {
            startScanWithCheck(this.getParentDelegate())
        }

        CallbackManager.getInstance()
                .addCallback(CallbackType.ON_SCAN) {
                    LogUtils.d(it as String)
                }

        et_search_view.onFocusChangeListener = this

//        RestClient.builder()
//                .url("http://mock.fulingjie.com/mock-android/data/index_data.json")
//                .success(ISuccess {response: String? ->
//                    val converter: IndexDataConverter = IndexDataConverter()
//                    converter.mJsonData = response
//                    val list =  converter.conver()
//                    vl image :String = list[1].getField(MultipleFields.IMAGE_URL) as String
//                    Toast.makeText(context,image,Toast.LENGTH_SHORT).show()
//                }).build().get()
        onCallRxGet()
    }

    private fun initRefreshLayout(){
        srl_index.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_red_light
        )
        srl_index.setProgressViewOffset(true,120,300)
//        Log.d("initRefreshLayout",srl_index.toString())
    }

    fun initRecyclerView(){
        val manager = GridLayoutManager(context, 4)
        rv_index.layoutManager = manager
        rv_index.addItemDecoration(
                BaseDecoration.create(
                        ContextCompat.getColor(
                                context!!,com.yuejianzhong.latte_core.R.color.app_background),5))

        var ecBottonDelegate: EcBottomDelegate = getParentDelegate()
        rv_index.addOnItemTouchListener(IndexItemClickListener.create(ecBottonDelegate))
    }

    fun onCallRxGet() {
        val url = "http://mock.fulingjie.com/mock-android/data/index_data.json"
        val params = WeakHashMap<String, Any>()
        val observable = RestCreator.getRxRestService().get(url, params)
        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())      // 主线程更新UI
                .subscribe { object : Observer<String> {
                    override fun onComplete() {

                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onNext(t: String) {
                        Toast.makeText(context,t,Toast.LENGTH_LONG).show()
                        Log.d("yjz.aa",t)
                    }

                    override fun onSubscribe(d: Disposable) {

                    }
                    }
                }
    }
}