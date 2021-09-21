package com.manish.koo.di.module

import com.manish.koo.post.PostFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributePostFragment(): PostFragment


}