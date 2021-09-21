package com.manish.koo.di.module

import com.manish.koo.helper.AppExecutors
import com.manish.koo.post.PostDao
import com.manish.koo.post.PostRepository
import com.manish.koo.remoteUtils.WebService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providePostRepository(dao: PostDao, webservice: WebService, executor: AppExecutors): PostRepository {
        return PostRepository(dao = dao,  webService = webservice, appExecutors = executor)
    }
}