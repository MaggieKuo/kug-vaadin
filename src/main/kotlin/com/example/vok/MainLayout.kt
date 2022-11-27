package com.example.vok

import com.example.vok.view.AllStudentsView
import com.example.vok.view.CRUDStudentView
import com.example.vok.view.StudentView
import com.github.mvysny.karibudsl.v10.KComposite
import com.github.mvysny.karibudsl.v10.appLayout
import com.github.mvysny.karibudsl.v10.loginForm
import com.github.mvysny.karibudsl.v10.p
import com.vaadin.flow.component.Component
import com.vaadin.flow.component.Html
import com.vaadin.flow.component.Text
import com.vaadin.flow.component.applayout.DrawerToggle
import com.vaadin.flow.component.html.Image
import com.vaadin.flow.component.html.Label
import com.vaadin.flow.component.icon.Icon
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.tabs.Tab
import com.vaadin.flow.component.tabs.Tabs
import com.vaadin.flow.router.BeforeEnterEvent
import com.vaadin.flow.router.BeforeEnterObserver
import com.vaadin.flow.router.RouterLayout
import com.vaadin.flow.router.RouterLink
import eu.vaadinonkotlin.vaadin.Session
import javax.imageio.stream.ImageInputStream

class MainLayout : KComposite(), RouterLayout, BeforeEnterObserver {
    val tabs = Tabs(
        Tab(VaadinIcon.HOME.create(), RouterLink("Home", WelcomeView::class.java)),
        Tab(VaadinIcon.USERS.create(), RouterLink("Students", AllStudentsView::class.java)),
        Tab(VaadinIcon.USERS.create(), RouterLink("CRUD", CRUDStudentView::class.java)),
    )
    var tabLogin = Tab(VaadinIcon.SIGN_IN.create(), RouterLink("Login", LoginView::class.java))
    var tabLogout = Tab(VaadinIcon.SIGN_OUT.create(), Label().p("Logout").also {
        it.addClickListener {
            Session.loginService.logout()
        }
    })

    val root = ui {
        appLayout {
            addToNavbar(
                DrawerToggle(),
                Image("images/jetbrains_icon.png", "2022 Kotlin 中文开发者大会").also { it.height = "60px" },
                Label("2022 Kotlin 中文开发者大会")
            )
            tabs.orientation = Tabs.Orientation.VERTICAL
            addToDrawer(tabs)
        }
    }

    override fun beforeEnter(event: BeforeEnterEvent?) {
        if (event?.navigationTarget != LoginView::class.java && !Session.loginService.isLoggedIn) {
//            tabs.add(tabLogin)
            event?.rerouteTo(LoginView::class.java)
        }else
            tabs.add(tabLogout)
    }
}