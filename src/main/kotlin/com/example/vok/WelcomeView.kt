package com.example.vok

import com.github.mvysny.karibudsl.v10.*
import com.github.mvysny.kaributools.VaadinVersion
import com.vaadin.flow.router.Route

@Route("", layout = MainLayout::class)
class WelcomeView: KComposite() {
    private val root = ui {
        verticalLayout {
            setSizeFull(); content { align(center, middle) }; isMargin = false; isSpacing = true
            h1("使用 Vaadin 搭配 Kotlin 快速开发 Web 应用")
            image("images/kotlin2022.jpg"){
                width = "80%"
            }
            div { html("<strong>Vaadin version: </strong> ${VaadinVersion.get}") }
            div { html("<strong>Kotlin version: </strong> ${KotlinVersion.CURRENT}") }
            div { html("<strong>JVM version: </strong> $jvmVersion") }
        }
    }
}
