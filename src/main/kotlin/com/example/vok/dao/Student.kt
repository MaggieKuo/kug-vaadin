package com.example.vok.dao

import com.example.vok.view.CreateStudentView
import com.github.vokorm.KEntity
import com.gitlab.mvysny.jdbiorm.Dao
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.Range
import java.time.LocalDate
import javax.validation.constraints.*

data class Student(
    override var id: Long? = null,
    @field:NotNull
    @field:Length(min = 2, max = 10, message = "姓名不分中英文字，最少2個字，最多10個字")
    var name: String? = null,
    @field:NotNull @field:Past
    var birthday: LocalDate? = null,
    var created: LocalDate? = null,
    @field:Email
    var email: String? = null,
    @field:NotNull
    var gender: Gender? = null,
    @field:NotNull @field:Min(100) @field:Max(200)
    var height: Double? = null,
    @field:NotNull @field:Range(min = 40, max = 150)
    var weight: Double? = null,
    var student_id: String? = null
) : KEntity<Long> {
    override fun save(validate: Boolean) {
        val isNew = (id == null)
        super.save(validate)
        if (isNew) {
            student_id = generateStudentId(id)
            created = LocalDate.now()
            super.save(false)
        }
    }
    companion object : Dao<Student, Long>(Student::class.java)

    fun generateStudentId(id: Long?): String =
        String.format("%s%04d%04d", "KUG", LocalDate.now().year, id)

}

enum class Gender {
    Female,
    Male,
    Custom
}