import * as ajax from "../../common/ajax";

export const select = (id) => ajax.post("/student/course/select/" + id, {});

export const getPageCount = (departmentName, courseName, teacherName) =>
  ajax.get("/student/course/select/page/count", {
    departmentName: departmentName,
    courseName: courseName,
    teacherName: teacherName,
  });

export const getPage = (index, departmentName, courseName, teacherName) =>
  ajax.get("/student/course/select/page/" + index, {
    departmentName: departmentName,
    courseName: courseName,
    teacherName: teacherName,
  });

export const pageSize = 20;
