package com.example.vok

import com.vaadin.flow.component.UI
import eu.vaadinonkotlin.vaadin.Session
import java.io.Serializable

class LoginService : Serializable {
    var currentUser: User? = null
        private set
    val isLoggedIn get() = currentUser != null

    fun login(username: String, password: String): Boolean {
        if ((username == "user" && password == "user") || (username == "admin" && password == "admin")) {
            currentUser = User(username)
            UI.getCurrent().page.reload()
            return true
        } else return false
    }

    fun logout() {
        Session.current.close()
        UI.getCurrent().navigate("")
        UI.getCurrent().page.reload()
    }
}

val Session.loginService get() = getOrPut(LoginService::class) { LoginService() }

data class User(val name: String) : Serializable