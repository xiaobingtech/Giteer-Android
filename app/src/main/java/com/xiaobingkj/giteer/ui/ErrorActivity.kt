package me.hgj.jetpackmvvm.demo.ui.activity

import android.content.ClipData
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import cat.ereza.customactivityoncrash.CustomActivityOnCrash
import com.blankj.utilcode.util.ToastUtils
import io.github.rosemoe.sora.app.R
import io.github.rosemoe.sora.app.databinding.ActivityErrorBinding
import me.hgj.jetpackmvvm.base.activity.BaseVmDbActivity
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.view.clickNoRepeat


/**
 * 作者　: hegaojian
 * 时间　: 2020/3/12
 * 描述　:
 */
class ErrorActivity : BaseVmDbActivity<BaseViewModel, ActivityErrorBinding>() {
    override fun layoutId(): Int = R.layout.activity_error
    override fun createObserver() {

    }

    override fun dismissLoading() {

    }

    override fun initView(savedInstanceState: Bundle?)  {
        val config = CustomActivityOnCrash.getConfigFromIntent(intent)
            mDatabind.errorRestart.clickNoRepeat{
            config?.run {
                CustomActivityOnCrash.restartApplication(this@ErrorActivity, this)
            }
        }

    }

    override fun showLoading(message: String) {

    }
}