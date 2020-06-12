import * as ajax from "../../common/ajax";

export const get = (id) => ajax.get("/admin/openedCourse/" + id);

export const create = (entity) => ajax.post("/admin/openedCourse", entity);

export const deleteItem = (id) => ajax.pureDelete("/admin/openedCourse/" + id);

export const update = (entity) => ajax.put("/admin/openedCourse", entity);

export const getPageCount = (departmentName, teacherName, name) =>
  ajax.get("/admin/openedCourse/page/count", {
    departmentName: departmentName,
    teacherName: teacherName,
    name: name,
  });

export const getPage = (index, departmentName, teacherName, name) =>
  ajax.get("/admin/openedCourse/page/" + index, {
    departmentName: departmentName,
    teacherName: teacherName,
    name: name,
  });

export const listName = () => ajax.pureGet("/admin/openedCourse/names");

export const pageSize = 20;
