package com.mindorks.framework.mvvm.tests

import android.app.Application
import android.content.Context
import cucumber.api.CucumberOptions
import cucumber.api.android.CucumberAndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

@CucumberOptions(glue = ["com.mindorks.framework.mvvm.tests"], features = ["features"], tags = ["~@wip"])
class MVVMDemoAppTestRunner: CucumberAndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}
