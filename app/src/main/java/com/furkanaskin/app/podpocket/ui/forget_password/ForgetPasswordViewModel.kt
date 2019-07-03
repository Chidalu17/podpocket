package com.furkanaskin.app.podpocket.ui.forget_password

import android.app.Application
import androidx.databinding.ObservableField
import com.furkanaskin.app.podpocket.Podpocket
import com.furkanaskin.app.podpocket.core.BaseViewModel
import com.furkanaskin.app.podpocket.core.Constants
import timber.log.Timber

/**
 * Created by Furkan on 15.04.2019
 */

class ForgetPasswordViewModel(app: Application) : BaseViewModel(app) {

    var userName: ObservableField<String> = ObservableField("")
    var sendMailSuccess: ObservableField<Boolean> = ObservableField(false)
    var sendVerifyMailSucces: ObservableField<Boolean> = ObservableField(false)
    var type: ObservableField<Int> = ObservableField(0)
    var showProgress: ObservableField<Boolean> = ObservableField(false)

    init {
        (app as? Podpocket)?.component?.inject(this)
    }

    fun buttonClick() {
        showProgress.set(true)
        when (type.get()) {
            Constants.LoginActivityType.FORGOT_PASS -> forgetPassword()
            Constants.LoginActivityType.EMAIL_VERIFY -> verifyEmail()
            else -> forgetPassword()
        }
    }

    private fun forgetPassword() {
        if (!userName.get().isNullOrEmpty()) {
            userName.get()?.let {
                mAuth.sendPasswordResetEmail(it).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        sendMailSuccess.set(true)
                        showProgress.set(false)

                    }
                }
            }?.addOnFailureListener {
                Timber.e(it.toString())
                showProgress.set(false)

            }
        }
    }

    private fun verifyEmail() {
        val mUser = mAuth.currentUser
        mUser!!.sendEmailVerification().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                sendVerifyMailSucces.set(true)
                showProgress.set(false)
            } else {
                showProgress.set(false)

            }
        }
    }
}