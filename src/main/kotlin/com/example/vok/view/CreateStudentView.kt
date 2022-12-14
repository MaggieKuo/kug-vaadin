package com.example.vok.view

import com.example.vok.MainLayout
import com.example.vok.dao.Gender
import com.example.vok.dao.Student
import com.github.mvysny.karibudsl.v10.*
import com.github.mvysny.kaributools.navigateTo
import com.vaadin.flow.router.Route
import java.time.LocalDate

@Route("create-student", layout = MainLayout::class)
class CreateStudentView : KComposite() {
    private val binder = beanValidationBinder<Student>()
    private val root = ui {
        verticalLayout {
            h1("新增學生資料")
            textField("姓名") {
                focus()
                bind(binder).bind(Student::name)
            }
            datePicker("生日") {
                bind(binder).bind(Student::birthday)
            }
            comboBox<Gender>("性別") {
                setItems(*Gender.values())
                bind(binder).bind(Student::gender)
            }
            numberField("身高") {
                bind(binder).bind(Student::height)
                placeholder = "公分"
            }
            numberField("體重") {
                bind(binder).bind(Student::weight)
                placeholder = "公斤"
            }
            button("Save") {
                onLeftClick {
                    val student = Student()
                    if (binder.validate().isOk && binder.writeBeanIfValid(student)) {
                        student.save()
                        navigateTo(StudentView::class, student.id)
                    }
                }
            }
        }
    }
}