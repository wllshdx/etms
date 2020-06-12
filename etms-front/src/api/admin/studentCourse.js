import * as ajax from "../../common/ajax";

export const get = (id) => ajax.get("/admin/student/course/" + id);

export const create = (entity) => ajax.post("/admin/student/course", entity);

export const deleteItem = (id) =>
  ajax.pureDelete("/admin/student/course/" + id);

export const update = (entity) => ajax.put("/admin/student/course", entity);

export const getPageCount = ( courseName, studentName,className,) =>
  ajax.get("/admin/student/course/page/count", {
    courseName: courseName,
    studentName: studentName,
    className: className,
  });

export const getPage = (index, courseName, studentName,className) =>
  ajax.get("/admin/student/course/page/" + index, {
    courseName: courseName,
    studentName: studentName,
    className: className,
  });

export const pageSize = 20;
