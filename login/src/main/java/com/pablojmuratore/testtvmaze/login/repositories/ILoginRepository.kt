package com.pablojmuratore.testtvmaze.login.repositories

import android.content.Context
import kotlinx.coroutines.flow.Flow

interface ILoginRepository {
    fun getLoginPin(context: Context): Flow<String>
    suspend fun setLoginPin(pin: String, context: Context)
}