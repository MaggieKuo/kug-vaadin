package com.example.vok.refactor.component

import com.example.vok.dao.Gender
import com.example.vok.dao.Student
import com.example.vok.view.AllStudentsView
import com.example.vok.view.StudentView
import com.github.mvysny.karibudsl.v10.*
import com.github.mvysny.kaributools.navigateTo
import com.vaadin.flow.component.HasComponents
import com.vaadin.flow.component.icon.VaadinIcon

class StudentEditor : KComposite() {
    private val binder = beanValidationBinder<Student>()
    var student: Student? = null
        set(value) {
            field = value
            if (value != null) binder.readBean(value)
        }

    private val root = ui {
        verticalLayout {
            isMargin = false
            routerLink(VaadinIcon.ARROW_BACKWARD, "Back", AllStudentsView::class)
            textField("姓名 : ") {
                bind(binder).bind(Student::name)
            }
            comboBox<Gender>("性別 : ") {
                setItems(*Gender.values())
                bind(binder).bind(Student::gender)
            }
            datePicker("生日 : ") {
                bind(binder).bind(Student::birthday)
            }
            numberField("身高") {
                bind(binder).bind(Student::height)
                placeholder = "公分"
            }
            numberField("體重") {
                bind(binder).bind(Student::weight)
                placeholder = "公斤"
            }

            button("儲存") {
                onLeftClick {
                    val student = student!!
                    if (binder.validate().isOk && binder.writeBeanIfValid(student)) {
                        student.save()
                        navigateTo(StudentView::class, student.id)
                    }
                }
            }
        }
    }
}

fun HasComponents.studentEditor(block: StudentEditor.() -> Unit = {}) = init(StudentEditor(), block)