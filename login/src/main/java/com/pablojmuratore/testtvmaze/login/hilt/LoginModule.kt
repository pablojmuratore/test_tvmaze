package com.pablojmuratore.testtvmaze.login.hilt

import com.pablojmuratore.testtvmaze.login.repositories.ILoginRepository
import com.pablojmuratore.testtvmaze.login.repositories.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LoginModule {
    @Singleton
    @Provides
    fun provideLoginRepository(): ILoginRepository {
        return LoginRepository()
    }
}