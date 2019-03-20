package com.bapp.ui.login

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

/**
 *  @author BuMingYang
 *  @des 谷歌登录
 */
class GoogleLogin(private var context: Activity) {

    private var mGoogleSignInClient: GoogleSignInClient? = null
    private var googleListener: GoogleListener? = null


    private val RC_SIGN_IN = 9001

    init {

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(context, gso)


    }

    //google
    fun signIn() {

        val signInIntent = mGoogleSignInClient?.signInIntent
        context.startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode === RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }

    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            var account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            // Signed in successfully, show authenticated UI.
            googleListener?.onGoogleSuccess("${account?.id}")

        } catch (e: ApiException) {
            Log.e("Login", "signInResult:failed code=" + e.statusCode)
        }
    }

    fun setGoogleListener(googleListener: GoogleListener) {

        this.googleListener = googleListener
    }

    interface GoogleListener {

        /**
         * 登录成功
         * @param id
         */
        fun onGoogleSuccess(id: String)
    }
}