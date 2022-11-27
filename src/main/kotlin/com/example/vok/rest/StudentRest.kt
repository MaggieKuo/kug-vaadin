package com.example.vok

import com.example.vok.dao.Student
import com.github.vokorm.getOneBy
import io.javalin.Javalin
import io.javalin.http.NotFoundResponse

fun Javalin.studentRest(){
    get("/rest/student/{id}"){ ctx ->
        val id = ctx.pathParam("id").toLong()
        ctx.json(Student.findById(id) ?: throw NotFoundResponse("找不到學生編號 $id") )
    }

    get("/rest/students"){ ctx ->
        ctx.json(Student.findAll())
    }

    get("/rest/student/id/{student_id}"){
        val id = it.pathParam("student_id")
        it.json(Student.getOneBy{ Student::student_id eq id} )
    }
}