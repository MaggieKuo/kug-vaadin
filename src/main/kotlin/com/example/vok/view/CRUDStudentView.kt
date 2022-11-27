package com.example.vok.view

import com.example.vok.MainLayout
import com.example.vok.dao.Gender
import com.example.vok.dao.Student
import com.github.mvysny.karibudsl.v10.KComposite
import com.github.mvysny.karibudsl.v10.grid
import com.github.mvysny.karibudsl.v10.verticalLayout
import com.github.vokorm.dataloader.dataLoader
import com.vaadin.flow.component.combobox.ComboBox
import com.vaadin.flow.component.crud.BinderCrudEditor
import com.vaadin.flow.component.crud.Crud
import com.vaadin.flow.component.crud.CrudEditor
import com.vaadin.flow.component.crud.CrudVariant
import com.vaadin.flow.component.datepicker.DatePicker
import com.vaadin.flow.component.formlayout.FormLayout
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.textfield.EmailField
import com.vaadin.flow.component.textfield.NumberField
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.binder.Binder
import com.vaadin.flow.router.Route
import eu.vaadinonkotlin.vaadin.vokdb.asDataProvider
import io.javalin.apibuilder.ApiBuilder.crud

@Route("crud-student", layout = MainLayout::class)
class CRUDStudentView: KComposite() {
    private val root = ui{
        verticalLayout {
            setSizeFull()
            val grid = Grid(Student::class.java)
            val crud = Crud(Student::class.java, grid, createCrud())
            with(crud){
                setSizeFull()
                dataProvider = Student.dataLoader.asDataProvider()
                addSaveListener { it.item.save() }
                addDeleteListener { it.item.delete() }
                Crud.addEditColumn(grid)
                grid.removeColumnByKey("id")
                grid.removeColumnByKey("email")
                addThemeVariants(CrudVariant.NO_BORDER)
                grid.columns.forEach {
                    it.setAutoWidth(true)
                }
            }
            add(crud)
        }
    }

    private fun createCrud(): CrudEditor<Student>? {
        val studentId = TextField("學號")
        val name = TextField("姓名")
        val brithday = DatePicker("生日")
        val gender = ComboBox<Gender>("性別").also {
            it.setItems(*Gender.values())
        }
        val email = EmailField("EMAIL").also {
            it.isClearButtonVisible = true
            it.isInvalid = true
        }
        val height = NumberField("身高").also {
            it.step = 0.5
            it.setHasControls(true)
        }
        val weight = NumberField("體重").also {
            it.step = 0.5
            it.setHasControls(true)
        }
        val form = FormLayout(studentId, name, brithday, gender, email, height, weight)
        val binder = Binder(Student::class.java).also {
            it.forField(studentId).asRequired().bind(Student::student_id, Student::student_id.setter)
            it.forField(name).asRequired().bind(Student::name, Student::name.setter)
            it.forField(brithday).asRequired().bind(Student::birthday, Student::birthday.setter)
            it.forField(gender).asRequired().bind(Student::gender, Student::gender.setter)
            it.forField(email).asRequired().bind(Student::email, Student::email.setter)
            it.forField(height).bind(Student::height, Student::height.setter)
            it.forField(weight).bind(Student::weight, Student::weight.setter)
        }
        return BinderCrudEditor(binder, form)
    }
}