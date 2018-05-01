package com.wenping.autoloayout.ktolintakeout.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.util.Log
import cn.smssdk.EventHandler
import cn.smssdk.SMSSDK
import com.heima.takeout.utils.SMSUtil
import com.wenping.autoloayout.ktolintakeout.R
import com.wenping.autoloayout.ktolintakeout.dagger2.component.DaggerLoginActivityComponent
import com.wenping.autoloayout.ktolintakeout.dagger2.module.LoginActivityModule
import com.wenping.autoloayout.ktolintakeout.model.User
import com.wenping.autoloayout.ktolintakeout.presenter.LoginActivityPresenter
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * Author WenPing
 * CreateTime 2018/5/1.
 * Description:LoginActivity
 */
class LoginActivity : AppCompatActivity() {

    companion object {
        val TAG = this.javaClass.simpleName
        val TIME_MINUS = -1
        val TIME_IS_OUT=-2
        val MIN = 1000L
    }

    var time = 60
    @Inject
    lateinit var loginActivityPresenter: LoginActivityPresenter

    val handler = @SuppressLint("HandlerLeak")
    object :Handler(){
        override fun handleMessage(msg: Message?) {
            when (msg?.what) {

                TIME_MINUS ->{
                    tv_user_code.text = "剩余时间(${time})秒"
                    time--
                    //如果时间等于0，发送另一条消息，否则继续发送消息
                    if (time > 0) {
                        sendEmptyMessageDelayed(TIME_MINUS, MIN)
                    } else {
                        sendEmptyMessageDelayed(TIME_IS_OUT, MIN)
                    }
                }
                TIME_IS_OUT->{
                    tv_user_code.isEnabled = true
                    tv_user_code.text = "点击重发"
                    time = 60
                }
            }
        }
    }

    val eventHandler = object : EventHandler() {

        override fun afterEvent(event: Int, result: Int, data: Any?) {
            if (data is Throwable) {
                val msg = data.message
                runOnUiThread {
                    msg?.let { toast(it) }
                }
            } else {
                if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //这里是验证成功的回调，可以处理验证成功后 自定义的逻辑；这一：这里不是主线程
                    Log.e("tag","AfterEvent 获取验证码成功")
                }else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    Log.e("tag","AfterEvent 提交验证码成功")
                    val phoneNum = et_user_phone.text.toString().trim()

                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//        loginActivityPresenter = LoginActivityPresenter(this)
//        DaggerHomeFragmentComponent.builder().
// homeFragmentModule(HomeFragmentModule(this)).build().inject(this)

        DaggerLoginActivityComponent.builder()
                .loginActivityModule(LoginActivityModule(this)).build().inject(this)
        initListener()

        //初测监听器
        SMSSDK.registerEventHandler(eventHandler)

    }

    private fun initListener() {
        iv_user_back.setOnClickListener{finish()}

        tv_user_code.setOnClickListener{
            val phoneNumber = et_user_phone.text.toString().trim()
            if (SMSUtil.judgePhoneNums(this, phoneNumber)) {
                SMSSDK.getVerificationCode("86", phoneNumber)
                tv_user_code.isEnabled = false
                handler.sendEmptyMessage(TIME_MINUS)
            } else {
                toast("请输入正确的手机号")
            }
        }
        iv_login.setOnClickListener{
            val phoneNumber = et_user_phone.text.toString().trim()
            val code = et_user_code.text.toString().trim()
//            if (SMSUtil.judgePhoneNums(this, phoneNumber) and code.isNotEmpty()) {
//                SMSSDK.submitVerificationCode("86",phoneNumber,code)
//                loginActivityPresenter.loginByPhone(phoneNumber)
//            }
                loginActivityPresenter.loginByPhone(phoneNumber)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
        SMSSDK.unregisterEventHandler(eventHandler)
    }

    fun onLoginSuccess(user: User?) {
        finish()
        toast("登陆成功")

    }

    fun onLoginFailed() {
        toast("登陆失败")
    }


}