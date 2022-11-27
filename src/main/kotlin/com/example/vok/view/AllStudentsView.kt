package com.example.vok.view

import com.example.vok.MainLayout
import com.example.vok.confirmDialog
import com.example.vok.dao.Gender
import com.example.vok.dao.Student
import com.example.vok.refactor.view.CreateStudentView
import com.example.vok.refactor.view.EditStudentView
import com.github.javafaker.Faker
import com.github.mvysny.karibudsl.v10.*
import com.github.mvysny.kaributools.addColumnFor
import com.github.mvysny.kaributools.navigateTo
import com.github.mvysny.kaributools.refresh
import com.github.vokorm.dataloader.dataLoader
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.data.converter.LocalDateToDateConverter
import com.vaadin.flow.data.renderer.NativeButtonRenderer
import com.vaadin.flow.router.AfterNavigationEvent
import com.vaadin.flow.router.AfterNavigationObserver
import com.vaadin.flow.router.Route
import eu.vaadinonkotlin.vaadin.vokdb.setDataLoader
import java.util.*

@Route("students", layout = MainLayout::class)
class AllStudentsView : KComposite(), AfterNavigationObserver {
    private lateinit var grid: Grid<Student>
    private val root = ui {
        verticalLayout {
            setSizeFull()
            h1("學生資料")
            horizontalLayout {
                button("Generate testing data") {
                    onLeftClick {
                        val faker = Faker(Locale.TAIWAN)
                        (1..20).forEach {
                            Student(
                                name = faker.name().fullName(),
                                birthday = LocalDateToDateConverter().convertToPresentation(
                                    faker.date().birthday(20, 21), null
                                ),
                                gender = Gender.values().random(),
                                height = faker.number().randomDouble(1, 155, 190),
                                weight = faker.number().randomDouble(1, 45, 80)
                            ).save()
                        }
                        grid.refresh()
                    }
                }
                routerLink(VaadinIcon.PLUS, "New", CreateStudentView::class)
            }

            grid = grid {
                isExpand = true
                setDataLoader(Student.dataLoader)
                addColumnFor(Student::id).setHeader("序號")
                addColumnFor(Student::student_id).setHeader("學號")
                addColumnFor(Student::name).setHeader("姓名")
                addColumnFor(Student::gender).setHeader("性別")
                addColumnFor(Student::birthday).setHeader("生日")
                addColumnFor(Student::height).setHeader("身高")
                addColumnFor(Student::weight).setHeader("體重")
                addColumn(NativeButtonRenderer("Show", { navigateTo(StudentView::class, it.id) }))
                addColumn(NativeButtonRenderer("Edit", { navigateTo(EditStudentView::class, it.id) }))
                addColumn(NativeButtonRenderer("Delete") {
                    confirmDialog {
                        it.delete()
                        this.refresh()
                    }
                })
            }
            grid.columns.forEach {
                it.setAutoWidth(true)
            }
        }
    }

    override fun afterNavigation(event: AfterNavigationEvent?) {
        grid.refresh()
    }
}