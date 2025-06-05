package com.ice.campus.user.model.request.student;

import com.ice.campus.common.core.validation.annotation.EnumCheck;
import com.ice.campus.user.model.enums.StudentDegreeEnum;
import com.ice.campus.user.model.enums.StudentStatusEnum;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 学生信息添加
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/4 11:49
 */
@Data
public class StudentEditRequest implements Serializable {

    /**
     * 学生年级
     * @see StudentDegreeEnum
     */
    @EnumCheck(enumClass = StudentDegreeEnum.class, message = "学生学历枚举不存在！")
    private Integer studentDegree;

    /**
     * 学校 id
     */
    private Long schoolId;

    /**
     * 学生专业
     */
    private Long majorId;

    /**
     * 入学年份
     */
    @Min(value = 1950, message = "入学年份不得早于 1950 年")
    @Max(value = 2099, message = "入学年份不得晚于 2099 年")
    private Integer enrollmentYear;

    /**
     * 毕业年份
     */
    @Min(value = 1950, message = "毕业年份不得早于 1950 年")
    @Max(value = 2099, message = "毕业年份不得晚于 2099 年")
    private Integer graduationYear;

    /**
     * 学生状态:1-在读 2-休学 3-毕业 4-退学
     *
     * @see StudentStatusEnum
     */
    @EnumCheck(enumClass = StudentStatusEnum.class, message = "学生状态枚举不存在！")
    private Integer status;

    @Serial
    private static final long serialVersionUID = -409394698179513412L;

}
