package br.com.leumas.daggerApp.di;

import dagger.Module;
import dagger.Provides;

@Module
class StringModule {

    //use a class type file and @Provides to inject a diferent library like Retrofit().builder or a stuff who has a logic to do
    @Provides
    fun providesString(): String {
        return "string provided"
    }


}
