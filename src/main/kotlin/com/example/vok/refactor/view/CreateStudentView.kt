package com.example.vok.refactor.view

import com.example.vok.MainLayout
import com.example.vok.dao.Gender
import com.example.vok.dao.Student
import com.example.vok.refactor.component.StudentEditor
import com.example.vok.refactor.component.studentEditor
import com.example.vok.view.StudentView
import com.github.mvysny.karibudsl.v10.*
import com.github.mvysny.kaributools.navigateTo
import com.vaadin.flow.router.Route
import java.time.LocalDate

@Route("create-student-2", layout = MainLayout::class)
class CreateStudentView : KComposite() {
    private lateinit var editor: StudentEditor
    private val root = ui {
        verticalLayout {
            h1("新增學生資料")
            editor = studentEditor {
                student = Student()
            }
        }
    }
}