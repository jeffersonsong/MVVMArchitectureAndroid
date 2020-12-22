package com.mindorks.framework.mvvm.tests

import android.app.Application
import android.content.Context
import com.mindorks.framework.mvvm.App
import cucumber.api.CucumberOptions
import cucumber.api.android.CucumberAndroidJUnitRunner

@CucumberOptions(glue = ["com.mindorks.framework.mvvm.tests"], features = ["features"], tags = ["~@wip"])
class MVVMDemoAppTestRunner: CucumberAndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(
            cl, App::class.java.name, context
        )
    }
}
