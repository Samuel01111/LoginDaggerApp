package br.com.leumas.daggerApp.di

import br.com.leumas.daggerApp.data.DefaultRepository
import br.com.leumas.daggerApp.data.Repository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

//@Singleton create a single instance of it, store it and give for every one who needs it

@Module
abstract class DataModule {
    //if some one need a (Repository) dagger gonna inject it
    @Singleton
    @Binds
    abstract fun provideLocalDataSource(repository: DefaultRepository): Repository

}
