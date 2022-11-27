package com.example.vok.refactor.view

import com.example.vok.MainLayout
import com.example.vok.dao.Gender
import com.example.vok.dao.Student
import com.example.vok.refactor.component.StudentEditor
import com.example.vok.refactor.component.studentEditor
import com.example.vok.view.AllStudentsView
import com.github.mvysny.karibudsl.v10.*
import com.github.mvysny.kaributools.navigateTo
import com.vaadin.flow.router.BeforeEvent
import com.vaadin.flow.router.HasUrlParameter
import com.vaadin.flow.router.Route

@Route("edit-student-2", layout = MainLayout::class)
class EditStudentView : KComposite(), HasUrlParameter<Long> {
    private lateinit var editor: StudentEditor
    private val root = ui {
        verticalLayout {
            routerLink(null, "返回", AllStudentsView::class)
            h1("學生資料修改")
            editor = studentEditor()
        }
    }
    override fun setParameter(event: BeforeEvent?, studentId: Long?) {
        editor.student = Student.getById(studentId!!)
    }
}