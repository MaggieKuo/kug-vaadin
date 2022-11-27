package com.example.vok

import com.github.mvysny.karibudsl.v10.*
import com.vaadin.flow.component.login.LoginForm
import com.vaadin.flow.router.BeforeEnterEvent
import com.vaadin.flow.router.BeforeEnterObserver
import com.vaadin.flow.router.Route
import eu.vaadinonkotlin.vaadin.Session

@Route("login")
class LoginView : KComposite(), BeforeEnterObserver {
    private lateinit var form: LoginForm
    val root = ui {
        verticalLayout {
            setSizeFull(); content { align(center, middle) }; isMargin = false; isSpacing = true
            val i18n = loginI18n {
                header.title = "歡迎來到 KOTLIN 世界"
                additionalInformation = "Login in as user/user or admin/admin"
                with(form) {
                    title = "登入"
                    submit = "登入"
                    forgotPassword = "忘記密碼"
                }
                with(errorMessage) {
                    title = "帳號/密碼錯誤"
                    message = "請檢查您的帳號/密碼是否正確"
                }
            }
            form = loginForm(i18n) {
                addLoginListener { e ->
                    if (!Session.loginService.login(e.username, e.password)) {
                        isError = true
                    }
                }
            }
        }
    }

    override fun beforeEnter(event: BeforeEnterEvent?) {
        if (Session.loginService.isLoggedIn)
            event?.rerouteTo("")
    }
}